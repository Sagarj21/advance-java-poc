package jdbc_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PSScrollableRSTest {
private static final String EMP_SELECT_QUERY="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","sagar");
				PreparedStatement ps=con.prepareStatement(EMP_SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs=ps.executeQuery();
				){
			if(rs!=null) {
			System.out.println("rs records top to bottom");
			while(rs.next()){
				System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			}
			System.out.println(" ------------------------------");
			System.out.println("rs records bottom to top ");
			rs.afterLast();
			while(rs.previous()){
				System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			}
			System.out.println(" ------------------------------");
			rs.first();
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			System.out.println(" ------------------------------");
			rs.last();
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			System.out.println(" ------------------------------");
			rs.absolute(3);
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			System.out.println(" ------------------------------");
			rs.absolute(-6);
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			System.out.println(" ------------------------------");
			rs.relative(-3);
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			System.out.println(" ------------------------------");
			rs.relative(-4);
			System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			System.out.println(" ------------------------------");
			}//if
		}//t1
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception se)
		{
			se.printStackTrace();
		}


	}

}
