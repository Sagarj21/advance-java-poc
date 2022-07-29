package test1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTestTWR {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			sc=new Scanner(System.in);
			int dno=0;
			if(sc!=null)
			{
				System.out.println("enter dpt no");
				dno=sc.nextInt();//gives 1
			}
       	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
    		//loading driver automatically and creating connection
		 //create jdbc stmt object
       	 if(con!=null)
       	 {
       		 st=con.createStatement();
         }
       	 //prepere sql query
       	 //select * from dept wjere deptno=40;
       	 String query="SELECT * FROM DEPT WHERE DEPTNO="+dno;
       	 System.out.println(query);
       	 
       	 //send and execute a query
       	 if(st!=null)
       	 {
       		 rs=st.executeQuery(query);
       		 
       	 }
       	 //process result
       	 if(rs!=null)
       	 {
       		 if(rs.next())
       		 {
       			 System.out.println(rs.getInt(1)+""+rs.getString(2) +"  "+rs.getString(3));
       			 
       		 }
       		 else
       			 System.out.println("no record found");
       			        			 
       	 }//if
       	 sc.close();
       	 
       	 
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception ss)
		{
			ss.printStackTrace();
		}
		

	}

}


//it is not recommended to place * symbol in select sql query of jdbc application bcoz it will not give clarity while processing redutset
//always write query after specifying col name














