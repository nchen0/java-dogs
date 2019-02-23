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
        //linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel() asks that Spring HATEOAS build a link to the EmployeeController 's one() method, and flag it as a self link.
        //linkTo(methodOn(EmployeeController.class).all()).withRel("employees") asks Spring HATEOAS to build a link to the aggregate root, all(), and call it "employees", in our case, dogs.

        // To Simplify Link Creation:
        // Simply put, you need to define a function that converts Employee objects to Resource<Employee> objects. While you could easily code this method yourself, there are benefits down the road of implementing Spring HATEOAS’s ResourceAssembler interface.
        // This simple interface has one method: toResource(). It is based on converting a non-resource object (Employee) into a resource-based object (Resource<Employee>).
    }
}
