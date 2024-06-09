package ex_03_10.ex_03.exception;

public class GitHubQueryErrorException extends RuntimeException {

    public GitHubQueryErrorException(String message) {
        super(message);
    }

}
