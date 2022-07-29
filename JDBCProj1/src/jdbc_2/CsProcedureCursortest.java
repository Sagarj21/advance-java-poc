package jdbc_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.CallableStatement;

import oracle.jdbc.OracleTypes;

/*
CREATE OR REPLACE PROCEDURE P_GET_EMPS_BYNAME_INITIAL 
(
  INITIALCHARS IN VARCHAR2 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
    OPEN DETAILS FOR
        SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE ENAME LIKE INITIALCHARS;

END P_GET_EMPS_BYNAME_INITIAL;*/
public class CsProcedureCursortest {
	private static final String PROCEDURE_CALL_QUERY="{CALL  P_GET_EMPS_BYNAME_INITIAL(?,?) }";
	public static void main(String[] args) {
		String initchars=null;
		try(Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("enter initial chars of emp name");
				initchars=sc.next()+"%";
			}
			try(//connection
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
					CallableStatement cs=con.prepareCall(PROCEDURE_CALL_QUERY);
					){
				//register out paramenters
				if(cs!=null) 
					cs.registerOutParameter(2, OracleTypes.CURSOR);
				//set value to in parameter
				if(cs!=null) 
					cs.setString(1, initchars);
				//execute or call pl sql
				if(cs!=null)
					cs.execute();
				//gather results from out parameters
				if(cs!=null) {
					ResultSet rs=(ResultSet)cs.getObject(2);
					System.out.println("output");
					boolean flag=false;
					while(rs.next()) {
						flag=true;
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getFloat(4)+ "   "+rs.getInt(5));
					}//while
					if(flag==false)
						System.out.println("records not found");
				}//if


			}//t2
		}//t1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception se) {
			se.printStackTrace();
		}
	}//main

}//class
