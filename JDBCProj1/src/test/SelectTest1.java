package test;
import java.sql.*;
import java.util.*;
public class SelectTest1 {

	public static void main(String[] args)throws Exception {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;


		try {
			//read inputs
			sc=new Scanner(System.in);
			System.out.println("enter the range from low to high");
			System.out.println("enter start range of emp sal  value");
			int first=sc.nextInt();
			System.out.println("enter end range of emp sal  value");
			int sec=sc.nextInt();
			if(sc!=null)
			{
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");

			}
			//preparestatement
			if(con!=null)
			{
				st=con.createStatement();

			}
			//prepare query
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>="+first+" AND SAL<="+sec;
			System.out.println(query);
			System.out.println("emp details are::");
			if(st!=null)
			{
				rs=st.executeQuery(query);
			}
			//processing resultset
			if(rs!=null)
			{
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception se)
		{
			se.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception se)
			{
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
