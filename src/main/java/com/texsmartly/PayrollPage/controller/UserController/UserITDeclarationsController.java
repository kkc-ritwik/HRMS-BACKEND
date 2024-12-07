package com.texsmartly.PayrollPage.controller.UserController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.ITDeclarationsDTO;
import com.texsmartly.PayrollPage.service.interf.ITDeclarationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping(ApiVersionConfig.API_VERSION + "/user/it-declarations")
@RestController
public class UserITDeclarationsController {
    @Autowired
    private ITDeclarationService itDeclarationsService;
    @PostMapping
    public ResponseEntity<ITDeclarationsDTO> saveITDeclaration(@RequestBody ITDeclarationsDTO itDeclarationsDTO, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        // Set the userId from the principal
        itDeclarationsDTO.setUserId(principal.getName());

        // Save the IT declaration and return the response
        ITDeclarationsDTO response = itDeclarationsService.saveITDeclaration(itDeclarationsDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<List<ITDeclarationsDTO>> getITDeclarationsByUser(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("User is not authenticated");
        }
        List<ITDeclarationsDTO> itDeclarationsDTOS = itDeclarationsService.getITDeclarationsByUser(principal.getName());
        return ResponseEntity.ok(itDeclarationsDTOS);
    }
}
