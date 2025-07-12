package com.NCHUStudent.form;

import com.NCHUStudent.dao.*;
import com.NCHUStudent.pojo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 李茂田
 * @since 2024-7-20 10:41:47
 */

public class AddCourseByClass extends JDialog {
    private JPanel jContentPane = null;
    private List<GradeModel> grade_lists;
    private List<ClassModel> class_lists;
    private List<CourseModel> course_lists;
    private JLabel jLabel1 = null;
    private JLabel jLabel2 = null;
    private JLabel jLabel3 = null;
    private JComboBox jComboBox_grade = null;
    private JComboBox jComboBox_class = null;
    private JComboBox jComboBox_course = null;
    private JTextArea jTextArea_student = null;
    private JButton jButton = null;


    public AddCourseByClass () {
        super();
        initialize();
    }

    private void initialize () {
        this.setSize(700, 550);
        this.setTitle("批量排课");
        this.setModal(true);//设置对话框为模态
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jLabel1 = new JLabel();
        jLabel1.setBounds(new Rectangle(17, 33, 155, 25));
        jLabel1.setText("年级选择：");
        jLabel2 = new JLabel();
        jLabel2.setBounds(new Rectangle(350, 33, 155, 25));
        jLabel2.setText("班级选择：");
        jLabel3 = new JLabel();
        jLabel3.setBounds(new Rectangle(17, 83, 155, 25));
        jLabel3.setText("选修课程：");
        jLabel1.setFont(new Font("宋体", Font.BOLD, 22));
        jLabel2.setFont(new Font("宋体", Font.BOLD, 22));
        jLabel3.setFont(new Font("宋体", Font.BOLD, 22));

        jComboBox_grade = new JComboBox();
        jComboBox_grade.setBounds(new Rectangle(151, 30, 165, 30));
        jComboBox_class = new JComboBox();
        jComboBox_class.setBounds(new Rectangle(480, 30, 165, 30));
        jComboBox_course = new JComboBox();
        jComboBox_course.setBounds(new Rectangle(151, 83, 165, 30));
        CourseDao cd1 = new CourseDao();
        course_lists = cd1.getLists(false, -1);
        for (int i = 0; i < course_lists.size(); i++) {
            jComboBox_course.addItem(course_lists.get(i).getCourse_name());
        }
        jComboBox_course.setSelectedItem(null);

        //多选框处理 以及初始化
        GradeDao gd = new GradeDao();
        ClassDao cd = new ClassDao();
        grade_lists = gd.getGradeModelList();
        for (int i = 0 ; i < grade_lists.size() ; i++) {
            jComboBox_grade.addItem(grade_lists.get(i).getGrade_name());
        }
        class_lists = cd.getListsByGradeId(grade_lists.get(jComboBox_grade.getSelectedIndex()).getGrade_id());
        for (int i = 0 ; i < class_lists.size() ; i++) {
            jComboBox_class.addItem(class_lists.get(i).getClass_name());
        }
        jComboBox_class.setSelectedItem(null);

        jTextArea_student = new JTextArea();
        jTextArea_student.setFont(new Font("楷体", Font.PLAIN, 22));
        jTextArea_student.setBounds(new Rectangle(20, 120, 630, 310));
        jTextArea_student.setEditable(false);

        //按钮
        jButton = new JButton("确认添加选课");
        jButton.setFont(new Font("楷体", Font.BOLD, 30));
        jButton.setBounds(new Rectangle(200, 450, 300, 50));


        jContentPane = new JPanel();
        jContentPane.setLayout(null);
        jContentPane.add(jLabel1, null);
        jContentPane.add(jLabel2, null);
        jContentPane.add(jLabel3, null);
        jContentPane.add(jComboBox_grade, null);
        jContentPane.add(jComboBox_class, null);
        jContentPane.add(jComboBox_course, null);
        jContentPane.add(jTextArea_student, null);
        jContentPane.add(jButton, null);
        this.setContentPane(jContentPane);

        btnListener btn = new btnListener();
        itemListener itemBtn = new itemListener();
        jComboBox_grade.addItemListener(itemBtn);
        jComboBox_class.addItemListener(itemBtn);
        jButton.addActionListener(btn);

    }


    public class btnListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == jButton) {
                //存在班级id 课程id 加课
                int index0 = jComboBox_class.getSelectedIndex();
                int index1 = jComboBox_course.getSelectedIndex();//课程号
                if(index0==-1||index1==-1){//如果没有选中
                    JOptionPane.showMessageDialog(null, "操作失败，你没有选中！");
                    return;
                }
                else {
                    //正常的情况
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    String dt = sdf.format(new Date());
                    //传入学生id 课程号 时间，给加课 布尔类型 判断是否成功
                    String strClass = (String) jComboBox_class.getSelectedItem();
                    ClassDao classDao = new ClassDao();
                    int classId = classDao.getClassIdByClassName(strClass);
                    List<StuEasyModel> lists = classDao.getStuByClassId(classId);
                    CCourseDao ccd = new CCourseDao();
                    int countNot =0;
                    int count=0;
                    for (int i = 0 ; i < lists.size() ; i++){
                        int stuID=lists.get(i).getId();
                        if (ccd.isCCourseExist(stuID, index1)) {
                            countNot++;
                        }
                        else{
                            //课程不存在，给学生加课
                            ccd.addCCourse(stuID, index1,dt);
                            count++;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "操作成功,已给+" +
                            count+"个学生加课，但有" +
                            countNot +"个学生已存在该门课程，跳过操作");
                }
            }
        }
    }


    public class itemListener implements ItemListener {
        public void itemStateChanged (ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (e.getSource() == jComboBox_grade) {
                    //点击年级多选框，刷新后续多选框
                    jComboBox_class.removeAllItems();
                    ClassDao cd = new ClassDao();
                    class_lists = cd.getListsByGradeId((grade_lists.get(jComboBox_grade.getSelectedIndex()).getGrade_id()));
                    for (int i = 0 ; i < class_lists.size() ; i++) {
                        jComboBox_class.addItem(class_lists.get(i).getClass_name());
                    }
                    jComboBox_class.setSelectedItem(null);
                } else if (e.getSource() == jComboBox_class) {
                    //点击年级多选框，刷新文本框
                    String strClass = (String) jComboBox_class.getSelectedItem();
                    ClassDao classDao = new ClassDao();
                    int classId = classDao.getClassIdByClassName(strClass);
                    List<StuEasyModel> lists = classDao.getStuByClassId(classId);
                    for (int i = 0 ; i < lists.size() ; i++) {
                        if (i == 0) {
                            jTextArea_student.setText("当前学生:");
                        }
                        if (i % 5 == 0) {
                            jTextArea_student.append("\n");
                        }
                        String name = lists.get(i).getName();
                        jTextArea_student.append(name + "     ");
                    }
                }
            }

        }
    }
}
