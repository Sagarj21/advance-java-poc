package jdbc_2;

import java.sql.CallableStatement;
//create or replace NONEDITIONABLE PROCEDURE P_AUTH 
//(
//  USERNAME IN VARCHAR2 
//, PASSWORD IN VARCHAR2 
//, RESULT OUT VARCHAR2 
//) AS 
// CNT NUMBER(5);
//BEGIN
//  SELECT COUNT(*) INTO CNT FROM IRCTC_TAB WHERE UNAME=USERNAME AND PWD=PASSWORD;
//  IF(CNT<>0) THEN
//    RESULT:='VALID CREDENTIALS';
//  ELSE
//    RESULT:='INVALID CREDENTIALS';
//  END IF;
//END P_AUTH;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTestAuth {
	private static final String call_procedure_query="{CALL P_AUTH(?,?,?)}";
	public static void main(String[] args) {
		String username=null,password=null;
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("enter user name");
				username=sc.next();
				System.out.println("enter password");
				password=sc.next();

			}
			try(//connection
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
					//create callable statement
					CallableStatement cs=con.prepareCall(call_procedure_query)	;){
				if(cs!=null){
					//register out params
					cs.registerOutParameter(3, Types.VARCHAR);
				}
				if(cs!=null){
					//set values to in param
					cs.setString(1, username);
					cs.setString(2, password);
				}
				//call pl sql procedure
				if(cs!=null)
					cs.execute();
				//gather result
				String result=null;
				if(cs!=null)
					result=cs.getString(3);
				System.out.println(result);

			}//t2
		}//t1
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main

}//class













