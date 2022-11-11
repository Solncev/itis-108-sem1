package com.solncev.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class NetSample {

    public static void main(String[] args) {

        // get method
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts?userId=2");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

//            System.out.println(connection.getResponseCode());

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(connection.getInputStream())
                         )) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
//                System.out.println(content.toString());
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // post
        try {
            URL postUrl = new URL("https://gorest.co.in/public/v1/users");
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            String token = "a3d70ee6db24230185f738f9b0653a40c2747ae5f52ef65c03520eaca00e8495";

            postConnection.setRequestMethod("POST");

            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestProperty("Authorization", "Bearer " + token);

            postConnection.setDoOutput(true);

            // email should be unique, in otherwise 422 status code will bee returned
            String jsonInputString = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\"tenali.ramakrishna2@gmail.com\", \"status\":\"active\"}";

            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println(postConnection.getResponseCode());

            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(postConnection.getInputStream())
                         )) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                System.out.println(content.toString());
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
