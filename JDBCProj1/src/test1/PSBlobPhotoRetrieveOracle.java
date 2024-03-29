package test1;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PSBlobPhotoRetrieveOracle {
	private static final String ARTIST_RETRIEVE_QUERY="SELECT AID,NAME,ADDRS,PHOTO FROM ARTIST_INFO WHERE AID=?";

	public static void main(String[] args) {
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			int aid=0;
			if(sc!=null) {
				System.out.println("enter artist id::");
				aid=sc.nextInt();	
			}
			//create connection and preparedStatement object
			try(Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
					PreparedStatement ps=con.prepareStatement(ARTIST_RETRIEVE_QUERY);
					)
			{
				//set query params
				if(ps!=null)
					ps.setInt(1, aid);

				//execute query
				try(ResultSet rs =ps.executeQuery()){
					//process teh resulrset

					if(rs!=null) {
						if(rs.next())
						{
							aid=rs.getInt(1);
							String name=rs.getString(2);
							String addrs=rs.getString(3);
							System.out.println(aid+"   "+name+"   "+addrs);
							//get input stream pointing to blob clumn value
							try( InputStream is = rs.getBinaryStream(4);
									//crette output stream
									OutputStream os=new FileOutputStream("retrieve_image.jpg");
									){
								//copy blob column value to destination folder
								IOUtils.copy(is, os);//source to destination ,download commons jar file from mvn repo
								System.out.println("blob value is retrieved and stored into file");
							}//t4

						}//if
						else {
							System.out.println("record not found");
						}
					}//if
				}//t3
				


			}///t2
			
		}//t1
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}//main

}//class
