package com.NCHUStudent.form;

import com.NCHUStudent.dao.CCourseDao;
import com.NCHUStudent.dao.CourseDao;
import com.NCHUStudent.pojo.CourseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static com.NCHUStudent.dao.CCourseDao.getCCourseMarkByCourseId;

/** 成绩统计和成绩柱状图
 * @author Morty Lee
 * @since 2024-7-19 13:41:13
 */

public class CCourseMarkStatistic extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel3 = null;
	private JComboBox jComboBox_course = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel_course_max = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel_course_min = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel_course_avg = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel_course_good = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel_course_ok = null;
	private JLabel jLabel_course_counts = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;

	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JButton jButton_export = null;
	JFrame jFrame_chart = null;

	private List<CourseModel> course_lists; 
	
	private List<String> course_threeMark;  
	private List<String> course_goodRadio;

	private JPanel graph=null;
	
	
	private void initialize() {
		this.setSize(477, 189);
		this.setTitle("成绩统计");
		this.setModal(true);
		this.setLocationRelativeTo(null);
		
		

		jLabel11 = new JLabel();
		jLabel11.setText("");
		jLabel11.setBounds(new Rectangle(191, 89, 45, 25));
		jLabel10 = new JLabel();
		jLabel10.setText("");
		jLabel10.setBounds(new Rectangle(192, 60, 45, 25));
		jLabel_course_counts = new JLabel();
		jLabel_course_counts.setText("JLabel");
		jLabel_course_counts.setBounds(new Rectangle(224, 23, 55, 25));
		jLabel_course_ok = new JLabel();
		jLabel_course_ok.setText("");
		jLabel_course_ok.setBounds(new Rectangle(148, 92, 40, 21));
		jLabel8 = new JLabel();
		jLabel8.setText("及格率(60分以上):");
		jLabel8.setBounds(new Rectangle(10, 89, 145, 24));
		jLabel_course_good = new JLabel();
		jLabel_course_good.setText("");
		jLabel_course_good.setBounds(new Rectangle(147, 58, 44, 26));
		jLabel7 = new JLabel();
		jLabel7.setText("优秀率(90分以上):");
		jLabel7.setBounds(new Rectangle(10, 57, 145, 25));
		jLabel_course_avg = new JLabel();
		jLabel_course_avg.setText("");
		jLabel_course_avg.setBounds(new Rectangle(66, 89, 107, 25));
		jLabel6 = new JLabel();
		jLabel6.setText("平均分：");
		jLabel6.setBounds(new Rectangle(4, 89, 65, 27));
		jLabel_course_min = new JLabel();
		jLabel_course_min.setText("");
		jLabel_course_min.setBounds(new Rectangle(68, 58, 103, 26));
		jLabel5 = new JLabel();
		jLabel5.setText("最低分：");
		jLabel5.setBounds(new Rectangle(3, 55, 65, 29));
		jLabel_course_max = new JLabel();
		jLabel_course_max.setText("");
		jLabel_course_max.setBounds(new Rectangle(65, 28, 105, 23));
		jLabel4 = new JLabel();
		jLabel4.setText("最高分：");
		jLabel4.setBounds(new Rectangle(3, 29, 65, 18));
		jLabel3 = new JLabel();
		jLabel3.setText("课程选择：");
		jLabel3.setBounds(new Rectangle(12, 25, 75, 21));
		jLabel = new JLabel();
		jLabel.setText("不同班级年级的学生可以选择同一门课程，只能按课程统计。");
		jLabel.setBounds(new Rectangle(8, 8, 450, 22));
		jComboBox_course = new JComboBox();
		jComboBox_course.setBounds(new Rectangle(85, 24, 137, 24));
		
		CourseDao cd = new CourseDao();
		course_lists = cd.getLists(false, -1);
		for(int i=0;i<course_lists.size();i++){
			jComboBox_course.addItem(course_lists.get(i).getCourse_name());
		}	
		
		
		
		jButton_export = new JButton();
		jButton_export.setText("导出统计");
		jButton_export.setBounds(new Rectangle(376, 8, 91, 22));
		
		jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBounds(new Rectangle(293, 32, 177, 120));
		jPanel.add(jLabel4, null);
		jPanel.add(jLabel_course_max, null);
		jPanel.add(jLabel5, null);
		jPanel.add(jLabel_course_min, null);
		jPanel.add(jLabel6, null);
		jPanel.add(jLabel_course_avg, null);
		jPanel.setBorder(BorderFactory.createTitledBorder("高低分统计"));

		jPanel1 = new JPanel();
		jPanel1.setLayout(null);
		jPanel1.setBounds(new Rectangle(-2, 29, 296, 124));
		jPanel1.setBorder(BorderFactory.createTitledBorder("课程选择"));
		jPanel1.add(jLabel7, null);
		jPanel1.add(jLabel_course_good, null);
		jPanel1.add(jLabel10, null);
		jPanel1.add(jLabel11, null);
		jPanel1.add(jLabel_course_ok, null);
		jPanel1.add(jLabel8, null);
		jPanel1.add(jLabel_course_counts, null);
		jPanel1.add(jComboBox_course, null);
		jPanel1.add(jLabel3, null);
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabel, null);
		jContentPane.add(jPanel, null);
		jContentPane.add(jPanel1, null);

		//点击右上角关闭时 两窗口同步关闭
		//private JPanel graph=null; 聚合关系
		// 关闭此A窗口,B窗口同步关闭； 单向关联

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (jFrame_chart!=null){
					jFrame_chart.dispose();
				}
			}
		});

		//jContentPane.add(jButton_export, null);
		this.setContentPane(jContentPane);
	}
	
	

	public void flashData(){
		//如果没有选中任何项，则返回 -1。
		if(jComboBox_course.getSelectedIndex()==-1)
			return;
		
		int index = jComboBox_course.getSelectedIndex();
		int course_id = course_lists.get(index).getCourse_id();
		List<Integer> cCourseMarkByCourseId = getCCourseMarkByCourseId(course_id);
		int [] data=new int[cCourseMarkByCourseId.size()];
		for (int i = 0; i < cCourseMarkByCourseId.size(); i++) {
			data[i] = cCourseMarkByCourseId.get(i);
		}

		if (jFrame_chart!=null){
			jFrame_chart.dispose();
		}
		jFrame_chart = BarChart.creatChart(data);
		jFrame_chart.setLocation(this.getX()+this.getWidth(),this.getY());

		jLabel_course_counts.setText("编号："+Integer.toString(course_id));
		
		CCourseDao ccd = new CCourseDao();
		course_threeMark = ccd.getThreeMark(course_id);
		jLabel_course_max.setText(course_threeMark.get(0));
		jLabel_course_min.setText(course_threeMark.get(1));
		jLabel_course_avg.setText(course_threeMark.get(2));
		
		course_goodRadio = ccd.getGoodRadio(course_id);

		
		int totalCounts = Integer.parseInt(course_goodRadio.get(0));
		if(totalCounts==0)
			return;
		jLabel_course_good.setText(Integer.parseInt(course_goodRadio.get(1))*100/totalCounts+"%");
		jLabel_course_ok.setText(Integer.parseInt(course_goodRadio.get(2))*100/totalCounts+"%");
		

		
	}
	
	public CCourseMarkStatistic() {
		super();
		////frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 默认点击关闭窗口是关闭整个项目
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 修改为关闭当前窗口
		initialize();
		flashData();
		jComboBox_course.addItemListener(new itemListener_course());
	}
	
	
	public class itemListener_course implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				flashData();
				}
			}
		}

	

} 
