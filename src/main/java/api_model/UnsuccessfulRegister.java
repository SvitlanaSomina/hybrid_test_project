package api_model;

public class UnsuccessfulRegister {
    private String error;

    public UnsuccessfulRegister() {
        super();
    }

    public UnsuccessfulRegister(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
