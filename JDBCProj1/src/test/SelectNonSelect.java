package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelect {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;  
		try {
			sc=new Scanner(System.in);
			int no=0;
			String query=null;
			if(sc!=null)
			{
				System.out.println("enter sql query(Select or Non select)");
				query=sc.nextLine();
			}

			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			if(con!=null)
				st=con.createStatement();
			if(st!=null)
			{
				boolean flag=st.execute(query);
				if(flag==true) {
					System.out.println("select query executed");
					rs=st.getResultSet();
					//process teh resultset object
					if(rs!=null) {
						while(rs.next())
							System.out.println(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
					}

				}
				else
				{	System.out.println("non select query executed");
				int count=st.getUpdateCount();
				System.out.println("no of records updated"+count);


				}
			}

		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc object7s
			try{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try{
				if(st!=null)
					st.close();
			}

			catch(SQLException se)
			{
				se.printStackTrace();
			}try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}try{
				if(sc!=null)
					sc.close();
			}
			catch(Exception se)
			{
				se.printStackTrace();
			}
		}



	}
}
