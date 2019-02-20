package com.lambda.javadogs;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DogResourceAssembler implements ResourceAssembler <Dogs, Resource<Dogs>> {
    @Override
    public Resource<Dogs> toResource(Dogs dog) {
        return new Resource<Dogs>(dog,
                linkTo(methodOn(DogController.class).getOne(dog.getId())).withSelfRel(),
                linkTo(methodOn(DogController.class).getAll()).withRel("dogs"));
    }
}
