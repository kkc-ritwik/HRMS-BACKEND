package com.example.Recruitment.service.settings;

import com.example.Recruitment.dto.settings.CarouselStatusDTO;
import com.example.Recruitment.model.settings.CarouselStatus;
import com.example.Recruitment.repository.settings.CarouselStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarouselStatusServiceImpl implements CarouselStatusService {

    @Autowired
    private CarouselStatusRepository carouselStatusRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CarouselStatusDTO saveCarouselStatus(CarouselStatusDTO carouselStatusDTO) {
        CarouselStatus carouselStatus = modelMapper.map(carouselStatusDTO, CarouselStatus.class);
        carouselStatus = carouselStatusRepository.save(carouselStatus);
        return modelMapper.map(carouselStatus, CarouselStatusDTO.class);
    }

    @Override
    public List<CarouselStatusDTO> getAllCarouselStatuses() {
        return carouselStatusRepository.findAll()
                .stream()
                .map(status -> modelMapper.map(status, CarouselStatusDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarouselStatusDTO getCarouselStatusById(String id) {
        CarouselStatus carouselStatus = carouselStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carousel Status not found"));
        return modelMapper.map(carouselStatus, CarouselStatusDTO.class);
    }

    @Override
    public CarouselStatusDTO updateCarouselStatus(String id, CarouselStatusDTO carouselStatusDTO) {
        CarouselStatus existingStatus = carouselStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carousel Status not found"));
        modelMapper.map(carouselStatusDTO, existingStatus);
        CarouselStatus updatedStatus = carouselStatusRepository.save(existingStatus);
        return modelMapper.map(updatedStatus, CarouselStatusDTO.class);
    }

    @Override
    public void deleteCarouselStatusById(String id) {
        carouselStatusRepository.deleteById(id);
    }
}
