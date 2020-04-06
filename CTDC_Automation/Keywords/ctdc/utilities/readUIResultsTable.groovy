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

import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory


public class readUIResultsTable {
	@Keyword
	public List<String> tableData() throws IOException {
		List<String> webData = new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\radhakrishnang2\\Desktop\\DataCommons_Automation\\CTDC_Automation\\chromedriver.exe");
		WebDriver driver = new ChromeDriver()
		driver.get("https://trialcommons-qa.cancer.gov/#/cases")
		driver.manage().window().maximize();
		// WebDriver driver = DriverFactory.getWebDriver()
		String ExpectedValue = "CTDC-43062";
		'To locate table'
		WebElement Table = driver.findElement(By.xpath("//*[@id=\"table_cases\"]/div[2]/div[2]/div/div[2]/table"))  // //*[@id=\"table_cases\"]  changed the xpath   //'//*[@id="table_cases"]/div[2]/div[2]/div/div[2]/table'
		List<WebElement> rows_table = Table.findElements(By.xpath("//*[contains(@id, \"MUIDataTableBodyRow-\")]"))
		int rows_count = rows_table.size()
		System.out.println("This is the size of the rows in the table in first page:"+(rows_count))

		WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"table_cases\"]/div[2]/div[2]/div/table/tfoot/tr/td/div/div[3]/button[2]"));

		WebElement tableHdr = driver.findElement(By.xpath("//*[@id=\"table_cases\"]/div[2]/div[2]/div/div[2]/table/thead"))
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

		//System.out.println(webData);
		//boolean buttonState= true;
		//do{
		while(nextButton.isEnabled()){
			String pageNm = driver.findElement(By.xpath("//*[@id=\"table_cases\"]/div[2]/div[2]/div/table/tfoot/tr/td/div/span[2]")).getText();
			System.out.println ("This is the page number : " +pageNm)
			int i = 0
			int j = 0
			for (i = 1; i <= rows_count; i++) {
				String data = ""
				for (j = 1; j < columns_count+10; j = j + 2) {
					data = data + (driver.findElement(By.xpath("//*[@id=\"table_cases\"]/div[2]/div[2]/div/div[2]/table/tbody/tr" + "[" + i + "]/*[" + j + "]"))
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
		return webData;

        driver.quit();
	}


}
