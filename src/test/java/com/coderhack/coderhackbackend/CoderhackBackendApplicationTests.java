package com.coderhack.coderhackbackend;

import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.coderhack.coderhackbackend.constants.Badges;
import com.coderhack.coderhackbackend.entity.User;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;




@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoderhackBackendApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(CoderhackBackendApplicationTests.class);

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnUserIfRegistered() {

		ResponseEntity<String> response = restTemplate.getForEntity("/coderhack/users/66423e742deb7362e70cb065", String.class);
		LOG.info("Response: {}", response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		DocumentContext documentContext = JsonPath.parse(response.getBody());

		String id = documentContext.read("$.id");
		assertNotNull(id);
		assertEquals( "66423e742deb7362e70cb065", id);

		String userName = documentContext.read("$.userName");
		assertEquals( "m@Singh", userName);

		List<String> badges = documentContext.read("$.badges");

		LOG.info("badges {}", badges.get(0));

		assertEquals(List.of("CODE_CHAMP"), badges);

		Double score = documentContext.read("$.score");

		assertEquals(50.00, score);

	}

	@Test
	void shouldNotReturnUserWithUnknownId() {

		ResponseEntity<String> response = restTemplate.getForEntity("/coderhack/users/9", String.class);
		LOG.info("Response: {}", response.getBody());

		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("USR-404 Sorry, requested user not found", response.getBody());
		// assertThat(response.getBody(), blankOrNullString());
	}

	@Test
	void shouldReturnAllRegisteredUsersBasedOnScores(){

		ResponseEntity<String> response = restTemplate.getForEntity("/coderhack/users", String.class);
		LOG.info("Response: {}", response.getBody());

		assertEquals(HttpStatus.OK, response.getStatusCode());

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		LOG.info("parsed: {}", documentContext.jsonString());

	}

	@Test 
	void shouldRegisterNewUser(){

		Set<Badges> badge1 = new HashSet<>();
        badge1.add(Badges.CODE_CHAMP);
		badge1.add(Badges.CODE_CHAMP);
		badge1.add(Badges.CODE_MASTER);
		

		User user = new User("m@Singh");

		ResponseEntity<Void> createResponse = restTemplate.postForEntity(
				"/coderhack/users", user, Void.class);

		
		LOG.info("Ressponse: {}", createResponse.getBody());
		assertThat(createResponse.getStatusCode(), equalTo(HttpStatus.CREATED));

		URI idOfNewUser = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(
			idOfNewUser ,String.class);

		assertEquals(HttpStatus.OK, getResponse.getStatusCode());

		LOG.info("Added user Details: {}", getResponse.getBody());

	}





}
