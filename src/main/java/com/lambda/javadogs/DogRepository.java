package com.lambda.javadogs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dogs, Long> {
    List<Dogs> findAllByOrderByWeight();
    List<Dogs> findAllByApartmentDog(Boolean bool);
    List<Dogs> findAllByBreed(String breed);
}
