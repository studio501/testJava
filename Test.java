
import java.util.Random;
import java.lang.reflect.Field;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.reflect.*;
import java.lang.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

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
		//test1();
		//test2();
		//test3();
		//test4();
		test5();
	}
	public static void test5()
	{
		class GD extends Frame
		{
			public void paint(Graphics g)
			{
				setXY();
				Point p = new Point(cx/2,cy/2);
				g.drawLine(0,0,cx,cy);
				g.draw3DRect(cx/2,cy/2,cx/3,cy/3,false);
				int o1= p.x-40,o2=(p.y-40),o3=80,o4=40;
				//Rectangle rect = new Rectangle((p.x-40),(p.y-40),80,40);
				int[] xp = {(p.x-40),(p.x+90),(p.x+200),p.x-40};
				int[] yp = {p.y-40,p.y+140,p.y+60,p.y-40};
				g.drawArc(o1,o2,o3,o4*2,270,90);
				g.drawPolygon(xp,yp,3);
			}
			
			public void setXY()
			{
				Dimension d = getSize();
				cx=d.width/2;
				cy=d.height/2;
			}
			
			public GD(String str)
			{
				super(str);
				addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent evt)
					{
						setVisible(false);
						dispose();
						System.exit(0);
					}
				});
			}
			private int cx;
			private int cy;
		}
		GD f = new GD("Graphics 绘图");
		f.setSize(500,300);
		f.setVisible(true);
	}
	public static void test4()
	{
		class SD extends Frame
		{
			public SD()
			{
				super();
				init();
			}
			
			Scrollbar slider;
			Label lable;
			void init()
			{
				setLayout(new GridLayout(1,3));
				slider = new Scrollbar(Scrollbar.HORIZONTAL,0,1,0,100);
				lable = new Label("0~100");
				lable.setBackground(Color.cyan);
				add(lable);
				add(slider);
				addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent evt)
					{
						setVisible(false);
						dispose();
						System.exit(0);
					}
				});
				setSize(300,50);
				setVisible(true);
				
			}
		}
		new SD();
	}
	public static void test3()
	{
		class CanvasDemo extends Panel
		{
			private myCanvas mc;
			private Frame f;
			public CanvasDemo()
			{
				f = new Frame();
				mc = new myCanvas();
				mc.repaint(0,0,100,100);
				add("Center",mc);
				f.add(mc);
				f.setSize(300,200);
				f.setLocation(400,400);
				f.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent evt)
					{
						f.setVisible(false);
						f.dispose();
						System.exit(0);
					}
				});
				f.setVisible(true);
				f.setBackground(Color.GREEN);
				
				f.add(new TextField("好"));
			}
			
			class myCanvas extends Canvas
			{
				public void paint(Graphics g)
				{
					g.setColor(Color.RED);
					g.drawRect(100,40,100,100);
					g.drawString("来个后空翻",100,50);
				}
			}
		}
		
		new CanvasDemo();
	}
	
	public static void test2()
	{
		String a="a",b="B",c="c",d="d",e="e";
		List<String> list = new LinkedList<String>();
		list.add(a);
		list.set(0,c);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		
		Iterator<String> it = list.iterator();
		while(it.hasNext())
			System.out.print(it.next()+" ");
		System.out.print("\n");
		Set<String> set = new HashSet<String>();
		set.addAll(list);
		Iterator<String> ist = set.iterator();
		while(ist.hasNext())
			System.out.print(ist.next()+" ");
		System.out.print("\n");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("1","apple");
		map.put("2","car");
		map.put("3","bird");
		map.put("4","cat");
		map.put("5","number");
		for(int i=1;i<=5;++i)
			System.out.println(map.get(""+i));
		
		Map<Integer,String> map1 = new HashMap<Integer,String>();
		map1.put(1,"apple");
		for(int i=1;i<=1;++i)
			System.out.println(map1.get(i));
		

		class firstFrame extends Frame
		{
			public firstFrame(String str)
			{
				super(str);
			}
			
			public void init()
			{
				int w=850,h=480;
				this.setSize(w,h);
				setBackground(Color.BLUE);
				setVisible(true);
				//setOpacity(0.5f);
				
				
				setLocation(1920/2 - w/2,1080/2 - h/2);
				addWindowListener(new MywindowAdapter());
				setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
			}
			
			class MywindowAdapter extends WindowAdapter
			{
				public void windowClosing(WindowEvent we)
				{
					firstFrame.this.dispose();
					System.exit(0);
				}
			}
		}
		
		firstFrame f = new firstFrame("hahhah");
		f.init();
		
		Panel p = new Panel();
		p.setSize(50,50);
		p.setBackground(Color.RED);
		//p.setLocation(50,50);
		f.add(p);
		
		Button bt = new Button("button");
		bt.setLocation(100,50);
		//bt.setSize(50,20);
		f.add(bt);
		
		Choice ColorChooser = new Choice();
		ColorChooser.setSize(100,100);
		//ColorChooser.setLocation(200,300);
		ColorChooser.add("Green");
		ColorChooser.add("Red");
		ColorChooser.add("Blue");
		f.add(ColorChooser);
		
		//f.setLayout(new GridLayout(3, 1));
		Checkbox c1 = new Checkbox("one");
		c1.setSize(100,100);
		//c1.setLocation(100,200);
		f.add(c1);
	
		Label l1 = new Label("HHHHHHHHHHHHHHHHHHHHH");
		f.add(l1);
		
		CheckboxGroup cbg = new CheckboxGroup();
		f.add(new Checkbox("one",cbg,true));
		f.add(new Checkbox("two",cbg,false));
		f.add(new Checkbox("three",cbg,false));
		f.add(new Checkbox("four",cbg,false));
		
		class myCanvas extends Canvas
		{
			public void paint(Graphics g)
			{
				g.setColor(Color.RED);
				g.drawRect(100,40,100,100);
				g.drawString("Canvas",100,50);
			}
		}
		
		Panel panel_ = new Panel();
		
		
		myCanvas mc = new myCanvas();
		mc.repaint(0,0,100,100);
		panel_.add("Center",mc);
		f.add(mc);
		
		f.add(new TextField("gooooooooooooooooooooooooooood"));
		f.add(new Scrollbar(Scrollbar.VERTICAL,0,1,0,255));

		//f.add(wd);
		
		//practice p255
		class myWind
		{
			public myWind()
			{
				
			}
		}
	}
	public static void test1()
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
			public MaxMin(){}
			private MaxMin(int a,int b,int c,String d){}
			public MaxMin(String... strings) throws NumberFormatException
			{
				for(int i=0;i<strings.length;++i)
					System.out.println(strings[i]);
			}
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
			public int a;
			private float b;
			protected String c;
			
			public void func1()
			{
				System.out.println("func1");
			}
			
			public void func2(int a,int b)
			{
				System.out.println("func2 "+a+" "+b);
			}
			
			private void func3()
			{
				System.out.println("func3");
			}
		}
		
		
		MaxMin mm = new MaxMin();
		MaxMin.Result mr = mm.getResult(new double[]{11.08,44.0,22.8,99.9,54.32,77.61});
		System.out.println("max is "+ mr.getMax()+" min is "+mr.getMin());
		System.out.println(mr.getClass().getPackage());
		System.out.println(mr.getClass().getName());
		System.out.println(mr.getClass().getMethods());
		try
		{
			System.out.println(mm.getClass().getDeclaredConstructor().isVarArgs());
			System.out.println(mm.getClass().getDeclaredConstructor().getParameterTypes());
			System.out.println(mm.getClass().getDeclaredConstructor().getModifiers());
			System.out.println(Modifier.toString( mm.getClass().getDeclaredConstructor().getModifiers()) );
			
			Constructor[] dc = mm.getClass().getDeclaredConstructors();
			for(int i=0;i<dc.length;++i)
			{
				Constructor ct = dc[i];
				System.out.println("is var args "+ct.isVarArgs());
				Class[] pt = ct.getParameterTypes();
				for(int j=0;j<pt.length;++j) System.out.println("param type is "+pt[j]);
				
				System.out.println("possible exception is ");
				Class[] et = ct.getExceptionTypes();
				for(int j=0;j<et.length;++j) System.out.println(" exception is "+et[j]);
			}
			Field df[] = mm.getClass().getDeclaredFields();
			for(int i=0;i<df.length;++i)
			{
				Field fd = df[i];
				System.out.println("filed name is "+fd.getName()+" type is "+fd.getType());
				boolean isTurn = true;
				while(isTurn)
				{
					try
					{
						isTurn = false;
						if(fd.getType().equals(int.class))
						{
							fd.setInt(mm,123456);
							//System.out.println("after modify :" +fd.get(mm));
						}
						else if(fd.getType().equals(float.class))
						{
							fd.set(mm,0.0134f);
						}
						else if(fd.getType().equals(String.class))
						{
							fd.set(mm,"Hello world");
						}
						
						System.out.println("after modify :" +fd.get(mm));
					}
					catch(Exception e)
					{
						System.out.println("set accessible true in exception");
						fd.setAccessible(true);
						isTurn = true;
					}
				}
				
			}
			
			Method[] dm = mm.getClass().getDeclaredMethods();
			for(int i=0;i<dm.length;++i)
			{
				Method md = dm[i];
				System.out.println("method name is "+md.getName());
				System.out.println("method is with args"+md.isVarArgs());
				Class[] pt = md.getParameterTypes();
				if (pt.length>0)
				{
					for(int j=0;j<pt.length;++j)
					{
						System.out.print(""+pt[j]+" ");
					}
					System.out.print("\n");
				}
				else
					System.out.print("null\n");
				boolean isTurn = true;
				while(isTurn)
				{
					try
					{
						isTurn = false;
						if(i==1) md.invoke(mm);
						else if(i==2) md.invoke(mm,1,2);
						else if(i==3) md.invoke(mm);
					}
					catch(Exception e)
					{
						System.out.println("set accessible true in exception");
						md.setAccessible(true);
						isTurn = true;
					}
				}
				
			}
		}
		catch(NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		// catch(NoSuchFieldException e)
		// {
			// e.printStackTrace();
		// }
		
		try
		{
			Method equals = String.class.getDeclaredMethod("equals",Object.class);
			System.out.println("is equal "+equals.invoke(new String("hahah"),"hahahq")) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		class Nothing
		{
			@SuppressWarnings("unchecked")
			public String toString(Object object)
			{
				Class clazz = object.getClass();
				StringBuilder sb = new StringBuilder();
				sb.append("this is my class hahahahha ~");
				sb.append(clazz.getSimpleName());
				return sb.toString();
			}
		}
		
		System.out.println(new Nothing().toString(new java.util.Date()));
		
		//practice p248
		
	}
	
}