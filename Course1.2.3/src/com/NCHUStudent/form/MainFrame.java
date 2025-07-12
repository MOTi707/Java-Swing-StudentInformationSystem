package com.NCHUStudent.form;

import javax.swing.*;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;


public class MainFrame extends JFrame {
	public int studentID=-1;
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenu jMenu_start = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu_information = null;
	private JMenu jMenu_ccourse = null;
	private JMenu jMenu_mark = null;

	private JMenuItem jMenuItem_relogin = null;

	private JMenuItem jMenuItem_change_password = null;
	public JMenuItem jMenuItem_user_manage = null;
	private JMenuItem jMenuItem_exit = null;
	private JMenuItem jMenuItem_student_manage = null;
	private JMenuItem jMenuItem_teacher_manage = null;
	private JMenuItem jMenuItem_course_manage = null;
	private JMenuItem jMenuItem_grade_class = null;
	private JMenuItem jMenuItem_class = null;

	private JMenuItem jMenuItem_ccourse = null;
	private JMenuItem jMenuItem_ccourse_add = null;
	private JMenuItem jMenuItem_mark_add = null;
	private JMenuItem jMenuItem_mark_statistics = null;
	private JMenuItem jMenuItem_lookMyScore=null;

	private JMenuItem jMenuItem_aboutSoftware =null;
	private JMenuItem jMenuItem_log=null;
	private JMenu jMenuItem_crew=null;
	private JMenu jMenu_about=null;
	private AboutSoftware aboutSoftware = null;
	private JMenuItem jMenuItem_addCourseByClass =null;

	private String userName=null;
	public static int typeOfUser=-1;
	private Label label_time=null;



	//private JLabel jLabel = null;
	private URL imgURL = null;
	private SystemTray sysTray = SystemTray.getSystemTray();
	
	Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/NCHUStudent/images/icon.jpg"));
	private TrayIcon trayicon = new TrayIcon(image, "学生信息管理系统", createMenu());

	public MainFrame() {
		super();
		initialize();
		initPrivilege();
	}

	public MainFrame(int ID) {
		super();
		this.studentID=ID;
		initialize();
		initPrivilege();
	}

	private void initialize() {
		this.setSize(800, 544);// 主界面大小
		this.setTitle("学生管理系统");
		//imgURL = this.getClass().getResource("/com/NCHUStudent/images/icon.png");
		imgURL = this.getClass().getResource("../images/ikun.jpg");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));

		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter()// 系统关闭事件
		{
			public void windowClosing(WindowEvent e) {
				SystemTrayInitial();//初始化托盘图标
			}
		});

		//第一个选项卡 开始菜单
		jMenuItem_relogin = new JMenuItem();
		jMenuItem_relogin.setText("重新登录");
		jMenuItem_change_password = new JMenuItem();
		jMenuItem_change_password.setText("修改密码");
		jMenuItem_user_manage = new JMenuItem();
		jMenuItem_user_manage.setText("用户管理");
		jMenuItem_exit = new JMenuItem();
		jMenuItem_exit.setText("退出");
		jMenu_start = new JMenu();
		jMenu_start.setText("开始  ");
		jMenu_start.add(jMenuItem_relogin);
		jMenu_start.add(jMenuItem_change_password);
		jMenu_start.add(jMenuItem_user_manage);
		jMenu_start.addSeparator();// 分割线
		jMenu_start.add(jMenuItem_exit);

		//第二个选项卡 后台管理
		jMenuItem_student_manage = new JMenuItem();
		jMenuItem_student_manage.setText("学生信息管理");
		jMenuItem_teacher_manage = new JMenuItem();
		jMenuItem_teacher_manage.setText("教师信息管理");
		jMenuItem_course_manage = new JMenuItem();
		jMenuItem_course_manage.setText("课程信息管理");
		jMenuItem_grade_class = new JMenuItem();
		jMenuItem_grade_class.setText("年级信息管理");
		jMenuItem_class = new JMenuItem();
		jMenuItem_class.setText("班级信息管理");
		jMenu_information = new JMenu();
		jMenu_information.setText("信息管理");
		jMenu_information.add(jMenuItem_student_manage);
		jMenu_information.add(jMenuItem_teacher_manage);
		jMenu_information.add(jMenuItem_course_manage);
		jMenu_information.add(jMenuItem_grade_class);
		jMenu_information.add(jMenuItem_class);

		//第三个选项栏 选课情况
		jMenuItem_ccourse_add = new JMenuItem();
		jMenuItem_ccourse_add.setText("选课");
		jMenuItem_ccourse = new JMenuItem();
		jMenuItem_ccourse.setText("选课总览");
		jMenu_ccourse = new JMenu();
		jMenu_ccourse.setText("选课管理");
		jMenuItem_addCourseByClass =new JMenuItem();
		jMenuItem_addCourseByClass.setText("按班级排课");
		jMenu_ccourse.add(jMenuItem_ccourse_add);
		jMenu_ccourse.add(jMenuItem_addCourseByClass);
		jMenu_ccourse.add(jMenuItem_ccourse);

		//第四个选项栏 成绩管理
		jMenuItem_mark_add = new JMenuItem();
		jMenuItem_mark_add.setText("成绩录入");
		jMenuItem_lookMyScore=new JMenuItem();
		jMenuItem_lookMyScore.setText("我的成绩");
		jMenuItem_mark_statistics = new JMenuItem();
		jMenuItem_mark_statistics.setText("成绩统计");
		jMenu_mark = new JMenu();
		jMenu_mark.setText("成绩管理");
		jMenu_mark.add(jMenuItem_mark_add);
		jMenu_mark.add(jMenuItem_mark_statistics);
		jMenu_mark.add(jMenuItem_lookMyScore);

		//第五个选项栏 关于
		jMenuItem_aboutSoftware=new JMenuItem();
		jMenuItem_log=new JMenuItem();
		jMenuItem_crew=new JMenu();
		JMenu xiong=new JMenu("熊锋");
		JMenu xin=new JMenu("邢沂炜");
		JMenu li=new JMenu("李茂田");
		jMenuItem_aboutSoftware.setText("关于系统");
		jMenuItem_log.setText("操作日记");
		jMenuItem_crew.setText("研发人员");
		jMenuItem_crew.add(li);
		jMenuItem_crew.add(xiong);
		jMenuItem_crew.add(xin);
		li.add(new JMenuItem("前端开发"));
		li.add(new JMenuItem("后端开发"));
		li.add(new JMenuItem("架构设计"));
		xiong.add(new JMenuItem("模型构建"));
		xiong.add(new JMenuItem("数据库设计"));
		xiong.add(new JMenuItem("数据处理"));
		xin.add(new JMenuItem("前端设计"));
		xin.add(new JMenuItem("配置管理"));
		xin.add(new JMenuItem("参数优化"));

		jMenu_about=new JMenu();
		jMenu_about.setText("关于");
		jMenu_about.add(jMenuItem_log);
		jMenu_about.addSeparator();
		jMenu_about.add(jMenuItem_crew);
		jMenu_about.add(jMenuItem_aboutSoftware);

		jJMenuBar = new JMenuBar();
		jJMenuBar.setPreferredSize(new Dimension(50, 40));
		jJMenuBar.add(jMenu_start);
		jJMenuBar.add(jMenu_information);
		jJMenuBar.add(jMenu_ccourse);
		jJMenuBar.add(jMenu_mark);
		jJMenuBar.add(jMenu_about);
		jJMenuBar.setFont(new Font("楷体", Font.PLAIN, 35));
		setJMenuBar(jJMenuBar);

		//设置背景图片
		JLabel jLabel = null;
		jLabel = new JLabel();
		jLabel.setText("");
		jLabel.setBounds(new Rectangle(0, -40, 800, 504));
		imgURL = this.getClass().getResource("/com/NCHUStudent/images/../images/NCHU.jpg");
		jLabel.setIcon(new ImageIcon(imgURL));

		//添加时钟
		label_time=new Label();
		label_time.setFont(new Font("宋体", Font.BOLD, 25));
		Color color = new Color(175, 180, 219, 255);
		label_time.setBackground(color); // 设置标签背景色为完全透明
		Label label_pro = new Label("北京时间 ");
		label_pro.setFont(new Font("楷体", Font.BOLD, 25));
		label_pro.setBackground(color);
		label_pro.setBounds(new Rectangle(10,385,150,35));
		label_time.setBounds(new Rectangle(10,420,150,20));


		this.add(label_time);
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jLabel.setForeground(new Color(255, 255, 255, 128));
		jContentPane.add(label_time,null);
		jContentPane.add(label_pro,null);
		jContentPane.add(jLabel, null);
		setContentPane(jContentPane);

		btnListener btn = new btnListener();
		jMenuItem_relogin.addActionListener(btn);
		jMenuItem_change_password.addActionListener(btn);
		jMenuItem_user_manage.addActionListener(btn);
		jMenuItem_exit.addActionListener(btn);
		jMenuItem_student_manage.addActionListener(btn);
		jMenuItem_teacher_manage.addActionListener(btn);
		jMenuItem_course_manage.addActionListener(btn);
		jMenuItem_grade_class.addActionListener(btn);
		jMenuItem_class.addActionListener(btn);
		jMenuItem_ccourse_add.addActionListener(btn);
		jMenuItem_mark_add.addActionListener(btn);
		jMenuItem_mark_statistics.addActionListener(btn);
		jMenuItem_ccourse.addActionListener(btn);
		jMenuItem_log.addActionListener(btn);
		jMenuItem_aboutSoftware.addActionListener(btn);
		jMenuItem_lookMyScore.addActionListener(btn);
		jMenuItem_addCourseByClass.addActionListener(btn);

		updateTime();
	}


	//并行： 定时更新时间，可能会导致界面以下的代码功能变得不响应
	private void updateTime() {
		new Thread(() -> {		// lambda	匿名函数。
			while (true) {
				String time = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
				label_time.setText(time);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

/**
 * @初始化托盘
 */
private void SystemTrayInitial() { // 托盘
	if (!SystemTray.isSupported()) // 判断当前系统是否支持系统栏
		return;
	try {
		sysTray.add(trayicon);
	} catch (AWTException e1) {
		e1.printStackTrace();
	}
	setVisible(false);
	trayicon.displayMessage("选课管理系统", "", MessageType.INFO);// 窗体托盘时所显示的消息对话
	trayicon.addActionListener(new ActionListener()// 击图标时显示窗体
	{
		public void actionPerformed(ActionEvent e) {
			sysTray.remove(trayicon);
			setVisible(true);
		}
	});
}
	/**
	 * @初始化托盘右键
	 * @return
	 */
	private PopupMenu createMenu() { // 创建系统栏菜单的方法
		PopupMenu menu = new PopupMenu();
		MenuItem exitItem = new MenuItem("退出本系统");
		exitItem.addActionListener(new ActionListener() { // 系统栏退出事件
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		MenuItem openItem = new MenuItem("打开主窗口");
		openItem.addActionListener(new ActionListener() {// 系统栏打开菜单项事件
			public void actionPerformed(ActionEvent e) {
				if (!isVisible()) {
					setVisible(true);
					sysTray.remove(trayicon);
				}
			}
		});


		menu.add(openItem);
		menu.add(exitItem);
		return menu;
	}

	public void initPrivilege(){
		if(Login.login_user_type==1){
			//老师类
			jMenuItem_user_manage.setEnabled(false);
			jMenu_information.setEnabled(false);
			jMenuItem_log.setEnabled(false);
			jMenuItem_lookMyScore.setEnabled(false);
		}
		else if(Login.login_user_type==0){
			//管理员类
			jMenuItem_lookMyScore.setEnabled(false);
		}
		else if(Login.login_user_type==2){
			//学生类
			jMenuItem_user_manage.setEnabled(false);
			jMenuItem_mark_add.setEnabled(false);
			jMenu_information.setEnabled(false);
			jMenuItem_log.setEnabled(false);
			jMenuItem_addCourseByClass.setEnabled(false);
			jMenuItem_ccourse.setEnabled(false);
		}
		else{
			//其他未知的登录
			jMenuItem_ccourse.setEnabled(false);
			jMenuItem_change_password.setEnabled(false);
			jMenuItem_user_manage.setEnabled(false);
			jMenu_information.setEnabled(false);
		}
		
	}


	public class btnListener implements ActionListener {
		//鼠标事件
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jMenuItem_relogin) {
				dispose();//dispose() 方法释放组件占用的资源
				Login login = new Login();// 调用无参数构造方法
				login.setVisible(true);
			} else if (e.getSource() == jMenuItem_change_password) {
				//修改为所有用户都可以修改密码
				UserChangePassword cp = new UserChangePassword();
				cp.mainFrame=MainFrame.this;
				cp.setVisible(true);
			} else if (e.getSource() == jMenuItem_user_manage) {
			
				UserManage um = new UserManage();
				um.setVisible(true);
			}
			else if (e.getSource() == jMenuItem_exit) {

				System.exit(0);
			}
			//第二栏
			else if (e.getSource() == jMenuItem_student_manage) {
				//学生信息管理
				StudentManage sm = new StudentManage();
				sm.setVisible(true);
			} else if (e.getSource() == jMenuItem_teacher_manage) {
		
				TeacherManage tm = new TeacherManage();
				tm.setVisible(true);
			}else if (e.getSource() == jMenuItem_grade_class) {
				GradeManage gm = new GradeManage();
				gm.setVisible(true);
			} else if(e.getSource() == jMenuItem_class){
				ClassManage cm= new ClassManage();
				cm.setVisible(true);
			}
			//第三栏
			else if (e.getSource() == jMenuItem_course_manage) {
				CourseManage cm = new CourseManage();
				cm.setVisible(true);
			} else if (e.getSource() == jMenuItem_ccourse_add) {
				CCourseAdd cm = new CCourseAdd();
				cm.setVisible(true);
			} else if (e.getSource() == jMenuItem_ccourse) {
				CCourseManage cci = new CCourseManage();
				cci.setVisible(true);
			}
			//第四栏
			else if (e.getSource() == jMenuItem_mark_add) {
				CCourseMarkAdd ccma = new CCourseMarkAdd();
				ccma.setVisible(true);
			}else if (e.getSource() == jMenuItem_mark_statistics) {
				CCourseMarkStatistic ccs = new CCourseMarkStatistic();
				ccs.setVisible(true);
			}
			//第五栏
			else if (e.getSource() == jMenuItem_log ) {
				LogManage logManage=new LogManage();
				logManage.setVisible(true);
			}
			else if (e.getSource() == jMenuItem_aboutSoftware ) {
				aboutSoftware = new AboutSoftware();
				aboutSoftware.setVisible(true);
			}
			else if (e.getSource() == jMenuItem_lookMyScore ) {
				MyScore myScore = new MyScore(studentID);
				myScore.setVisible(true);
			}
			//补充  按班级加课
			else if (e.getSource() == jMenuItem_addCourseByClass) {
				AddCourseByClass addCourseByClass = new AddCourseByClass();
				addCourseByClass.setVisible(true);
			}
		}

	}
	
	
	public static void main(String[] args) {
		MainFrame login = new MainFrame();
		login.setVisible(true);
	}

	public String getUserName () {
		return userName;
	}

	public void setUserName (String userName) {
		this.userName = userName;
	}

	public int getTypeOfUser () {
		return typeOfUser;
	}

	public void setTypeOfUser (int typeOfUser) {
		this.typeOfUser = typeOfUser;
	}
}
