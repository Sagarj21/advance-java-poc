package DateFormat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateRetriveByDateRange {
	private static final String RETRIVE_DATE_QUERY="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSONAL_INFO_DATES WHERE DOB>=? AND DOB<=?";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			String sdob=null,edob=null;
			if(sc!=null)
			{
				System.out.println("Enter start range of dob(dd-MM-yyyy)");
				sdob=sc.next();
				System.out.println("Enter end range of dob(dd-MM-yyyy)");
				edob=sc.next();
			}
				//convert styring date values to java.util.date value
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				java.sql.Date ssqdob=new java.sql.Date( sdf.parse(sdob).getTime());
				java.sql.Date esqdob=new java.sql.Date(sdf.parse(edob).getTime());


				//load jdbc drievr class
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
				//create ppdstmt obj
				if(con!=null) {
					ps=con.prepareStatement(RETRIVE_DATE_QUERY);
				}

				//set values to query parameters
				if(ps!=null) {
					ps.setDate(1, ssqdob);
					ps.setDate(2, esqdob);
					rs=ps.executeQuery();
				}
				//execute query

				
				System.out.println("---------------------");
				//process resultset again for

				if(rs!=null) {
					System.out.println("---------------------");

					while(rs.next()) {
						System.out.println("---------------------");
						int pid=rs.getInt(1);
						String name=rs.getString(2);
						java.sql.Date sqdob=rs.getDate(3);
						java.sql.Date sqdoj=rs.getDate(4);
						java.sql.Date sqdom=rs.getDate(5);
						//convert java.sql.date to STring date values
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						String dob=sdf1.format(sqdob);
						String doj=sdf1.format(sqdoj);
						String dom=sdf1.format(sqdom);

						System.out.println(pid+"  "+name+"  "+dob+"  "+doj+"  "+dom);
					}

				}


			}
			catch(SQLException e) {
				e.printStackTrace();

			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(con!=null)
						con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				try {
					if(ps!=null)
						ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				try {
					if(rs!=null)
						rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				try {
					if(sc!=null)
						sc.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}//finally




		}//main

	}//class
