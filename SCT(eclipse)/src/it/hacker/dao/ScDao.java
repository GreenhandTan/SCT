package it.hacker.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import it.hacker.entity.SC;
import it.hacker.entity.Student;
import it.hacker.utils.PropertiesUtils;

public class ScDao {
	//批量保存选课信息
	public int[] add(List<Integer> cIdArray,Integer stuNo) throws SQLException {
		//开启事务，防止报错，数据库数据丢失
		DataSource dataSource = PropertiesUtils.getDataSource();
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);//数据库自动提交false
		QueryRunner queryRunner = new QueryRunner(dataSource);
		//先删除该学生全部选课参数
		String _sql = "delete from sc where stuNo = ?";
		queryRunner.update(_sql,stuNo);
		Object[][] object = new Object[cIdArray.size()][2];
		//将信息保存为二维数组
		for(int i=0;i<cIdArray.size();i++) {
			object[i][0] = stuNo;
			object[i][1] = cIdArray.get(i);
		}
		String sql = "insert into sc(stuNo,cId) values(?,?)";
		int[] arr = queryRunner.batch(sql, object);
		conn.commit();
		return arr;
	}
	public void delete(Integer scId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from sc where scId = ?";
		queryRunner.update(sql,scId);
	}
	public void update(String[] stuNoArr,String[] scoreArr,Integer cId) throws SQLException {
		DataSource dataSoure = PropertiesUtils.getDataSource();
		Connection connection = dataSoure.getConnection();
		connection.setAutoCommit(false);
		QueryRunner queryRunner = new QueryRunner(dataSoure);
		Object[][] object = new Object[stuNoArr.length][3];
		//将信息保存为二维数组
		for(int i=0;i<stuNoArr.length;i++) {
			object[i][0] = Integer.parseInt(scoreArr[i]==null?"0":scoreArr[i]);
			object[i][1] = cId;
			object[i][2] = stuNoArr[i];
		}
		String sql = "update sc set score=? where cId=? and stuNo = ?";
		queryRunner.batch(sql, object);
		connection.commit();
	}
	public List<SC> list(SC sc) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from sc";
		List<SC> list = queryRunner.query(sql, new BeanListHandler<SC>(SC.class));
		return list;
	}
	
	public List<SC> listByStuNo(String stuNo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from sc where stuNo = ?";
		List<SC> list = queryRunner.query(sql, new BeanListHandler<SC>(SC.class),stuNo);
		return list;
	}
	
	public SC findById(Integer scId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from sc where scId=?";
		SC sc = queryRunner.query(sql, new BeanHandler<SC>(SC.class),scId);
		return sc;
	}
	
	public List<Student> listStudentByStuNo(Integer cId) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select student.*,score from sc,student where sc.stuNo = student.stuNo and cId = ?";
		List<Student> list = queryRunner.query(sql, new BeanListHandler<Student>(Student.class),cId);
		return list;
	}
	
	//管理员区间查询sql语句
	public List<Map<String, Object>> query_range() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select course.cId,cName,ifnull(fail,0) fail,ifnull(medium,0) medium,ifnull(good,0) good,ifnull(best,0) best from course"
				+ " left join(select cId,count(*) fail from sc where score<60 group by cId) A on course.cId = A.cId"
				+ " left join(select cId,count(*) medium from sc where score>=60 and score<=70 group by cId) B on course.cId = B.cId"
				+ " left join(select cId,count(*) good from sc where score>70 and score<=85 group by cId) C on course.cId = C.cId"
				+ " left join(select cId,count(*) best from sc where score>85 and score<=100 group by cId) D on course.cId = D.cId";
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler());
		return list;
	}
	//老师区间查询sql语句
	public List<Map<String, Object>> query_rangeBytName(String tName) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select course.cId,cName,ifnull(fail,0) fail,ifnull(medium,0) medium,ifnull(good,0) good,ifnull(best,0) best from course"
				+ " left join(select cId,count(*) fail from sc where score<60 group by cId) A on course.cId = A.cId"
				+ " left join(select cId,count(*) medium from sc where score>=60 and score<=70 group by cId) B on course.cId = B.cId"
				+ " left join(select cId,count(*) good from sc where score>70 and score<=85 group by cId) C on course.cId = C.cId"
				+ " left join(select cId,count(*) best from sc where score>85 and score<=100 group by cId) D on course.cId = D.cId where tName = ?";
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(),tName);
		return list;
	}
	//学生查询自己成绩
	public List<Map<String, Object>> query_jglBystuNo(String stuNo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from course right join (select cid,stuNo,score from sc where stuNo = ? ) A on course.cId = A.cId";
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(),stuNo);
		return list;
	}
	//管理员及格率查询
	public List<Map<String, Object>> query_jgl() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select A.cId,(select cName from course where A.cId = course.cId) cName,jgnum,allnum,round(jgnum/allnum,2)*100 jgl"
				+ " from(select cId,count(*) jgnum from sc where score>=60 group by cId) A,"
				+ "(select cId,count(*) allnum from sc group by cId) B where A.cId = B.cId";
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler());
		return list;
	}
	//老师及格率查询
	public List<Map<String, Object>> query_jglBytName(String tName) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from course,(select A.cId,(select cName from course where A.cId = course.cId) cName,jgnum,allnum,round(jgnum/allnum,2)*100 jgl"
				+ " from(select cId,count(*) jgnum from sc where score>=60 group by cId) A,"
				+ "(select cId,count(*) allnum from sc group by cId) B where A.cId = B.cId) C where course.cId = C.cId and tName = ?";
		List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(),tName);
		return list;
	}
}
