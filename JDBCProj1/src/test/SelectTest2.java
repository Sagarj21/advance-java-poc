package test;
import java.util.*;
import java.sql.*;

public class SelectTest2 {

	public static void main(String[] args) {
		try {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter start range of employee salary");
		float startSalary=sc.nextFloat();//1000
		System.out.println("Enter end range of employee salary");
		float endSalary=sc.nextFloat();//3000
		//regidter jdbc class
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "sagar");
		Statement st= con.createStatement();//create stmt
		String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>="+startSalary+" AND SAL<="+endSalary;
		System.out.println(query);
		ResultSet rs=st.executeQuery(query);
		System.out.println("employe details ahving salary range"+startSalary+"..."+endSalary);
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getFloat(4));
		}
		
		
		rs.close();
		st.close();
		con.close();
		
  
	  }catch(Exception e)
	  {
		e.printStackTrace();
	  }

    }
}
