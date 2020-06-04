package hotel;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appConfigs.ApiURL;
import appConfigs.Constants;
import appConfigs.HeaderConfigs;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import utils.RandomDataGenerator;

public class SearchTests {
	HeaderConfigs header = new HeaderConfigs();
	RandomDataGenerator dataGenerator = new RandomDataGenerator();
	String validBody, invalidBody, invalidBody_with_empty_CheckInDate, invalidBody_with_empty_CheckOutDate,
			invalidBody_with_Space_in_Date_Field;

	@BeforeClass
	public void generateRandomDataForRequestBody() {
		// Generate Random dates checkIn date is less than checkOut date Positive TC
		validBody = dataGenerator.getJSonBody(Constants.POSITIVE_TC_CHECKIN_DAY_MARGIN,
				Constants.POSITIVE_TC_CHECKOUT_DAY_MARGIN);

		// Generate Random dates checkIn date is greater than checkOut date Negative TC
		invalidBody = dataGenerator.getJSonBody(Constants.NEGATIVE_TC_CHECKIN_DAY_MARGIN,
				Constants.NEGATIVE_TC_CHECKOUT_DAY_MARGIN);

		// Generate Random checkOut date but empty checkIn date Negative TC
		invalidBody_with_empty_CheckInDate = dataGenerator.getJSonBodyWithEmptyCheckInDate("",
				Constants.POSITIVE_TC_CHECKOUT_DAY_MARGIN);

		// Generate Random checkIn date but empty checkOut date Negative TC
		invalidBody_with_empty_CheckOutDate = dataGenerator
				.getJSonBodyWithEmptyCheckOutDate(Constants.POSITIVE_TC_CHECKIN_DAY_MARGIN, "");

		// Generate Random checkIn date but space checkOut date Negative TC
		invalidBody_with_Space_in_Date_Field = invalidBody_with_empty_CheckOutDate = dataGenerator
				.getJSonBodyWithEmptyCheckOutDate(Constants.POSITIVE_TC_CHECKIN_DAY_MARGIN, " ");
	}

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = ApiURL.HOTEL_SEARCH;
	}

	@Test(priority = 0, description = "Verify Api respose is valid as per search Request")
	public void check_SearchRequest_with_valid_Request_Body() {
		ExtractableResponse<Response> response = RestAssured.given().headers(header.defautHeaders()).body(validBody)
				.when().post().then().extract();

		Assert.assertNotNull(response);
		Assert.assertEquals(response.statusCode(), (int) Constants.SUCCESS_STATUS_CODE_200);
		Assert.assertEquals(response.contentType(), header.defautHeaders().get("Content-Type"));

		//Generating requestBodyJson
		JSONObject requestJson = new JSONObject(validBody);
		
		//getting dates from requestBody
		JSONObject dateJson =  new JSONObject(requestJson.get("dates").toString());
		
		//Generating responseBodyJson
		JSONObject responseJson = new JSONObject(response.asString());		
		String qureyObject  = (String) responseJson.get("query");
		String[] responeQureyParameterarray = qureyObject.split("/", 0);
		
		//Validate response body
		Assert.assertEquals(requestJson.get("destination"),responeQureyParameterarray[0]);
		Assert.assertEquals(dateJson.get("checkin"), responeQureyParameterarray[1]);
		Assert.assertEquals(dateJson.get("checkout"), responeQureyParameterarray[2]);
		
	}

	@Test(priority = 1, description = "Validate API response is 400, checkIn date value is after than checkOut date")
	public void check_SearchRequest_with_Invalid_Request_Body() {
		RestAssured.given().headers(header.defautHeaders()).body(invalidBody).when().post().then()
				.statusCode(Constants.FAILED_BAD_REQUEST_STATUS_CODE_400);
	}

	@Test(priority = 2, description = "Validate API response is 400, checkIn date value empty in request")
	public void check_SearchRequest_with_emptyCheckIndDate_In_Body() {
		RestAssured.given().headers(header.defautHeaders()).body(invalidBody_with_empty_CheckInDate).when().post()
				.then().statusCode(Constants.FAILED_BAD_REQUEST_STATUS_CODE_400);
	}

	@Test(priority = 3, description = "Validate API response is 400, checkOut date value empty in request")
	public void check_SearchRequest_with_emptyCheckOutDate_Body() {
		RestAssured.given().headers(header.defautHeaders()).body(invalidBody_with_empty_CheckOutDate).when().post()
				.then().statusCode(Constants.FAILED_BAD_REQUEST_STATUS_CODE_400);
	}

	@Test(priority = 4, description = "Validate API response is 400, checkOut date value is space in request")
	public void checkRequest_with_Space_in_DateField() {
		RestAssured.given().headers(header.defautHeaders()).body(invalidBody_with_Space_in_Date_Field).when().post()
				.then().statusCode(Constants.FAILED_BAD_REQUEST_STATUS_CODE_400);
	}

}
