package ex_03_10.ex_03.model;

public record GitHubUser(String login, String bio, int public_repos) {
    @Override
    public String toString() {
        return """
                Login: %s
                Bio: %s
                Public repositories: %d""".formatted(login, bio, public_repos);
    }
}
