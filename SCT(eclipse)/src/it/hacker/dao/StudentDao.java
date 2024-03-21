package it.hacker.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import it.hacker.entity.Student;
import it.hacker.utils.PageInfo;
import it.hacker.utils.PropertiesUtils;

public class StudentDao {
	public void add(Student student) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into student(stuName,stuNo,stuPwd) values(?,?,?)";
		queryRunner.update(sql,student.getStuName(),student.getStuNo(),student.getStuPwd());
	}
	public void delete(Integer stuNo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from student where stuNo = ?";
		queryRunner.update(sql,stuNo);
	}
	public void update(Student student) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update student set stuName=?,stuPwd=? where stuNo=?";
		queryRunner.update(sql,student.getStuName(),student.getStuPwd(),student.getStuNo());
	}
	//分页改造
	public PageInfo<Student> list(Student student,PageInfo<Student> pageInfo) throws SQLException {
		int Temp1 = (pageInfo.getPageNo()-1)*(pageInfo.getPageSize());
		int Temp2 = pageInfo.getPageSize();
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = null;
		if(student != null) {
			String _sql = "";//条件拼接
			if(StringUtils.isNoneBlank(student.getStuName())) {
				_sql += " and stuName like \"%"+student.getStuName()+"%\"";
			}
			if(StringUtils.isNoneBlank(student.getStuNo())) {
				_sql += " and stuNo like \"%"+student.getStuNo()+"%\"";
			}
			sql = "select * from student where 1=1 "+_sql+" limit ?,?";//带条件查询数据库
		}else {
			sql = "select * from student limit ?,?";//分页查询数据库
		}
		List<Student> list = queryRunner.query(sql, new BeanListHandler<Student>(Student.class),Temp1,Temp2);
		pageInfo.setList(list);//学生参数
		pageInfo.setTotalCount(this.count(student));//页数
		return pageInfo;
	}
	public Long count(Student student) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		
		//控制组合查询分页
		String sql = null;
		if(student != null) {
			String _sql = "";//条件拼接
			if(StringUtils.isNoneBlank(student.getStuName())) {
				_sql += " and stuName like \"%"+student.getStuName()+"%\"";
			}
			if(StringUtils.isNoneBlank(student.getStuNo())) {
				_sql += " and stuNo like \"%"+student.getStuNo()+"%\"";
			}
			sql = "select count(*) from student where 1=1 "+_sql;//带条件查询数据库
		}else {
			sql = "select count(*) from student";//分页查询数据库
		}
		
		Long count = (Long)queryRunner.query(sql, new ScalarHandler());
		return count;
	}
	public Student findById(Integer stuNo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from student where stuNo=?";
		Student student = queryRunner.query(sql, new BeanHandler<Student>(Student.class),stuNo);
		return student;
	}
	public Student login(String stuNo,String stuPwd) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from student where stuNo=? and stuPwd=?";
		Student student = queryRunner.query(sql, new BeanHandler<Student>(Student.class),stuNo,stuPwd);
		return student;
	}
}
