package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest1 {
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";
	public static void main(String[] args) {


		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			sc= new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("enter students count");
				count=sc.nextInt();
			}
			//register jdbc driver
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			//create preparedststemant object
			if(con!=null) {
				ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			}
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
					//read each student input values
					System.out.println("Enter "+i+"student detaoils");
					System.out.println("enter student id");
					int id= sc.nextInt();
					System.out.println("enter student name");
					String name=sc.next();
					System.out.println("enter student couirseid");
					int cid=sc.nextInt();
					//set each student details as pecompiled sql query parms
					ps.setInt(1,id);ps.setString(2, name);ps.setInt(3, cid);

					//execute prcompilefd query each time
					int result=ps.executeUpdate();
					//process result of each precompiled query
					if(result==0)
						System.out.println(i+"student details are not insertsd");
					else
						System.out.println(i+"details are insertded");
				}//for
			}
			//if
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
