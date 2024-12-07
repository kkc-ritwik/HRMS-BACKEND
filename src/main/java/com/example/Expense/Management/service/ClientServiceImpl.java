

package com.example.Expense.Management.service;

import com.example.Expense.Management.dto.ClientDto;
import com.example.Expense.Management.entity.Client;
import com.example.Expense.Management.repository.ClientRepository;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
@Value("${frontendmailurl}")
private String frontendurl;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Client addClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(String id, ClientDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        modelMapper.map(clientDto, existingClient);
        return clientRepository.save(existingClient);
    }

    @Override
    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void sendClientForm(String emailAddress, String billingRate) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailAddress);
            helper.setSubject("Client Registration Form");

            Map<String, Object> model = new HashMap<>();
            model.put("submitUrl", frontendurl+"/clients/add");

            // Ensure billingRate is passed correctly
            model.put("defaultBillingRate", billingRate != null ? billingRate : "Not specified");

            Template template = freemarkerConfig.getTemplate("client-form-template.ftl");
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email", e);
        }
    }

    @Override
    public int getTotalClientCount() {
        return (int) clientRepository.count();
    }
}