package DateFormat;

import java.text.SimpleDateFormat;

public class DateValueConversion {

	public static void main(String[] args)throws Exception {
		//converting string date value to java.util.Date class
		String s1="21-11-1990";//dd-mm-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf.parse(s1);
		System.out.println("String date value"+s1);
		System.out.println("String date value"+ud1);
		
		//converting java.util.date to java.sql.date obj
		long ms=ud1.getTime();//gives number of milisecs that elsps between ud1 date and time and 1970 jan 1st midnight 00.00hrs
		java.sql.Date sd1=new java.sql.Date(ms);
		System.out.println("util date"+s1);
		System.out.println("sql date"+sd1);
		
		
		
		//if string date value is yyyy-MM-dd then it can be cob=nverted directly to java.sql.Date class obj without converting into java.util.Date obj
		String s2="1991-12-25";
		java.sql.Date sqd2=java.sql.Date.valueOf(s2); //stATIC METHOD OF java.sql.date class
		System.out.println("string date value"+s2);
		System.out.println("sql date value"+sqd2); //default date pattern of sql.Date class is yyyy-MM-dd
		//extra days in date value will not be added ,rather we wil get illegal arg exception
		
		
		
		//converting java.sql.Date class obj to java.util.Date class obj
		//here we can use java.util.date ref java.sql.date obj ,no need of typecasting
		java.util.Date ud2=sqd2;
		System.out.println("sql date 2::"+sqd2);
		System.out.println("util date 2::"+ud2);
		
		//converting java.sql.Date class obj or java.util.Date class obj to string date value
		SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
		String s3=sdf2.format(sqd2);
		System.out.println("sql date 2::"+sqd2);
		System.out.println("util date 2::"+ud2);
		System.out.println("String date value::"+s3);//gives dd-MM-yyyy
				
		
		
	}

} 