package com.NCHUStudent.form;

import com.NCHUStudent.dao.UserDao;
import com.NCHUStudent.util.JDBCUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;


public class Login extends JFrame {
    private static final long serialVersionUID = 1L;
    public static String storeUserName = null;// 登录用户名
    public static String storeUserPassword = null;// 登录密码
    static int storeUserId;// 登录用户名
    static boolean RELOAD = true;// 重新登陆标记
    static int login_user_type;//0表示管理员，1表示老师，2表示学生
    private JPanel jContentPane = null;
    private JButton jButton21 = null;
    private JButton jButton22 = null;
    private JTextField jTextField = null;
    private JPasswordField jPasswordField = null;
    private JLabel jLabel_userName = null;
    private JLabel jLabel_password = null;
    private JLabel jLabel_privilege = null;
    private JLabel jLabel_NCHU = null;
    private BtnListener btl = null;
    private JComboBox jComboBox = null;
    private JLabel jLabel_tips = null;
    private JLabel jLabel_title = null;

    public Login() {
        super();
        initialize();
    }

    public Login(boolean reload) {
        super();
        initialize();
    }

    /**
     * @param args
     * @主函数
     */
    public static void main(String[] args) {
        Login login = new Login(RELOAD);
        login.setVisible(true);
    }

    //载入图片
    private static void loadImageAndDisplay(JLabel label, String imagePath) {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            // 自定义分辨率 缩放
            int labelWidth = 800;
            int labelHeight = 544;
            // 缩放图片以适应 JLabel 的大小
            Image scaledImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImg);
            label.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        URL resourceTemp = this.getClass().getResource("../images/ikun.jpg");
        ImageIcon iconTemp = new ImageIcon(resourceTemp);
        this.setIconImage(iconTemp.getImage());

        this.setSize(800, 544);
        this.setTitle("用户登陆");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //使用Windows的look和feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
        } catch (Exception e) {
            e.printStackTrace();
        }
        //显示图片
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        URL resource = this.getClass().getResource("../images/NCHU.jpg");
        ImageIcon icon = new ImageIcon(resource);
        imageLabel.setIcon(icon);
        imageLabel.setBounds(0, 0, 800, 544);

        //标题文字
        jLabel_title = new JLabel("学生信息管理系统");
        Font font5 = new Font("宋体", Font.PLAIN, 70);
        jLabel_title.setFont(font5);
        // 计算使 jLabel_title 水平居中的位置
        int windowWidth = getWidth(); // 获取窗口宽度
        int titleLabelWidth = jLabel_title.getPreferredSize().width; // 获取 jLabel_title 的宽度
        int x = (windowWidth - titleLabelWidth) / 2;
        int y = 20;
        jLabel_title.setBounds(x, y, titleLabelWidth, 70); // 70 是字体大小
        jLabel_NCHU = new JLabel("NCHU All Rights Reserved");
        jLabel_NCHU.setFont(new Font("宋体", Font.PLAIN, 22));
        jLabel_NCHU.setBounds(new Rectangle(350, 100, 400, 20));

        //登录按钮 退出按钮
        jButton21 = new JButton();
        jButton21.setBounds(new Rectangle(150, 420, 200, 50));
        jButton21.setText("登录");
        getRootPane().setDefaultButton(jButton21);// 回车登录
        jButton22 = new JButton();
        jButton22.setBounds(new Rectangle(450, 420, 200, 50));
        jButton22.setText("退出");
        Font buttonFont = new Font("华文黑体", Font.BOLD, 35);
        jButton21.setFont(buttonFont);
        jButton22.setFont(buttonFont);

        //用户名
        jLabel_userName = new JLabel();
        jLabel_userName.setText("用户名：");
        Font font2 = new Font("宋体", Font.PLAIN, 35);
        jLabel_userName.setFont(font2);
        jTextField = new JTextField(40);

        double widthUsernameText = jLabel_userName.getPreferredSize().getWidth();
        double widthUserinput = jTextField.getPreferredSize().getWidth();
        double gap1 = 50;
        double x1 = (windowWidth - (widthUsernameText + widthUserinput + gap1)) / 2.0;
        double x2 = x1 + gap1 + widthUsernameText;
        jLabel_userName.setBounds(new Rectangle((int) x1, 150, 150, 50));
        jTextField.setBounds(new Rectangle((int) x2, 150, 250, 40));

        //密码
        jPasswordField = new JPasswordField(30);
        jLabel_password = new JLabel();
        jLabel_password.setText("密  码：");
        Font font = new Font("宋体", Font.PLAIN, 35); // 字体名称，样式，大小
        jLabel_password.setFont(font);
        jLabel_password.setBounds(new Rectangle((int) x1, 240, 159, 50));
        jPasswordField.setBounds(new Rectangle((int) x2, 240, 250, 40));

        //登录类型 多选框
        jLabel_privilege = new JLabel();
        jLabel_privilege.setText("登录类型：");
        Font font4 = new Font("宋体", Font.PLAIN, 20);
        jLabel_privilege.setFont(font4);
        jComboBox = new JComboBox();
        jComboBox.addItem("管理员登录");
        jComboBox.addItem("老师登录");
        jComboBox.addItem("学生登录");
        jLabel_privilege.setBounds(240, 330, 406, 28);
        jComboBox.setBounds(370, 325, 140, 34);


        // 新建jPanel面板
        jContentPane = new JPanel();
        jContentPane.setLayout(null);
        jContentPane.setBackground(new Color(255, 255, 255));
        jContentPane.add(jLabel_userName, null);
        jContentPane.add(jLabel_password, null);
        jContentPane.add(jButton21, null);
        jContentPane.add(jButton22, null);
        jContentPane.add(jTextField, null);
        jContentPane.add(jPasswordField, null);
        jContentPane.add(jComboBox, null);
        jContentPane.add(jLabel_privilege, null);
        jContentPane.add(jLabel_title, null);
        jContentPane.add(jLabel_NCHU, null);
        jContentPane.add(imageLabel, null);
        setContentPane(jContentPane);
        btl = new BtnListener();
        jButton21.addActionListener(btl);
        jButton22.addActionListener(btl);
    }

    /**
     * @监听类
     */
    public class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jButton21) {
                //用户输入数据与 数据库交互 ud
                UserDao ud = new UserDao();
                String user = jTextField.getText().trim();
                String password = new String(jPasswordField.getPassword()).trim();//char to String

                storeUserName = user;
                storeUserPassword = password;
                login_user_type = jComboBox.getSelectedIndex();

                if ("".equals(user)) {
                    JOptionPane.showMessageDialog(null, "用户名不能为空");
                    return;
                }
                if ("".equals(password)) {
                    JOptionPane.showMessageDialog(null, "密码不能为空");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dt = sdf.format(new java.util.Date());

                //如果是管理员，可以直接使用账户登录
                if (login_user_type == 0) {
                    if (ud.userLogin(login_user_type, storeUserName, storeUserPassword)) {
                        dispose();
                        MainFrame mf = new MainFrame();
                        mf.setTypeOfUser(0);
                        mf.setUserName(storeUserName);
                        mf.setVisible(true);
                        JOptionPane.showMessageDialog(null, "欢迎 " + user + "登陆！", "关于学生信息管理系统", JOptionPane.INFORMATION_MESSAGE);
                        storeUserId = ud.getUserIdByUserName(storeUserName);
                        String log_operate = "[" + storeUserName + "]" + "管理员登陆系统";
                        JDBCUtil.update("insert into c_log(login_user,log_operate,log_time) values('" + storeUserName + "','" + log_operate + "','" + dt + "')");
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败","错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //教师登录
                } else if (login_user_type == 1) {
                    if (ud.userLogin(login_user_type, storeUserName, storeUserPassword)) {
                        dispose();
                        MainFrame mf = new MainFrame();
                        mf.setTypeOfUser(1);
                        mf.setUserName(storeUserName);
                        mf.setVisible(true);
                        JOptionPane.showMessageDialog(null, "欢迎 " + storeUserName + "号教师登陆！", "关于学生信息管理系统", JOptionPane.INFORMATION_MESSAGE);
                        String log_operate = "[" + storeUserName + "]号教师" + "用户登陆系统";
                        JDBCUtil.update("insert into c_log(login_user,log_operate,log_time) values('" + storeUserName + "','" + log_operate + "','" + dt + "')");

                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败","错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else if (login_user_type == 2) {
                    //学生登录
                    if (ud.userLogin(login_user_type, storeUserName, storeUserPassword)) {
                        dispose();
                        MainFrame mf = new MainFrame(Integer.parseInt(storeUserName));
                        mf.studentID=Integer.parseInt(storeUserName);
                        mf.setTypeOfUser(2);
                        mf.setUserName(storeUserName);
                        mf.setVisible(true);
                        JOptionPane.showMessageDialog(null, "欢迎 " + user + "号学生登陆！", "关于学生信息管理系统", JOptionPane.INFORMATION_MESSAGE);
                        String log_operate = "[" + storeUserName + "]" + "号学生登陆系统";
                        JDBCUtil.update("insert into c_log(login_user,log_operate,log_time) values('" + storeUserName + "','" + log_operate + "','" + dt + "')");
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败","错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } else if (e.getSource() == jButton22) {
                System.exit(0);
            }
        }
    }
}