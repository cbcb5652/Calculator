package FirstApp;

public class Hexadecimal {
	    //进制转换函数
		public Hexadecimal(String label) {
			if (label == "二进制") // 二进制的运算
			{
				String str="0";
				//判断是文本框上的整数是从什么进制转换而来的
				if(Handle.min(Calculator.resultText.getText())==0) {//判断是否有运算符，没有的话直接中resultText中直接获取字符进行计算
					str=Calculator.resultText.getText();
					//判断resultText是否是空的，但是resultText上面是一个字符串式子，则直接在把算式文本框归0，结果文本框清空，不进行转换
				}else if(Calculator.resultText1.getText()==""&&Handle.min(Calculator.resultText.getText())!=0){
					Calculator.resultText.setText(str);
					Calculator.resultText1.setText("");
					
				}
				else {//要是结果框上面有字符，直接从resultText1上面获取字符进行计算，但是要把结果后面的小数点截掉，以防在计算时的报错
					if(Calculator.resultText1.getText().length()>2) 
					{
					if(Calculator.resultText1.getText().indexOf(".")>0) {
					str=Calculator.resultText1.getText().substring(0,Calculator.resultText1.getText().length()-2);
					}else {
						str=Calculator.resultText1.getText();
						
					}
					Calculator.resultText1.setText("");
					}
					Calculator.vbegin = true;//最后把算式框里面的vbegin设为true防止屏幕上的0被用到算式中去。
					Calculator.equals_flag = true;
					
				}
				if(Calculator.count==8) {//如果计数器是8那么就是从八进制转换为二进制
					Calculator.resultText.setText(Integer.toBinaryString(Change.changetoeight(str)));
				}
				else if(Calculator.count==10) {//如果是十就从十进制转换为二进制
					if(Calculator.resultText.getText().indexOf(".")>0) {
						Calculator.resultText.setText(Integer.toBinaryString(Integer.parseInt(str.substring(0, str.length()-2))));
					}else {
						Calculator.resultText.setText(Integer.toBinaryString(Integer.parseInt(str)));
					}
				}else if(Calculator.count==16){//如果是十六就从十六进制转换为二进制
					Calculator.resultText.setText(Integer.toBinaryString(Change.changetosixteen(str)));
				}else {
					Calculator.resultText.setText(str);
				}
				
				Calculator.count=2;
				if (Calculator.a == 0) {//设置进制转换时的某些按钮失效，以防输入报错
					for (int j = 0; j < 2; j++) {
						Calculator.line1[j].setEnabled(false);
						Calculator.line2[j].setEnabled(false);
						Calculator.line3[j].setEnabled(false);
						Calculator.line4[j].setEnabled(false);
						Calculator.line5[j].setEnabled(false);
					}
				}
				Calculator.line1[2].setEnabled(false);
				Calculator.line1[3].setEnabled(false);
				for (int i = 2; i < 6; i++) {
					Calculator.line2[i].setEnabled(false);
					Calculator.line3[i].setEnabled(false);
				}
				Calculator.line4[2].setEnabled(false);
				Calculator.line4[4].setEnabled(false);
				Calculator.line4[5].setEnabled(false);
				Calculator.line5[2].setEnabled(false);
				Calculator.line5[3].setEnabled(false);
				Calculator.line5[5].setEnabled(false);
				
				Calculator.count = 2;
			} else if (label == "八进制") // 八进制的运算
			{
				String str="0";
				//判断是文本框上的整数是从什么进制转换而来的
				if(Handle.min(Calculator.resultText.getText())==0) {//判断是否有运算符，没有的话直接中resultText中直接获取字符进行计算
					str=Calculator.resultText.getText();
					//判断resultText是否是空的，但是resultText上面是一个字符串式子，则直接在把算式文本框归0，结果文本框清空，不进行转换
				}else if(Calculator.resultText1.getText()==""&&Handle.min(Calculator.resultText.getText())!=0){
					Calculator.resultText.setText(str);
					Calculator.resultText1.setText("");
					
				}
				else {//要是结果框上面有字符，直接从resultText1上面获取字符进行计算，但是要把结果后面的小数点截掉，以防在计算时的报错
					if(Calculator.resultText1.getText().length()>2) 
					{
					if(Calculator.resultText1.getText().indexOf(".")>0) {
					str=Calculator.resultText1.getText().substring(0,Calculator.resultText1.getText().length()-2);
					}else {
						str=Calculator.resultText1.getText();
						
					}
					Calculator.resultText1.setText("");
					}
					Calculator.vbegin = true;//最后把算式框里面的vbegin设为true防止屏幕上的0被用到算式中去。
					Calculator.equals_flag = true;
					
				}
				//判断是文本框上的整数是从什么进制转换而来的
				if(Calculator.count==2) {
					Calculator.resultText.setText(Integer.toOctalString(Change.changetotwo(str)));
				}
				else if(Calculator.count==10) {
					Calculator.resultText.setText(Integer.toOctalString(Integer.parseInt(str)));
				}else if(Calculator.count==16){
					Calculator.resultText.setText(Integer.toOctalString(Change.changetosixteen(str)));
				}else {
					Calculator.resultText.setText(str);
				}
				
				
				if (Calculator.a == 0) {
					for (int i = 0; i < 2; i++) {
						Calculator.line1[i].setEnabled(false);
						Calculator.line2[i].setEnabled(false);
						Calculator.line3[i].setEnabled(false);
						Calculator.line4[i].setEnabled(false);
						Calculator.line5[i].setEnabled(false);
					}
				}
				Calculator.line2[3].setEnabled(true);
				Calculator.line3[3].setEnabled(true);
				Calculator.line3[4].setEnabled(true);
				Calculator.line3[5].setEnabled(true);
				Calculator.line4[4].setEnabled(true);
				Calculator.line4[5].setEnabled(true);
				Calculator.line4[6].setEnabled(true);
				Calculator.line1[2].setEnabled(false);
				Calculator.line1[3].setEnabled(false);
				Calculator.line2[2].setEnabled(false);
				Calculator.line2[4].setEnabled(false);
				Calculator.line2[5].setEnabled(false);
				Calculator.line3[2].setEnabled(false);
				Calculator.line4[2].setEnabled(false);
				Calculator.line5[2].setEnabled(false);
				Calculator.line5[3].setEnabled(false);
				Calculator.line5[5].setEnabled(false);
				
				Calculator.count = 8;
			} else if (label == "十进制") {//十进制的运算
				String str="0";
				//判断是文本框上的整数是从什么进制转换而来的
				if(Handle.min(Calculator.resultText.getText())==0) {//判断是否有运算符，没有的话直接中resultText中直接获取字符进行计算
					str=Calculator.resultText.getText();
					//判断resultText是否是空的，但是resultText上面是一个字符串式子，则直接在把算式文本框归0，结果文本框清空，不进行转换
				}else if(Calculator.resultText1.getText()==""&&Handle.min(Calculator.resultText.getText())!=0){
					Calculator.resultText.setText(str);
					Calculator.resultText1.setText("");
					
				}
				else {//要是结果框上面有字符，直接从resultText1上面获取字符进行计算，但是要把结果后面的小数点截掉，以防在计算时的报错
					if(Calculator.resultText1.getText().length()>2) 
					{
					if(Calculator.resultText1.getText().indexOf(".")>0) {
					str=Calculator.resultText1.getText().substring(0,Calculator.resultText1.getText().length()-2);
					}else {
						str=Calculator.resultText1.getText();
						
					}
					Calculator.resultText1.setText("");
					}
					Calculator.vbegin = true;//最后把算式框里面的vbegin设为true防止屏幕上的0被用到算式中去。
					Calculator.equals_flag = true;
					
				}
				//判断是文本框上的整数是从什么进制转换而来的
				if(Calculator.count==2) {
					Calculator.resultText.setText(String.valueOf(Change.changetotwo(str)));
				}
				else if(Calculator.count==8) {
					Calculator.resultText.setText(String.valueOf(Change.changetoeight(str)));
				}else if(Calculator.count==16){
					Calculator.resultText.setText(String.valueOf(Change.changetosixteen(str)));
				}else {
					Calculator.resultText.setText(str);
				}
				
				for (int i = Calculator.a; i < Calculator.Line1.length; i++) {
					Calculator.line1[i].setEnabled(true);
					Calculator.line2[i].setEnabled(true);
					Calculator.line3[i].setEnabled(true);
					Calculator.line4[i].setEnabled(true);
					Calculator.line5[i].setEnabled(true);

				}
			} else if (label == "十六进制") // 十六进制的运算
			{
				String str="0";
				//判断是文本框上的整数是从什么进制转换而来的
				if(Handle.min(Calculator.resultText.getText())==0) {//判断是否有运算符，没有的话直接中resultText中直接获取字符进行计算
					str=Calculator.resultText.getText();
					//判断resultText是否是空的，但是resultText上面是一个字符串式子，则直接在把算式文本框归0，结果文本框清空，不进行转换
				}else if(Calculator.resultText1.getText()==""&&Handle.min(Calculator.resultText.getText())!=0){
					Calculator.resultText.setText(str);
					Calculator.resultText1.setText("");
					
				}
				else {//要是结果框上面有字符，直接从resultText1上面获取字符进行计算，但是要把结果后面的小数点截掉，以防在计算时的报错
					if(Calculator.resultText1.getText().length()>2) 
					{
					if(Calculator.resultText1.getText().indexOf(".")>0) {
					str=Calculator.resultText1.getText().substring(0,Calculator.resultText1.getText().length()-2);
					}else {
						str=Calculator.resultText1.getText();
						
					}
					Calculator.resultText1.setText("");
					}
					Calculator.vbegin = true;//最后把算式框里面的vbegin设为true防止屏幕上的0被用到算式中去。
					Calculator.equals_flag = true;
					
				}
				//判断是文本框上的整数是从什么进制转换而来的
				if(Calculator.count==2) {
					Calculator.resultText.setText(Integer.toHexString(Change.changetotwo(str)));
				}
				else if(Calculator.count==8) {
					Calculator.resultText.setText(Integer.toHexString(Change.changetoeight(str)));
				}else if(Calculator.count==10){
					Calculator.resultText.setText(Integer.toHexString(Integer.valueOf(str)));
				}else {
					Calculator.resultText.setText(str);
				}
				
				Calculator.count=16;
				if (Calculator.a == 0) {
					for (int j = 0; j < 2; j++) {
						Calculator.line1[j].setEnabled(false);
						Calculator.line2[j].setEnabled(false);
						Calculator.line3[j].setEnabled(false);
						Calculator.line4[j].setEnabled(false);
						Calculator.line5[j].setEnabled(false);
					}
				}
				for (int i = 2; i < 6; i++) {
					Calculator.line2[i].setEnabled(true);
					Calculator.line3[i].setEnabled(true);
				}
				Calculator.line4[4].setEnabled(true);
				Calculator.line4[5].setEnabled(true);
				Calculator.line1[2].setEnabled(false);
				Calculator.line1[3].setEnabled(false);
				Calculator.line2[2].setEnabled(false);
				Calculator.line3[2].setEnabled(false);
				Calculator.line4[2].setEnabled(false);
				Calculator.line5[2].setEnabled(false);
				Calculator.line5[3].setEnabled(false);
				Calculator.line5[5].setEnabled(false);
				
				Calculator.count = 16;
			}
		}
}
