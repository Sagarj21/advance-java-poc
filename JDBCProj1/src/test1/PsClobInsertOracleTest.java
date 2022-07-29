package test1;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/// CREATE TABLE "SYSTEM"."JOBSEEKER_INFO" 
//(	"JSID" NUMBER(10,0) NOT NULL ENABLE, 
//	"JSNAME" VARCHAR2(20 BYTE), 
//	"JSADDRS" VARCHAR2(20 BYTE), 
//	"RESUME" CLOB, 
//	 CONSTRAINT "JOBSEEKER_INFO_PK" PRIMARY KEY ("JSID")
//)
//CREATE SEQUENCE  "SYSTEM"."JSID_SEQ1"  MINVALUE 1000 MAXVALUE 100000 INCREMENT BY 1 START WITH 1000 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;


public class PsClobInsertOracleTest {
	private static final String INSERT_JOBSEEKER_QUERY="INSERT INTO JOBSEEKER_INFO VALUES(JSID_SEQ1.NEXTVAL,?,?,?)";

	public static void main(String[] args) {



		try(Scanner sc=new Scanner(System.in)){
			//read inf
			String name=null,addrs=null,photolocation=null;
			if(sc!=null) {
				System.out.println("Enter JOBSEEKER name::");
				name=sc.next();
				System.out.println("Enter arJOBSEEKER address::");
				addrs=sc.next();
				System.out.println("Enter JOBSEEKER RESUME location");
				photolocation=sc.next().replace("?","");

			}//if
			//create input stream pointing to photo file
			try(Reader reader=new FileReader(photolocation)){//try with resources
				//establish the connection and create prepared statement object
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar")){
					PreparedStatement ps=con.prepareStatement(INSERT_JOBSEEKER_QUERY);
					//set values to params
					if(ps!=null)
					{
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setCharacterStream(3, reader);
						System.out.println("******************");
					}
					//execute the query
					int count=0;
					if(ps!=null)
						count=ps.executeUpdate();
					//process the result
					if(count==0)
					{
						System.out.println("record not inserted");
					}else
						System.out.println("Record inserted");
				}//try
			}
			//try
			catch(SQLException se) {
				se.printStackTrace();
				System.out.println("Proble in record insertion");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//try
	}
}
