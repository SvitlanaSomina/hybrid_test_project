package api_model;

public class UnsuccessfulLogin {
    private String error;

    public UnsuccessfulLogin() {
        super();
    }

    public UnsuccessfulLogin(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}

