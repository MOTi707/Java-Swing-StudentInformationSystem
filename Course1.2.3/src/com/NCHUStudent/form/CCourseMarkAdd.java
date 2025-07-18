package com.NCHUStudent.form;

import com.NCHUStudent.dao.CCourseDao;
import com.NCHUStudent.dao.ClassDao;
import com.NCHUStudent.dao.GradeDao;
import com.NCHUStudent.dao.StudentDao;
import com.NCHUStudent.pojo.CCourseModel;
import com.NCHUStudent.pojo.ClassModel;
import com.NCHUStudent.pojo.GradeModel;
import com.NCHUStudent.pojo.StudentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CCourseMarkAdd extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JButton jButton_ok = null;
	private JComboBox jComboBox_course = null;
	private JLabel jLabel3 = null;
	private JComboBox jComboBox_stu = null;
	
	private List<GradeModel> grade_lists;
	private List<ClassModel> class_lists;
	private List<StudentModel> student_lists;
	private List<CCourseModel> ccourse_lists;
	
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JComboBox jComboBox_grade = null;
	private JComboBox jComboBox_class = null;
	private JCheckBox jCheckBox_stu_filter = null;
	private JTextField jTextField_course_mark = null;
	
	

	public CCourseMarkAdd() {
		super();
		initialize();
		flashCourseData();

		jButton_ok.addActionListener(new btnListener());
		jComboBox_grade.addItemListener(new itemListener());
		jComboBox_class.addItemListener(new itemListener());
		jComboBox_stu.addItemListener(new itemListener());
		jCheckBox_stu_filter.addItemListener(new checkListener());
		//jComboBox_course.addItemListener(new itemListener());
		
	}

	private void initialize() {

		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(279, 33, 75, 18));
		jLabel2.setText("班级选择：");
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(17, 32, 75, 18));
		jLabel1.setText("年级选择：");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setSize(600, 240);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setTitle("已选课程成绩录入");
		jLabel3 = new JLabel();
		jLabel3.setBounds(new Rectangle(19, 150, 400, 26));
		jLabel3.setText("注：“开启过滤”只显示学生成绩还没被录入的课程。");

		jLabel5 = new JLabel();
		jLabel5.setBounds(new Rectangle(202, 73, 75, 18));
		jLabel5.setText("已修课程：");
		jLabel4 = new JLabel();
		jLabel4.setBounds(new Rectangle(19, 70, 75, 18));
		jLabel4.setText("选课学生：");
		
		jTextField_course_mark = new JTextField();
		jTextField_course_mark.setBounds(new Rectangle(350, 105, 53, 26));


		jButton_ok = new JButton();
		jButton_ok.setBounds(new Rectangle(406, 105, 92, 26));
		jButton_ok.setText("确定录入");

		
		
		jCheckBox_stu_filter = new JCheckBox();
		jCheckBox_stu_filter.setBounds(new Rectangle(426, 72, 90, 23));
		jCheckBox_stu_filter.setText("开启过滤");

		jComboBox_grade = new JComboBox();
		jComboBox_grade.setBounds(new Rectangle(101, 23, 152, 31));
		GradeDao gd = new GradeDao();
		grade_lists = gd.getGradeModelList();
		for (int i = 0; i < grade_lists.size(); i++) {
			jComboBox_grade.addItem(grade_lists.get(i).getGrade_name());
		}

		//根据第一个框的选择，决定第二个框的选项
		jComboBox_class = new JComboBox();
		jComboBox_class.setBounds(new Rectangle(347, 21, 151, 28));
		ClassDao cd = new ClassDao();
		class_lists = cd.getListsByGradeId(grade_lists.get(jComboBox_grade.getSelectedIndex()).getGrade_id());
		for (int i = 0; i < class_lists.size(); i++) {
			jComboBox_class.addItem(class_lists.get(i).getClass_name());
		}

		jComboBox_stu = new JComboBox();
		StudentDao sd = new StudentDao();
		student_lists = sd.getLists(false, -1);
		for (int i = 0; i < student_lists.size(); i++) {
			jComboBox_stu.addItem(student_lists.get(i).getStu_name());
		}

		jComboBox_course = new JComboBox();
		jComboBox_course.setBounds(new Rectangle(271, 62, 150, 29));


		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabel4, null);
		jContentPane.add(jLabel5, null);
		jContentPane.add(jButton_ok, null);
		jContentPane.add(jComboBox_course, null);
		jContentPane.add(jLabel3, null);
		jContentPane.add(jComboBox_stu, null);
		jContentPane.add(jLabel1, null);
		jContentPane.add(jLabel2, null);
		jContentPane.add(jComboBox_grade, null);
		jContentPane.add(jComboBox_class, null);
		jContentPane.add(jCheckBox_stu_filter, null);
		jContentPane.add(jTextField_course_mark, null);
		jComboBox_stu.setBounds(new Rectangle(102, 63, 93, 30));
		this.setContentPane(jContentPane);
		
		

	}
	
	/**
	 * @刷新学生，课程下拉框的数据以及分数窗口
	 */
	public void flashCourseData(){
		
		if(jComboBox_stu.getSelectedIndex()==-1)
			return;
		jComboBox_course.removeAllItems();
		CCourseDao cd1 = new CCourseDao();
		int stu_id = student_lists.get(jComboBox_stu.getSelectedIndex()).getStu_id();
		ccourse_lists = cd1.getListsByStuId(stu_id, false);
		for (int i = 0; i < ccourse_lists.size(); i++) {
			jComboBox_course.addItem(ccourse_lists.get(i).getCourse_name());
		}
		
		int course_index = jComboBox_course.getSelectedIndex();
		//int course_id = ccourse_lists.get(course_index).getCourse_id();
		if(course_index != -1) {
			jTextField_course_mark.setText(Integer.toString(ccourse_lists.get(course_index).getCcourse_mark()));
		}
		
	}

	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton_ok) {
				int index0 = jComboBox_stu.getSelectedIndex();
				int index1 = jComboBox_course.getSelectedIndex();
				if(index0==-1||index1==-1)//如果没有选中，如果学号或者课程号获取不到
				{JOptionPane.showMessageDialog(null, "操作失败，请重新选择！");
					return;
				}
				int stu_id =student_lists.get(index0).getStu_id();
				int course_id = ccourse_lists.get(index1).getCourse_id();

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dt = sdf.format(new Date());

				CCourseDao ccd = new CCourseDao();
				int mark = -1;
				mark=Integer.parseInt(jTextField_course_mark.getText());
				if (mark>=0){
					ccd.modifyCCourseAndMark(stu_id, course_id,mark);
					JOptionPane.showMessageDialog(null, "操作成功");
				}
				else {
					JOptionPane.showMessageDialog(null, "操作失败,成绩不能为负数");
				}
			}
		}
	}

	/**
	 * @author 李茂田
	 */


	public class itemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			//分级下拉框 刷新的实现
			// 比如 地区选择
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if(e.getSource()==jComboBox_grade){
					//改变下一个 下拉框
					jComboBox_class.removeAllItems();
					ClassDao cd = new ClassDao();
					class_lists = cd.getListsByGradeId(grade_lists.get(jComboBox_grade.getSelectedIndex()).getGrade_id());
					for (int i = 0; i < class_lists.size(); i++) {
						jComboBox_class.addItem(class_lists.get(i).getClass_name());
					}
				}else if(e.getSource()==jComboBox_class){
					//改变下一个 下拉框
					jComboBox_stu.removeAllItems();
					StudentDao cd = new StudentDao();
					student_lists = cd.getListsByClassId(class_lists.get(jComboBox_class.getSelectedIndex()).getClass_id());
					for (int i = 0; i < student_lists.size(); i++) {
						jComboBox_stu.addItem(student_lists.get(i).getStu_name());
					}
					
				}else if(e.getSource()==jComboBox_stu){
					int stu_index = jComboBox_stu.getSelectedIndex();
					int stu_id = student_lists.get(stu_index).getStu_id();
					flashCourseData();
				}
				

			}

		}	
	}
	/**
	 * @复选框监听
	 *
	 */
	public class checkListener implements ItemListener{
		public void itemStateChanged(ItemEvent e){
			if(e.getSource()==jCheckBox_stu_filter){
				if(e.getStateChange()==ItemEvent.SELECTED){
					if(jComboBox_stu.getSelectedIndex()!=-1&&jComboBox_course.getSelectedIndex()!=-1) {
						int stu_id = student_lists.get(jComboBox_stu.getSelectedIndex()).getStu_id();
						
						CCourseDao ccd = new CCourseDao();
						ccourse_lists = ccd.getListsByStuId(stu_id, true);
						jComboBox_course.removeAllItems();	
						
						for(int i =0;i<ccourse_lists.size();i++){
							jComboBox_course.addItem(ccourse_lists.get(i).getCourse_name());		
						}
						int course_index = jComboBox_course.getSelectedIndex();
						if(course_index != -1)
						jTextField_course_mark.setText(Integer.toString(ccourse_lists.get(course_index).getCcourse_mark()));
					}else
						return;
				}else if(e.getStateChange()==ItemEvent.DESELECTED){//取消
					int stu_id = student_lists.get(jComboBox_stu.getSelectedIndex()).getStu_id();
					
					CCourseDao ccd = new CCourseDao();
					ccourse_lists = ccd.getListsByStuId(stu_id, false);
					jComboBox_course.removeAllItems();	
					
					for(int i =0;i<ccourse_lists.size();i++){
						jComboBox_course.addItem(ccourse_lists.get(i).getCourse_name());		
					}
					int course_index = jComboBox_course.getSelectedIndex();
					jTextField_course_mark.setText(Integer.toString(ccourse_lists.get(course_index).getCcourse_mark()));
				}
			}
		}
	}
} 