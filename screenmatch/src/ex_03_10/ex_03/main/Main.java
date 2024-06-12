package ex_03_10.ex_03.main;

import com.google.gson.Gson;
import ex_03_10.ex_03.exception.GitHubQueryErrorException;
import ex_03_10.ex_03.model.GitHubUser;
import ex_03_10.ex_03.view.MainWindow;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Search user: ");
        String user = scanner.nextLine();

        final String baseUri = "https://api.github.com/users/";
        final String uri = baseUri + URLEncoder.encode(user, "UTF-8");

        System.out.println(uri);
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

        MainWindow mainWindow = new MainWindow();
    }
}
