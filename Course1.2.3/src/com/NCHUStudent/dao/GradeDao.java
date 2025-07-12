package com.NCHUStudent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.NCHUStudent.pojo.GradeModel;
import com.NCHUStudent.util.JDBCUtil;

public class GradeDao {
	
	/**
	 * @根据id值，返回数据库实体
	 * @param grade_id
	 * @return
	 */
	public GradeModel getGradeModel(int grade_id){
		String sql="select * from c_grade where grade_id="+grade_id;
		GradeModel gm = new GradeModel();
		ResultSet rs = JDBCUtil.query(sql);
		
		try {
			if(rs.next()){
				gm.setGrade_id(rs.getInt("grade_id"));
				gm.setGrade_name(rs.getString("grade_name"));
			}else{
				rs.close();
				gm = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gm;
		
	}
	
	public List<GradeModel> getGradeModelList(){
		String sql = "select * from c_grade";
		
		
		ResultSet rs = JDBCUtil.query(sql);
		List<GradeModel> lists = new ArrayList<GradeModel>();

		try {
			while(rs.next()){
				GradeModel gm = new GradeModel();
				gm.setGrade_id(rs.getInt("grade_id"));
				gm.setGrade_name(rs.getString("grade_name"));
				lists.add(gm);
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lists;
	}
	
	public boolean addGrade(String grade_name){
		String sql = "insert into c_grade(grade_name) values('"+grade_name+"')";
		return JDBCUtil.update(sql)==true?true:false;
	}
	
	public boolean modifyGrade(Integer grade_id,String grade_name){
		String sql = "update c_grade set grade_name='"+grade_name+"' where grade_id = "+grade_id;
		return JDBCUtil.update(sql)==true?true:false;
	}
	public boolean deleteListByGradeId(int grade_id){
		String sql ="delete from c_grade where grade_id="+grade_id;
		return (JDBCUtil.update(sql))?true:false;
	}
	

}
