package com.texsmartly.PayrollPage.controller.AdminController;

import com.texsmartly.PayrollPage.config.ApiVersionConfig;
import com.texsmartly.PayrollPage.dto.ITDeclarationsDTO;
import com.texsmartly.PayrollPage.service.interf.ITDeclarationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(ApiVersionConfig.API_VERSION + "/admin/it-declarations")
@RestController
public class AdminITDeclarationsController {
    @Autowired
    private ITDeclarationService itDeclarationsService;
    @GetMapping
    public ResponseEntity<List<ITDeclarationsDTO>> getAllITDeclarations() {
        List<ITDeclarationsDTO> itDeclarations = itDeclarationsService.getAllITDeclarations();
        return ResponseEntity.ok(itDeclarations);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<ITDeclarationsDTO>> getITDeclarationsByUserId(@PathVariable String userId) {
        List<ITDeclarationsDTO> userDeclarations = itDeclarationsService.getITDeclarationsByUserId(userId);
        return ResponseEntity.ok(userDeclarations);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<ITDeclarationsDTO> updateITDeclarationsByUserId(
            @PathVariable String userId,
            @RequestBody ITDeclarationsDTO updatedITDeclarationsDTO) {
        ITDeclarationsDTO updatedDeclaration = itDeclarationsService.updateITDeclarationsByUserId(userId, updatedITDeclarationsDTO);
        return ResponseEntity.ok(updatedDeclaration);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteITDeclarationsByUserId(@PathVariable String userId) {
        itDeclarationsService.deleteITDeclarationsByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
