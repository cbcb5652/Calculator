package FirstApp;

public class Operator {
	public Operator(String key) { //进行非正常操作外的计算
		long t1;
		double t2;
		double resultNum = 0;
		//对输入的运算符以及文本框上的数字进制简单的运算，不传入handle()函数，计算完成后，直接打印在文本框上
		if (key.equals("x²")) {
			if(Calculator.resultText1.getText().equals("")) {//如果结果框里没有东西，就拿算式框里面的值来计算sin
			resultNum = Math.pow(getNumberFromText(), 2);
			}else {
				resultNum = Math.pow(getNumberFromText1(), 2);//如果结果框里面有东西就拿结果框来计算sin
			}
			t1 = (long) resultNum;
			t2 = resultNum - t1;
			Calculator.resultText.setText("("+Calculator.resultText.getText()+")"+"^"+"2");
			Calculator.resultText1.setText(String.valueOf(resultNum));
		} else if (key.equals("x³")) {
			if(Calculator.resultText1.getText().equals("")) {//以下if语句都是同sin的
			resultNum = Math.pow(getNumberFromText(), 3);
			}else {
				resultNum = Math.pow(getNumberFromText1(), 3);
			}
			t1 = (long) resultNum;
			t2 = resultNum - t1;
			Calculator.resultText.setText("("+Calculator.resultText.getText()+")"+"^"+"3");
			Calculator.resultText1.setText(String.valueOf(resultNum));
		} else if (key.equals("1/x")) {
			if(Calculator.resultText1.getText().equals("")) {
			resultNum = 1 / getNumberFromText();
			}else {
				resultNum = 1 / getNumberFromText1();
			}
			Calculator.resultText.setText("1/"+"("+Calculator.resultText.getText()+")");
			Calculator.resultText1.setText(String.valueOf(resultNum));
		} else if (key.equals("%")) {
			if(Calculator.resultText1.getText().equals("")) {
			resultNum = getNumberFromText() / 100;
			}else {
				resultNum = getNumberFromText1() / 100;
			}
			Calculator.resultText.setText(Calculator.resultText.getText()+"/100");
			Calculator.resultText1.setText(String.valueOf(Handle.FP(resultNum)));
		} 
		else if(key.equals("e^x")) {
			if(Calculator.resultText1.getText().equals("")) {
			resultNum = Math.pow(Calculator.e, getNumberFromText());
			}else {
				resultNum = Math.pow(Calculator.e, getNumberFromText1());
			}
			Calculator.resultText.setText("("+"e^"+Calculator.resultText.getText()+")");//加个括号容易理解
			Calculator.resultText1.setText(String.valueOf(resultNum));
		}else if(key.equals("+/-")) {
			if(Calculator.resultText1.getText().equals("")) {
			 resultNum = getNumberFromText() * (-1);
			 Calculator.resultText.setText(String.valueOf(resultNum));
			 Calculator.resultText1.setText(String.valueOf(resultNum));
			}else {
				 Calculator.resultText.setText(String.valueOf(getNumberFromText1()*-1));
				 Calculator.resultText1.setText(String.valueOf(getNumberFromText1()*-1));
				 
			}
		}
	
				
		
	}
	private  double getNumberFromText() {//定义把计算框内的字符转换为数值的函数
		double result = 0;
		try {
			result = Double.valueOf(Calculator.resultText.getText()).doubleValue();
		} catch (NumberFormatException e) {
		}
		return result;
	}
	private  double getNumberFromText1() {//定义把结果框内的字符转换为数值的函数
		double result = 0;
		try {
			result = Double.valueOf(Calculator.resultText1.getText()).doubleValue();
		} catch (NumberFormatException e) {
		}
		return result;
	}
}
