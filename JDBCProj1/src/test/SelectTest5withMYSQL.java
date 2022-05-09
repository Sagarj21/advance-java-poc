package test;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Scanner;

public class SelectTest5withMYSQL {

	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			
       	 con=DriverManager.getConnection("jdbc:mysql:///nataraz","root","root");
    		//loading driver automatically and creating connection
		 //create jdbc stmt object
       	 if(con!=null)
       	 {
       		 st=con.createStatement();
         }
       	 //prepere sql query
       	 //select count(*) from student;
       	 String query="SELECT COUNT(*) FROM ARTIST_INFO";
       	 System.out.println(query);
       	 
       	 //send and execute a query
       	 if(st!=null)
       	 {
       		 rs=st.executeQuery(query);
       		 
       	 }
       	 //process result
       	 if(rs!=null)
       	 {
       		 rs.next();
       		 //int count=rs.getInt(1);
       		 int count=rs.getInt("COUNT(*)");
       		 System.out.println("records count in student db table  ::"+count);
       			        			 
       	 }//if
       	
       	 }catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception ss)
		{
			ss.printStackTrace();
		}
		finally {
			//close djbc connection
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
			  se.printStackTrace();
			}
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


//it is not recommended to place * symbol isn select sql query of djcb appliction bcoz it willnot give clairty while processing reduutset
//aleways write query after specifying col name














