package tests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.baseURI;
public class SoapXMLRequest {
@Test 
   public void ValidateSoapXML() throws IOException {
	
	File file = new File("C://Users//AYMEN//eclipse-workspace//RESTAssuredAutomation//SoapRequest//Add.xml");
	if(file.exists())
     System.out.println(">> File Exists ");
     
	FileInputStream fileInputStream = new FileInputStream(file);
	String requestBody = IOUtils.toString(fileInputStream,"UTF-8");
	
	baseURI = "http://dneonline.com";
	
	given().contentType("text/xml")
	.accept(ContentType.XML)
	.body(requestBody)
	.when()
	.post("/calculator.asmx ")
	.then()
    .statusCode(200).log().all().and()
    .body("//*:AddResult.text()", equalTo("5"))
	;
}
}
