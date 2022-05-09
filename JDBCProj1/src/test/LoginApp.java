package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			String user=null,pass=null;
			if(sc!=null)
			{
				System.out.println("enter login username");
				user=sc.nextLine();
				System.out.println("enter login pasword");
				pass=sc.nextLine();

			}
			//convert input values as require for sql query
			user="'"+user+"'";
			pass="'"+pass+"'";
			//load jdbc driver class and connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "sagar");
			//create jdbc statement object
			if(con!=null)
			{
				st=con.createStatement();
			}
			//pepare sql query
			String query="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME= "+user+"AND PWD= "+pass;

			System.out.println(query);

			//send and execute query
			if(st!=null)
				rs=st.executeQuery(query);
			//process the resultSet object
			if(rs!=null) {
				rs.next();//movers cursor to first position
				int count=rs.getInt(1);//get first col value of that first record
				if(count==0)
					System.out.println("invalid credentials");
				else
					System.out.println("valid credentials");
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
			//close jdbc obj
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
