package test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SelectTest55 {

	public static void main(String[] args)  {


		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {

			//load jdbc drivber cLASS
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			if(con!=null)
				st=con.createStatement();

			//prepare sql query
			//select (*) from emp
			String query="SELECT COUNT(*) FROM EMP";
			System.out.println(query);

			//send and execute query
			if(st!=null)
				rs=st.executeQuery(query);

			//process the resultSet (0 record)
			if(rs!=null) {
				rs.next();
				int count=rs.getInt(1);
				System.out.println("records count in emp"+count);

			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();

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

		}//finally
	}//main
}//class
