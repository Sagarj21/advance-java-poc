package test1; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSelectTest3{
	private static final String SELECT_EMP_QUERY="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN ('CLERK','MANAGER')";

	public static void main(String[] args) 
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {

			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			if(con!=null)
				ps=con.prepareStatement(SELECT_EMP_QUERY);
			if(ps!=null)
				rs=ps.executeQuery();
			if(rs!=null) {
				boolean flag=false;
				while(rs.next())
				{
					flag=true;

					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getFloat(4));
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception ss)
		{
			ss.printStackTrace();
		}
		finally {

			try {
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException s)
			{
				s.printStackTrace();
			}

			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}

		}


	}

}


