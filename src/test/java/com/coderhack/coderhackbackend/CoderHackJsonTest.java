package com.coderhack.coderhackbackend;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.core.io.ClassPathResource;

import com.coderhack.coderhackbackend.constants.Badges;
import com.coderhack.coderhackbackend.entity.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@JsonTest
public class CoderHackJsonTest {
    private static final Logger LOG = LoggerFactory.getLogger(CoderHackJsonTest.class);

    @Autowired
    private JacksonTester<User> json;

    @Autowired
    private JacksonTester<User[]> jsonList;

    @Autowired
    ObjectMapper objectMapper;

    private User[] users;

    @BeforeEach
    void setUp(){
        Set<Badges> badge1 = new HashSet<>();
        badge1.add(Badges.CODE_CHAMP);
        Set<Badges> badge2 = new HashSet<>();
        badge2.add(Badges.CODE_CHAMP);
        badge2.add(Badges.CODE_MASTER);


        users = Arrays.array(
            new User("1", "x@singh", 1001, badge1),
            new User("2", "y@singh", 1001, badge2)
        );
    }


    @Test
    void userSerializationTest() throws IOException{
        // assertThat(1, is(equalTo(1)));

        ClassPathResource resource = new ClassPathResource("expected.json");
        JsonNode expectedJson = objectMapper.readTree(resource.getInputStream());

        Set<Badges> badges = new HashSet<>();
        badges.add(Badges.CODE_CHAMP);

       
        User userRegistered = new User("1", "x@singh", 1001, badges);

    
        String expectedJsonAsString = objectMapper.writeValueAsString(expectedJson);

        LOG.info("testJson: {}  testJsonAsString: {}, expectedJson: {}", json.write(userRegistered).getJson(), expectedJsonAsString, expectedJson);

        assertThat(json.write(userRegistered).getJson(),  equalTo(expectedJsonAsString));
        
        
        //assertEquals(expectedJsonAsString, objectMapper.writeValueAsString(userRegistered));


    }

    @Test
    void userListSerializationTest() throws IOException{
        // assertThat(1, is(equalTo(1)));

        ClassPathResource resource = new ClassPathResource("userList.json");
        JsonNode expectedJson = objectMapper.readTree(resource.getInputStream());

      
        String expectedJsonAsString = objectMapper.writeValueAsString(expectedJson);

        LOG.info("testJsonLIST: {}  testJsonAsString: {}, expectedJson: {}", jsonList.write(users).getJson(), expectedJsonAsString, expectedJson);

        assertThat(jsonList.write(users).getJson(),  equalTo(expectedJsonAsString));
        
        //assertEquals(expectedJsonAsString, objectMapper.writeValueAsString(userRegistered));


    }

    @Test
    void userDeserializationTest() throws IOException{
        
        String expected = """      
        {
            "id": 1,
            "userName": "x@singh",
            "score": 1001.0,
            "badges": ["CODE_CHAMP"]
        }
                """;
        
        Set<Badges> badges = new HashSet<>();
        badges.add(Badges.CODE_CHAMP);

        User userRegistered = new User("1", "x@singh", 1001, badges);

        LOG.info("testObjectFromJson: {}  testObject: {}", json.parse(expected), userRegistered.toString());

        // assertThat(json.parse(expected).getObject(),  equalTo(userRegistered));
        
        
        assertEquals(json.parse(expected).getObject(), userRegistered);


    }
    
}
