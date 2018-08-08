package main;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import utility.ExcelRead;
public class Sample {
ExcelRead excel = new ExcelRead();

public void ProgramToPrint()
{
	try {
		System.out.println("hello ..the program to print has just worked");
	}
	catch(Exception e)
	{
		System.out.println("error in getTableArray()");
		e.printStackTrace();
	}
}

	
	@Test(dataProvider = "data")
	public void SamplePrgm1(String userName, String passWord) {
	try{
		System.out.println("username :" + userName);
		System.out.println("passWord :" + passWord);
		System.out.println("hello.. there..");
	}		catch (Exception e) {
			System.out.println("error in getTableArray()");
			e.printStackTrace();
		}
	}
	



@Test
@DataProvider(name = "data")
public Object[][] mixed() throws Exception {
	
	return new Object[][] {{"Old", "test"},{"New", "data"}};
	
}

}