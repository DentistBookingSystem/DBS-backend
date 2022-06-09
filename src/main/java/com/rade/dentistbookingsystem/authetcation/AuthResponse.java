package com.rade.dentistbookingsystem.authetcation;

public class AuthResponse {
    private String phone;
    private String accessToken;

    public AuthResponse() { }

    public AuthResponse(String phone, String accessToken) {
        this.phone = phone;
        this.accessToken = accessToken;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
