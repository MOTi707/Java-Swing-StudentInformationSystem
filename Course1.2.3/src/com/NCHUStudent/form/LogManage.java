package com.NCHUStudent.form;

import com.NCHUStudent.dao.LogDao;
import com.NCHUStudent.pojo.LogModel;
import com.NCHUStudent.util.JDBCUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * 操作日记类
 * @author 李茂田
 * @since 2024-7-17 13:00:19
 */
public class LogManage extends JDialog {

    private static final long serialVersionUID = 1L;
    DefaultTableModel model = new DefaultTableModel();
    private JPanel jContentPane = null;

    private JButton jButton_empty = null;
    private JScrollPane jScrollPane = null;
    private JTable jTable = null;
    private JTextArea jTextArea = null;
    private JLabel topic=null;

    public LogManage() {
        super();
        initialize();
        showTableData();
        jButton_empty.addActionListener(new btnListener());

    }

    public static void main(String args[]) {
        LogManage logManage = new LogManage();
        logManage.setVisible(true);
    }

    private void initialize() {
        //this.setSize(new Dimension(600, 550));
        this.setSize(new Dimension(700, 550));
        this.setTitle("日志管理");
        this.setModal(true);//设置对话框为模态
        this.setLocation(new Point(0, 0));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        topic=new JLabel("操作日记管理");
        topic.setFont(new Font("宋体", Font.PLAIN, 35));
        topic.setBounds(new Rectangle(210, 10, 250, 40));

        jButton_empty = new JButton();
        jButton_empty.setText("清空全部日志");
        jButton_empty.setFont(new Font("宋体", Font.PLAIN, 28));
        jButton_empty.setBounds(new Rectangle(10, 420, 480, 50));

        jTextArea = new JTextArea();
        jTextArea.setBounds(new Rectangle(510, 150, 165, 150));
        jTextArea.setFont(new Font("宋体", Font.PLAIN, 25));
        jTextArea.setBackground(SystemColor.control);
        jTextArea.setTabSize(6);
        jTextArea.setText("  请使用鼠标点击或键盘↑↓键查看左边列表的详情！");
        jTextArea.setEditable(false);
        jTextArea.setAutoscrolls(true);
        jTextArea.setLineWrap(true);
		//以上为提示文字

        jTable = new JTable();
        jScrollPane = new JScrollPane();
        jScrollPane.setBounds(new Rectangle(15, 60, 485, 330));
        jScrollPane.setViewportView(jTable);

        jContentPane = new JPanel();
        jContentPane.setLayout(null);
        jContentPane.add(jButton_empty, null);
        jContentPane.add(jScrollPane, null);
        jContentPane.add(jTextArea, null);
        jContentPane.add(topic,null);
        this.setContentPane(jContentPane);
    }

    // 显示数据
    public void showTableData() {
		LogDao ld = new LogDao();
        List<LogModel> lists = ld.getLists();
        String heads[] = { "用户ID", "操作", "操作时间"};
        model = new DefaultTableModel(null, heads);
        model.setRowCount(lists.size());
        try {
            for (int i = 0; i < lists.size(); i++) {
                String[] times = lists.get(i).getLog_time().toString().split(" ");
                model.setValueAt(lists.get(i).getLogin_user(), i, 0);
                model.setValueAt(lists.get(i).getOperate(), i, 1);
                //model.setValueAt(times[0], i, 2);//只显示年月日
                model.setValueAt(lists.get(i).getLog_time(), i, 2);//显示日期+时间
            }
        }
        catch (Exception e) {
        }
        jTable.setModel(model);
        jTable.setFont(new Font("宋体", Font.PLAIN, 16));
		//c0.setWidth(15);
        jTable.setRowHeight(27);
        TableColumn c0 = jTable.getColumnModel().getColumn(0);
        TableColumn c1 = jTable.getColumnModel().getColumn(1);
        TableColumn c2 = jTable.getColumnModel().getColumn(2);
        c0.setPreferredWidth(15);
        c1.setPreferredWidth(65);
        c2.setPreferredWidth(45);
    }



	public void refresh(){
		this.dispose();
		LogManage logManage = new LogManage();
		logManage.setVisible(true);
	}

    public class btnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jButton_empty) {
                Object[] options = {"确认", "取消"};

                if (JOptionPane.showOptionDialog(null, "警告，重要日志删除，你确定要删除吗？",
						"删除警告",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0])
                        == JOptionPane.OK_OPTION)
                {
                    String sql = "truncate table c_log";
                    JDBCUtil.update(sql);
					refresh();
                }
                else {
                    return;
                }
            }
        }
    }
} 
