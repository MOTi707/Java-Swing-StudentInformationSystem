package com.NCHUStudent.form;

import com.NCHUStudent.dao.UserDao;

import javax.swing.*;
import java.awt.*;

public class UserChangePassword extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JPasswordField jPasswordField_old = null;
	private JPasswordField jPasswordField_new1 = null;
	private JPasswordField jPasswordField_new2 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	public MainFrame mainFrame=null;
	
	
	ImageIcon icon0 = new ImageIcon(getClass().getResource("../images/main1.gif"));
	ImageIcon icon1 = new ImageIcon(getClass().getResource("../images/main.gif"));
	/**
	 * 构造函数模块
	 */
	public UserChangePassword() {
		super();
		initialize();
	}

	private void initialize() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(270, 250);
		this.setContentPane(getJContentPane());
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setTitle("修改密码");
	}

	/**
	 * 获取面板内容模块
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(16, 113, 80, 29));
			jLabel2.setText("确认密码：");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(17, 71, 74, 31));
			jLabel1.setText("新密码：");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(18, 27, 70, 35));
			jLabel.setText("旧密码：");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJPasswordField_old(), null);
			jContentPane.add(getJPasswordField_new1(), null);
			jContentPane.add(getJPasswordField_new2(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.setBorder(BorderFactory.createTitledBorder("修改密码"));
		}
		return jContentPane;
	}

	private JPasswordField getJPasswordField_old() {
		if (jPasswordField_old == null) {
			jPasswordField_old = new JPasswordField();
			jPasswordField_old.setBounds(new Rectangle(102, 35, 125, 24));
		}
		return jPasswordField_old;
	}

	private JPasswordField getJPasswordField_new1() {
		if (jPasswordField_new1 == null) {
			jPasswordField_new1 = new JPasswordField();
			jPasswordField_new1.setBounds(new Rectangle(101, 78, 124, 24));
		}
		return jPasswordField_new1;
	}

	private JPasswordField getJPasswordField_new2() {
		if (jPasswordField_new2 == null) {
			jPasswordField_new2 = new JPasswordField();
			jPasswordField_new2.setBounds(new Rectangle(102, 118, 124, 24));
		}
		return jPasswordField_new2;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(15, 153, 77, 27));
			jButton.setText("确定");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					UserDao ud = new UserDao();

					String old = new String(getJPasswordField_old().getPassword());
					String new1 = new String(getJPasswordField_new1().getPassword());
					String new2 = new String(getJPasswordField_new2().getPassword());

					if (old.equals(Login.storeUserPassword)) {
						if (new1.equals(new2)) {
							ud.changeUserPassword(MainFrame.typeOfUser, Login.storeUserName, Login.storeUserPassword, new1);
							/*if (MainFrame.typeOfUser==0){
								//管理员修改密码
								ud.changeUserPassword(0, Login.storeUserName, Login.storeUserPassword, new1);
							}
							else if (MainFrame.typeOfUser==1) {
								//教师修改密码
								ud.changeUserPassword(1, Login.storeUserName, Login.storeUserPassword, new1);
							}
							else if (MainFrame.typeOfUser==2) {
								//学生修改密码
								ud.changeUserPassword(2, Login.storeUserName, Login.storeUserPassword, new1);
							}*/
							JOptionPane.showMessageDialog(null,
									"孩子密码对了，\n你也太厉害了吧，\n让我们妮露给你跳支舞。\n最后再次恭喜您密码修改成功！！！！\n点击确认后将重新登录", "密码修改成功",
									JOptionPane.INFORMATION_MESSAGE,icon0);

							Login.storeUserPassword = new1;//把改好的密码写回去。
							dispose();//关闭修改密码窗口
							mainFrame.dispose();//关闭主界面窗口
							Login loginDialog = new Login();
							loginDialog.setVisible(true);//改好了重新登录
						} else {
							JOptionPane.showMessageDialog(null,
									"请您重新确定两次新密码输入，请重新输入！", "系统提示",
									JOptionPane.ERROR_MESSAGE);
							return;} }
					else {
						JOptionPane.showMessageDialog(null,"孩子这么短的密码都记不住\n你是废物吗\n但是请你记住我们家妮露天下第一，\n最后强调一下！旧密码不正确！！！！！！","旧密码不正确！",
								JOptionPane.INFORMATION_MESSAGE,icon1);
						return;
					}
				}
			});
		}return jButton;

	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(147, 153, 77, 27));
			jButton1.setText("取消");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return jButton1;
	}

} 
