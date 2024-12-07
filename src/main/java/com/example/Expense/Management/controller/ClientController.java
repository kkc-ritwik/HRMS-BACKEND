package com.example.Expense.Management.controller;

import com.example.Expense.Management.config.ApiVersionConfig;
import com.example.Expense.Management.dto.ClientDto;
import com.example.Expense.Management.entity.Client;
import com.example.Expense.Management.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody ClientDto clientDto) {
        Client client = clientService.addClient(clientDto);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody ClientDto clientDto) {
        Client client = clientService.updateClient(id, clientDto);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-form")
    public ResponseEntity<Void> sendClientForm(@RequestBody Map<String, Object> payload) {
        String emailAddress = (String) payload.get("emailAddress");
        String billingRate = payload.get("billingRate") != null
                ? payload.get("billingRate").toString()
                : "";
        clientService.sendClientForm(emailAddress, billingRate);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/submit-form")
    public ResponseEntity<Client> submitClientForm(@RequestBody ClientDto clientDto) {
        Client client = clientService.addClient(clientDto);
        return ResponseEntity.ok(client);
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> getTotalClientCount() {
        int count = clientService.getTotalClientCount();
        return ResponseEntity.ok(count);
    }

}