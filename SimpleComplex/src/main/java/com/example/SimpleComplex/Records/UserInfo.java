package com.example.SimpleComplex.Records;

public record UserInfo(
    int id,
    String firstName,
    String lastName,
    String complexName,
    String email,
    String password,
    String phoneNumber,
    String address,
    String city,
    String state,
    String zipCode,
    String country
) {

}
