package ctdc.utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.*
import java.lang.String as String
import groovy.transform.Field as Field


import internal.GlobalVariable
import ctdc.utilities.ReadExcel

public class RunTestcase {


	public static WebDriver driver = new ChromeDriver()
	@Keyword
	public  void Run() {

		String filename = (GlobalVariable.G_InputExcelFileName)
		// Create an ArrayList to store the data read from excel sheet.
		List<List<XSSFCell>> sheetData = new ArrayList<>();
		FileInputStream fis = new FileInputStream(filename)
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// Get the  sheets on the workbook.
		int numberOfSheets = workbook.getNumberOfSheets();
		//for (int s = 0 ; s< numberOfSheets; s++){
		int countrow = 0
		int countcol= 0

		//Workbook workbook1 = (Workbook) workbook;
		//System.out.println( numberOfSheets)
		XSSFSheet sheet = workbook.getSheetAt(1);  // Very important that we read the sheet 1 as its test case sheet
		countrow = sheet.lastRowNum- sheet.firstRowNum;
		//System.out.println ( "roww count is  : " + countrow);
		countcol = sheet.getRow(0).getLastCellNum();
		//System.out.println("Col count : " + countcol);
		Iterator rows = sheet.rowIterator();
		while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();

			List<XSSFCell> data = new ArrayList<>();
			while (cells.hasNext()) {
				XSSFCell cell = (XSSFCell) cells.next();
				data.add(cell);
			}
			sheetData.add(data);
			//workbook.close();
		}
		//} catch (IOException e) {
		//e.printStackTrace();
		//	}
		//System.out.println(   " Before Initial " + workbook.getSheetName(0) )
		excelparsing(sheetData,driver);
		//	}
		//System.out.println(   " Before Initial " + workbook.getSheetName(s) )
		//Initialising(sheetData);

	}


	private static void excelparsing(List<List<XSSFCell>> sheetData, WebDriver driver) {
		// Iterates the data and print it out to the console.
		int countrow = 0
		countrow = sheetData.size();
		System.out.println ( " sheetdata size countrow " + countrow )
		System.out.println ( "sheet  data size :" + sheetData.get(0).size())


		for (int ii = 1; ii < countrow; ii++){
			//System.out.println ("value of  i :"  + i + "  Value of j  : " + jj )
			//for (List<XSSFCell> data : sheetData) {g
			List<XSSFCell> datarow = sheetData.get(ii);

			for (int jj = 0; jj < datarow.size(); jj++){
				System.out.println ("value of  ii :"  + ii + "  Value of jj  : " + jj )

				XSSFCell cell = datarow.get(jj);

				System.out.println ("Before Switch value  : " + sheetData.get(0).get(jj).getStringCellValue().trim()  )
				System.out.println ( "Value in column :" + sheetData.get(ii).get(jj).getStringCellValue())
				switch(sheetData.get(0).get(jj).getStringCellValue().trim() )
				{

					case("propertyName"):
						GlobalVariable.G_propertyName = sheetData.get(ii).get(jj).getStringCellValue()
						System.out.println ( " THE Property name  BEING saved :  "  +  GlobalVariable.G_propertyName )

					//Do Test here
						break;
					case("propertyvalue"):
						GlobalVariable.G_propertyvalue = sheetData.get(ii).get(jj).getStringCellValue()
						System.out.println ( " THE propertyvalue BEING saved :  "  +  GlobalVariable.G_propertyvalue )

					//Do Test here
						break;
					case("locateby"):
						GlobalVariable.G_locateby = sheetData.get(ii).get(jj).getStringCellValue()
					//Do Test here
						break;
					case("locatorvalue"):
						GlobalVariable.G_locatorvalue = sheetData.get(ii).get(jj).getStringCellValue()
					//Do Test here
						break;
					case("action"):
						GlobalVariable.G_Action = sheetData.get(ii).get(jj).getStringCellValue()
					//Do Test here
						break;
					case("Query"):

						GlobalVariable.G_Query = sheetData.get(ii).get(jj).getStringCellValue()
						System.out.println ( " THE QUERY BEING saved :  "  +  GlobalVariable.G_Query )




					//Do Test here
						break;
					case("Page"):
						GlobalVariable.G_Page = sheetData.get(ii).get(jj).getStringCellValue()
						break;
					case("Function"):


						System.out.println ( "  the value tobe uses in the function call  : " + sheetData.get(ii).get(jj).getStringCellValue().trim() )
						switch(sheetData.get(ii).get(jj).getStringCellValue().trim() )
						{
							case("InitialLoad"):
								ReadExcel.initialLoad()
								ReadExcel.PrintG()
								break;
							case ("Dbconnect"):
								System.out.println  (" In dataload")
								ReadExcel.Neo4j()
								break;

							case ("action_click"):

								System.setProperty('webdriver.chrome.driver',  GlobalVariable.G_BrowserDriverPath )

								System.out.println ( " locator from excel  :"  + GlobalVariable.G_locatorvalue )

								driver.get(GlobalVariable.G_Page)

								driver.findElement(By.xpath( GlobalVariable.G_locatorvalue)).click()

								System.out.println( " clicked on :" + GlobalVariable.G_locatorvalue )

								driver.manage().window().maximize()

								break;


							default:
								System.out.println ("nothing in function column :")
								break;
						}
						break;
					case("Run"):
						GlobalVariable.G_Run = sheetData.get(ii).get(jj).getStringCellValue()
						break;
					case("Otherimportuser"):
						break;

					default :
						System.out.println ( " here at the last ")
						break;
				}


			}
		}
	}





	//----------------web data --------------




	@Keyword
	public static ArrayList<String> CaseData() {
		List<String> webData = new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver", GlobalVariable.G_BrowserDriverPath);  //path of browser driver is in global variable under profiles
		WebDriver driver = new ChromeDriver()
		driver.get("https://trialcommons-qa.cancer.gov/#/cases")
		driver.manage().window().maximize();		// WebDriver driver = DriverFactory.getWebDriver()
		//'To locate table'
		//int i = 0
		//*[@id="table_cases"]/div

		WebElement Table = driver.findElement(By.xpath('//*[@id=\"table_cases\"]/div'));

		List<WebElement> rows_table = Table.findElements(By.xpath("//*[contains(@id, \"MUIDataTableBodyRow-\")]"))
		int rows_count = rows_table.size()
		System.out.println("This is the size of the rows in the table in first page:"+(rows_count))
		//'hardcode'
		WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"table_cases\"]/div/table/tfoot/tr/td/div/div[3]/button[2]"));

		WebElement tableHdr = driver.findElement(By.xpath("//*[@id=\"table_cases\"]/div/div[2]/table/thead"))

		List<WebElement> colHeader = tableHdr.findElements(By.tagName("th"));
		int columns_count = (colHeader.size())-1
		System.out.println("No.of cols is : "+columns_count)
		String hdrdata = ""
		for(int c=1;c<=columns_count;c++){
			hdrdata = hdrdata + ((colHeader.get(c).getText()) + "||");
		}
		webData.add(hdrdata);
		System.out.println("Size of web data list with header :" +webData.size())
		for(int index = 0; index < webData.size(); index++) {
			System.out.println("Web Data: with header data is :" + webData.get(index))
		}
		while(nextButton.isEnabled()){
			// String pageNm = driver.findElement(By.xpath("//*[@id=\"table_cases\"]/div[2]/div[2]/div/table/tfoot/tr/td/div/span[2]")).getText();
			// System.out.println ("This is the page number : " +pageNm)
			int i = 0
			int j = 0
			for (i = 1; i <= rows_count; i++) {
				String data = ""
				for (j = 1; j < columns_count+10; j = j + 2) {
					data = data + (driver.findElement(By.xpath("//*[@id=\"table_cases\"]/div/div[2]/table/tbody/tr" + "[" + i + "]/*[" + j + "]"))
							.getText() + "||")

					//*[@id="table_cases"]/div[2]/div[2]/div/div[2]/table/tbody
					//*[@id="MUIDataTableBodyRow-58"]
					//*[@id="MUIDataTableBodyRow-16"]/td[1]
				}
				webData.add(data)
			}


			System.out.println("Size of Web Data list in current page is : " + webData.size())
			for(int index = 0; index < webData.size(); index++) {
				System.out.println("Web Data: from current page is" + webData.get(index))
			}
			nextButton.click()
			Thread.sleep(3000)
			System.out.println("navigated to next page")


			//			if ((nextButton.isEnabled()){
			//				nextButton.click();
			//			    Thread.sleep(3000)
			//			    System.out.println("navigated to next page")
			//				boolean buttonState= true
			//			   }else{
			//			   buttonState =false
			//			   }
		}
		//
		//}while((nextButton.isEnabled());

		System.out.println(webData);
		GlobalVariable.G_CaseData=webData
		return webData;

	}
}

