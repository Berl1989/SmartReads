package com.sp.smartreads.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.text.ParseException;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sp.smartreads.SmartreadsApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmartreadsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmartReadsControllerTest {
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void accountWith_MultipleElectricityAndGasReadings() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/smart/reads/SI_1987"),
				HttpMethod.GET, entity, String.class);

		String expectedJsonResponse = "{accountNumber:SI_1987,gasRead:{gasMeterId:SGM_135,gasReading:250.0,gasReadingDate:2019-02-01},electricityRead:{electricityMeterId:SEM_156,electricityReading:150.0,electricityReadingDate:2019-02-01}}";
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		JSONAssert.assertEquals(expectedJsonResponse, response.getBody(), false);
	}
	
	@Test
	public void accountWith_SingleElectricityReading_And_MultipleGasReadings() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/smart/reads/BN_987"),
				HttpMethod.GET, entity, String.class);

		String expectedJsonResponse = "{accountNumber:BN_987,gasRead:{gasMeterId:SGM_235,gasReading:2500.0,gasReadingDate:2019-02-01},electricityRead:{electricityMeterId:SEM_256,electricityReading:1000.0,electricityReadingDate:2019-01-01}}";
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		JSONAssert.assertEquals(expectedJsonResponse, response.getBody(), false);
	}
	
	@Test
	public void accountWith_OnlyElectricityReading() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/smart/reads/VV_111"),
				HttpMethod.GET, entity, String.class);

		String expectedJsonResponse = "{accountNumber:VV_111,gasRead:null,electricityRead:{electricityMeterId:SEM_1112,electricityReading:112.5,electricityReadingDate:2019-03-01}}";
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		JSONAssert.assertEquals(expectedJsonResponse, response.getBody(), false);
	}
	
	@Test
	public void accountWith_OnlyGasReading() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/smart/reads/KC_123"),
				HttpMethod.GET, entity, String.class);

		String expectedJsonResponse = "{accountNumber:KC_123,gasRead:{gasMeterId:SEM_1231,gasReading:25.78,gasReadingDate:2019-03-01},electricityRead:null}";
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		JSONAssert.assertEquals(expectedJsonResponse, response.getBody().toString(), false);
	}

	@Test
	public void accountWith_NoReading_NewAccount() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/smart/reads/WT_123"),
				HttpMethod.GET, entity, String.class);

		String expectedJsonResponse = "{accountNumber:WT_123,gasRead:null,electricityRead:null}";
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		JSONAssert.assertEquals(expectedJsonResponse, response.getBody().toString(), false);
	}
	
	@Test
	public void invalidAccountNumber_returns_NOT_FOUND() throws ParseException, JSONException {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/smart/reads/ABC_109"),
				HttpMethod.GET, entity, String.class);
		
		String expectedJsonResponse = "{status:NOT_FOUND,message:'Customer Account Not Found for Account Number ABC_109'}";
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
		JSONAssert.assertEquals(expectedJsonResponse, response.getBody().toString(), false);
				
	}
	
	@Test
	public void invalidApiURL_returns_NOT_FOUND() throws ParseException, JSONException {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/smart/reads/scottish/ABC_109"),
				HttpMethod.GET, entity, String.class);
		String expectedJsonResponse = "{status:NOT_FOUND,message:'Bad request. Incorrect resource URI'}";
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
		JSONAssert.assertEquals(expectedJsonResponse, response.getBody(), false);
		
	}
	
	@Test
	public void invalidHttpMethod_returns_METHOD_NOT_ALLOWED() throws ParseException, JSONException {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/api/smart/reads/ABC_109"),
				HttpMethod.POST, entity, String.class);
		
		String expectedJsonResponse = "{status:METHOD_NOT_ALLOWED,message:\"Request method 'POST' not supported\"}";
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.METHOD_NOT_ALLOWED));
		JSONAssert.assertEquals(expectedJsonResponse, response.getBody(), false);
		
			
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
