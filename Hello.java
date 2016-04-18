
//package tw;
/**
*Description
*<br>??: <a href="htttp://www.baidu.com">??</a>
*<br>Copyright (c),2016-2026,tangwen
*<br>This program is protected by tw laws.
*<br>Program Name:
*<br>Data:
*@author tw 18210436456@163.com
*@version 1.0
*/
import java.util.Scanner;
public class Hello
{
	/**
	*????
	*/
	public static void main(String arg[])
	{
		System.out.print("Hello");
		int age1=200;
		char c ='中';
		System.out.println(c);
		
		int oct = 013;
		int hexv1 = 0x13;
		int hexv2 = 0xaf;
		int binv1 = 0b1000_0000_0000_0000_0000_0000_0000_0011;
		int binv2 = 0b11111111;
		byte binv3 = (byte)0b11101001;
		long binv4 = 0b10000000000000000000000000000011L;
		char c1 = '\"';
		char c2 = '\u0022';
		char c3 = '\u9999';
		char c4 = 0x22;
		
		System.out.println(oct);
		System.out.println(hexv1);
		System.out.println(hexv2);
		System.out.println(binv1);
		System.out.println(binv2);
		System.out.println(binv3);
		System.out.println(binv4);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(-1/0.0f);
		System.out.println(-0.0f/0.0f);
		
		String result ="";
		for(int i=0;i<6;i++)
		{
			int intval=(int)(Math.random()*26+97);
			result = result + (char)intval;
		}
		System.out.println(result);
		
		// for(char i=1;i<=0xffff;++i)
		// {
			// System.out.println(i);
		// }
		
		String as = "12345";
		System.out.println(Integer.parseInt(as)+1);
		System.out.println(5.2/3.1);
		for(int i=0;i<10;++i) System.out.println(i);
		for(int i=0;i<10;i++) System.out.println(i);
		System.out.println(Math.pow(5,25));
		System.out.println(Math.sin(1.57));
		int am = 167;
		System.out.println(am>>2);
		System.out.println(am);
		
		// Scanner scan = new Scanner(System.in);
		// System.out.println("请输入用户名");
		// String username=scan.nextLine();
		// System.out.println("请输入登录密码");
		// String password=scan.nextLine();
		
		// System.out.println(username);
		// System.out.println(password);
		
		int arr1[]={1,2,3,4,5,6,7,8};
		for(int x:arr1) System.out.println(x);
		
		System.out.println( showOdd());
		
		printHoleStar(4);
	}
	static int showOdd()
	{
		int i=0;
		System.out.println("输出100以内的所有奇数");
		while(i<100)
		{	
			i++;
			if(i%2==0) continue;
			System.out.println(i+" ");
				
		}
		return 0;
	}
	
	static void printAStar(boolean isStar)
	{
		if (isStar)
			System.out.print("*");
		else System.out.print(" ");
	}
	
	static void printLineStar(int curLine,int totalLine)
	{
		int hft = (int)(totalLine/2);
		if(curLine==1 || curLine==totalLine)
		{
			for(int i=0;i<hft+1;++i) printAStar(false);
			printAStar(true);
		}
		else
		{
			if(curLine>hft+1) curLine = totalLine - curLine+1;
			int ls=0,hs=0;
			ls=(hft+1)-(curLine-1);
			hs=(hft+1)+(curLine-1);
			for(int i=0;i<=totalLine;++i)
			{
				if(i==ls||i==hs) printAStar(true);
				else printAStar(false);
			}
		}
		System.out.print("\n");
	}
	
	static void printHoleStar(int totalLine)
	{
		if(totalLine%2==0) totalLine-=1;
		for(int i=1;i<=totalLine;++i) printLineStar(i,totalLine);
	}
}