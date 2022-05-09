package test1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//CREATE TABLE "SYSTEM"."ARTIST_INFO" 
//(	"AID" NUMBER(10,0) NOT NULL ENABLE, 
//	"NAME" VARCHAR2(20 BYTE), 
//	"ADDRS" VARCHAR2(20 BYTE), 
//	"PHOTO" BLOB, 
//	 CONSTRAINT "ARTIST_INFO_PK" PRIMARY KEY ("AID"))


//CREATE SEQUENCE  "SYSTEM"."AID_SQL"  MINVALUE 1000 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1000 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;


public class PsBlobInsertTest {
	private static final String INSERT_ARTIST_QUERY="INSERT INTO ARTIST_INFO VALUES(AID_SQL.NEXTVAL,?,?,?)";

	public static void main(String[] args) {



		try(Scanner sc=new Scanner(System.in)){
			//read inp
			String name=null,addrs=null,photolocation=null;
			if(sc!=null) {
				System.out.println("Enter artist name::");
				name=sc.next();
				System.out.println("Enter artist address::");
				addrs=sc.next();
				System.out.println("Enter photo location");
				photolocation=sc.next();

			}//if
			//create input stream pointing to photo file
			try(InputStream is=new FileInputStream(photolocation)){
				//establish the connection and create prepared statement object
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar")){
					PreparedStatement ps=con.prepareStatement(INSERT_ARTIST_QUERY);

					//set values to params
					if(ps!=null)
					{
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setBinaryStream(3, is);
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
