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
 * @author ��ï��
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
        this.setTitle("�����ſ�");
        this.setModal(true);//���öԻ���Ϊģ̬
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jLabel1 = new JLabel();
        jLabel1.setBounds(new Rectangle(17, 33, 155, 25));
        jLabel1.setText("�꼶ѡ��");
        jLabel2 = new JLabel();
        jLabel2.setBounds(new Rectangle(350, 33, 155, 25));
        jLabel2.setText("�༶ѡ��");
        jLabel3 = new JLabel();
        jLabel3.setBounds(new Rectangle(17, 83, 155, 25));
        jLabel3.setText("ѡ�޿γ̣�");
        jLabel1.setFont(new Font("����", Font.BOLD, 22));
        jLabel2.setFont(new Font("����", Font.BOLD, 22));
        jLabel3.setFont(new Font("����", Font.BOLD, 22));

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

        //��ѡ���� �Լ���ʼ��
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
        jTextArea_student.setFont(new Font("����", Font.PLAIN, 22));
        jTextArea_student.setBounds(new Rectangle(20, 120, 630, 310));
        jTextArea_student.setEditable(false);

        //��ť
        jButton = new JButton("ȷ�����ѡ��");
        jButton.setFont(new Font("����", Font.BOLD, 30));
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
                //���ڰ༶id �γ�id �ӿ�
                int index0 = jComboBox_class.getSelectedIndex();
                int index1 = jComboBox_course.getSelectedIndex();//�γ̺�
                if(index0==-1||index1==-1){//���û��ѡ��
                    JOptionPane.showMessageDialog(null, "����ʧ�ܣ���û��ѡ�У�");
                    return;
                }
                else {
                    //���������
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    String dt = sdf.format(new Date());
                    //����ѧ��id �γ̺� ʱ�䣬���ӿ� �������� �ж��Ƿ�ɹ�
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
                            //�γ̲����ڣ���ѧ���ӿ�
                            ccd.addCCourse(stuID, index1,dt);
                            count++;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "�����ɹ�,�Ѹ�+" +
                            count+"��ѧ���ӿΣ�����" +
                            countNot +"��ѧ���Ѵ��ڸ��ſγ̣���������");
                }
            }
        }
    }


    public class itemListener implements ItemListener {
        public void itemStateChanged (ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (e.getSource() == jComboBox_grade) {
                    //����꼶��ѡ��ˢ�º�����ѡ��
                    jComboBox_class.removeAllItems();
                    ClassDao cd = new ClassDao();
                    class_lists = cd.getListsByGradeId((grade_lists.get(jComboBox_grade.getSelectedIndex()).getGrade_id()));
                    for (int i = 0 ; i < class_lists.size() ; i++) {
                        jComboBox_class.addItem(class_lists.get(i).getClass_name());
                    }
                    jComboBox_class.setSelectedItem(null);
                } else if (e.getSource() == jComboBox_class) {
                    //����꼶��ѡ��ˢ���ı���
                    String strClass = (String) jComboBox_class.getSelectedItem();
                    ClassDao classDao = new ClassDao();
                    int classId = classDao.getClassIdByClassName(strClass);
                    List<StuEasyModel> lists = classDao.getStuByClassId(classId);
                    for (int i = 0 ; i < lists.size() ; i++) {
                        if (i == 0) {
                            jTextArea_student.setText("��ǰѧ��:");
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
