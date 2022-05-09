package Test1;
//video no 46
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMysqlDataTransferTest {
	private static final String MYSQL_INSERT_STUDENT="INSERT INTO STUDENT( SNO,SNAME,SADD) VALUES(?,?,?)";
	private static final String ORACLE_INSERT_STUDENT="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		Connection oracon=null,mysqlcon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//register jdbc driver(optional)
			Class.forName("Oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish cinnection
			oracon=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:orcl","system","sagar");
			mysqlcon=DriverManager.getConnection("jdbc:mysql:///ntaj415db","root","root");


			//create statement object
			if(oracon!=null)
			{
				st=oracon.createStatement();
			}
			if(mysqlcon!=null)
			{
				ps=mysqlcon.prepareStatement(MYSQL_INSERT_STUDENT);
			}
			//send and execute sselcet sql query and get resulrtseti obej
			if(st!=null)
				rs=st.executeQuery(ORACLE_INSERT_STUDENT);
			//gather each record of resultset obj and insert inot mysql db table
			if(rs!=null && ps!=null)
			{
				while(rs.next()) {
					//gather each record from resultsset
					int no=rs.getInt(1);
					String  name=rs.getString(2);
					String addrs=rs.getString(3);
					float avg=rs.getFloat(4);
					//set Each record values as INSERT QUERY PARAM VALUE
					
					ps.setString(1, name);
					ps.setString(2, addrs);
					ps.setFloat(3, avg);
					//exectie the query 
					ps.executeUpdate();

				}
				System.out.println("Reco=rds are copied to mysql db table sucessfully");
			}//if


		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
			System.out.println("Records are not copied from oracle to mysql db table");
		}
		catch(Exception se)
		{
			se.printStackTrace();
			System.out.println("Problem in application execuution");
		}
		finally
		{
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException se)
			{
				se.printStackTrace();

			}
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se)
			{
				se.printStackTrace();

			}
			try {
				if(ps!=null)
					ps.close();
			}catch(SQLException se)
			{
				se.printStackTrace();

			}
			try {
				if(oracon!=null)
					oracon.close();
			}catch(SQLException se)
			{
				se.printStackTrace();

			}
			try {
				if(mysqlcon!=null)
					mysqlcon.close();
			}catch(SQLException se)
			{
				se.printStackTrace();

			}
		}//finally



	}//main

}//class
