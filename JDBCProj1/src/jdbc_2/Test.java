public class Parent
{	
	public void property()
	{   
		System.out.println("cash home gold land");
	}
	public void marry()
	{   
		System.out.println("bolywood heroin");   
	}
}
public class Child extends Parent
{
	public void marry()
	{   
		System.out.println("hollywood heroin");   
	}	
	
public class Test
{
	public static void main(String []args)
	{  
		Parent p=new Parent();
		p.marry();
		Child c = new Child();
		c.marry();
		Parent p1=new Child();
		p1.marry();
	}
}
