package test;
//delete stuent from address
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		
		
		try {
			sc=new Scanner(System.in);
			String city=null;
			if(sc!=null) {
				System.out.println("Enter sutudent address");
				city=sc.next();//
			}
			city="'"+city+"'";//gives 'hyd'
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "sagar");
			if(con!=null)
				st= con.createStatement();//create stmt
			//prepare sql query
			// delete from student where sadd='hyd';
			String query=" DELETE FROM STUDENT WHERE SADD="+city;
			System.out.println(query);
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0)
				System.out.println("no records found");
			else
				System.out.println("Number of records affected::"+count);
			
		}catch(SQLException e)
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
				if(st!=null)
					st.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			
		}

	}

}