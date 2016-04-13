
public class Singleton
{
	static private Singleton sig = null;
	
	private Singleton()
	{}
	
	public static Singleton getInstance()
	{
		if(sig == null) sig = new Singleton();
		return sig;
	}
	
	public void saySomething()
	{
		System.out.println("saySomething");
	}
}