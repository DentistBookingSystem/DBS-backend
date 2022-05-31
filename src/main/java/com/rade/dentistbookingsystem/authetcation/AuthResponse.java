package com.rade.dentistbookingsystem.authetcation;

public class AuthResponse {
    private String phone;
    private String accessToken;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String phone, String accessToken) {
        this.phone = phone;
        this.accessToken = accessToken;

    }

    public AuthResponse(String phone, String accessToken, String role) {
        this.phone = phone;
        this.accessToken = accessToken;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
