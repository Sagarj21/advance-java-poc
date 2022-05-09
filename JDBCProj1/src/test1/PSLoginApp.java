package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PSLoginApp {
	private static final String LOGIN_QUERY="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";


	public static void main(String[] args) {

		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
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

			//load jdbc driver class and connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "sagar");
			//create jdbc Preparedstatement object
			if(con!=null)
			{
				ps=con.prepareStatement(LOGIN_QUERY);
			}
			if(ps!=null)
			{
				ps.setString(1, user);
				ps.setString(2, pass);
			}

			//send and execute query
			if(ps!=null)
				rs=ps.executeQuery();
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
				if(ps!=null)
					ps.close();
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
