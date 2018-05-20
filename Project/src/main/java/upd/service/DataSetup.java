package upd.service;

/**
 * Created by Anda on 13.1.2017.
 */

import upd.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upd.model.Address;
import upd.model.Person;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSetup {

    /*@Autowired
    private PersonService personService;

    @PostConstruct
    private void setupData() {

        Address address = new Address();
        address.setId(1);
        address.setStreetName("Květinová");
        address.setStreetNumber(5);
        address.setCity("Praha");
        address.setPostalCode(12000);

        String password = "admin";

        Person person = new Person("Anna", "Zlámalová", "admin", "email@email.cz", 111222333, address);
        personService.persist(person);

        /* USERS.stream().filter(t -> !userService.exists(t.getName()))
                .forEach(t -> userService.persist(t)); */

        /* CATEGORIES.stream().filter(t -> !priceService.exists(t.getId()))
                .forEach(t -> priceService.persist(t));

        COMPANY_TYPES.stream().filter(t -> !companyTypeService.exists(t.getId()))
                .forEach(t -> companyTypeService.persist(t));
    }*/



}
