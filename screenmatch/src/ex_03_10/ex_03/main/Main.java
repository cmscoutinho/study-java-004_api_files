package ex_03_10.ex_03.main;

import com.google.gson.Gson;
import ex_03_10.ex_03.exception.GitHubQueryErrorException;
import ex_03_10.ex_03.model.GitHubUser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Search user: ");
        String user = scanner.next();

        final String baseUri = "https://api.github.com/users/";
        final String uri = baseUri + user;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        try {
            Gson gson = new Gson();
            GitHubUser userObj = gson.fromJson(json, GitHubUser.class);
            if (userObj.login() == null) {
                throw new GitHubQueryErrorException("User not found!");
            }
            System.out.println(userObj);
        } catch (GitHubQueryErrorException e) {
            System.out.println(e.getMessage());
        }
    }
}
