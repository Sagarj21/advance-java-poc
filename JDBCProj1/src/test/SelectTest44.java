package test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectTest44 {

	public static void main(String[] args)  {

		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int dno=0;
			if(sc!=null) {
				System.out.println("Enter dept no");
				dno=sc.nextInt(); //stores deptno
			}
			//load jdbc drivber cLASS
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			if(con!=null)
				st=con.createStatement();

			//prepare sql query
			//select * from dept where deptno=40
			String query="SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO="+dno;
			System.out.println(query);

			//send and execute query
			if(st!=null)
				rs=st.executeQuery(query);

			//process the resultSet (0 or 1 record)
			if(rs!=null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				else
					System.out.println("no record found");
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();

			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();

			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();

			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception se) {
				se.printStackTrace();

			}
		}//finally
	}//main
}//class
