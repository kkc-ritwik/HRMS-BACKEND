package com.example.Recruitment.controller.settings;

import com.example.Recruitment.config.ApiVersionConfig;
import com.example.Recruitment.dto.settings.CarouselStatusDTO;
import com.example.Recruitment.service.settings.CarouselStatusService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(ApiVersionConfig.API_VERSION + "/carousel")
@CrossOrigin("*")
public class CarouselStatusController {

    @Autowired
    private CarouselStatusService carouselStatusService;

    @PostMapping
    public ResponseEntity<CarouselStatusDTO> createCarouselStatus(@Valid @RequestBody CarouselStatusDTO carouselStatusDTO) {
        try {
            CarouselStatusDTO createdStatus = carouselStatusService.saveCarouselStatus(carouselStatusDTO);
            return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating carousel status", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<CarouselStatusDTO>> getAllCarouselStatuses() {
        List<CarouselStatusDTO> statuses = carouselStatusService.getAllCarouselStatuses();
        return statuses.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(statuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarouselStatusDTO> getCarouselStatusById(@PathVariable String id) {
        CarouselStatusDTO status = carouselStatusService.getCarouselStatusById(id);
        if (status == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carousel status not found with ID: " + id);
        }
        return ResponseEntity.ok(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarouselStatusDTO> updateCarouselStatus(
            @PathVariable String id,
            @Valid @RequestBody CarouselStatusDTO carouselStatusDTO) {
        try {
            CarouselStatusDTO updatedStatus = carouselStatusService.updateCarouselStatus(id, carouselStatusDTO);
            return ResponseEntity.ok(updatedStatus);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating carousel status", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarouselStatus(@PathVariable String id) {
        try {
            carouselStatusService.deleteCarouselStatusById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting carousel status", e);
        }
    }
}
