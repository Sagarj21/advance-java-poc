package test1;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PSClobRetrieveMysql {
	private static final String ARTIST_JOBSEEKER_QUERY="SELECT jsID,jsNAME,resume,PHOTO FROM JOBSEEKER_INFO WHERE JSID=?";

	public static void main(String[] args) {
		//read inputs
		try(Scanner sc=new Scanner(System.in)){//try1
			int jsid=0;
			if(sc!=null) {
				System.out.println("enter JOBSEEKER id::");
				jsid=sc.nextInt();	
			}
			//create connection and preparedStatement object
			try(Connection con= DriverManager.getConnection("jdbc:mysql:///nataraz","root","root");
					PreparedStatement ps=con.prepareStatement(ARTIST_JOBSEEKER_QUERY);
					)
			{//try2
				//set query params
				if(ps!=null)
					ps.setInt(1, jsid);

				//execute query
				try(ResultSet rs =ps.executeQuery()){//try3
					//process teh resulrset

					if(rs!=null) {
						if(rs.next())
						{
							jsid=rs.getInt(1);
							String name=rs.getString(2);
							
							System.out.println(jsid+"   "+name);
							//get reader stream pointing to blob clumn value
							try( Reader reader = rs.getCharacterStream(3);
									InputStream is=rs.getBinaryStream(4);
									//crette output stream
									OutputStream os=new FileOutputStream("retrieve_image_mysql.jpg");
									Writer writer=new FileWriter("retrieve_resume.Txt");){//try4
								//copy blob column value to destination folder
								IOUtils.copy(is, os);//takes images
								IOUtils.copy(reader,writer);//takes texts
								System.out.println("blob,clob value is retrieved and stored into file");
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
