package com.example.Expense.Management.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
@Data
public class Client {
    @Id
    private String id;
    private String name;
    private String administrator;
    private String nick;
    private String email;
    private String website;
    private String billingRate;
    private String address1;
    private String address2;
    private String country;
    private String city;
    private String state;
    private String zipcode;
    private String telephone1;
    private String telephone2;
    private String fax;
}