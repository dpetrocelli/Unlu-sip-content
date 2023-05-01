package spring.examples.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpRequests {

    
    public void HttpRequests(){
        
    }

    
    public String PostHttpRequest(String url , HashMap<String, String> parametersMap) throws IOException, InterruptedException {


        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(parametersMap);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        return "";
    }
}
