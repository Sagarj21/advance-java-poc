package jdbc_2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
//CREATE OR REPLACE FUNCTION FX_GET_STUDENTDETAILS_BY_NO 
//(
//  NO IN VARCHAR2 
//, NAME OUT VARCHAR2 
//, ADDRS OUT VARCHAR2 
//) RETURN FLOAT AS
// PERCENTAGE FLOAT;
//BEGIN
//  SELECT SNAME ,SADD,AVG INTO NAME,ADDRS,PERCENTAGE FROM STUDENT WHERE SNO=NO;
//  RETURN PERCENTAGE;
//  END FX_GET_STUDENTDETAILS_BY_NO;
public class CSFunctionTest {
	private static final String CALL_FX_QUERY="{?=call FX_GET_STUDENTDETAILS_BY_NO (?,?,?)}";
	public static void main(String[] args) {
	
		try( Scanner sc=new Scanner(System.in)){
			int no=0;
			if(sc!=null) {
				System.out.println("enter student no");
				no=sc.nextInt();
				
			}
			//CONNECTION
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
					//CREATE callable statemnnts object calling procedure
					CallableStatement cs=con.prepareCall(CALL_FX_QUERY);){
				//register out params with jdbc datatypes
				if(cs!=null)
					cs.registerOutParameter(1, Types.FLOAT);//return param
				cs.registerOutParameter(3, Types.VARCHAR);//OUT PARAM
				cs.registerOutParameter(4, Types.VARCHAR);//OUT PARAM
				//set values to in params
				if(cs!=null) {
					cs.setInt(2,no);
				}
				//call plsql function
				if(cs!=null)
					cs.execute();
				//gather results from outparam
				if(cs!=null) {
					System.out.println("student name"+cs.getString(3));//out param
					System.out.println("student addrs"+cs.getString(4));//out param
					System.out.println("student avg"+cs.getString(1));//return param
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
