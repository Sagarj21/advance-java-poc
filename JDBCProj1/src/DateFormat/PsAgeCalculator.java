package DateFormat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsAgeCalculator {
	private static final String AGE_CALCULATOR="SELECT ROUND((SYSDATE-DOB)/365.25,2) FROM PERSONAL_INFO_DATES WHERE PID=?";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			sc=new Scanner(System.in);
			int pid= 0;
			if(sc!=null)
			{
				System.out.println("enter Person id?");
				pid=sc.nextInt();

			}
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			if(con!=null)
			{
				ps=con.prepareStatement(AGE_CALCULATOR);

			}

			//set quweeyr paranms
			if(ps!=null)
				ps.setInt(1, pid);
			if(ps!=null)
				rs=ps.executeQuery();
			//process teh resultset
			float age=0.0f;
			if(rs!=null)
			{
				if(rs.next())
					age=rs.getFloat(1);
				
					System.out.println("person age is"+age);
			}
			else {
				System.out.println("person not fond");
			}



		}catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception s)
		{
			s.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();

			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();

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

	}

}
