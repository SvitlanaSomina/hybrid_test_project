package api_model;

public class SuccessfulRegister {
    private int id;
    private String token;

    public SuccessfulRegister() {
        super();
    }

    public SuccessfulRegister(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}

