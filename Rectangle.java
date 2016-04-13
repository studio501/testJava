
public class Rectangle
{
	private double weight,height;
	public Rectangle()
	{
		weight=height=1;
	}
	public Rectangle(double w,double h)
	{
		weight = w;
		height = h;
	}
	
	public double getArea()
	{
		return weight*height;
	}
}