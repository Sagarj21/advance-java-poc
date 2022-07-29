package test1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*CREATE TABLE `nataraz`.`jobseeker_info` (
		  `JSID` INT NOT NULL,
		  `JSNAME` VARCHAR(25) NULL,
		  `jsad` VARCHAR(25) NULL,
		  `resume` LONGTEXT NULL,
		  `photo` BLOB NULL,
		  PRIMARY KEY (`JSID`));*/
public class PsClobInsertMysqlTest {
	private static final String INSERT_JOBSEEKER_QUERY="INSERT INTO JOBSEEKER_INFO(JSNAME,JSad,RESUME,PHOTO) VALUES(?,?,?,?)";

	public static void main(String[] args) {

		try(Scanner sc=new Scanner(System.in)){
			//read inp
		
			String name=null,addrs=null,resumelocation=null,photolocation=null;;
			if(sc!=null) {
				
				System.out.println("Enter JS name::");
				name=sc.next();
				System.out.println("Enter JS address::");
				addrs=sc.next();
				System.out.println("Enter resume address::");
				resumelocation=sc.next().replace("?", "");
				System.out.println("Enter photo location");
				photolocation=sc.next().replace("?", "");
				

			}//if
			//create input stream pointing to photo file
			try(Reader reader=new FileReader(resumelocation);
			InputStream is=new FileInputStream(photolocation)){
				//establish the connection and create prepared statement object
				try(Connection con=DriverManager.getConnection("jdbc:mysql:///nataraz","root","root");
					PreparedStatement ps=con.prepareStatement(INSERT_JOBSEEKER_QUERY);){
					//set values to params
					if(ps!=null)
					{						
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setCharacterStream(3, reader);
						ps.setBinaryStream(4, is);
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
