package br.com.alura.forum.config.errors;

public class RestErrorDTO {
    private String field;
    private String message;

    public RestErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
