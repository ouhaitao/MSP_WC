package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		wc();
	}

	public static void wc() {
		int words = 0;// 单词数
		int character = 0;// 字符数
		int lines = 0;// 行数

		try {
			File file = new File("Test.txt");
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));

				String str;
				while ((str = br.readLine()) != null && !str.isEmpty()) {
					char[] ch = str.toLowerCase().toCharArray();
					// 如果上一个字符是标点符号则为true
					boolean flag = true;
					// 如果上一个字符是字母则为ture
					boolean cFlag = false;
					character += ch.length;
					for (char c : ch) {
						// 如果是字母
						if ((c >= 'a' && c <= 'z')) {
							flag = false;
							cFlag = true;
						}
						// 如果是汉字或者数字
						else if ((("" + c).getBytes().length == 2) || (c >= '0' && c <= '9')) {
							if (cFlag) {
								words++;
								cFlag = false;
							}
							words++;
							flag = false;
						} else if ((c >= ' ' && c <= '/') || (c >= '[' && c <= '`') || (c >= '{' && c <= '`')) {
							if (!flag)
								words++;
							flag = true;
						}
					}
					char c = ch[ch.length - 1];
					if (c >= 'a' && c <= 'z')
						words++;
					lines++;
				}
				System.out.println("char:" + character);
				System.out.println("words:" + words);
				System.out.println("lines:" + lines);
				// 关闭文件
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
