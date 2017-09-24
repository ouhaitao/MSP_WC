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
		int words = 0;// ������
		int character = 0;// �ַ���
		int lines = 0;// ����

		try {
			File file = new File("Test.txt");
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));

				String str;
				while ((str = br.readLine()) != null && !str.isEmpty()) {
					char[] ch = str.toLowerCase().toCharArray();
					// �����һ���ַ��Ǳ�������Ϊtrue
					boolean flag = true;
					// �����һ���ַ�����ĸ��Ϊture
					boolean cFlag = false;
					character += ch.length;
					for (char c : ch) {
						// �������ĸ
						if ((c >= 'a' && c <= 'z')) {
							flag = false;
							cFlag = true;
						}
						// ����Ǻ��ֻ�������
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
				// �ر��ļ�
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
