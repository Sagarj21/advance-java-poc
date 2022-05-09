package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//modify student name and
public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			sc=new Scanner(System.in);
			String newcity=null,newName=null;
			float newAvg=0.0f;
			int no=0;
			if(sc!=null) {
				System.out.println("Enter sutudent name");
				newName=sc.nextLine();//teakes whole line ,,not a single word
				System.out.println("Enter  address");
				newcity =sc.nextLine();
				System.out.println("Enter new avg for sutudent");
				newAvg=sc.nextFloat();//
				System.out.println("Enter sno of studnet");
				no=sc.nextInt();//
			}
			newName="'"+newName+"'";//gives 'hyd'
			newcity="'"+newcity+"'";//gives 'anil rao'
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "sagar");
			if(con!=null)
				st= con.createStatement();//create stmt
			//prepare sql query
		
			String query="UPDATE STUDENT SET SNAME="+newName+",sadd="+newcity+",avg="+newAvg+",sno="+no;
			System.out.println(query);
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0)
				System.out.println("no records found");
			else
				System.out.println("Number of records updated::"+count);
			sc.close();
			
		}catch(SQLException e)
		{
			if(e.getErrorCode()>=900 && e.getErrorCode()<=999)
				System.out.println("invalid column names or sql keywords");
			else if(e.getErrorCode()==12899)
				System.out.println("do not insert more than col size");
				
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