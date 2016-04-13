
public class TWCircle extends Circle implements Interface_Attack
{
	private String name="hahah";
	public void printName()
	{
		System.out.println(name);
	}
	public float getArea(float r)
	{
		System.out.println("area in Circle is "+ super.getArea(r));
		return PI*r*r*9999;
	}
	public void commitAttack()
	{
		System.out.println("TW Attack 1000+");
	}
	
	public void commitAttack(int a)
	{
		System.out.println("TW Attack 1000+ " +a);
	}
	
	innerClass in = new innerClass();
	public void ouf()
	{
		System.out.println("outclass ouf");
		in.inf();
	}
	
	class innerClass
	{
		innerClass()
		{}
		
		public void inf()
		{
			System.out.println("innerClass inf");
		}
		int y=0;
	}
	
	public innerClass doit()
	{
		System.out.println("doittttttttttt");
		in.y=4;
		System.out.println("out y is "+ TWCircle.this.y+" and inner y is "+in.y);
		return new innerClass();
	}
	private int y=10;
	
	public void sell(int price)
	{
		class Apple
		{
			int innerPrice = 0;
			public Apple(int p)
			{
				innerPrice = p;
			}
			public void price()
			{
				System.out.println("y is " + y);
				System.out.println("price of apple is "+innerPrice);
			}
		}
		Apple ap = new Apple(price);
		ap.price();
	}
	
	
	public void noNameClass()
	{
		Haha h = new Haha()
		{
			@Override
			public void syaHaha()
			{
				System.out.println("hahahahhahahhaha");
			}
		};
		h.syaHaha();
	}
	
}