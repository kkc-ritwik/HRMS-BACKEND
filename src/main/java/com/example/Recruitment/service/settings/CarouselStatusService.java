package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.CarouselStatusDTO;

import java.util.List;

public interface CarouselStatusService {
    CarouselStatusDTO saveCarouselStatus(CarouselStatusDTO carouselStatusDTO);
    List<CarouselStatusDTO> getAllCarouselStatuses();
    CarouselStatusDTO getCarouselStatusById(String id);
    CarouselStatusDTO updateCarouselStatus(String id, CarouselStatusDTO carouselStatusDTO);
    void deleteCarouselStatusById(String id);
}
