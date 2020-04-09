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

public class databaseFunctions {


	//	@Keyword
	//	public void DBconnect(String name) throws IOException{

	//************************************************************************************************************
	//	 **  Create database connection and execute DB query to retrieve the results
	//************************************************************************************************************/

	//	def runDBQuery ( pQry ) {
	//		try {
	//			List lvRtrnVl = []
	//			log.info " ------------------------ DB SQL QUERY (AS-IS) --------------- : " + pQry
	//
	//			if ( pQry != "" && pQry != null ) {
	//				def lvDrvr		= 'com.mysql.jdbc.Driver'
	//				def lvHst		 	= 'jdbc:mysql://pelp-db-1.fqt.uspto.gov'
	//				def lvPrtNmbr	 	= ':3306'
	//				def lvSchmNm	 	= '/pelpdb116'
	//				def lvUsrNm		= 'pelp_read' //db account username
	//				def lvPsswrd	 	= 'CChangMeme$1234S' //CUSTOM PASSWORD
	//
	//				def lvSql = Sql.newInstance( ( lvHst + lvPrtNmbr + lvSchmNm ), ( lvUsrNm ), ( lVPsswrd ( lvPsswrd ) ), lvDrvr )
	//
	//				lvRtrnVl = lvSql.rows ( pQry )
	//
	//				//log.info " ------------------------ DB SQL RESULT (AS-IS) -------------- : " + lvRtrnVl
	//
	//				lvSql.close()
	//			} else {
	//				log.info " ------------------------ OBSERVED NOTE: DB SQL QUERY is EMPTY/NULL"
	//			}
	//
	//			return lvRtrnVl
	//		} catch ( MalformedURLException e ) {
	//			assert true
	//			log.error " ---------------------------------------- OBSERVED EXCEPTION --------------------------------------------------------- : in method runDBQuery ( pQry )"
	//			assert e in MalformedURLException
	//		}
	//	}




	//***************************************************************************************************************
	//	 **  CUSTOM PASSWORD METHOD – need to change based on your password
	//*************************************************************************************************************/

	def lVPsswrd ( pPass ) {
		def lvRtrnVl = pPass.substring ( 1, 6 ) + pPass.substring ( 7, 15 )

		return lvRtrnVl
	}

}
