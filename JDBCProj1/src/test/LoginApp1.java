package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp1 {

	public static void main(String[] args) {
		//read inputs
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			String user=null,pass=null;
			if(sc!=null) {
				System.out.println("enter login username   ");
				user=sc.nextLine();
				System.out.println("enter login password   ");
				pass=sc.nextLine();
				}//if
			//convert input values as required for the sql query
			user="'"+user+"'";//gives 'raja rao'
			pass="'"+pass+"'";//gievs 'raja rao'
			//establslioh connection
		    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
		   //create statement obejct
		    if(con!=null)
		    {
		    	st=con.createStatement();
		    }
		    //prepere sql query
		    String query=null;
		    query="SELECT count(*) FROM IRCTC_TAB WHERE UNAME="+user+"AND PWD="+pass;
		    System.out.println(query);
		    //send and execute sql query in db sw
		    if(st!=null)
		    {
		    	rs=st.executeQuery(query);
		    }
		    //preocess the resultobject
		    if(rs!=null)
		    {
		    	rs.next();
		    	int count=rs.getInt(1);
		    	if(count==0)
		    		System.out.println("Invalid credentials");
		    	else
		    		System.out.println("vallid credentials");
		    	
		    }//if
		    
		}//try
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
			}try {
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
		}
	}//main

}//class
