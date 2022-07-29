package com.example.gestiontaches.dto;

public class TokenDto {
    private String authentification;
    private String refresh;

    public TokenDto() {
    }

    public TokenDto(String authentification, String refresh) {
        this.authentification = authentification;
        this.refresh = refresh;
    }

    public String getAuthentification() {
        return authentification;
    }

    public void setAuthentification(String authentification) {
        this.authentification = authentification;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
}
