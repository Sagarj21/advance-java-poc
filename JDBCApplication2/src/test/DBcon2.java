package test;
import java.sql.*;
public class DBcon2 {

	public static void main(String[] args) {
		try {
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
			//creating connection
			Statement stm=con.createStatement();//jdbc statement
			ResultSet rs=stm.executeQuery("select * from Product41");
			while(rs.next())
			{
				System.out.println(rs.getString(1)+"\t"+ rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getInt(4));
				
				
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
