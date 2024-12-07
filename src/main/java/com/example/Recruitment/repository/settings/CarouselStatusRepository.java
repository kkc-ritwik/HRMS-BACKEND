package com.example.Recruitment.repository.settings;

import com.example.Recruitment.model.settings.CarouselStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarouselStatusRepository extends MongoRepository<CarouselStatus, String> {
}
