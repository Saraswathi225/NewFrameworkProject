package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path =".\\testData\\Opencart_LoginData.xlsx"; // taking excel file from this path
		ExcelUtils xlutils = new ExcelUtils(path);
		
		int noofrows = xlutils.getRowCount("Sheet1");
		int noofcolumns = xlutils.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[noofrows][noofcolumns];
		for(int i=1;i<=noofrows;i++) 
		{
			for(int j=0;j<noofcolumns;j++)
			{
				logindata[i-1][j]= xlutils.getcellData("Sheet1", i, j);
			}
		}
		
		return logindata;	
		
		
	}
  
	
	// DataProvider 2
	// DataProvider 3
	// DataProvider 4
	

}
