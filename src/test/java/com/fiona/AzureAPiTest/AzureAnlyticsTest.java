package com.fiona.AzureAPiTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AzureAnlyticsTest {


    @Value("${AzureApiKey}")
    private  String AzureAPiKey;

    private static final String  AZURE_ENDPOINT="https://meru-hotel-feedback.cognitiveservices.azure.com/" ;
    private static final  String AZURE_ENDPOINT_PATH ="text/analytics/v3.0/keyPhrases";
    private static final  String API_KEY_HEADER_NAME="Ocp-Apim-Subscription-Key";
    private static final String CONTENT_TYPE="Content_Type";
    private static  final String APPLICATION_JSON="application/json";
    private  static final    String EXAMPLE_JSON = "";

    private static final String textForAnalysis="Hello world. This is some input text that I love.";

    @Autowired
    public ObjectMapper mapper;
    @Test
    public void getKeyPhrases() throws IOException,InterruptedException{

        TextDocument document = new TextDocument("1",textForAnalysis,"en");
        TextAnalyticsRequest reqBody   = new TextAnalyticsRequest();
        reqBody.getDocuments().add(document);
        HttpClient client= HttpClient.newHttpClient();

        HttpRequest.BodyPublishers BodyPublisher = null;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AZURE_ENDPOINT+AZURE_ENDPOINT_PATH))
                .header(CONTENT_TYPE,APPLICATION_JSON)
                .header(API_KEY_HEADER_NAME,this.AzureAPiKey)
                .POST(BodyPublisher.ofString(mapper.writeValueAsString(reqBody)))
                .build();

        HttpResponse<String> response =client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200,response.statusCode());
        System.out.println(response.body());
    }


}
