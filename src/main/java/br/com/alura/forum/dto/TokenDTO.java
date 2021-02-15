package br.com.alura.forum.dto;

public class TokenDTO {
    private String token;
    private String type;

    public TokenDTO(String token, String type) {
        this.type = type;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }
}
