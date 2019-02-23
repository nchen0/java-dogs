package com.lambda.javadogs;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/dogs")
public class DogController {
    private final DogRepository repository;
    private final DogResourceAssembler assembler;

    // To leverage this assembler, you only have to alter the EmployeeController by injecting the assembler in the constructor.
    public DogController(DogRepository repository, DogResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // CRUD Operations
    @GetMapping("/breeds")
    public Resources<Resource<Dogs>> getAll() {
        List<Resource<Dogs>> dogs = repository.findAll().stream()
                .map(assembler::toResource)
                .sorted((d1, d2) -> d1.getContent().getBreed().compareToIgnoreCase(d2.getContent().getBreed()))
                .collect(Collectors.toList());
        return new Resources<>(dogs, linkTo(methodOn(DogController.class).getAll()).withSelfRel());
    }

    @GetMapping("/weight")
    public Resources<Resource<Dogs>> sortByWeight()
    {
        List<Resource<Dogs>> dogs = repository.findAllByOrderByWeight().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(dogs,
                linkTo(methodOn(DogController.class).getAll()).withSelfRel());
    }

    @GetMapping("/apartment")
    public Resources<Resource<Dogs>> getApartmentDogs()
    {
        List<Resource<Dogs>> dogs = repository.findAllByApartmentDog(true).stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(dogs,
                linkTo(methodOn(DogController.class).getAll()).withSelfRel());
    }

    @GetMapping("/breeds/{breed}")
    public Resources<Resource<Dogs>> getBreed(@PathVariable String breed)
    {
        List<Resource<Dogs>> dogs = repository.findAllByBreed(breed).stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(dogs,
                linkTo(methodOn(DogController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<Dogs> getOne(@PathVariable Long id) {
        Dogs dog = repository.findById(id)
                .orElseThrow(() -> new DogNotFoundException(id));
        return assembler.toResource(dog);
    }
}
