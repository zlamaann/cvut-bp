package upd.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSetup {

    /*private static final List<City> CITIES = initCities();

    private static final List<User> USERS = initUsers();

    private static final List<PriceCategory> CATEGORIES = initCategories();

    private static final List<CompanyType> COMPANY_TYPES = initCompanyTypes();


    @Autowired
    private UserService userService;

    @Autowired
    private PriceCategoryService priceService;

    @Autowired
    private CompanyTypeService companyTypeService;

    @Autowired
    private CompanyService companyService;

    private static List<City> initCities() {
        final List<City> lst = new ArrayList<>();
        lst.add(new City("Praha 2", 12000));
        lst.add(new City("Praha 6", 16000));
        return lst;
    }

    private static List<User> initUsers() {
        final List<User> lst = new ArrayList<>();
        /* lst.add(new User(Role.ADMIN_ROLE, "Anda", "Panda", "admin", "admin", "email", "Please work, you bastard!", 0));
        lst.add(new User(Role.USER_ROLE, "Anda", "Panda", "user", "user", "email", "Please work, you bastard!", 0));
        */
        /*lst.add(new User(Role.ADMIN_ROLE, "Trang", "Vu", "trang", "trang", "vuthitra@fel.cvut.cz", "Eat clean.", 0));

        return lst;
    }

    private static List<PriceCategory> initCategories() {
        final List<PriceCategory> lst = new ArrayList<>();
        lst.add(new PriceCategory("HIGH"));
        lst.add(new PriceCategory("MIDDLE"));
        lst.add(new PriceCategory("LOW"));
        return lst;
    }

    private static List<CompanyType> initCompanyTypes() {
        final List<CompanyType> lst = new ArrayList<>();
        lst.add(new CompanyType("RESTAURANT"));
        lst.add(new CompanyType("COFFEEHOUSE"));
        lst.add(new CompanyType("PUB"));
        lst.add(new CompanyType("TEAHOUSE"));
        lst.add(new CompanyType("KEBAB"));
        return lst;
    }*/

    @PostConstruct
    private void setupData() {
        /*
        Address address = new Address();
        address.setId(1);
        address.setStreet("Květinová");
        address.setNumber(5);
        address.setCity(city);

        CompanyType companyType = companyTypeService.findByName("TEAHOUSE");
        PriceCategory priceCategory = priceService.findByName("LOW");
        Company company = new Company("Květinová čajovna", 5, companyType, priceCategory, address);
        companyService.persist(company); */

        /* USERS.stream().filter(t -> !userService.exists(t.getName()))
                .forEach(t -> userService.persist(t)); */

        /* CATEGORIES.stream().filter(t -> !priceService.exists(t.getId()))
                .forEach(t -> priceService.persist(t));

        COMPANY_TYPES.stream().filter(t -> !companyTypeService.exists(t.getId()))
                .forEach(t -> companyTypeService.persist(t)); */
    }



}
