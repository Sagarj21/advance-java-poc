package test;
//write a jdbc app to get details of employree ha ving lowest salary and highest salary
import java.sql.*;
import java.util.Scanner;

public class SelectTest6 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			if(con!=null)
				st=con.createStatement();
			String query="SELECT EMPNO,ENAME,job,hiredate,SAL,DEPTNO FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP) ";
			System.out.println(query);
			if(st!=null)
			{
				rs=st.executeQuery(query);
			}
			if(rs!=null)
			{
				boolean flag=false;
			 while(rs.next())
			 {
				 flag=true;
				 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6));
			 }
			 if(flag=false)
			 {
				 System.out.println("records not found");
			 }
			}
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}catch(Exception se)
			{
				se.printStackTrace();
			}
		}//finally

	}//main

}//class
