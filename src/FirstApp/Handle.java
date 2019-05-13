package FirstApp;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Handle {

	public Handle(String key)//算式的处理
	{
		String text = Calculator.resultText.getText();
		if (Calculator.equals_flag == false) { // && "πe0123456789.()+-*/^√sin".indexOf(key) != -1
			Calculator.list.add(text);// true为未输入=，false表示输入=
			Calculator.vbegin = false;// 控制输入，true为重新输入，false为接着输入
		}

		if (!Calculator.list.isEmpty()) {
			TipChecker(Calculator.list.get(Calculator.list.size() - 1), key);
		} else {
			TipChecker("#", key);
		}
		if (Calculator.isContinueInput && "πe0123456789.()+-×/^√sincostanIn".indexOf(key) != -1) {// true为正确，可以继续输入，false错误，输入锁定
			Calculator.list.add(key);
		}

		// 若输入正确，则将输入信息显示到显示器上
		if (Calculator.isContinueInput && "πe0123456789.()+-×/^√sincostan!In".indexOf(key) != -1) {
			if (Calculator.equals_flag == false && ("+-×/^sincostan!".indexOf(key) != -1)) {
				Calculator.vbegin = false;
				Calculator.equals_flag = true;
				printText(key);
			} else if (Calculator.equals_flag == false && ("πe0123456789.()".indexOf(key) != -1)) {
				Calculator.vbegin = true;
				Calculator.equals_flag = true;
				printText(key);
			} else {
				printText(key);
			}

		} else if (Calculator.isContinueInput && Calculator.equals_flag && key.equals("=")) {
			Calculator.isContinueInput = false;// 表明不可以继续输入
			Calculator.equals_flag = false;// 表明已经输入=
			Calculator.vbegin = true;// 重新输入标志设置true
			process(Calculator.resultText.getText()); // 整个程序的核心，计算表达式的值并显示
			Calculator.list.clear();
		}
		Calculator.isContinueInput = true;
	}

	private void printText(String key) {
		if (Calculator.vbegin) {
			Calculator.resultText.setText(key);// 清屏后输出
			// firstDigit = false;
		} else {
			Calculator.resultText.setText(Calculator.resultText.getText() + key);
		}
		Calculator.vbegin = false;
	}

	private void TipChecker(String tipcommand1, String tipcommand2) {
		// Tipcode1表示错误类型，Tipcode2表示名词解释类型
		int Tipcode1 = 0, Tipcode2 = 0;
		// 表示命令类型
		int tiptype1 = 0, tiptype2 = 0;
		// 括号数
		int bracket = 0;
		// “+-*/ ^”不能作为第一位
		//compareTo如果前面的值大就返回1，后面大返回-1，相等返回0
		if (tipcommand1.compareTo("#") == 0 && (tipcommand2.compareTo("/") == 0 || tipcommand2.compareTo("×") == 0
				|| tipcommand2.compareTo("+") == 0 || tipcommand2.compareTo(")") == 0
				|| tipcommand2.compareTo("^") == 0||tipcommand2.compareTo("√")==0)){
			Tipcode1 = -1;
		}
		// 定义存储字符串中最后一位的类型
		else if (tipcommand1.compareTo("#") != 0) {
			if (tipcommand1.compareTo("(") == 0) {
				tiptype1 = 1;
			} else if (tipcommand1.compareTo(")") == 0) {
				tiptype1 = 2;
			} else if (tipcommand1.compareTo(".") == 0) {
				tiptype1 = 3;
			} else if ("0123456789".indexOf(tipcommand1) != -1) {
				tiptype1 = 4;
			} else if ("+-×/".indexOf(tipcommand1) != -1) {
				tiptype1 = 5;
			} else if ("^√".indexOf(tipcommand1) != -1) {
				tiptype1 = 6;
			} else if ("πe".indexOf(tipcommand1) != -1) {
				tiptype1 = 7;
			}else if("sincostan".indexOf(tipcommand1)!=-1) {
				tiptype1 = 8;
			}else if("In".indexOf(tipcommand1)!=-1) {
				tiptype1=9;
			}
			// 定义欲输入的按键类型
			if (tipcommand2.compareTo("(") == 0) {
				tiptype2 = 1;
			} else if (tipcommand2.compareTo(")") == 0) {
				tiptype2 = 2;
			} else if (tipcommand2.compareTo(".") == 0) {
				tiptype2 = 3;
			} else if ("0123456789".indexOf(tipcommand2) != -1) {
				tiptype2 = 4;
			} else if ("+-×/".indexOf(tipcommand2) != -1) {
				tiptype2 = 5;
			} else if ("^√".indexOf(tipcommand2) != -1) {
				tiptype2 = 6;
			} else if ("πe".indexOf(tipcommand2) != -1) {
				tiptype2 = 7;
			}else if("sincostan".indexOf(tipcommand1)!=-1) {
				tiptype2 = 8;
			}else if("In".indexOf(tipcommand1)!=-1) {
				tiptype2=9;
			}

			switch (tiptype1) {
			case 1:
				// 左括号后面直接接右括号,“+×/”（负号“-”不算）,或者" ^√!"
				if (tiptype2 == 2 || (tiptype2 == 5 && tipcommand2.compareTo("-") != 0) || tiptype2 == 6||tipcommand2.compareTo("!") == 0)
					Tipcode1 = 1;
				break;
			case 2:
				// 右括号后面接左括号，数字，“+-×/^...πe”
				if (tiptype2 == 1 || tiptype2 == 3 || tiptype2 == 4 || tiptype2 == 7||tiptype2 == 8||tipcommand2.compareTo("sin") == 0||tipcommand2.compareTo("cos") == 0||tipcommand2.compareTo("tan") == 0||tipcommand2.compareTo("In") == 0)

					Tipcode1 = 2;
				break;
			case 3:
				// “.”后面接左括号，π
				if (tiptype2 == 1 || tiptype2 == 7||tiptype2 == 8)
					Tipcode1 = 3;
				// 连续输入两个“.”
				if (tiptype2 == 3)
					Tipcode1 = 8;
				break;
			case 4:
				// 数字后面直接接左括号和π
				if (tiptype2 == 1 || tiptype2 == 7||tipcommand2.compareTo("sin") == 0||tipcommand2.compareTo("cos") == 0||tipcommand2.compareTo("tan") == 0||tipcommand2.compareTo("In") == 0)
					Tipcode1 = 4;
				break;
			case 5:
				// “+-*/”后面直接接右括号，“+-×/ ^√!”
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6||tipcommand2.compareTo("-") == 0||tipcommand2.compareTo("!") == 0)
					Tipcode1 = 5;
				break;
			case 6:
				// “ ^”后面直接接右括号，“+-×/ ^πe”
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6 || tiptype2 == 7||tipcommand2.compareTo("!") == 0)
					Tipcode1 = 6;
				break;
			case 7:
				// "π"之后只能为"+-×/^)"不能为"π(.0123456789"
				if (tiptype2 == 1 || tiptype2 == 3 || tiptype2 == 4 || tiptype2 == 7||tiptype2 == 8||tipcommand2.compareTo("sin") == 0||tipcommand2.compareTo("cos") == 0||tipcommand2.compareTo("tan") == 0||tipcommand2.compareTo("In") == 0) {
					Tipcode1 = 7;
				}
				break;
			case 8:
				if( tiptype2 == 2|| tiptype2 == 3|| tiptype2 == 5|| tiptype2 == 6|| tiptype2 == 8) {
					Tipcode1 = 8;
				}
				break;
			case 9:
				if( tiptype2 == 2|| tiptype2 == 3|| tiptype2 == 5|| tiptype2 == 6|| tiptype2 == 8|| tiptype2 == 9)
					Tipcode1=9;
				break;
			}
		}
		if (Tipcode1 == 0 && tipcommand2.compareTo(".") == 0) {
			int tip_point = 0;
			for (int i = 0; i < Calculator.list.size(); i++) {
				// 若之前出现一个小数点点，则小数点计数加1
				if (Calculator.list.get(i).equals(".")) {
					tip_point++;
				}
				// 若出现以下几个运算符之一，小数点计数清零
				if (Calculator.list.get(i).equals("^") ||Calculator.list.get(i).equals("√")|| Calculator.list.get(i).equals("/") || Calculator.list.get(i).equals("×")
						|| Calculator.list.get(i).equals("-") ||Calculator. list.get(i).equals("+") || Calculator.list.get(i).equals("(")
						|| Calculator.list.get(i).equals(")")|| Calculator.list.get(i).equals("sin")|| Calculator.list.get(i).equals("cos")|| Calculator.list.get(i).equals("tan")|| Calculator.list.get(i).equals("!")||Calculator.list.get(i).equals("In")) {
					tip_point = 0;
				}
			}
			tip_point++;
			// 若小数点计数大于1，表明小数点重复了
			if (tip_point > 1) {
				Tipcode1 = 8;
			}
		}
		// 检测右括号是否匹配
		if (Tipcode1 == 0 && tipcommand2.compareTo(")") == 0) {
			int tip_right_bracket = 0;
			for (int i = 0; i < Calculator.list.size(); i++) {
				// 如果出现一个左括号，则计数加1
				if (Calculator.list.get(i).equals("(")) {
					tip_right_bracket++;
				}
				// 如果出现一个右括号，则计数减1
				if (Calculator.list.get(i).equals(")")) {
					tip_right_bracket--;
				}
			}
			// 如果右括号计数=0,表明没有响应的左括号与当前右括号匹配
			if (tip_right_bracket == 0) {
				Tipcode1 = 10;
			}

		}
		// 检查输入=的合法性
		if (Tipcode1 == 0 && tipcommand2.compareTo("=") == 0) {
			// 括号匹配数
			int tip_bracket = 0;
			for (int i = 0; i < Calculator.list.size(); i++) {
				if (Calculator.list.get(i).equals("(")) {
					tip_bracket++;
				}
				if (Calculator.list.get(i).equals(")")) {
					tip_bracket--;
				}
			}
			// 若大于0，表明左括号还有未匹配的
			if (tip_bracket > 0) {
				Tipcode1 = 9;
				bracket = tip_bracket;
			} else if (tip_bracket == 0) {
				// 若前一个字符是以下之一，表明=号不合法
				if ("+-×/".indexOf(tipcommand1) != -1) {
					Tipcode1 = 5;
				}
			}
		}

		if (Tipcode1 != 0) {
			Calculator.isContinueInput = false;// 表明不可以继续输入
		}
	}
	

	public void process(String str) {
		int weightPlus = 0, topOp = 0, topNum = 0, flag = 1, weightTemp = 0;
		// weightPlus为同一（）下的基本优先级，weightTemp临时记录优先级的变化
		// topOp为weight[]，operator[]的计数器；topNum为number[]的计数器
		// flag为正负数的计数器，1为正数，-1为负数
		int weight[]; // 保存operator栈中运算符的优先级，以topOp计数
		double number[]; // 保存数字，以topNum计数
		char ch, ch_gai, operator[];// operator[]保存运算符，以topOp计数
		String num;// 记录数字，str以+-*/() ! ^分段，+-*/() ^字符之间的字符串即为数字
		weight = new int[Calculator.MAXLEN];
		number = new double[Calculator.MAXLEN];
		operator = new char[Calculator.MAXLEN];
		String expression = str.replace("π", String.valueOf(Calculator.PI));// 将字符串中的π用PI
		expression=expression.replace("e", String.valueOf(Calculator.e));
	    expression=expression.replace("sin", "1s");
	    expression=expression.replace("cos", "2c");
	    expression=expression.replace("tan", "3t");
	    expression=expression.replace("!", "!4");
	    expression=expression.replace("In", "5I");
		// 以符号"+-×/()^√"为分隔符分割expression表达式
		StringTokenizer expToken = new StringTokenizer(expression, "+-×/()^√sct!I");
		int i = 0;
		while (i < expression.length()) {
			ch = expression.charAt(i);
			// 判断正负数
			if (i == 0) {
				if (ch == '-')
					flag = -1;
			} else if (expression.charAt(i - 1) == '(' && ch == '-')
				flag = -1;
			// 取得数字，并将正负符号转移给数字,E是科学计数
			if (ch <= '9' && ch >= '0' || ch == '.' || ch == 'E') {
				num = expToken.nextToken();// 分割后的StringTokenizer中的下一个索引数据
				ch_gai = ch;
				// 取得整个数字
				while (i < expression.length() && (ch_gai <= '9' && ch_gai >= '0' || ch_gai == '.' || ch_gai == 'E')) {
					ch_gai = expression.charAt(i++);
				}
				// 将指针退回之前的位置，即每个数字的末尾位置
				if (i >= expression.length())
					i -= 1;
				else {
					i -= 2;
				}
				if (num.compareTo(".") == 0)
					number[topNum++] = 0;
				// 将正负符号转移给数字
				else {
					if (Calculator.count == 2) {
						number[topNum++] = Change.changetotwo(num) * flag;
					} else if (Calculator.count == 8) {
						number[topNum++] = Change.changetoeight(num) * flag;
					} else if (Calculator.count == 16) {
						number[topNum++] = Change.changetosixteen(num) * flag;
					} else {
						number[topNum++] = Double.parseDouble(num) * flag;
						flag = 1;
					}
				}
			}
			// 计算运算符的优先级
			if (ch == '(')
				weightPlus += 4;
			if (ch == ')')
				weightPlus -= 4;
			if (ch == '-' && flag == 1 || ch == '+' || ch == '×' || ch == '/' || ch == '^'||ch=='√'||ch=='s'||ch=='c'||ch=='t'||ch=='!'||ch=='I') {

				switch (ch) {
				// +-的优先级最低，为1
				case '+':
				case '-':
					weightTemp = 1 + weightPlus;
					break;
				// x/的优先级稍高，为2
				case '×':
				case '/':
					weightTemp = 2 + weightPlus;
					break;
				case 's':
				case 'c':
				case 't':
				case '!':
				case 'I':
					weightTemp=4+weightPlus;
				
				default:
					weightTemp = 3 + weightPlus;
					break;
				}
				// 如果当前优先级大于堆栈顶部元素，则直接入栈
				if (topOp == 0 || weight[topOp - 1] < weightTemp) {
					weight[topOp] = weightTemp;
					operator[topOp] = ch;
					topOp++;
					// 否则将堆栈中运算符逐个取出，直到当前堆栈顶部运算符的优先级小于当前运算符
				} else {
					while (topOp > 0 && weight[topOp - 1] >= weightTemp) {
						switch (operator[topOp - 1]) {
						// 取出数字数组的相应元素进行运算
						case '+':
							number[topNum - 2] += number[topNum - 1];
							break;
						case '-':
							number[topNum - 2] -= number[topNum - 1];
							break;
						case '×':
							number[topNum - 2] *= number[topNum - 1];
							break;
						// 判断除数为0的情况
						case '/':
							if (number[topNum - 1] == 0) {
								// showError(1, str_old);
								return;
							}
							number[topNum - 2] /= number[topNum - 1];
							break;

						case '^':
							number[topNum - 2] = Math.pow(number[topNum - 2], number[topNum - 1]);
							break;
						case '√':
							number[topNum - 2] = Math.pow( number[topNum - 1],1/number[topNum - 2]);
							break;
							// 计算时进行角度弧度转换，并计算
						case 's':
							 double a = Math.toRadians(number[topNum - 1]);// 把输入的数字 转换成 度数
							 number[topNum -2]= FP( Math.sin(a));
							 break;
						case 'c':
							 a = Math.toRadians(number[topNum - 1]);// 把输入的数字 转换成 度数
							 number[topNum -2]= FP( Math.cos(a));
							 break;
						case 't':
							 a = Math.toRadians(number[topNum - 1]);// 把输入的数字 转换成 度数
							 number[topNum -2]= FP( Math.tan(a));
							number[topNum -2] =FP(Math.tan( Math.toRadians(number[topNum - 1])));// 把输入的数字 转换成 度数
							 break;
						case '!':
							number[topNum-2]=FP(jc( number[topNum -2]));
							break;
						case 'I':
							number[topNum-2]=FP(Math.log(number[topNum - 1]));
							break;
						
					
						}
						// 继续取堆栈的下一个元素进行判断
						topNum--;
						topOp--;
					}
					// 将运算符入堆栈
					weight[topOp] = weightTemp;
					operator[topOp] = ch;
					topOp++;
				}
			}
			i++;
		}
		// 依次取出堆栈的运算符进行运算
		while (topOp > 0) {
			// +-x直接将数组的后两位数取出运算
			switch (operator[topOp - 1]) {
			case '+':
				number[topNum - 2] += number[topNum - 1];
				break;
			case '-':
				number[topNum - 2] -= number[topNum - 1];
				break;
			case '×':
				number[topNum - 2] *= number[topNum - 1];
				break;
			// 涉及到除法时要考虑除数不能为零的情况
			case '/':
				if (number[topNum - 1] == 0) {
					// showError(1, str_old);
					return;
				}
				number[topNum - 2] /= number[topNum - 1];
				break;

			case '^':
				number[topNum - 2] = Math.pow(number[topNum - 2], number[topNum - 1]);
				break;
			case '√':
				number[topNum - 2] = Math.pow( number[topNum - 1],1/number[topNum - 2]);
				break;
			case 's':
				 double a = Math.toRadians(number[topNum - 1]);// 把输入的数字 转换成 度数
				 number[topNum -2]= FP( Math.sin(a));
				 break;
			case 'c':
				 a = Math.toRadians(number[topNum - 1]);// 把输入的数字 转换成 度数
				 number[topNum -2]= FP( Math.cos(a));
				 break;
			case 't':
				 a = Math.toRadians(number[topNum - 1]);// 把输入的数字 转换成 度数
				 number[topNum -2]= FP( Math.tan(a));
				number[topNum -2] =FP(Math.tan( Math.toRadians(number[topNum - 1])));// 把输入的数字 转换成 度数
				 break;
			case '!':
				number[topNum-2]=FP(jc( number[topNum -2]));
				break;
				
				
			
			}
			// 取堆栈下一个元素计算
			topNum--;
			topOp--;
		}
		// 如果是数字太大，提示错误信息
		if (number[0] > 7.3E306) {
			// showError(3, str_old);
			return;
		}
		// 输出最终结果
		if (Calculator.count == 2) {//判断为几进制模式进行最终打印的进制结果转换
			String str1,text;
			int local = (int) FP(number[0]);
			str1 = Integer.toBinaryString(local);
				
				Calculator.resultText.setText(Calculator.resultText.getText());
				Calculator.resultText1.setText(str1);
		} else if (Calculator.count == 8) {
			String str1,text;
			int local = (int) FP(number[0]);
			str1 = Integer.toOctalString(local);
				Calculator.resultText1.setText(str1);
		} else if (Calculator.count == 16) {
			String str1,text;
			int local = (int) FP(number[0]);
			str1 = Integer.toHexString(local);
				Calculator.resultText1.setText(str1);
		} else {
			String text ;
				Calculator.resultText1.setText(String.valueOf(FP(number[0])));
			
		}
	}
	public static int min(String str) {//传入算式字符串，并返回属于/×+-字符indexOf值最小的位置，也可以用于判断式子中是否有/×-+
		int min = 0;
	    if(str.indexOf("+")>0) {
	    	if(str.indexOf("-")>0) {
	    		min=str.indexOf("+")<str.indexOf("-")?str.indexOf("+"):str.indexOf("-");
	    	}
	    	if(str.indexOf("×")>0) {
	    		min=str.indexOf("+")<str.indexOf("×")?str.indexOf("+"):str.indexOf("×");
	    	}
	    	if(str.indexOf("/")>0) {
	    		min=str.indexOf("+")<str.indexOf("-")?str.indexOf("+"):str.indexOf("+");
	    	}
	    	if(str.indexOf("+")>0) {
	    		min=str.indexOf("+")<str.indexOf("+")?str.indexOf("+"):str.indexOf("+");
	    	}
	    }
	    if(str.indexOf("-")>0) {
	    	if(str.indexOf("×")>0) {
	    		min=str.indexOf("-")<str.indexOf("×")?str.indexOf("-"):str.indexOf("×");
	    	}
	    	if(str.indexOf("/")>0) {
	    		min=str.indexOf("-")<str.indexOf("/")?str.indexOf("-"):str.indexOf("/");
	    	}
	    	if(str.indexOf("+")>0) {
	    		min=str.indexOf("-")<str.indexOf("+")?str.indexOf("-"):str.indexOf("+");
	    	}
	    	if(str.indexOf("-")>0) {
	    		min=str.indexOf("-")<str.indexOf("-")?str.indexOf("-"):str.indexOf("-");
	    	}
	    }
	    if(str.indexOf("×")>0) {
	    	if(str.indexOf("+")>0) {
	    		min=str.indexOf("+")<str.indexOf("×")?str.indexOf("+"):str.indexOf("×");
	    	}
	    	if(str.indexOf("/")>0) {
	    		min=str.indexOf("×")<str.indexOf("/")?str.indexOf("×"):str.indexOf("/");
	    	}
	    	if(str.indexOf("-")>0) {
	    		min=str.indexOf("-")<str.indexOf("×")?str.indexOf("-"):str.indexOf("×");
	    	}
	    	if(str.indexOf("×")>0) {
	    		min=str.indexOf("×")<str.indexOf("×")?str.indexOf("×"):str.indexOf("×");
	    	}
	    }
	    return min;
	    }
	public double jc(double number) {
		double a1=1;
		for (int i1 = 1; i1 <= number; i1++) {
			a1 = a1 * i1;
		}
		return a1;
	}
	public static double FP(double n) {//格式换函数
		// NumberFormat format=NumberFormat.getInstance(); //创建一个格式化类f
		// format.setMaximumFractionDigits(18); //设置小数位的格式
		DecimalFormat format = new DecimalFormat("0.#############");
		return Double.parseDouble(format.format(n));
	}
}
