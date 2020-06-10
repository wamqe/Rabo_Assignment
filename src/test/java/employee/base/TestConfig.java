package employee.base;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.github.tomakehurst.wiremock.WireMockServer;

import employee.utils.PropertyUtils;
import io.restassured.RestAssured;

public class TestConfig {

	public static String config = System.getProperty("user.dir") + "/src/test/resources/mock.properties";

	WireMockServer wireMockServer;

	@BeforeTest
	public void setup() {
		wireMockServer = new WireMockServer(8090);
		wireMockServer.start();
		setupStub();
	}

	@AfterTest
	public void teardown() {
		wireMockServer.stop();
	}

	public void setupStub() {
		wireMockServer.stubFor(get(urlPathEqualTo("/v2/employee/Luther_Ankunding"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200).withBody(
						"{\"id\":9103608,\"employeename\":\"Luther_Ankunding\",\"firstName\":\"Luther\",\"lastName\":\"Ankunding\",\"email\":\"uliwiy@abc.com\",\"password\":\"8u1cs7wbp\",\"phone\":\"221-560-0077\",\"employeeStatus\":1}")));

		wireMockServer.stubFor(put("/v2/employee/Luther_Ankunding")
				.willReturn(aResponse().withHeader("Content-Type", "application/json")
						.withStatus(202).withBody("{\"id\":9103608,\"employeename\":\"Luther_Ankunding\",\"firstName\":\"Luther\",\"lastName\":\"Ankunding\",\"email\":\"update12@abc.com\",\"password\":\"8u1cs7wbp\",\"phone\":\"221-560-0077\",\"employeeStatus\":1,\"Status\":\"Updated Successfully\"}")));
		
		wireMockServer.stubFor(get(urlPathEqualTo("/v2/employee/dummyuser"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200).withBody(
						"{\"id\":9103607,\"employeename\":\"dummyuser\",\"firstName\":\"dummy\",\"lastName\":\"user\",\"email\":\"uliwiy@abc.com\",\"password\":\"8u1cs7wbp\",\"phone\":\"221-560-0076\",\"employeeStatus\":1}")));

		
		wireMockServer.stubFor(put(urlPathMatching("/v2/employee/dummyuser"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json")
						.withStatus(404).withBody("{}")));

		wireMockServer.stubFor(delete(urlPathMatching("/v2/employee/tomuser"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json")
						.withStatus(404).withBody("{}")));
		
		wireMockServer.stubFor(delete("/v2/employee/Luther_Ankunding")
				.willReturn(aResponse().withHeader("Content-Type", "application/json")
						.withStatus(204).withBody("[]")));
		
		wireMockServer.stubFor(get(urlPathEqualTo("/v2/employee/login"))
				//.withQueryParam("format", (StringValuePattern) equalTo("json"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withStatus(200).withBody("{\"Status\":\"Logged In Successfully\"}")));
		
		wireMockServer.stubFor(get(urlPathEqualTo("/v2/employee/logout"))
				//.withQueryParam("format", (StringValuePattern) equalTo("json"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json")
				.withStatus(200).withBody("{\"Status\":\"Logged Out Successfully\"}")));
		

		
		wireMockServer.stubFor(post(urlPathEqualTo("/v2/employee")).withName("id")
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(201).withBody(
						"{\"id\":9131824,\"employeename\":\"Alexandrine_Grimes\",\"firstName\":\"Alexandrine\",\"lastName\":\"Grimes\",\"email\":\"neioew@abc.com\",\"password\":\"xy7s920po\",\"phone\":\"560-333-7360\",\"employeeStatus\":0}")));
		
		wireMockServer.stubFor(post(urlPathEqualTo("/v2/employee/createWithArray")).withName("Array_User")
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(201).withBody(
						"[{\"id\":900200,\"employeename\":\"Array_User\",\"firstName\":\"Array\",\"lastName\":\"User\",\"email\":\"auser@abc.com\",\"password\":\"xy7s920po\",\"phone\":\"560-333-7160\",\"employeeStatus\":0}]")));
				
		wireMockServer.stubFor(post(urlPathEqualTo("/v2/employee/createWithList")).withName("List_User")
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(201).withBody(
						"[{\"id\":900200,\"employeename\":\"List_User\",\"firstName\":\"List\",\"lastName\":\"User\",\"email\":\"l1user@abc.com\",\"password\":\"xy7s920po\",\"phone\":\"560-333-7160\",\"employeeStatus\":0},{\"id\":700300,\"employeename\":\"List1_User1\",\"firstName\":\"List1\",\"lastName\":\"User1\",\"email\":\"l2user@abc.com\",\"password\":\"xy7s920po\",\"phone\":\"560-333-7460\",\"employeeStatus\":1}]")));
		
	}

	@BeforeSuite
	public void beforeSuite() {
		System.setProperty("org.eclipse.jetty.util.log.class", "org.eclipse.jetty.util.log.StdErrLog");
		System.setProperty("org.eclipse.jetty.LEVEL", "OFF");
		
		// Loading the properties file
		PropertyUtils.readProperty(config);
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8090;
		RestAssured.basePath = "/v2";
	}

	@AfterSuite
	public void afterSuite() {
		RestAssured.reset();
	}

}
