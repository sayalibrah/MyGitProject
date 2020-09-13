package restAutomation;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aflac.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;


public class TC01_GetRequest extends TestBase {

	
	@BeforeTest
	public void GetSingleUserDetails() {
		//test.info("*** Get single user details ***");
		test = extent.createTest("GetSingleUserDetails");
		RestAssured.baseURI = "https://reqres.in/api/users";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/2");
//		List<Header> headerList = response.getHeaders().asList();
//		System.out.println("Header list is:" + headerList);
	}
	@Test(priority = 0)
	public void CheckResponseBody()
	{
		String ResponseBody = response.getBody().asString();
		//System.out.println("Response body is:" + ResponseBody);
		test.info("Response body is: "+ResponseBody);
		Assert.assertTrue(ResponseBody!=null);
	}
	
	@Test(priority = 1)
	public void Get_StatusCode() {
		test = extent.createTest("Get_StatusCode");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);	
	}

	@Test(priority = 2)
	public void Get_ContentType() {
		test = extent.createTest("Get_ContentType");
		String contentType = response.getContentType();
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test(priority = 3)
	public void ResponseLastName() {
		test = extent.createTest("ResponseLastName");
		JsonPath jsonpath = response.jsonPath();
		String lastName = jsonpath.get("data.last_name");
		Assert.assertEquals(lastName, "Weaver");
	}

	@Test(priority = 4)
	public void ResponseFirstName() {
		test = extent.createTest("ResponseFirstName");
		Map<String, String> data = response.jsonPath().getMap("data");
		String firstName = data.get("first_name");
		Assert.assertEquals(firstName, "Janet");

	}
	
	@AfterTest
	public void EndTest()
	{
		extent.flush();
	}

}
