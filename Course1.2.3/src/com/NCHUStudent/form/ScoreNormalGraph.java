package com.NCHUStudent.form;
import javax.swing.*;
import java.awt.*;

/**
 * 绘制分数所在区间的柱状图
 * @author  李茂田
 * @since 2024-7-18 20:01:18
 */
class BarChart extends JPanel {
    public BarChart () {
    }

    public BarChart (int[] data, int amount) {
        this.data = data;
        this.amount = amount;
    }

    private int[] data;
    private int amount=0;

    public int[] getData () {
        return data;
    }

    public void setData (int[] data) {
        this.data = data;
    }

    public int getAmount () {
        return amount;
    }

    public void setAmount (int amount) {
        this.amount = amount;
    }


    public BarChart(int[] data) {
        super();
        this.data = data;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 获取面板的尺寸
        int width = getWidth();
        int height = getHeight();
        // 计算每个柱子的宽度
        int barWidth = width / data.length;
        // 找到数据中的最大值，以便缩放柱状图
        int maxValue = 0;
        for (int value : data) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        // 绘制每个柱子
        for (int i = 0; i < data.length; i++) {
            g.setColor(new Color(155, 149, 201));
            //g.setColor(Color.BLUE);
            // 计算柱子的位置和高度
            int x = i * barWidth;
            int barHeight = (int) ((double) data[i] / maxValue * height); // 缩放数据以适应面板高度
            int y = height - barHeight; // 计算柱子顶部位置
            // 绘制柱子
            g.fillRect(x, y, barWidth - 1, barHeight);
            // 添加数据的下标
            if (data[i]>0){
                String str=null;
                String pencentage=null;
                switch (i){
                    case 0:str="0-40";break;
                    case 1:str="40-60";break;
                    case 2:str="60-70";break;
                    case 3:str="70-80";break;
                    case 4:str="80-90";break;
                    case 5:str="90-100";break;
                }
                FontMetrics fm = g.getFontMetrics();
                int labelWidth = fm.stringWidth(str);
                g.setColor(Color.orange);
                g.setFont(new Font("Cambria", Font.BOLD, 28));
                //0.02222
                double temp=data[i]/(double)amount*100;
                pencentage=String.format("%.1f" ,temp)+ "%";
                int pWidth = fm.stringWidth(pencentage);
                g.drawString(str, x + (barWidth - labelWidth) / 2, 390);
                //y为柱子顶部底的高度
                g.setFont(new Font("Cambria", Font.BOLD, 25));
                y+=fm.stringWidth(pencentage);
                if (y-50<=0){
                }else {
                    y-=50;
                }
                g.setColor(new Color(241,115,172));
                g.drawString(pencentage, x + (barWidth - pWidth) / 2, y);
            }
        }
    }

    public static JFrame creatChart(int[] data) {
        //临时的对象
        BarChart barChart = new BarChart();
        int []range=barChart.dataProcess(data);
        int amount=0;
        for (int i = 0; i < range.length; i++) {
            amount+=range[i];
        }
        JFrame frame = new JFrame("成绩分布柱状图");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 450);
        barChart = new BarChart(range,amount);
        frame.add(barChart);
        frame.setVisible(true);
        return frame;
    }

    public int[] dataProcess(int []data){
        this.setAmount(data.length);
        //1-40 40-60 60-70 70-80 80-90 90-100
        int []range=new int[6];
        for (Integer each:data){
            if (each>0&&each<=40){
                range[0]++;
                continue;
            }
            else if (each>40&&each<=60){
                range[1]++;
                continue;
            }
            else if (each>60&&each<=70){
                range[2]++;
                continue;
            }
            else if (each>70&&each<=80){
                range[3]++;
                continue;
            }
            else if (each>80&&each<=90){
                range[4]++;
                continue;
            }
            else if (each>90&&each<=100){
                range[5]++;
                continue;
            }
            else if (each>90&&each<=100){
                range[6]++;
                continue;
            }
        }
        return  range;
    }
}
