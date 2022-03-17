package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleJDBCExecuteQueryTest {

	@Test
	public void sampleJDBCExecuteQuery() throws Throwable
	{
		Connection con = null;
		try
		{
			//Step 1: register the database
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			
			//Step 2: get connector from database - provide db name
			con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "root");
			System.out.println("Connection established");
			
			//Step 3: issue create statement
			 java.sql.Statement state = con.createStatement();
			
			//Step 4: execute Query --provide table name
			ResultSet result = state.executeQuery("select * from sttudent;");
			
			while(result.next())
			{
				System.out.println(result.getString(1)+" "+result.getString(2)+ " "+result.getString(3));
			}
		}
		catch(Exception e)
		{
			
		}
		finally  //to close database connection to avoid data leakage, memory, performance issue
		{		
		//Step 5: close the database
		con.close();
		System.out.println("Connection closed");
		}
		
	}
}
