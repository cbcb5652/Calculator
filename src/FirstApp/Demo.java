package FirstApp;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class Demo extends JFrame{//让Demo类成为JFrame的子类
	public Demo()
	{
		setTitle("简易记事本");//设置窗体标题
		setBounds(100,100,400,400);//设置窗体的坐标，大小
		setDefaultCloseOperation(EXIT_ON_CLOSE);//设置窗体关闭规则，关闭窗口时关闭程序
		Container c=getContentPane();//获取窗体的容器
		c.setLayout(new FlowLayout());//给容器设置流布局
		JComboBox<String> comboBox1=new JComboBox<>();//创建下拉列表
		String select[]= {"新建","打开","保存","另存","退出"};
	//	comboBox1.setBounds(15,15,80,40);
		comboBox1.setModel(new DefaultComboBoxModel(select));
		 c.add(comboBox1);
		 
		 JComboBox<String> comboBox2=new JComboBox<>();//创建下拉列表
			comboBox2.addItem("编辑");//向下拉列表添加数据
			comboBox2.addItem("复制");//向下拉列表添加数据
			comboBox2.addItem("粘贴");//向下拉列表添加数据
			comboBox2.addItem("删除");//向下拉列表添加数据
			comboBox2.addItem("剪切");//向下拉列表添加数据
			comboBox2.addItem("查找");//向下拉列表添加数据
			comboBox2.addItem("替换");//向下拉列表添加数据
		//	comboBox2.setBounds(100,15,80,40);
			 c.add(comboBox2);
			 
				JTextArea area=new JTextArea();
			    area.setRows(10);//设定文本域的长
		area.setColumns(20);//设定列数	
		area.setFont(new Font("楷体",Font.PLAIN,20));//字体，字体类型，字体大小
	
	JScrollPane js=new JScrollPane(area);//给文本域添加滚动条
	c.add(js);//添加滚动面板对象
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 改用时间监听了，监听按钮的属性
	 */
	comboBox1.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getSource().toString());//这里给你打印你可以看下字符串里面包含什么。
				if(e.getSource().toString().contains(select[0]))//获取时间监听的字符串,判断是佛偶包含这个字符串
				{
					System.out.println("Good");
				}
				//.....下面多加几个判断就能监听所有的按钮拉。
				//加油，
				
		}
	});
		
	
	
	
	
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
      new Demo();
		
	}

}
