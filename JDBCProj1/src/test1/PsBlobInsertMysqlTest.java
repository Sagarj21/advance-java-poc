package test1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//CREATE TABLE `nataraz`.`artist_info` (
//		  `aid` INT NOT NULL AUTO_INCREMENT,
//		  `name` VARCHAR(25) NULL,
//		  `addrs` VARCHAR(25) NULL,
//		  `photo` BLOB NULL,
//		  PRIMARY KEY (`aid`),
//		  UNIQUE INDEX `aid_UNIQUE` (`aid` ASC) VISIBLE);

public class PsBlobInsertMysqlTest {
	private static final String INSERT_ARTIST_QUERY="INSERT INTO ARTIST_INFO(NAME,ADDRS,PHOTO) VALUES(?,?,?)";

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
				photolocation=sc.next().replace("?", "");

			}//if
			//create input stream pointing to photo file
			try(InputStream is=new FileInputStream(photolocation)){
				//establish the connection and create prepared statement object
				try(Connection con=DriverManager.getConnection("jdbc:mysql:///nataraz","root","root");
					PreparedStatement ps=con.prepareStatement(INSERT_ARTIST_QUERY);){

					//set values to params
					if(ps!=null)
					{
						
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setBinaryStream(3, is);
						System.out.println("****************");

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
