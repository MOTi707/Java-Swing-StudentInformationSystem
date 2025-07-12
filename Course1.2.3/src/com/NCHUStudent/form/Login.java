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
    public static String storeUserName = null;// ��¼�û���
    public static String storeUserPassword = null;// ��¼����
    static int storeUserId;// ��¼�û���
    static boolean RELOAD = true;// ���µ�½���
    static int login_user_type;//0��ʾ����Ա��1��ʾ��ʦ��2��ʾѧ��
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
     * @������
     */
    public static void main(String[] args) {
        Login login = new Login(RELOAD);
        login.setVisible(true);
    }

    //����ͼƬ
    private static void loadImageAndDisplay(JLabel label, String imagePath) {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            // �Զ���ֱ��� ����
            int labelWidth = 800;
            int labelHeight = 544;
            // ����ͼƬ����Ӧ JLabel �Ĵ�С
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
        this.setTitle("�û���½");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //ʹ��Windows��look��feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// ʹ��windows���
        } catch (Exception e) {
            e.printStackTrace();
        }
        //��ʾͼƬ
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        URL resource = this.getClass().getResource("../images/NCHU.jpg");
        ImageIcon icon = new ImageIcon(resource);
        imageLabel.setIcon(icon);
        imageLabel.setBounds(0, 0, 800, 544);

        //��������
        jLabel_title = new JLabel("ѧ����Ϣ����ϵͳ");
        Font font5 = new Font("����", Font.PLAIN, 70);
        jLabel_title.setFont(font5);
        // ����ʹ jLabel_title ˮƽ���е�λ��
        int windowWidth = getWidth(); // ��ȡ���ڿ��
        int titleLabelWidth = jLabel_title.getPreferredSize().width; // ��ȡ jLabel_title �Ŀ��
        int x = (windowWidth - titleLabelWidth) / 2;
        int y = 20;
        jLabel_title.setBounds(x, y, titleLabelWidth, 70); // 70 �������С
        jLabel_NCHU = new JLabel("NCHU All Rights Reserved");
        jLabel_NCHU.setFont(new Font("����", Font.PLAIN, 22));
        jLabel_NCHU.setBounds(new Rectangle(350, 100, 400, 20));

        //��¼��ť �˳���ť
        jButton21 = new JButton();
        jButton21.setBounds(new Rectangle(150, 420, 200, 50));
        jButton21.setText("��¼");
        getRootPane().setDefaultButton(jButton21);// �س���¼
        jButton22 = new JButton();
        jButton22.setBounds(new Rectangle(450, 420, 200, 50));
        jButton22.setText("�˳�");
        Font buttonFont = new Font("���ĺ���", Font.BOLD, 35);
        jButton21.setFont(buttonFont);
        jButton22.setFont(buttonFont);

        //�û���
        jLabel_userName = new JLabel();
        jLabel_userName.setText("�û�����");
        Font font2 = new Font("����", Font.PLAIN, 35);
        jLabel_userName.setFont(font2);
        jTextField = new JTextField(40);

        double widthUsernameText = jLabel_userName.getPreferredSize().getWidth();
        double widthUserinput = jTextField.getPreferredSize().getWidth();
        double gap1 = 50;
        double x1 = (windowWidth - (widthUsernameText + widthUserinput + gap1)) / 2.0;
        double x2 = x1 + gap1 + widthUsernameText;
        jLabel_userName.setBounds(new Rectangle((int) x1, 150, 150, 50));
        jTextField.setBounds(new Rectangle((int) x2, 150, 250, 40));

        //����
        jPasswordField = new JPasswordField(30);
        jLabel_password = new JLabel();
        jLabel_password.setText("��  �룺");
        Font font = new Font("����", Font.PLAIN, 35); // �������ƣ���ʽ����С
        jLabel_password.setFont(font);
        jLabel_password.setBounds(new Rectangle((int) x1, 240, 159, 50));
        jPasswordField.setBounds(new Rectangle((int) x2, 240, 250, 40));

        //��¼���� ��ѡ��
        jLabel_privilege = new JLabel();
        jLabel_privilege.setText("��¼���ͣ�");
        Font font4 = new Font("����", Font.PLAIN, 20);
        jLabel_privilege.setFont(font4);
        jComboBox = new JComboBox();
        jComboBox.addItem("����Ա��¼");
        jComboBox.addItem("��ʦ��¼");
        jComboBox.addItem("ѧ����¼");
        jLabel_privilege.setBounds(240, 330, 406, 28);
        jComboBox.setBounds(370, 325, 140, 34);


        // �½�jPanel���
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
     * @������
     */
    public class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jButton21) {
                //�û����������� ���ݿ⽻�� ud
                UserDao ud = new UserDao();
                String user = jTextField.getText().trim();
                String password = new String(jPasswordField.getPassword()).trim();//char to String

                storeUserName = user;
                storeUserPassword = password;
                login_user_type = jComboBox.getSelectedIndex();

                if ("".equals(user)) {
                    JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
                    return;
                }
                if ("".equals(password)) {
                    JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dt = sdf.format(new java.util.Date());

                //����ǹ���Ա������ֱ��ʹ���˻���¼
                if (login_user_type == 0) {
                    if (ud.userLogin(login_user_type, storeUserName, storeUserPassword)) {
                        dispose();
                        MainFrame mf = new MainFrame();
                        mf.setTypeOfUser(0);
                        mf.setUserName(storeUserName);
                        mf.setVisible(true);
                        JOptionPane.showMessageDialog(null, "��ӭ " + user + "��½��", "����ѧ����Ϣ����ϵͳ", JOptionPane.INFORMATION_MESSAGE);
                        storeUserId = ud.getUserIdByUserName(storeUserName);
                        String log_operate = "[" + storeUserName + "]" + "����Ա��½ϵͳ";
                        JDBCUtil.update("insert into c_log(login_user,log_operate,log_time) values('" + storeUserName + "','" + log_operate + "','" + dt + "')");
                    } else {
                        JOptionPane.showMessageDialog(null, "��¼ʧ��","����", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //��ʦ��¼
                } else if (login_user_type == 1) {
                    if (ud.userLogin(login_user_type, storeUserName, storeUserPassword)) {
                        dispose();
                        MainFrame mf = new MainFrame();
                        mf.setTypeOfUser(1);
                        mf.setUserName(storeUserName);
                        mf.setVisible(true);
                        JOptionPane.showMessageDialog(null, "��ӭ " + storeUserName + "�Ž�ʦ��½��", "����ѧ����Ϣ����ϵͳ", JOptionPane.INFORMATION_MESSAGE);
                        String log_operate = "[" + storeUserName + "]�Ž�ʦ" + "�û���½ϵͳ";
                        JDBCUtil.update("insert into c_log(login_user,log_operate,log_time) values('" + storeUserName + "','" + log_operate + "','" + dt + "')");

                    } else {
                        JOptionPane.showMessageDialog(null, "��¼ʧ��","����", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else if (login_user_type == 2) {
                    //ѧ����¼
                    if (ud.userLogin(login_user_type, storeUserName, storeUserPassword)) {
                        dispose();
                        MainFrame mf = new MainFrame(Integer.parseInt(storeUserName));
                        mf.studentID=Integer.parseInt(storeUserName);
                        mf.setTypeOfUser(2);
                        mf.setUserName(storeUserName);
                        mf.setVisible(true);
                        JOptionPane.showMessageDialog(null, "��ӭ " + user + "��ѧ����½��", "����ѧ����Ϣ����ϵͳ", JOptionPane.INFORMATION_MESSAGE);
                        String log_operate = "[" + storeUserName + "]" + "��ѧ����½ϵͳ";
                        JDBCUtil.update("insert into c_log(login_user,log_operate,log_time) values('" + storeUserName + "','" + log_operate + "','" + dt + "')");
                    } else {
                        JOptionPane.showMessageDialog(null, "��¼ʧ��","����", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } else if (e.getSource() == jButton22) {
                System.exit(0);
            }
        }
    }
}