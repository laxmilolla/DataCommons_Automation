package ctdc.utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.annotation.Keyword

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.FileDescriptor
import java.io.File
import java.lang.String
import java.lang.Object

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



//ExcelTableTest.createTable()

public class readInputExcel {


	@Keyword
	public ArrayList<String> G_readInputExcel(String name) throws IOException{
		System.out.println( "This is the string coming in: " + name );
		Path filepath = Paths.get(System.getProperty("user.dir"), "TestData", "Input_TestData.xlsx");
		System.out.println("This is the full filepath after converting to string :"+filepath.toString());

		FileInputStream fis = new FileInputStream(filepath.toString()); //"C:\\Users\\radhakrishnang2\\Desktop\\Katalon_POC\\readInput.xlsx"

		//FileInputStream fis = new FileInputStream(GlobalVariable.G_InputExcelFileName);  //Data Files/readWriteExcel // ("C:\\Users\\radhakrishnang2\\Desktop\\Katalon_POC\\readInput.xlsx")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		//XSSFSheet sheet = workbook.getSheet("Sheet1");  //instead of hardcoding sheet1 make it dynamic like ldlink fmwk
		//int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();  //find the column count and then do 2 nested for loops to read


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
					System.out.println("Cell value is: " + value);
					xlContents.add(value);
				}
				currRow = rows.next();
			}

		}
		System.out.println("This is the value of the arraylist named xlContents: " + xlContents);
		System.out.println("This is the element at index 0:"+ xlContents.get(0));

		return xlContents;
	}


}



