package DateFormat;
//ageCalculator generic .java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class PsAgeCalculatorGeneric {
	private static final String GET_DOB="SELECT DOB FROM PERSONAL_INFO_DATES WHERE PID=?";

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
				ps=con.prepareStatement(GET_DOB);

			}

			//set quweeyr paranms
			if(ps!=null)
				ps.setInt(1, pid);
			if(ps!=null)
				rs=ps.executeQuery();
			//process teh resultset

			if(rs!=null)
			{
				if(rs.next()) {
					java.sql.Date sqdob=rs.getDate(1);
					java.util.Date sysDate=new java.util.Date();
					float age=((sysDate.getTime()-sqdob.getTime())/(1000.0f*60.0f*60.0f*24.0f*365.25f));
					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);
					System.out.println("Person age::"+df.format(age));
				}
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
