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

public class ReadExcel {

	@Keyword


	public static void Test() {




		// An excel file name. You can create a file name with a fullSystem.out.println("sjhdajdakjsd")

		System.out.println("00000000")

		// path information.
		String filename = ("C:\\Data\\readInput.xlsx")
		// Create an ArrayList to store the data read from excel sheet.
		List<List<XSSFCell>> sheetData = new ArrayList<>();

		//try (FileInputStream fis = new FileInputStream(filename)) {
		// Create an excel workbook from the file system.
		FileInputStream fis = new FileInputStream(filename)
		XSSFWorkbook workbook = new XSSFWorkbook(fis);


		System.out.println("333333333")
		// Get the first sheet on the workbook.
		XSSFSheet sheet = workbook.getSheetAt(0);

		// When we have a sheet object in hand we can iterator on
		// each sheet's rows and on each row's cells. We store the
		// data read on an ArrayList so that we can printed the
		// content of the excel to the console.
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
		}
		//} catch (IOException e) {
		//e.printStackTrace();
		//	}

		showExcelData1(sheetData);
	}

	private static void showExcelData1(List<List<XSSFCell>> sheetData) {
		// Iterates the data and print it out to the console.
		for (List<XSSFCell> data : sheetData) {
			String str = "";
			if(data.get(0).stringCellValue.equals("CTDC-43123")){
				for (XSSFCell cell : data) {

					str =str+ cell.getStringCellValue() + "||"
				}
				System.out.println(str);

			}
		}
	}

	@Keyword

	public static void Neo4j() {


		// ---------------------------------



		//"MATCH (n:case) RETURN n LIMIT 25"

		//output= 'C:\laxmi\

		String query ="MATCH (t:clinical_trial)<--(a:arm)<--(:assignment_report)-[*]->(c:case) WITH DISTINCT c AS c, t ,a  OPTIONAL MATCH (c)<-[*]-(f:file)  Return c.case_id  As case_id,t.clinical_trial_designation as clinical_trial_code,a.arm_id As arm_id, a.arm_drug As arm_drug, a.pubmed_id As pubmed_id, c.disease As disease, c.gender As gender, c.race As race, c.ethnicity As ethnicity, t.clinical_trial_id As clinical_trial_id, a.arm_id+'_'+ a.arm_drug As trial_arm, COLLECT(DISTINCT(f.file_type)) AS file_types, COLLECT(DISTINCT(f.file_format)) AS file_formats, COLLECT(DISTINCT(f)) AS files";
		//String neo4jServer = "bolt://ncias-q2251-c.nci.nih.gov/";
		String neo4jServer = "bolt://ncias-d2267-c.nci.nih.gov/" ;

		String userName="neo4j";
		String pwd="icdcDBneo4j0";
		String output="C:/laxmi/Second_try.xlsx" ;
		//executor.run(neo4jServer,userName,pwd, query,output);





		//----------------




		CypherExecutor Test1 = new CypherExecutor()

		Test1.run(neo4jServer,userName,pwd, query,output)


	}

	@Keyword

	public  void RunTestcase(String name) {

		System.out.println ( " My first variable GlobalVariable.G_InputExcelFileName "  + GlobalVariable.G_InputExcelFileName)
		FileInputStream fis = new FileInputStream(GlobalVariable.G_InputExcelFileName);  //Data Files/readWriteExcel // ("C:\\Users\\radhakrishnang2\\Desktop\\Katalon_POC\\readInput.xlsx")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		ArrayList<String> xlContents = new ArrayList<String>();

		int numberOfSheets = workbook.getNumberOfSheets();
		Workbook workbook1 = (Workbook) workbook;

		for (int i = 0; i < numberOfSheets; i++) {
			XSSFSheet aSheet = workbook.getSheetAt(i);
			System.out.println(aSheet.getSheetName());  // prints all the sheet names
			int numOfRows = aSheet.getLastRowNum() - aSheet.getFirstRowNum();
			System.out.println("Number of rows in Sheet: " + aSheet.getSheetName() + " = " + numOfRows );
			Iterator<Row> rows = aSheet.iterator();
			Row currRow = rows.next();
			while( rows.hasNext() ){
				Iterator<Cell> ce = currRow.cellIterator();
				while( ce.hasNext() ){
					Cell value = ce.next();

					//--------------------------
					System.out.println( "Before  case value " : value )
					//data.get(0).stringCellValue.equals("CTDC-43123")
					switch( value )
					{

						case("Browser"):
							System.out.println( "inside case")
						//Do Test here
							System.out.println (  "Browser in excel is : " +ce.get(1).stringCellValue )
							break;
						case("server"):
							break;
						//Do Test here
						case("user_Id"):
						//Do Test here
						case("Password"):
						//Do Test here
						case("location_path"):
						//Do Test here
						case("Page"):
						//Do Test here
						case("ObjectType"):
						//Do Test here
						case("propertyName"):
						//Do Test here
						case("propertyvalue"):
						//Do Test here
						case("action"):
						//Do Test here
						case("Query"):
						//Do Test here
						case("Function"):
						//Do Test here
						case("Run"):
						//Do Test here

						case("Otherimportantuser"):
						//Do Test here
							break;
					}



					//----------------------


					System.out.println("Cell value is: " + value);
					xlContents.add(value);
				}
				currRow = rows.next();
			}

		}
		System.out.println("This is the value of the arraylist named xlContents: " + xlContents);
		System.out.println("This is the element at index 0:"+ xlContents.get(0));

		/*GlobalVariable.
		 //System.out.println("name hjjjj")
		 GlobalVariable.G_Browser =name
		 G_BrowserDriverPath
		 G_Browser
		 G_Urlname
		 <name>G_Object</name>
		 <name>G_Action</name>
		 <name>G_server</name>
		 <name>G_UserId</name>
		 <name>G_Password</name>
		 <name>G_ResultPath</name>
		 <name>G_Page</name>
		 <name>G_propertyName</name>
		 <name>G_propertyvalue</name>
		 <name>G_Query</name>
		 <name>G_Run</name>
		 System.out.println( "This is the string coming in: " + GlobalVariable.G_Browser );
		 //System.Out.println ("the excel name : " + ExcelName ) */
	}

	/*	@Keyword
	 public  void glo() {
	 //System.out.println("name hjjjj")
	 //'GlobalVariable.G_Browser =name
	 System.out.println( "This is the string coming in: from glo " + GlobalVariable.G_Browser );
	 //System.Out.println ("the excel name : " + ExcelName )
	 } */


	@Keyword

	public static  void initialLoad() {

		System.setProperty('webdriver.chrome.driver',  GlobalVariable.G_BrowserDriverPath )
		//GlobalVariable.G_Driver = new ChromeDriver()

		System.out.println ( "*********************************INITIAL LOAD working *****************************")

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
		XSSFSheet sheet = workbook.getSheetAt(0);  // Very important that we read the sheet 0
		//System.out.println( workbook.getSheetName(0) )
		// When we have a sheet object in hand we can iterator on
		// each sheet's rows and on each row's cells. We store the
		// data read on an ArrayList so that we can printed the
		// content of the excel to the console.
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
		System.out.println(   " Before Initial " + workbook.getSheetName(0) )
		Initialising(sheetData);
		//	}
		//System.out.println(   " Before Initial " + workbook.getSheetName(s) )
		//Initialising(sheetData);

	}


	private static void Initialising(List<List<XSSFCell>> sheetData) {
		// Iterates the data and print it out to the console.
		int countrow = 0
		countrow = sheetData.size();
		//System.out.println ( " sheetdata size countrow " + countrow )
		//System.out.println ( "sheet  data size :" + sheetData.get(0).size())


		for (int i = 1; i < countrow; i++){
			//System.out.println ("value of  i :"  + i + "  Value of j  : " + j )
			//for (List<XSSFCell> data : sheetData) {
			List<XSSFCell> datarow = sheetData.get(i);
			System.out.println (" Columns Size  " : + datarow.size())
			String str = "";
			//if(data.get(0).stringCellValue.equals("CTDC-43123")){
			for (int j = 0; j < datarow.size(); j++){
				System.out.println ("value of  i :"  + i + "  Value of j  : " + j )
				XSSFCell cell = datarow.get(j);


				//for (XSSFCell cell : data) {
				//System.out.println ( "Header Before switch  :" + sheetData.get(0).get(j).getStringCellValue())
				//System.out.println( "Data in variable : "  + sheetData.get(i).get(j).getStringCellValue())
				//--------------------
				switch(sheetData.get(0).get(j).getStringCellValue().trim() ) //First ROW  , hedear.
				{
					case("Browser"):


					//int cr =
					//System.out.println( "inside case Browser" )
					//System.out.println ( "Header  :" + sheetData.get(0).get(j).getStringCellValue())
					// System.out.println ("value of  i :"  + i + "  Value of j  : " + j )
					//System.out.println(i);
					//System.out.println(j);
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
						GlobalVariable.G_Browser = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
					//System.out.println data.get().indexOf("Browser")
					//System.out.println (  "Browser in excel is : " +cell.stringCellValue    + cell.getAddress()   +" row : " + cell.row  +  "col : " + cell.getColumnIndex()  )
						break;
					case("server"):
					//System.out.println( "inside case server" )
					//System.out.println ( "Header  :" + sheetData.get(0).get(j).getStringCellValue())
					// System.out.println( "inside case server")
					//System.out.println ("value of  i :"  + i + "  Value of j  : " + j )
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
						GlobalVariable.G_server = sheetData.get(i).get(j).getStringCellValue()
						break;
					//Do Test here
					case("user_Id"):
					//System.out.println( "inside case user_Id" )
					//System.out.println ( "Header  :" + sheetData.get(0).get(j).getStringCellValue())
					//System.out.println( "inside case User ID")
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
						GlobalVariable.G_UserId = sheetData.get(i).get(j).getStringCellValue()
						break;
					//Do Test here
					case("Password"):
					//System.out.println( "inside case Pasword")
					//System.out.println ( "Header  :" + sheetData.get(0).get(j).getStringCellValue())
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
						GlobalVariable.G_Password = sheetData.get(i).get(j).getStringCellValue()
						break;
					//Do Test here
					case("location_path"):
						System.out.println( "inside case Loacation Path")
					//System.out.println ( "Header  :" + sheetData.get(0).get(j).getStringCellValue())
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
						GlobalVariable.G_ResultPath = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
						break;
					case("Environment"):
						System.out.println( "inside case Environment")
					//System.out.println ( "Header  :" + sheetData.get(0).get(j).getStringCellValue())
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
						GlobalVariable.G_Environment = sheetData.get(i).get(j).getStringCellValue()
						break;
					//Do Test here
					case("url"):
					//System.out.println( "inside case url")
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
						GlobalVariable.G_Urlname = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
						break;
					//case("propertyName"):
					//System.out.println( "inside case Propertyname" )
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
					//GlobalVariable.G_propertyName = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
					//break;
					//case("propertyvalue"):
					//System.out.println( "inside case propertyvalue")
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
					//GlobalVariable.G_propertyvalue = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
					//break;
					//case("action"):
					//System.out.println( "inside case action")
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
					//GlobalVariable.G_Action = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
					//break;
					//case("Query"):
					//System.out.println( "inside case query")
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
					//GlobalVariable.G_Query = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
					//break;
					//case("Function"):
					//System.out.println( "inside case Function")
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
					//GlobalVariable.G_Function = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
					//break;
					//case("Run"):
					//System.out.println( "inside case Run")
					//System.out.println(sheetData.get(i).get(j).getStringCellValue())
					//GlobalVariable.G_Run = sheetData.get(i).get(j).getStringCellValue()
					//Do Test here
					//break;
					//case("Otherimportuser"):
					//Do Test here
					//break;

					default :
						System.out.println ( " here at the last ")
						break;
				}

				//System.out.println GlobalVariable(0).value

				//--------------------------


				str =str+ cell.getStringCellValue() + "||"
				//}
				//System.out.println(str);

			}
		}
	}


	@Keyword

	public static void PrintG() {
		//System.out.println ("Action :" + GlobalVariable.G_Action)
		System.out.println ("*******************PRINTING ENVIRONMENTAL VARIALBES*********************************")

		System.out.println ("ENvironment :" + GlobalVariable.G_Environment)
		System.out.println ( "Browser : " + GlobalVariable.G_Browser)
		System.out.println ("Server :" + GlobalVariable.G_server)
		System.out.println ("UserID :" + GlobalVariable.G_UserId)

		System.out.println (" location_path  :" + GlobalVariable.G_ResultPath)
		//System.out.println ("Object : " + GlobalVariable.G_Object)
		System.out.println (" Page :  " + GlobalVariable.G_Page)
		System.out.println (" Password : " + GlobalVariable.G_Password)
		//System.out.println (" Property Name :" + GlobalVariable.G_propertyName)
		//System.out.println ("Property Value :" + GlobalVariable.G_propertyvalue)
		//System.out.println ("URLNAME  : " + GlobalVariable.G_Urlname)
		//System.out.println (" Query  : " +  GlobalVariable.G_Query)
		//System.out.println	GlobalVariable.G_Action
		//System.out.println	GlobalVariable.
		System.out.println ("******************END PRINGING**********************************")
	}





}







