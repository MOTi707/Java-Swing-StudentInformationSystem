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
	private TrayIcon trayicon = new TrayIcon(image, "ѧ����Ϣ����ϵͳ", createMenu());

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
		this.setSize(800, 544);// �������С
		this.setTitle("ѧ������ϵͳ");
		//imgURL = this.getClass().getResource("/com/NCHUStudent/images/icon.png");
		imgURL = this.getClass().getResource("../images/ikun.jpg");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));

		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter()// ϵͳ�ر��¼�
		{
			public void windowClosing(WindowEvent e) {
				SystemTrayInitial();//��ʼ������ͼ��
			}
		});

		//��һ��ѡ� ��ʼ�˵�
		jMenuItem_relogin = new JMenuItem();
		jMenuItem_relogin.setText("���µ�¼");
		jMenuItem_change_password = new JMenuItem();
		jMenuItem_change_password.setText("�޸�����");
		jMenuItem_user_manage = new JMenuItem();
		jMenuItem_user_manage.setText("�û�����");
		jMenuItem_exit = new JMenuItem();
		jMenuItem_exit.setText("�˳�");
		jMenu_start = new JMenu();
		jMenu_start.setText("��ʼ  ");
		jMenu_start.add(jMenuItem_relogin);
		jMenu_start.add(jMenuItem_change_password);
		jMenu_start.add(jMenuItem_user_manage);
		jMenu_start.addSeparator();// �ָ���
		jMenu_start.add(jMenuItem_exit);

		//�ڶ���ѡ� ��̨����
		jMenuItem_student_manage = new JMenuItem();
		jMenuItem_student_manage.setText("ѧ����Ϣ����");
		jMenuItem_teacher_manage = new JMenuItem();
		jMenuItem_teacher_manage.setText("��ʦ��Ϣ����");
		jMenuItem_course_manage = new JMenuItem();
		jMenuItem_course_manage.setText("�γ���Ϣ����");
		jMenuItem_grade_class = new JMenuItem();
		jMenuItem_grade_class.setText("�꼶��Ϣ����");
		jMenuItem_class = new JMenuItem();
		jMenuItem_class.setText("�༶��Ϣ����");
		jMenu_information = new JMenu();
		jMenu_information.setText("��Ϣ����");
		jMenu_information.add(jMenuItem_student_manage);
		jMenu_information.add(jMenuItem_teacher_manage);
		jMenu_information.add(jMenuItem_course_manage);
		jMenu_information.add(jMenuItem_grade_class);
		jMenu_information.add(jMenuItem_class);

		//������ѡ���� ѡ�����
		jMenuItem_ccourse_add = new JMenuItem();
		jMenuItem_ccourse_add.setText("ѡ��");
		jMenuItem_ccourse = new JMenuItem();
		jMenuItem_ccourse.setText("ѡ������");
		jMenu_ccourse = new JMenu();
		jMenu_ccourse.setText("ѡ�ι���");
		jMenuItem_addCourseByClass =new JMenuItem();
		jMenuItem_addCourseByClass.setText("���༶�ſ�");
		jMenu_ccourse.add(jMenuItem_ccourse_add);
		jMenu_ccourse.add(jMenuItem_addCourseByClass);
		jMenu_ccourse.add(jMenuItem_ccourse);

		//���ĸ�ѡ���� �ɼ�����
		jMenuItem_mark_add = new JMenuItem();
		jMenuItem_mark_add.setText("�ɼ�¼��");
		jMenuItem_lookMyScore=new JMenuItem();
		jMenuItem_lookMyScore.setText("�ҵĳɼ�");
		jMenuItem_mark_statistics = new JMenuItem();
		jMenuItem_mark_statistics.setText("�ɼ�ͳ��");
		jMenu_mark = new JMenu();
		jMenu_mark.setText("�ɼ�����");
		jMenu_mark.add(jMenuItem_mark_add);
		jMenu_mark.add(jMenuItem_mark_statistics);
		jMenu_mark.add(jMenuItem_lookMyScore);

		//�����ѡ���� ����
		jMenuItem_aboutSoftware=new JMenuItem();
		jMenuItem_log=new JMenuItem();
		jMenuItem_crew=new JMenu();
		JMenu xiong=new JMenu("�ܷ�");
		JMenu xin=new JMenu("�����");
		JMenu li=new JMenu("��ï��");
		jMenuItem_aboutSoftware.setText("����ϵͳ");
		jMenuItem_log.setText("�����ռ�");
		jMenuItem_crew.setText("�з���Ա");
		jMenuItem_crew.add(li);
		jMenuItem_crew.add(xiong);
		jMenuItem_crew.add(xin);
		li.add(new JMenuItem("ǰ�˿���"));
		li.add(new JMenuItem("��˿���"));
		li.add(new JMenuItem("�ܹ����"));
		xiong.add(new JMenuItem("ģ�͹���"));
		xiong.add(new JMenuItem("���ݿ����"));
		xiong.add(new JMenuItem("���ݴ���"));
		xin.add(new JMenuItem("ǰ�����"));
		xin.add(new JMenuItem("���ù���"));
		xin.add(new JMenuItem("�����Ż�"));

		jMenu_about=new JMenu();
		jMenu_about.setText("����");
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
		jJMenuBar.setFont(new Font("����", Font.PLAIN, 35));
		setJMenuBar(jJMenuBar);

		//���ñ���ͼƬ
		JLabel jLabel = null;
		jLabel = new JLabel();
		jLabel.setText("");
		jLabel.setBounds(new Rectangle(0, -40, 800, 504));
		imgURL = this.getClass().getResource("/com/NCHUStudent/images/../images/NCHU.jpg");
		jLabel.setIcon(new ImageIcon(imgURL));

		//���ʱ��
		label_time=new Label();
		label_time.setFont(new Font("����", Font.BOLD, 25));
		Color color = new Color(175, 180, 219, 255);
		label_time.setBackground(color); // ���ñ�ǩ����ɫΪ��ȫ͸��
		Label label_pro = new Label("����ʱ�� ");
		label_pro.setFont(new Font("����", Font.BOLD, 25));
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


	//���У� ��ʱ����ʱ�䣬���ܻᵼ�½������µĴ��빦�ܱ�ò���Ӧ
	private void updateTime() {
		new Thread(() -> {		// lambda	����������
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
 * @��ʼ������
 */
private void SystemTrayInitial() { // ����
	if (!SystemTray.isSupported()) // �жϵ�ǰϵͳ�Ƿ�֧��ϵͳ��
		return;
	try {
		sysTray.add(trayicon);
	} catch (AWTException e1) {
		e1.printStackTrace();
	}
	setVisible(false);
	trayicon.displayMessage("ѡ�ι���ϵͳ", "", MessageType.INFO);// ��������ʱ����ʾ����Ϣ�Ի�
	trayicon.addActionListener(new ActionListener()// ��ͼ��ʱ��ʾ����
	{
		public void actionPerformed(ActionEvent e) {
			sysTray.remove(trayicon);
			setVisible(true);
		}
	});
}
	/**
	 * @��ʼ�������Ҽ�
	 * @return
	 */
	private PopupMenu createMenu() { // ����ϵͳ���˵��ķ���
		PopupMenu menu = new PopupMenu();
		MenuItem exitItem = new MenuItem("�˳���ϵͳ");
		exitItem.addActionListener(new ActionListener() { // ϵͳ���˳��¼�
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		MenuItem openItem = new MenuItem("��������");
		openItem.addActionListener(new ActionListener() {// ϵͳ���򿪲˵����¼�
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
			//��ʦ��
			jMenuItem_user_manage.setEnabled(false);
			jMenu_information.setEnabled(false);
			jMenuItem_log.setEnabled(false);
			jMenuItem_lookMyScore.setEnabled(false);
		}
		else if(Login.login_user_type==0){
			//����Ա��
			jMenuItem_lookMyScore.setEnabled(false);
		}
		else if(Login.login_user_type==2){
			//ѧ����
			jMenuItem_user_manage.setEnabled(false);
			jMenuItem_mark_add.setEnabled(false);
			jMenu_information.setEnabled(false);
			jMenuItem_log.setEnabled(false);
			jMenuItem_addCourseByClass.setEnabled(false);
			jMenuItem_ccourse.setEnabled(false);
		}
		else{
			//����δ֪�ĵ�¼
			jMenuItem_ccourse.setEnabled(false);
			jMenuItem_change_password.setEnabled(false);
			jMenuItem_user_manage.setEnabled(false);
			jMenu_information.setEnabled(false);
		}
		
	}


	public class btnListener implements ActionListener {
		//����¼�
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jMenuItem_relogin) {
				dispose();//dispose() �����ͷ����ռ�õ���Դ
				Login login = new Login();// �����޲������췽��
				login.setVisible(true);
			} else if (e.getSource() == jMenuItem_change_password) {
				//�޸�Ϊ�����û��������޸�����
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
			//�ڶ���
			else if (e.getSource() == jMenuItem_student_manage) {
				//ѧ����Ϣ����
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
			//������
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
			//������
			else if (e.getSource() == jMenuItem_mark_add) {
				CCourseMarkAdd ccma = new CCourseMarkAdd();
				ccma.setVisible(true);
			}else if (e.getSource() == jMenuItem_mark_statistics) {
				CCourseMarkStatistic ccs = new CCourseMarkStatistic();
				ccs.setVisible(true);
			}
			//������
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
			//����  ���༶�ӿ�
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
