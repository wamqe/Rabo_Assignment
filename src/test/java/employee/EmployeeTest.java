package employee;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import employee.base.TestConfig;
import employee.utils.PropertyUtils;
import employee.utils.RestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmployeeTest extends TestConfig {
	// http://localhost:8080/v2/swagger-ui.html    
	Response response;
	Faker faker = new Faker();
	long rand_id = RestUtils.generateRandomId();
	String firstName = faker.name().firstName();
	String lastName = faker.name().lastName();
	String fullName = firstName + "_" + lastName;
	String password = faker.internet().password(8, 10, false);
	
	@Test
	public void testUpdateEmployee() {
		
		
		//perform login
		Response loginResp = given().param("employeename", "Luther_Ankunding").param("password", password).when().get("/employee/login")
				.then().contentType(ContentType.JSON).extract().response();
		System.out.println("Login-Status:"+loginResp.asString());
		
		// get employee information
		Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		String empData =  given().headers(headers).pathParam("employeename", "Luther_Ankunding").when()
				.get("/employee/{employeename}").then().extract().response().asString();

		
		//update
		empData = empData.replace("uliwiy@abc.com","update12@abc.com");
		System.out.println("Update-Payload:\n" + RestUtils.formatPrint(empData));
		response = given().contentType(ContentType.JSON).pathParam("employeename", "Luther_Ankunding").body(empData).when()
				.put("/employee/{employeename}").then().contentType(ContentType.JSON).extract()
				.response();
		int status_code = response.statusCode();
		String msg = response.getStatusLine();
		Assert.assertEquals(202, status_code);
		Assert.assertEquals("HTTP/1.1 202 Accepted", msg);
		System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));
	}
	
	@Test	
	public void testDeleteEmployee() {
		response = given().pathParam("employeename", "Luther_Ankunding").when().delete("/employee/{employeename}").then()
				.contentType(ContentType.JSON).extract().response();
		int status_code = response.statusCode();
		String msg = response.getStatusLine();
		Assert.assertEquals(204, status_code);
		Assert.assertEquals("HTTP/1.1 204 No Content", msg);
		System.out.println("Pretty:\n" + response.asString());
	}
	@Test	
	public void testNonLoggedInDeleteEmployee() {
		Response logOutResp = given().when().get("/employee/logout").then().contentType(ContentType.JSON)
				.extract().response();
		System.out.println("Logout-Status-Before Delete Operation:"+logOutResp.asString());
		
		
		response = given().pathParam("employeename", "tomuser").when().delete("/employee/{employeename}").then()
						.contentType(ContentType.JSON).extract().response();
		int status_code = response.statusCode();
		String msg = response.getStatusLine();
		Assert.assertEquals(404, status_code);
		Assert.assertEquals("HTTP/1.1 404 Not Found", msg);
		System.out.println("Pretty:\n" + response.asString());
	}


	
	@Test
	public void testGetEmployeeByName() {
		Map<String, String> headers = new HashMap<>();
		headers.put("Accept", "application/json");
		response = given().headers(headers).pathParam("employeename", "Luther_Ankunding").when()
				.get("/employee/{employeename}").then().statusCode(200).extract().response();
		int status_code = response.statusCode();
		String msg = response.getStatusLine();
		Assert.assertEquals(200, status_code);
		Assert.assertEquals("HTTP/1.1 200 OK", msg);
		Assert.assertEquals("8u1cs7wbp",response.path("password")); 
		System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));
	}
	@Test
	public void testLoginEmployee() {
		response = given().param("employeename", firstName).param("password", password).when().get("/employee/login")
				.then().contentType(ContentType.JSON).extract().response();
		//System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));
		int status_code = response.statusCode();
		String msg = response.getStatusLine();
		Assert.assertEquals(200, status_code);
		Assert.assertEquals("HTTP/1.1 200 OK", msg);
		Assert.assertTrue(response.asString().contains("Logged In"));
		System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));
	}
	@Test
	public void testLogoutEmployee() {
		response = given().when().get("/employee/logout").then().contentType(ContentType.JSON)
				.extract().response();
		//System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));
		int status_code = response.statusCode();
		String msg = response.getStatusLine();
		Assert.assertEquals(200, status_code);
		Assert.assertEquals("HTTP/1.1 200 OK", msg);
		Assert.assertTrue(response.asString().contains("Logged Out"));
		System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));
	}

	

	@Test
	public void testCreateEmployee() throws Exception {
		String jsonEmployee = PropertyUtils.getKey("employeeData");
		// long rand_id = RestUtils.generateRandomId();
		jsonEmployee = jsonEmployee.replace(String.valueOf(1111), Long.toString(rand_id));

		jsonEmployee = jsonEmployee.replace("{{full_name}}", fullName);
		jsonEmployee = jsonEmployee.replace("{{first_name}}", firstName);
		jsonEmployee = jsonEmployee.replace("{{last_name}}", lastName);
		String rand_email = RestUtils.generateEmail("abc.com", 6);
		jsonEmployee = jsonEmployee.replace("{{email_id}}", rand_email);
		String phoneNo = RestUtils.generatePhoneNumber();
		jsonEmployee = jsonEmployee.replace("{{phoneNo}}", phoneNo);
		jsonEmployee = jsonEmployee.replace("{{password}}", password);
		int status = RestUtils.generateRandomBetween(0, 1);
		// jsonEmployee = jsonEmployee.replace(String.valueOf(9999),
		// Integer.toString(status));
		jsonEmployee = jsonEmployee.replace("9999", Integer.toString(0));
		System.out.println("Employee-Payload:\n" + RestUtils.formatPrint(jsonEmployee)); 		
		response = given().contentType(ContentType.JSON).body(jsonEmployee).when().post("/employee").then()
				.extract().response();
		int status_code = response.statusCode();
		String msg = response.getStatusLine();
		Assert.assertEquals(201, status_code);
		Assert.assertEquals("HTTP/1.1 201 Created", msg);		
		System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));		
	}
	@Test
	public void testCreateEmployeeArray() throws Exception {
		String jsonEmployee = PropertyUtils.getKey("employeeDataArray");
		// long rand_id = RestUtils.generateRandomId();
		jsonEmployee = jsonEmployee.replace(String.valueOf(1111), Long.toString(rand_id));

		jsonEmployee = jsonEmployee.replace("{{full_name}}", "Array_User");
		jsonEmployee = jsonEmployee.replace("{{first_name}}", "Array");
		jsonEmployee = jsonEmployee.replace("{{last_name}}", "User");
		String rand_email = RestUtils.generateEmail("abc.com", 6);
		jsonEmployee = jsonEmployee.replace("{{email_id}}", rand_email);
		String phoneNo = RestUtils.generatePhoneNumber();
		jsonEmployee = jsonEmployee.replace("{{phoneNo}}", phoneNo);
		jsonEmployee = jsonEmployee.replace("{{password}}", password);
		int status = RestUtils.generateRandomBetween(0, 1);
		// jsonEmployee = jsonEmployee.replace(String.valueOf(9999),
		// Integer.toString(status));
		jsonEmployee = jsonEmployee.replace("9999", Integer.toString(0));
		System.out.println("Array-Payload:\n" + RestUtils.formatPrint(jsonEmployee)); 		
		response = given().contentType(ContentType.JSON).body(jsonEmployee).when().post("/employee/createWithArray").then()
				.extract().response();
		int status_code = response.statusCode();
		String msg = response.getStatusLine();
		Assert.assertEquals(201, status_code);
		Assert.assertEquals("HTTP/1.1 201 Created", msg);	
		System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));
	}
	
	@Test
	public void testCreateEmployeeList() throws Exception {
		String jsonEmployee = PropertyUtils.getKey("employeeDataList");
		long rand_id1 = RestUtils.generateRandomId();
		long rand_id2 = rand_id1+1;
		
		jsonEmployee = jsonEmployee.replace(String.valueOf(1111), Long.toString(rand_id1));
		jsonEmployee = jsonEmployee.replace("{{full_name}}", "List_User");
		jsonEmployee = jsonEmployee.replace("{{first_name}}", "List");
		jsonEmployee = jsonEmployee.replace("{{last_name}}", "User");
		String rand_email = RestUtils.generateEmail("abc.com", 6);
		jsonEmployee = jsonEmployee.replace("{{email_id}}", rand_email);
		String phoneNo = RestUtils.generatePhoneNumber();
		jsonEmployee = jsonEmployee.replace("{{phoneNo}}", phoneNo);
		jsonEmployee = jsonEmployee.replace("{{password}}", password);
		jsonEmployee = jsonEmployee.replace("9999", Integer.toString(0));
		jsonEmployee = jsonEmployee.replace(String.valueOf(2222), Long.toString(rand_id2));
		jsonEmployee = jsonEmployee.replace("{{full_name1}}", "List_User1");
		jsonEmployee = jsonEmployee.replace("{{first_name1}}", "List1");
		jsonEmployee = jsonEmployee.replace("{{last_name1}}", "User1");
		String rand_email1 = RestUtils.generateEmail("def.com", 6);
		jsonEmployee = jsonEmployee.replace("{{email_id1}}", rand_email1);
		String phoneNo1 = RestUtils.generatePhoneNumber();
		jsonEmployee = jsonEmployee.replace("{{phoneNo1}}", phoneNo1);
		String password1 = faker.internet().password(8, 10, false);
		jsonEmployee = jsonEmployee.replace("{{password1}}", password1);
		jsonEmployee = jsonEmployee.replace("8888", Integer.toString(1));
		System.out.println("List-Payload:\n" + RestUtils.formatPrint(jsonEmployee));
		
		response = given().contentType(ContentType.JSON).body(jsonEmployee).when().post("/employee/createWithList").then()
				.extract().response();
		int status_code = response.statusCode();
		String msg = response.getStatusLine();
		Assert.assertEquals(201, status_code);
		Assert.assertEquals("HTTP/1.1 201 Created", msg);	
		System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));
	}
	
	@Test
		public void testNonLoggedInUpdateEmployee() {
			// get employee information
			Map<String, String> headers = new HashMap<>();
			headers.put("Accept", "application/json");
			String empData =  given().headers(headers).pathParam("employeename", "dummyuser").when()
					.get("/employee/{employeename}").then().extract().response().asString();		
			
			
			System.out.println("Update-Payload:\n" + RestUtils.formatPrint(empData));
			response = given().contentType(ContentType.JSON).pathParam("employeename", "dummyuser").body(empData).when()
					.put("/employee/{employeename}").then().contentType(ContentType.JSON).extract()
					.response();
			
			int status_code = response.statusCode();
			String msg = response.getStatusLine();
			Assert.assertEquals(404, status_code);
			Assert.assertEquals("HTTP/1.1 404 Not Found", msg);
			System.out.println("Pretty:\n" + RestUtils.formatPrint(response.asString()));
		}

}
