package com.NCHUStudent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.NCHUStudent.pojo.ClassModel;
import com.NCHUStudent.util.JDBCUtil;
import com.NCHUStudent.pojo.StuEasyModel;



public class ClassDao {

	/**
	 * @根据class_id 查找本表所有信息
	 * @param class_id
	 * @return 数据库中某一行信息
	 */
	
	public ClassModel getListByClassId(int class_id){
		String sql="select * from c_class where class_id="+class_id;
		ClassModel cm = new ClassModel();
		ResultSet rs = JDBCUtil.query(sql);
		
		try {
			if(rs.next()){
				cm.setClass_id(rs.getInt("class_id"));    
				cm.setClass_name(rs.getString("class_name"));
				cm.setClass_maxnum(rs.getInt("class_maxnum"));
				cm.setGrade_id(rs.getInt("grade_id"));
			}else
				cm = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
		
	}
	
	/**
	 * @获取本表所有记录
	 * @return 多行数据
	 */
	public List<ClassModel> getLists(){
		String sql = "select * from c_class";
		ResultSet rs = JDBCUtil.query(sql);
		List<ClassModel> lists = new ArrayList<ClassModel>();

		try {
			while(rs.next()){
				ClassModel cm = new ClassModel();
				cm.setClass_id(rs.getInt("class_id"));    
				cm.setClass_name(rs.getString("class_name"));
				cm.setClass_maxnum(rs.getInt("class_maxnum"));
				cm.setGrade_id(rs.getInt("grade_id"));
				lists.add(cm);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lists;
	}
	
	/**
	 * @ 根据grade_id 查找本表所有记录
	 * @param grade_id
	 * @return 多行记录
	 */
	
	public List<ClassModel> getListsByGradeId(int grade_id){
		String sql = "select * from c_class where grade_id="+grade_id;
		ResultSet rs = JDBCUtil.query(sql);
		List<ClassModel> lists = new ArrayList<ClassModel>();

		try {
			while(rs.next()){
				ClassModel cm = new ClassModel();
				cm.setClass_id(rs.getInt("class_id"));    
				cm.setClass_name(rs.getString("class_name"));
				cm.setClass_maxnum(rs.getInt("class_maxnum"));
				cm.setGrade_id(rs.getInt("grade_id"));
				lists.add(cm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lists;
	}
	
	/**
	 * @根据class_id 查找对于的class_name
	 * @param class_id
	 * @return 班级名称
	 */
	public String getClassNameByClassId(int class_id){
		String class_name ="";
		
		String sql = "select class_name from c_class where class_id="+class_id;
		ResultSet rs = JDBCUtil.query(sql);
		try {
			if(rs.next()){
				class_name=rs.getString("class_name");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return class_name;
		
	}
	
	/**
	 * @根据学生id，查询学生所在班级id
	 * @param stu_id
	 * @return
	 */
	public List<String> getListByStuId(int stu_id){
		List<String> list = new ArrayList<String>();
		String sql = "select * from c_student where stu_id ="+stu_id;
		ResultSet rs = JDBCUtil.query(sql);
		try {
			if(rs.next()){
				list.add(Integer.toString(rs.getInt("class_id")));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean addClass(String class_name,Integer grade_id,Integer max_num){
		String sql = "insert into c_class(class_name,class_maxnum,grade_id) values('"+class_name+"',"+max_num+","+grade_id+")";
		return JDBCUtil.update(sql)==true?true:false;
	}
	
	public boolean modifyClass(Integer class_id,String class_name,Integer grade_id,Integer max_num){
		String sql = "update c_class set class_name='"+class_name+"',class_maxnum = "+max_num+",grade_id = "+grade_id+" where class_id = "+class_id;
		return JDBCUtil.update(sql)==true?true:false;
	}
	public boolean deleteListByClassId(int class_id){
		String sql ="delete from c_class where class_id="+class_id;
		return (JDBCUtil.update(sql))?true:false;
	}

	public List<StuEasyModel> getStuByClassId (int class_id){
		List<StuEasyModel> list = new ArrayList<>();
		String sql = "select * from c_student where class_id="+class_id;
		ResultSet rs = JDBCUtil.query(sql);
		try{
			while(rs.next()){
				StuEasyModel sm = new StuEasyModel();
				sm.setId(rs.getInt("stu_id"));
				sm.setName(rs.getString("stu_name"));
				list.add(sm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}


	public int getClassIdByClassName(String class_name){
		String sql = "select class_id from c_class where class_name='"+class_name+"'";
		ResultSet rs = JDBCUtil.query(sql);
		try{
			if(rs.next()){
				return rs.getInt("class_id");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


}
