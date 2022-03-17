package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;


public class DataVerificationDBTest {

	private static final String DriverManager = null;

	@Test
	public void sampleJDBCExecuteQuery() throws Throwable
	{
        String expData = "Nithesh";
		Connection con = null;
		
			//Step 1: register the database
			Driver driverRef = new Driver();
			//DriverManager.registerDriver(driverRef);
			
			//Step 2: get connector from database - provide db name
			//con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "root");
			System.out.println(("Connection established"));
			
			//Step 3: issue create statement
			 java.sql.Statement state = con.createStatement();
			
			//Step 4: execute Query --provide table name
			ResultSet result = state.executeQuery("select * from student;");
			
			while(result.next())
			{
		          String actData = result.getString(2);
		          System.out.println(actData);
		          /*if(expData.equalsIgnoreCase(actData))
		           {
			       System.out.println(actData+ " data is verified");
			        break;
		     }*/
				
      }
			con.close();
			System.out.println("Connection is closed");
		}
}
