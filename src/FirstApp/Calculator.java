package FirstApp;


import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;//导入字符串分割器

import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
	public static ArrayList<String> list;
	private Dialog dialog;
	public  static boolean vbegin = true;// 控制输入，true为重新输入，false为接着输入
	public  static boolean equals_flag = true;// true为未输入=，false表示输入=
	public  static boolean isContinueInput = true;// true为正确，可以继续输入，false错误，输入锁定
	//什么按钮并给具体符号
	static final String[] Line1 = { "e", "sin", "%", "^", "C", "←", "/" };
	static final String[] Line2 = { "!", "cos", "√", "7", "8", "9", "×" };
	static final String[] Line3 = { "In", "tan", "x²", "4", "5", "6", "-" };
	static final String[] Line4 = { "π", "e^x", "x³", "1", "2", "3", "+" };
	static final String[] Line5 = { "(", ")", "1/x", "+/-", "0", ".", "=" };
	static JButton line1[] = new JButton[Line1.length];
	static JButton line2[] = new JButton[Line2.length];
	static JButton line3[] = new JButton[Line3.length];
	static JButton line4[] = new JButton[Line4.length];
	static JButton line5[] = new JButton[Line5.length];
	public static JTextField resultText1 = new JTextField("");// 算式文本框
	public static  JTextField resultText = new JTextField("0");// 计算文本框
	final static int MAXLEN = 500;
	final static  double PI = Math.PI;//定义常数以及循环时所用变量
	final static double e=Math.E;//定义e常数
	public static int a = 2, num = 0,count=10;//定义函数中所用到的标志
	
	public Calculator(int a) // 构造方法设置布局、为按钮注册事件监听器
	{
		//初始化计算器并规定大小及位置
		super("My Calculator");
		this.a = a;
		this.setBackground(Color.LIGHT_GRAY);
		this.setTitle("计算器ios11.3：计科一班陈彬。！！请注意下面的(选项)和(计算模式)");
		this.setLocation(350, 150);
		this.setSize(700, 500);
		this.setResizable(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // 窗口不能直接关闭
		setVisible(true);
		addWindowListener(new WindowAdapter() {//弹出提示窗口询问是否确认关闭
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(null, "确定退出? ", "确定？", 2) == 0) {
					System.exit(0);
				}
			}
		});

		this.setLayout(new GridLayout(8, 1));//申明容器为七行一列 
		this.addmyMenu(); // 调用成员方法添加菜单
		list = new ArrayList<String>();
		
		resultText.setFont(new Font("宋体", Font.BOLD, 18));
		resultText1.setFont(new Font("宋体", Font.BOLD, 25));
		resultText1.setEditable(false);//规定文本框无法编辑
		resultText.setEditable(false);
		resultText1.setHorizontalAlignment(JTextField.RIGHT);
		resultText.setHorizontalAlignment(JTextField.RIGHT);
		this.add(resultText);
		this.add(resultText1);
		//以下为对面板的初始化及规定颜色字体
		JPanel LinePanel1 = new JPanel();
		LinePanel1.setLayout(new GridLayout(1, 5, 3, 3));
		for (int i = a; i < Line1.length; i++) {
			line1[i] = new JButton(Line1[i]);
			LinePanel1.add(line1[i]);
			line1[i].setBackground(Color.LIGHT_GRAY);
			line1[i].setFont(new Font("黑体", Font.PLAIN, 20));
		}
		JPanel LinePanel2 = new JPanel();
		LinePanel2.setLayout(new GridLayout(1, 5, 3, 3));
		for (int i = a; i < Line2.length; i++) {
			line2[i] = new JButton(Line2[i]);
			LinePanel2.add(line2[i]);
			line2[i].setBackground(Color.white);
			line2[i].setFont(new Font("黑体", Font.BOLD, 25));
		}
		JPanel LinePanel3 = new JPanel();
		LinePanel3.setLayout(new GridLayout(1, 5, 3, 3));
		for (int i = a; i < Line3.length; i++) {
			line3[i] = new JButton(Line3[i]);
			LinePanel3.add(line3[i]);
			line3[i].setBackground(Color.white);
			line3[i].setFont(new Font("黑体", Font.BOLD, 25));
		}
		JPanel LinePanel4 = new JPanel();
		LinePanel4.setLayout(new GridLayout(1, 5, 3, 3));
		for (int i = a; i < Line4.length; i++) {
			line4[i] = new JButton(Line4[i]);
			LinePanel4.add(line4[i]);
			line4[i].setBackground(Color.white);
			line4[i].setFont(new Font("黑体", Font.BOLD, 25));
		}
		JPanel LinePanel5 = new JPanel();
		LinePanel5.setLayout(new GridLayout(1, 5, 3, 3));
		for (int i = a; i < Line5.length; i++) {
			line5[i] = new JButton(Line5[i]);
			LinePanel5.add(line5[i]);
			line5[i].setBackground(Color.white);
			line5[i].setFont(new Font("黑体", Font.BOLD, 25));
		}
		for (int i = a; i < 7; i++) {
			if (a == 0 && i == 0 || i == 1 || i == 2 || i == 6) {
				line2[i].setBackground(Color.lightGray);
				line3[i].setBackground(Color.lightGray);
				line4[i].setBackground(Color.lightGray);
				line5[i].setBackground(Color.lightGray);
				line2[i].setFont(new Font("黑体", Font.PLAIN, 20));
				line3[i].setFont(new Font("黑体", Font.PLAIN, 20));
				line4[i].setFont(new Font("黑体", Font.PLAIN, 20));
				line5[i].setFont(new Font("黑体", Font.PLAIN, 20));
			} else if (a == 2 && i == 2 || i == 6) {
				line2[i].setBackground(Color.lightGray);
				line3[i].setBackground(Color.lightGray);
				line4[i].setBackground(Color.lightGray);
				line5[i].setBackground(Color.lightGray);
				line2[i].setFont(new Font("黑体", Font.PLAIN, 20));
				line3[i].setFont(new Font("黑体", Font.PLAIN, 20));
				line4[i].setFont(new Font("黑体", Font.PLAIN, 20));
				line5[i].setFont(new Font("黑体", Font.PLAIN, 20));
			}
			line3[2].setFont(new Font("宋体", Font.PLAIN, 20));
			line4[2].setFont(new Font("宋体", Font.PLAIN, 20));
		}
		//为每个按钮添加事件监听器
		for (int i = a; i < Line1.length; i++) {
			line1[i].addActionListener(this);
			line2[i].addActionListener(this);
			line3[i].addActionListener(this);
			line4[i].addActionListener(this);
			line5[i].addActionListener(this);

		}

		this.add(LinePanel1);
		this.add(LinePanel2);
		this.add(LinePanel3);
		this.add(LinePanel4);
		this.add(LinePanel5);
		
		this.setVisible(true);

	}

	private void addmyMenu() // 菜单的添加及颜色字体的设置
	{
		JMenuBar menubar = new JMenuBar();
		this.add(menubar);
		JMenu m1 = new JMenu("选项");
		m1.setFont(new Font("黑体", Font.CENTER_BASELINE, 15));
		JMenu m2 = new JMenu("计算模式");
		m2.setFont(new Font("黑体", Font.CENTER_BASELINE, 15));
		JMenuItem m1_exit = new JMenuItem("标准计算器");
		m1_exit.setFont(new Font("黑体", Font.CENTER_BASELINE, 20));
		JMenuItem m1_exit2 = new JMenuItem("科学计算器");
		m1_exit2.setFont(new Font("黑体", Font.CENTER_BASELINE, 20));
		m1_exit.addActionListener(this);
		m1_exit2.addActionListener(this);
		JMenuItem m2_ejz = new JMenuItem("二进制");
		m2_ejz.setFont(new Font("黑体", Font.CENTER_BASELINE, 17));
		m2_ejz.addActionListener(this);
		JMenuItem m2_bjz = new JMenuItem("八进制");
		m2_bjz.setFont(new Font("黑体", Font.CENTER_BASELINE, 17));
		m2_bjz.addActionListener(this);
		JMenuItem m2_sjz = new JMenuItem("十进制");
		m2_sjz.setFont(new Font("黑体", Font.CENTER_BASELINE, 17));
		m2_sjz.addActionListener(this);
		JMenuItem m2_sljz = new JMenuItem("十六进制");
		m2_sljz.setFont(new Font("黑体", Font.CENTER_BASELINE, 17));
		m2_sljz.addActionListener(this);

		JMenu m3 = new JMenu("帮助");
		m3.setFont(new Font("黑体", Font.CENTER_BASELINE, 15));
		JMenuItem m3_Help = new JMenuItem("用法");
		m3_Help.setFont(new Font("黑体", Font.CENTER_BASELINE, 15));
		m3_Help.addActionListener(this);
	
		m1.add(m1_exit2);//把菜单按钮添加到菜单中去
		m1.add(m1_exit);
		menubar.add(m1);
		m2.add(m2_ejz);
		m2.add(m2_bjz);
		m2.add(m2_sjz);
		m2.add(m2_sljz);
		menubar.add(m2);
		m3.add(m3_Help);
		menubar.add(m3);
	}

	public void actionPerformed(ActionEvent e) // 按钮的单击事件处理方法
	{
		String label = e.getActionCommand();//按了删除键后的处理
		if (label.equals(Line1[5])) {
			HandleBackspace.HandleBackspace();
		} else if (label.equals(Line1[4])) {//按了C键时候的处理
			list.clear();//把Handle函数里面的list列表清0
			resultText.setText("0");//重置resultText文本框和resultText1文本框
			resultText1.setText("");//并把vbegin，equals_flag参数也设为true防止把0添加进去。
			vbegin = true;
			equals_flag = true;
		}  else if ("x²x³%".indexOf(label) != -1||"e^x".equals(label)||"1/x".equals(label)||"+/-".equals(label)) {

			Operator op=new Operator(label);
		}
		//下面为对菜单栏上进制的处理
		else if("二进制".equals(label)||"十进制".equals(label)||"八进制".equals(label)||"十六进制".equals(label)) {
			this.setTitle(label);
			Hexadecimal hl=new Hexadecimal(label);
		}
		
		 else if (label == "科学计算器") // 选项中切换到科学计算器的处理方法
		{
			this.dispose();//隐藏之前的面板
			Calculator calculator = new Calculator(0);//给构造方法一个初始值改变面板大小
		} else if (label == "标准计算器") {
			this.dispose();
			Calculator calculator = new Calculator(2);
		} else if (label == "用法") // 按下'帮助'菜单栏中用法的处理方法
		{
			JDialog dialog = new JDialog();//弹出Jdiolog窗口提示信息
	        JLabel label1 = new JLabel("<html><body>1.计算器可以进行进制计算，但不支持十进制以外的小数点运算<br>2.该计算器可以切换科学计算器<br>3.该计算器已经限制了运算符的输入<br>4.计算模式会把文本框上的数字转换为相应进制的数字<br>5.在√前面的数字表示开几次方，后面表示它的开方数</p></body></html>");//JLabel组件
	        label1.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
	        dialog.add(label1);
	        dialog.setTitle("提示");
	        dialog.setSize(400, 250);
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
			dialog.setLocation(400, 250);
		} else // 各运算符的识别
		{
			Handle hd=new Handle(label);
		}
	}
	public static void main(String args[]) {
		Calculator calculator = new Calculator(2);//调用时传入参数，切换为参数值为2的面板

	}
}

