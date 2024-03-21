package it.hacker.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import it.hacker.entity.Course;
import it.hacker.entity.Teacher;
import it.hacker.utils.PageInfo;
import it.hacker.utils.PropertiesUtils;

public class CourseDao {
	public void add(Course course) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into Course(cName,tName) values(?,?)";
		queryRunner.update(sql,course.getcName(),course.gettName());
	}
	public void delete(Integer cId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from Course where cId = ?";
		queryRunner.update(sql,cId);
	}
	public void update(Course course) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update Course set cName=?,tName=? where cId=?";
		queryRunner.update(sql,course.getcName(),course.gettName(),course.getcId());
	}
	public PageInfo<Course> list(Course course,PageInfo<Course> pageInfo) throws SQLException {
		int Temp1 = (pageInfo.getPageNo()-1)*(pageInfo.getPageSize());
		int Temp2 = pageInfo.getPageSize();
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = null;
		if(course != null) {
			String _sql = "";//条件拼接
			if(StringUtils.isNoneBlank(course.getcName())) {
				_sql += " and cName like \"%"+course.getcName()+"%\"";
			}
			if(StringUtils.isNoneBlank(course.gettName())) {
				_sql += " and tName like \"%"+course.gettName()+"%\"";
			}
			sql = "select * from course where 1=1 "+_sql+" limit ?,?";//带条件查询数据库
		}else {
			sql = "select * from course limit ?,?";//分页查询数据库
		}
		List<Course> list = queryRunner.query(sql, new BeanListHandler<Course>(Course.class),Temp1,Temp2);
		pageInfo.setList(list);//课程参数
		pageInfo.setTotalCount(this.count(course));//页数
		return pageInfo;
	}
	
	//查询总数
	public Long count(Course course) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = null;
		if(course != null) {
			String _sql = "";//条件拼接
			if(StringUtils.isNoneBlank(course.getcName())) {
				_sql += " and cName like \"%"+course.getcName()+"%\"";
			}
			if(StringUtils.isNoneBlank(course.gettName())) {
				_sql += " and tName like \"%"+course.gettName()+"%\"";
			}
			sql = "select count(*) from course where 1=1 "+_sql;//带条件查询数据库
		}else {
			sql = "select count(*) from course";//分页查询数据库
		}
		Long count = (Long)queryRunner.query(sql, new ScalarHandler());
		return count;
	}
	
	public Course findById(Integer cId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from course where cId=?";
		Course course = queryRunner.query(sql, new BeanHandler<Course>(Course.class),cId);
		return course;
	}
}
