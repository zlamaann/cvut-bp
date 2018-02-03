package upd.persistence.dao;

import org.springframework.stereotype.Repository;
import upd.model.City;

@Repository
public class CityDao extends BaseDao<City> {

    protected CityDao() {
        super(City.class);
    }
}
