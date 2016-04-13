
import java.util.Random;
import java.lang.reflect.Field;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Test
{
	private String name;
	private int age;
	
	public Test(String name,int age)
	{
		this.name = name;
		this.age=age;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj) return true;
		if(obj==null) return false;
		if(this.getClass()!=obj.getClass()) return false;
		Test temp = (Test)obj;
		return this.age==temp.age && this.name.equals(temp.name);
	}
	
	@Override
	public int hashCode()
	{
		return this.name.hashCode() + 2*new Integer(this.age).hashCode();
	}
	
	public void moveDish(int lv,char from,char inter,char to)
	{
		if(lv==1) System.out.println("move "+from+" 1 "+to);
		else
		{
			moveDish(lv-1,from,to,inter);
			System.out.println("move "+from+" "+lv+" to "+to);
			moveDish(lv-1,inter,from,to);
		}
	}
	public static void main(String[] args)
	{
		System.out.println("Hello World!");
		
		Test1 t11 = new Test1();
		t11.saySomething();
		
		for(int i=0;i<10;++i) System.out.println(new Random().nextInt(3));
		for(int i=0;i<10;++i) System.out.println(new Random().nextBoolean());
		
		Test t1 = new Test("tw",10);
		Test t2 = new Test("ta",10);
		Test t3 = t1;
		System.out.println("t1 == t2 ? "+ t1.equals(t2));
		System.out.println("t1 == t3 ? "+ t1.equals(t3));
		System.out.println("t1 custom hashCode  "+ t1.hashCode());
		System.out.println("t2 custom hashCode  "+ t2.hashCode());
		
		t1.moveDish(4,'a','b','c');
		t2.moveDish(3,'A','B','C');
		
		Singleton.getInstance().saySomething();
		Singleton.getInstance().saySomething();
		Singleton.getInstance().saySomething();
		
		Rectangle r1 = new Rectangle();
		Rectangle r2 = new Rectangle(10.0,9.9);
		System.out.println("r1 area "+ r1.getArea());
		System.out.println("r2 area "+ r2.getArea());
		
		Circle c1 = new Circle();
		System.out.println("c1 area  "+ c1.getArea(10));
		System.out.println("c1 circumference "+ c1.getCircumference(10));
		
		TWCircle c2 = new TWCircle();
		System.out.println("c2 area  "+ c2.getArea(8));
		System.out.println("c2 circumference "+ c2.getCircumference(8));
		c2.commitAttack();
		c2.commitAttack(9999);
		System.out.println(c2.getClass().getSimpleName());
		c2.printName();
		Class<?> clzz = c2.getClass();
		System.out.println("canonical name is : " + clzz.getCanonicalName());
		
		try{
			Field pName = clzz.getDeclaredField("name");
			pName.setAccessible(true);
			pName.set(c2,"tw");
			c2.printName();
		}catch(SecurityException e)
		{
			e.printStackTrace();
		}catch(NoSuchFieldException e)
		{
			e.printStackTrace();
		}catch(IllegalArgumentException e)
		{
			e.printStackTrace();
		}catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		TWCircle.innerClass in = c2.doit();
		c2.ouf();
		TWCircle.innerClass in1 = c2.new innerClass();
		c2.sell(99);
		c2.noNameClass();
		
		class oini extends TWCircle.innerClass
		{
			public oini(TWCircle ddd)
			{
				ddd.super();
			}
		}
		
		oini i_ = new oini(c2);
		
		class AlarmClock
		{
			private int delay;
			private boolean flag;
			public AlarmClock(int delay,boolean flag)
			{
				this.delay=delay;
				this.flag=flag;
			}
			
			public void start()
			{
				class Printer implements ActionListener
				{
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						SimpleDateFormat format = new SimpleDateFormat("k:m:s");
						String result = format.format(new Date());
						System.out.println("cur time is : "+result);
						if(flag)
						{
							Toolkit.getDefaultToolkit().beep();
						}
					}
				}
				new Timer(delay,new Printer()).start();
			}
		}
		
		//AlarmClock ck1 = new AlarmClock(1000,true);
		//ck1.start();
		//JOptionPane.showMessageDialog(null,"are you sure exit?");
		class MaxMin
		{
			class Result
			{
				private double max;
				private double min;
				public Result(double max,double min)
				{
					this.max = max;
					this.min= min;
				}
				public double getMax(){return max;}
				public double getMin(){return min;}
			}
			
			public  Result getResult(double[] array)
			{
				double max = Double.MIN_VALUE;
				double min = Double.MAX_VALUE;
				for(double i : array)
				{
					if(i>max) max = i;
					if(i<min) min = i;
				}
				return new Result(max,min);
			}
		}
		
		MaxMin mm = new MaxMin();
		MaxMin.Result mr = mm.getResult(new double[]{11.08,44.0,22.8,99.9,54.32,77.61});
		System.out.println("max is "+ mr.getMax()+" min is "+mr.getMin());
		System.out.println(mr.getClass().getPackage());
		System.out.println(mr.getClass().getName());
		System.out.println(mr.getClass().getMethods());
		
	}
}