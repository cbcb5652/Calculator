package FirstApp;

public class HandleBackspace {
	static void HandleBackspace()//删除函数 
	{
		String text = Calculator.resultText.getText();
		Calculator.list.add(text);
		int i = text.length();
		if (i > 0 && Calculator.list.size() > 0) {
			text = text.substring(0, i - 1);
			Calculator.list.remove(Calculator.list.size() - 1); // 移除栈顶的那个元素
			if (text.length() == 0) {
				Calculator.list.clear();
				Calculator.resultText.setText("0");
				Calculator.vbegin = true;
				Calculator.equals_flag = true;
			} else {
				Calculator.resultText.setText(text);
			}
		}
	}
}
