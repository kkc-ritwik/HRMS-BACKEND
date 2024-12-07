package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.ClientDto;
import com.example.Expense.Management.entity.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {
    Client addClient(ClientDto clientDto);
    List<Client> getAllClients();
    Client updateClient(String id, ClientDto clientDto);
    void deleteClient(String id);
    void sendClientForm(String emailAddress, String billingRate);
    int getTotalClientCount();  // New method to get total client count
}