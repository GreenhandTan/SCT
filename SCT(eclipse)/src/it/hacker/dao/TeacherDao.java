package it.hacker.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import it.hacker.entity.Teacher;
import it.hacker.utils.PageInfo;
import it.hacker.utils.PropertiesUtils;

public class TeacherDao {
	public void add(Teacher teacher) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into teacher(tName,userName,pwd) values(?,?,?)";
		queryRunner.update(sql,teacher.gettName(),teacher.getUserName(),teacher.getPwd());
	}
	public void delete(String userName) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from teacher where userName = ?";
		queryRunner.update(sql,userName);
	}
	public void update(Teacher teacher) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update teacher set tName=?,pwd=? where userName=?";
		queryRunner.update(sql,teacher.gettName(),teacher.getPwd(),teacher.getUserName());
	}
	public PageInfo<Teacher> list(Teacher teacher,PageInfo<Teacher> pageInfo) throws SQLException {
		int Temp1 = (pageInfo.getPageNo()-1)*(pageInfo.getPageSize());
		int Temp2 = pageInfo.getPageSize();
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = null;
		if(teacher != null) {
			String _sql = "";//条件拼接
			if(StringUtils.isNoneBlank(teacher.gettName())) {
				_sql += " and tName like \"%"+teacher.gettName()+"%\"";
			}
			if(StringUtils.isNoneBlank(teacher.getUserName())) {
				_sql += " and userName like \"%"+teacher.getUserName()+"%\"";
			}
			sql = "select * from teacher where 1=1 "+_sql+" limit ?,?";//带条件查询数据库
		}else {
			sql = "select * from teacher limit ?,?";//分页查询数据库
		}
		List<Teacher> list = queryRunner.query(sql, new BeanListHandler<Teacher>(Teacher.class),Temp1,Temp2);
		pageInfo.setList(list);//老师参数
		pageInfo.setTotalCount(this.count(teacher));//页数
		return pageInfo;
	}
	//查询总数
	public Long count(Teacher teacher) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = null;
		if(teacher != null) {
			String _sql = "";//条件拼接
			if(StringUtils.isNoneBlank(teacher.gettName())) {
				_sql += " and tName like \"%"+teacher.gettName()+"%\"";
			}
			if(StringUtils.isNoneBlank(teacher.getUserName())) {
				_sql += " and userName like \"%"+teacher.getUserName()+"%\"";
			}
			sql = "select count(*) from teacher where 1=1 "+_sql;//带条件查询数据库
		}else {
			sql = "select count(*) from teacher";//分页查询数据库
		}
		Long count = (Long)queryRunner.query(sql, new ScalarHandler());
		return count;
	}
	
	public Teacher findById(String userName) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from teacher where userName = ?";
		Teacher teacher = queryRunner.query(sql, new BeanHandler<Teacher>(Teacher.class),userName);
		return teacher;
	}
	public Teacher login(String uesrName,String pwd) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from teacher where userName =? and pwd = ?";
		Teacher teacher = queryRunner.query(sql, new BeanHandler<Teacher>(Teacher.class),uesrName,pwd);
		return teacher;
	}
}
