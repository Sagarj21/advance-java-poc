 package DateFormat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PsDateRetriveTestOracle {
	private static final String RETRIVE_DATE_QUERY="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSONAL_INFO_DATES";

	public static void main(String[] args) {

		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {

			//load jdbc drievr class
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			//create ppdstmt obj
			if(con!=null) {
				ps=con.prepareStatement(RETRIVE_DATE_QUERY);
			}


			//execute query

			if(ps!=null) {
				rs=ps.executeQuery();
			}
			//process resultset obj
			/*if(rs!=null) {

				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5));
				}

			}*/
			System.out.println("---------------------");
			//process resultset again for
			
			if(rs!=null) {

				while(rs.next()) {
					int pid=rs.getInt(1);
					String name=rs.getString(2);
					java.sql.Date sqdob=rs.getDate(3);
					java.sql.Date sqdoj=rs.getDate(4);
					java.sql.Date sqdom=rs.getDate(5);
					//convert java.sql.date to STring date values
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String sdob=sdf.format(sqdob);
					String sdoj=sdf.format(sqdoj);
					String sdom=sdf.format(sqdom);

					System.out.println(pid+"  "+name+"  "+sdob+"  "+sdoj+"  "+sdom);
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
		}//finally




	}//main

}//class
