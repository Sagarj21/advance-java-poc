package jdbc_2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
//CREATE OR REPLACE PROCEDURE P_GET_EMPDETAILS_BY_ID 
//(
//  NO IN VARCHAR2 
//, NAME OUT  VARCHAR2 
//, DESIG OUT VARCHAR2 
//, SALARY OUT VARCHAR2 
//) AS 
//BEGIN
//SELECT ENAME ,JOB,SAL INTO NAME,DESIG,SALARY FROM EMP WHERE EMPNO=NO;
//
//END;
public class CSProcedureTest2 {
	private static final String CALL_PROCEDURE="{CALL P_GET_EMPDETAILS_BY_ID (?,?,?,?)";
	public static void main(String[] args) {
		int no=0;
		try( Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("enter femp no");
				no=sc.nextInt();
				
			}
			//CONNECTION
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
					//CREATE callable statemnnts object calling procedure
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
				//register out params with jdbc data types
				if(cs!=null) {
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.FLOAT);
				}
				
				//set values to in parameter
				if(cs!=null) {
					cs.setInt(1, no);
					
				}
				//call plsql function
				if(cs!=null)
					cs.execute();
				//gather results from outparam
				String name=null,desg=null;
				float sal=0;
				if(cs!=null) {
					name=cs.getString(2);
					desg=cs.getString(3);
					sal=cs.getFloat(4);
					System.out.println("name     "+name+"\n designamion      "+desg+"\nsalary         "+sal);
				}
			}//try2

		}//try1
		catch(SQLException e) {
			System.out.println("requested data not available");
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}