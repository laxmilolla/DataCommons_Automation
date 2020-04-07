import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.*
import java.lang.String as String
import groovy.transform.Field as Field

System.setProperty('webdriver.chrome.driver', 'C:\\Users\\radhakrishnang2\\Desktop\\DataCommons_Automation\\CTDC_Automation\\chromedriver.exe')

WebDriver driver = new ChromeDriver()

driver.get('https://trialcommons-qa.cancer.gov/')

driver.manage().window().maximize()

ArrayList<String> elemLoc = CustomKeywords.'readInputExcel.G_readInputExcel'('This is from a test case for reading input')

String casesLink = elemLoc.get(15)

String trialArm = elemLoc.get(25)

String trialArmOption = elemLoc.get(35)

String gender = elemLoc.get(45)

String genderOption = elemLoc.get(55)

//String clearAllButton = elemLoc.get(71)
System.out.println('Before clicking on the Cases button using locator')

//driver.get(casesLink)
//String loc = '//*[@id="button_navbar_cases"]'
//WebElement casesLink1 = driver.findElement(By.xpath("//*[@id=\"button_navbar_cases\"]"))  // only this type works
//WebElement leep(3000)
WebElement casesLink1 = driver.findElement(By.id(casesLink)).click()

Thread.sleep(3000)

WebElement trialArm1 = driver.findElement(By.id(trialArm)).click()

Thread.sleep(2000)

WebElement trialArmOption1 = driver.findElement(By.id(trialArmOption)).click()

Thread.sleep(2000)

WebElement gender1 = driver.findElement(By.id(gender)).click()

Thread.sleep(2000)

WebElement genderOption1 = driver.findElement(By.id(genderOption)).click()

Thread.sleep(2000)

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
driver.quit()

