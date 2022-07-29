package jdbc_2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CSProcedureTest {
	private static final String CALL_PROCEDURE="{CALL FIRST_PRO(?,?,?)";
	public static void main(String[] args) {
		int first=0,second=0;


		try( Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("enter first value");
				first=sc.nextInt();
				System.out.println("enter second value");
				second=sc.nextInt();
			}
			//CONNECTION
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
					//CREATE callable statemnnts object calling procedure
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
				//register out params with jdbc datatypes
				if(cs!=null)
					cs.registerOutParameter(3, Types.INTEGER);
				//set values to in params
				if(cs!=null) {
					cs.setInt(1, first);
					cs.setInt(2, second);
				}
				//call plsql function
				if(cs!=null)
					cs.execute();
				//gather results from outparam
				int result=0;
				if(cs!=null) {
					result=cs.getInt(3);
					System.out.println("sum is::"+result);
				}
			}//try2

		}//try1
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
