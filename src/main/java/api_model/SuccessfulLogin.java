package api_model;

public class SuccessfulLogin {
    private String token;

    public SuccessfulLogin() {
        super();
    }

    public SuccessfulLogin(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
