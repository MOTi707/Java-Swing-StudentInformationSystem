package com.NCHUStudent.form;

import com.NCHUStudent.dao.CCourseDao;
import com.NCHUStudent.pojo.StuCourseMarkModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/** ��ʾ�ҵĳɼ�
 * @author Morty Lee
 * @since 2024-7-19 17:05:17
 */
public class MyScore extends JDialog {
    private JLabel topic=null;
    private JTextArea jTextArea_ccourse = null;
    private JPanel jContentPane = null;
    public int studentID;

    public MyScore (int studentID) {
        super();
        this.studentID = studentID;
        initialize();
    }

    public MyScore () {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(700, 550);
        this.setTitle("�鿴�ҵĳɼ�");
        this.setModal(true);//���öԻ���Ϊģ̬
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        topic=new JLabel();
        topic.setText("�ҵĳɼ�");
        topic.setFont(new Font("����", Font.BOLD, 35));
        int x= (int) ((this.getWidth()-topic.getPreferredSize().getWidth())/2);

        jTextArea_ccourse = new JTextArea();
        CCourseDao cCourseDao = new CCourseDao();
        List<StuCourseMarkModel> lists = cCourseDao.getCCourseMarkCourseNameByStuId(studentID);
        for(int i=0;i<lists.size();i++){
            String courseName = lists.get(i).getCourseName();
            int mark=lists.get(i).getCourseMark();
            jTextArea_ccourse.append("�γ�����" + courseName +"         "+"�γ̷�����"+mark);
            jTextArea_ccourse.append("\n");
        }
        jTextArea_ccourse.setFont(new Font("����", Font.PLAIN, 30));
        jTextArea_ccourse.setEditable(false);

        topic.setBounds(x, 40, 300, 40);
        jTextArea_ccourse.setBounds(20,100,800,400);

        jContentPane=new JPanel();
        jContentPane.setLayout(null);
        jContentPane.setBackground(new Color(255, 255, 255));
        jContentPane.add(topic,null);
        jContentPane.add(jTextArea_ccourse,topic);
        setContentPane(jContentPane);
    }
}
