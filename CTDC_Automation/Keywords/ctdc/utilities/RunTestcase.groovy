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

	@Keyword
	public static WebDriver driver = new ChromeDriver()
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

				System.out.println ("Before swith value  : " + sheetData.get(0).get(jj).getStringCellValue().trim()  )
				System.out.println ( "VALES :" + sheetData.get(ii).get(jj).getStringCellValue())
				switch(sheetData.get(0).get(jj).getStringCellValue().trim() )
				{

					case("propertyName"):
						GlobalVariable.G_propertyName = sheetData.get(ii).get(jj).getStringCellValue()
					//Do Test here
						break;
					case("propertyvalue"):
						GlobalVariable.G_propertyvalue = sheetData.get(ii).get(jj).getStringCellValue()
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
					//Do Test here
						break;
					case("Page"):
						GlobalVariable.G_Page = sheetData.get(ii).get(jj).getStringCellValue()
						break;
					case("Function"):

						System.out.println ( " ******************************* IN the FUnction **************************")
						System.out.println ( "  the value tobe uses in the function call  : " + sheetData.get(ii).get(jj).getStringCellValue().trim() )
						switch(sheetData.get(ii).get(jj).getStringCellValue().trim() )
						{
							case("InitialLoad"):

								System.out.println ("  in the FUNCTION hjdhgdfghdghdfjdfgdWORK NOW dumbo ")
							//CustomKeywords.'ctdc.utilities.ReadExcel.initialLoad'()
								ReadExcel.initialLoad()
								ReadExcel.PrintG()
								break;
							case ("Dbconnect"):
								ReadExcel.Neo4j()
								break;

							case ("action_click"):

								System.setProperty('webdriver.chrome.driver',  GlobalVariable.G_BrowserDriverPath )
							//	'C:\\Users\\radhakrishnang2\\Desktop\\DataCommons_Automation\\CTDC_Automation\\chromedriver.exe')
								System.out.println ("//******************IN ACTION CLICKKKKKKK********************************* ")

								System.out.println ( " locator from excel  :"  + GlobalVariable.G_locatorvalue )
							//WebDriver driver = new ChromeDriver()

								driver.get(GlobalVariable.G_Page)

							//driver.findElement(By.xpath("//*[@type = 'button' and (text() = 'Cases' or . = 'Cases')]")).click()

								driver.findElement(By.xpath( GlobalVariable.G_locatorvalue)).click()

								System.out.println( " clicked on :" + GlobalVariable.G_locatorvalue )

								driver.manage().window().maximize()
							//driver.get("https://caninecommons.cancer.gov/#/cases")

							//ArrayList<String> elemLoc = CustomKeywords.'readInputExcel.G_readInputExcel'('This is from a test case for reading input')

							//		String casesLink = elemLoc.get(15)
							//
							//String trialArm = elemLoc.get(25)

							//	String trialArmOption = elemLoc.get(35)

							//	String gender = elemLoc.get(45)

							//	String genderOption = elemLoc.get(55)

							//String clearAllButton = elemLoc.get(71)
							//System.out.println("Before clicking on the Cases button using locator : ")

							//driver.get(casesLink)
							//String loc = '//*[@id="button_navbar_cases"]'
							//WebElement casesLink1 = driver.findElement(By.xpath("//*[@id=\"button_navbar_cases\"]"))  // only this type works
							//WebElement leep(3000)
							//WebElement casesLink1 = driver.findElement(By.id(casesLink)).click()

							//WebUI.click("//*[@type = 'button' and (text() = 'Cases' or . = 'Cases')]")
							//								driver.findElement(By.xpath ("//*[@type = 'button' and (text() = 'Cases' or . = 'Cases')]")).click()

							//WebElement casesLink1 = driver.findElement(By.id(casesLink)).click()
							//Thread.sleep(3000)

							//WebElement trialArm1 = driver.findElement(By.id(trialArm)).click()

							//Thread.sleep(2000)

							//WebElement trialArmOption1 = driver.findElement(By.id(trialArmOption)).click()

							//Thread.sleep(2000)

							//WebElement gender1 = driver.findElement(By.id(gender)).click()

							//Thread.sleep(2000)

							//WebElement genderOption1 = driver.findElement(By.id(genderOption)).click()

							//Thread.sleep(2000)

							//WebElement clearAllButton1 = driver.findElement(By.id(trialArmOption)).click()  //this will work only after scrollinto view is implemented
							//Thread.sleep(2000)
							//String assertTxt = 'https://trialcommons-qa.cancer.gov/#/cases'  //use assertion after each filter to check the count of cases
							//
							//if (driver.getCurrentUrl() == assertTxt) {
							//	System.out.println('This is the url from the cases page:' + driver.getCurrentUrl())
							//}
							//
							//System.out.println('After clicking on the Caess hyperlink using locator')
							//
							//Thread.sleep(3000)
							//driver.quit()



							//********************************************************************


							//ReadExcel.Neo4j()
							//WebElement casesLink1 = driver.findElement(By.id(casesLink)).click()
								break;


							default:
								System.out.println ("nothing in function column :")
								break;
						}
					//CustomKeywords.'ctdc.utilities.ReadExcel.Neo4j'()

					//System.out.println( "inside case Function")
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
					//	GlobalVariable.G_Function = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
						break;
					case("Run"):
					//System.out.println( "inside case Run")
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
						GlobalVariable.G_Run = sheetData.get(ii).get(jj).getStringCellValue()
					//Do Test here
						break;
					case("Otherimportuser"):
					//Do Test here
						break;

					default :
						System.out.println ( " here at the last ")
						break;
				}

				//System.out.println GlobalVariable(0).value

				//--------------------------


				//str =str+ cell.getStringCellValue() + "||"
				//}
				//System.out.println(str);

			}
		}
	}



}
