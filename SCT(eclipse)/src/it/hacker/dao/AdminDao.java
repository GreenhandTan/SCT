package it.hacker.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import it.hacker.entity.Admin;
import it.hacker.utils.PropertiesUtils;

public class AdminDao {
	public void add(Admin admin) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into admin(userName,pwd,name) values(?,?,?)";
		queryRunner.update(sql,admin.getUserName(),admin.getPwd(),admin.getName());
	}
	public void delete(Integer id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from admin where id = ?";
		queryRunner.update(sql,id);
	}
	public void update(Admin admin) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update admin set userName=?,pwd=?,name=?,where id=?";
		queryRunner.update(sql,admin.getUserName(),admin.getPwd(),admin.getName(),admin.getId());
	}
	public List<Admin> list(Admin admin) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from admin";
		List<Admin> list = queryRunner.query(sql, new BeanListHandler<Admin>(Admin.class));
		return list;
	}
	public Admin findById(Integer id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from admin where id =?";
		Admin admin = queryRunner.query(sql, new BeanHandler<Admin>(Admin.class),id);
		return admin;
	}
	public Admin login(String userName,String password) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from admin where userName = ? and pwd = ?";
		Admin entity = queryRunner.query(sql, new BeanHandler<Admin>(Admin.class),userName,password);
		return entity;
	}
}
