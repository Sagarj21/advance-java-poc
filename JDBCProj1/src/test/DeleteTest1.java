package test;

import java.sql.*;
import java.util.Scanner;

public class DeleteTest1 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			System.out.println("ENter stident id");
			int stid=sc.nextInt();
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			if(con!=null)
				st=con.createStatement();
			String query="delete from student where stid="+stid+"";
			System.out.println(query);
			int result=0;
			if(st!=null)
			{
				result=st.executeUpdate(query);
			
			}
			if(result==0)
				System.out.println("record not found");
			else
				System.out.println("no. of record deleted"+result);
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
