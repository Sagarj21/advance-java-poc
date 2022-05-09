package test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertSurrogatePK {
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(sno_se1.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;

		try {
			//read inputs
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null)
			{
				System.out.println("enter sutudents count");
				count=sc.nextInt();
			}
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "sagar");
			//create preparedstatement object having precompiled sql query
			if(con!=null)
			{
				ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			}
			//reads input values from user ,set them to query param valuess and execute teh precompiled sql query for multiple times
			if(ps!=null)
			{
				for(int i=1;i<=count;i++) {
					//read inputs
					 System.out.println("enter"+i+"students details");
					
					System.out.println("Enter student name");
					String name=sc.next();
					System.out.println("Enter student address");
					String addrs=sc.next();
					System.out.println("Enter student average");
					float avg=sc.nextFloat();
					//set each student details as precompiled sql query params
					
					ps.setString(1, name);
					ps.setString(2, addrs);
					ps.setFloat(3,avg);
					//execute precompiled query each time
					int result=ps.executeUpdate();
					//preocess execution result of precompiled sql query
					if(result==0)
						System.out.println(i+"student details not inserted");
					else
						System.out.println(i+"student details inserted");
				}
			}//if

		}catch(SQLException se)
		{
			se.printStackTrace();

		}catch(Exception e)
		{
			e.printStackTrace();

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
