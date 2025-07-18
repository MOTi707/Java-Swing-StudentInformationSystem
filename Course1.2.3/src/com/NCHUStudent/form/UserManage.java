package com.NCHUStudent.form;

import com.NCHUStudent.dao.UserDao;
import com.NCHUStudent.pojo.UserModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 用户管理
 */
public class UserManage extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton_add = null;
	private Label label_user_name = null;
	private Label label_user_password = null;
	private Label label_user_privilege = null;
	private JTextField jTextField_user_name = null;
	private JPasswordField jPasswordField_user_password = null;
	private JComboBox jComboBox_user_privilege = null;

	private JButton jButton_delete = null;
	private JTable jTable = null;
	private DefaultTableModel model;
	private List<UserModel> user_lists;  //  @jve:decl-index=0:
	private int counts =0 ;
	private JScrollPane jScrollPane = null;
	/**
	 * 构造函数模块
	 */
	public UserManage() {
		super();
		initialize();
		
		initData();
	}

	private void initialize() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("用户管理");
		this.setModal(true);
		this.setSize(new Dimension(550, 400));
		this.setLocation(new Point(0, 0));
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("../images/icon.png")));

		jButton_add = new JButton();
		jButton_add.setBounds(new Rectangle(311, 183, 78, 26));
		jButton_add.setText("添加");
		jTextField_user_name = new JTextField();
		jTextField_user_name.setBounds(new Rectangle(98, 183, 165, 28));
		jPasswordField_user_password = new JPasswordField();
		jPasswordField_user_password.setBounds(new Rectangle(98, 222, 165, 26));
		jComboBox_user_privilege = new JComboBox();
		jComboBox_user_privilege.addItem("管理员");
		jComboBox_user_privilege.addItem("普通用户");
		jComboBox_user_privilege.setBounds(new Rectangle(99, 256, 163, 27));
		jButton_delete = new JButton();
		jButton_delete.setBounds(new Rectangle(314, 228, 78, 26));

		jButton_delete.setText("删除");
		label_user_privilege = new Label();
		label_user_privilege.setBounds(new Rectangle(16, 261, 76, 20));
		label_user_privilege.setText("权   限：");
		label_user_password = new Label();
		label_user_password.setBounds(new Rectangle(17, 213, 76, 31));
		label_user_password.setText("密   码：");
		label_user_name = new Label();
		label_user_name.setBounds(new Rectangle(17, 183, 76, 26));
		label_user_name.setText("用 户 名：");
		
	
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jButton_add, null);
		jContentPane.add(label_user_name, null);
		jContentPane.add(label_user_password, null);
		jContentPane.add(label_user_privilege, null);
		jContentPane.add(jTextField_user_name, null);
		jContentPane.add(jPasswordField_user_password, null);
		jContentPane.add(jComboBox_user_privilege, null);
		jContentPane.add(jButton_delete, null);
		jContentPane.setBorder(BorderFactory.createTitledBorder("管理数字化"));
		this.setContentPane(jContentPane);

		jTable = new JTable();
		jScrollPane = new JScrollPane();
		jScrollPane.setBounds(new Rectangle(15, 24, 378, 149));
		jScrollPane.setViewportView(jTable);
		
		jContentPane.add(jScrollPane, null);
		setContentPane(jContentPane);
		
		jButton_add.addActionListener(new btnListener());
		jButton_delete.addActionListener(new btnListener());
	}
	
	

	/**
	 * @初始化界面
	 */
	public void initData() {
		String heads[] = { "ID", "姓名", "登录时间", "登录ip","用户权限" };
		model = new DefaultTableModel(null, heads);
		UserDao ud= new UserDao();
		user_lists = ud.getLists(false, -1);
		flashData();
	}
	public void flashData(){
		counts = user_lists.size();
		model.setRowCount(user_lists.size());// 设置行数
			for(int i=0;i<counts;i++){
				model.setValueAt(user_lists.get(i).getUser_id(), i, 0);
				model.setValueAt(user_lists.get(i).getUser_name(), i, 1);
				model.setValueAt(user_lists.get(i).getUser_login_time(), i, 2);
				model.setValueAt(user_lists.get(i).getUser_login_ip(), i, 3);
				model.setValueAt(user_lists.get(i).getUser_privilege(), i, 4);
			}
			jTable.setModel(model);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(35);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(40);

	}

	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			UserDao ud = new UserDao();
			if (e.getSource() == jButton_delete) {
				int id  = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(),0).toString());
					if (Login.storeUserId == id) {
						JOptionPane.showMessageDialog(null, "自己不能删除自己！");
					} else {
						ud.deleteListByUserId(id);
						user_lists = ud.getLists(false, -1);
						JOptionPane.showMessageDialog(null, "删除用户成功！");
						flashData();
					}
				} else if (e.getSource() == jButton_add) {
					String user_name = jTextField_user_name.getText();
					String user_password = jPasswordField_user_password.getText().toString();
					String user_privilege = jComboBox_user_privilege.getSelectedItem().toString();
					if("".equals(user_name)){
						JOptionPane.showMessageDialog(null, "用户名不能为空！");
						return;
					}
					if("".equals(user_password)){
						JOptionPane.showMessageDialog(null, "密码不能为空！");
						return;
					}
					if(ud.isExist(user_name)){
						JOptionPane.showMessageDialog(null, "用户名已经存在！");
						return;
					}
					if(ud.addUser(user_name, user_password, user_privilege))
						JOptionPane.showMessageDialog(null, "添加成功");
					user_lists = ud.getLists(false, -1);
					flashData();
				}
			}
		}

	
}
