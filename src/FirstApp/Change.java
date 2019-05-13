package FirstApp;

public class Change {
	//把二进制转换为十进制的函数
		public static int changetotwo(String s) {
			int str;
			str = Integer.parseInt(s, 2);
			return str;
		}
		//把八进制转换为十进制的函数
		public static int changetoeight(String s) {
			int str;
			str = Integer.parseInt(s, 8);
			return str;
		}
		//把十六进制转换为十进制的函数
		public static int changetosixteen(String s) {
			int str;
			str = Integer.parseInt(s, 16);
			return str;
		}
}
