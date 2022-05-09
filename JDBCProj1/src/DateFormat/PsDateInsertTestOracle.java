package DateFormat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateInsertTestOracle {
	private static final String INSERT_DATE_QUERY="INSERT INTO PERSONAL _INFO_DATES VALUES(PID_SEQ.NEXTVAL,?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//erd inputs
			sc=new Scanner(System.in);
			String name=null,sdob=null,sdoj=null,sdom=null;
			if(sc!=null)
			{
				System.out.println("Person name");
				name=sc.next();
				System.out.println("Person dob(dd-MM-yyyy)");
				sdob=sc.next();
				System.out.println("Person doj(yyyy-MM-dd)");
				sdoj=sc.next(); 
				System.out.println("Person doM(MMM-dd-yyyy)");
				sdom=sc.next();
				
						
			}
			//convert string date values to java.sql.Date class objects
			//for dob
			//convert to java.util.date
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=sdf1.parse(sdob);
			//converting java.util.date to sql.date
			long ms=udob.getTime();
			java.sql.Date sqldob= new java.sql.Date(ms);
			System.out.println("sql date"+sqldob);
			
			
			//DOJ  direct conevrsion happens
			//converting string date to sql.date
			
			java.sql.Date sqldoj= java.sql.Date.valueOf(sdoj);
			System.out.println("sql date"+sqldoj);
			
			
			//convert string date values to java.sql.Date class objects
			//for dom  MMM-dd-yyyy
			//convert to java.util.date
			SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy");
			java.util.Date udom=sdf2.parse(sdom);
			//converting java.util.date to sql.date
			ms=udom.getTime();
			java.sql.Date sqldom= new java.sql.Date(ms);
			System.out.println("sql date"+sqldom);
			
			
			//load jdbc drievr class
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			//create ppdstmt obj
			if(con!=null) {
				ps=con.prepareStatement(INSERT_DATE_QUERY);
			}
			//set values to query params
            if(ps!=null)
            {
            	ps.setString(1, name);
            	ps.setDate(2, sqldob);
            	ps.setDate(3, sqldoj);
            	ps.setDate(4, sqldom);
            }
            //execuete query
            int count=0;
            if(ps!=null)
            	count=ps.executeUpdate();
            if(count!=0)
            	System.out.println("Record inserted");
            else
            	System.out.println("Record inserted");
            
           	
		}
		catch(SQLException e) {
		  e.printStackTrace();
		  System.out.println("Problem in data insertion");
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
				if(sc!=null)
					sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
		
		
		

	}//main

}//class
