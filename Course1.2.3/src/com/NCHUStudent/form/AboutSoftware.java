package com.NCHUStudent.form;

import javax.swing.*;
import java.awt.*;


/**
 * @author Morton Lee
 * @since  2024-7-17 14:59:32
 */
public class AboutSoftware extends JDialog {
    JTextArea jTextField_info=null;
    JLabel jLabel_image=null;
    public AboutSoftware() {
        super();
        initialize();
    }
    private void initialize() {
        this.setSize(new Dimension(600,400));
        this.setLocationRelativeTo(null);
        this.setTitle("关于系统");
        this.setResizable(false);
        jTextField_info=new JTextArea();
        jTextField_info.setFont(new Font("宋体", Font.PLAIN, 35));
        jTextField_info.setText("\n   该学生信息管理系统于2024年7月编写，由NCHU成员 李茂田，熊锋，邢沂炜" +
                "合作完成。"+"        "+"\n"+"   All Rights Reserved");
        jTextField_info.setBounds(new Rectangle(150,150,200,450));
        jTextField_info.setEditable(false);
        jTextField_info.setLineWrap(true);

        this.add(jTextField_info);
    }
}
