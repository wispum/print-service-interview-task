package org.home.printservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.home.printservice.config.MongoConfig;
import org.home.printservice.service.PrintJobService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(PrintJobController.class)
@Import({MongoConfig.class})
@Slf4j
public class PrintJobControllerTest {


    @Autowired
    private MockMvc mvc;
    @MockBean
    private PrintJobService printJobService;

    @Before
    public void setup() {
        // configure our mock bean
        when(printJobService.saveJobRequest(any())).then((e) -> {
            log.info("request is {}", e.getArgument(0).toString());
            return new HashMap<String, Long>();
        });
    }

    /**
     * test only for checking format of request
     *
     * @throws Exception
     */
    @Test
    public void saveXmlJob() throws Exception {
        MvcResult mvcResult = mvc.perform(post("http://localhost:8080/api/v1/job")
                .contentType(MediaType.APPLICATION_XML)
                .content("<jobs>\n" +
                        "<job id=\"1\">\n" +
                        "<type>PRINT</type>\n" +
                        "<user>user1</user>\n" +
                        "<device>device1</device>\n" +
                        "<amount>10</amount>\n" +
                        "</job>\n" +
                        "<job id=\"2\">\n" +
                        "<type>SCAN</type>\n" +
                        "<user>user1</user>\n" +
                        "<device>device1</device>\n" +
                        "<amount>12</amount>\n" +
                        "</job>\n" +
                        "<job id=\"3\">\n" +
                        "<type>FAX</type>\n" +
                        "<user>user2</user>\n" +
                        "<device>device1</device>\n" +
                        "<amount>5</amount>\n" +
                        "</job>\n" +
                        "</jobs>"))
                .andReturn();
    }

    public void findAll() {
        // TODO
    }


}