package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upd.model.City;
import upd.persistence.dao.CityDao;
import upd.persistence.dao.GenericDao;

@Service
public class CityService extends BaseService<City> {

    @Autowired
    public CityDao cityDao;


    @Override
    protected GenericDao<City> getPrimaryDao() {
        return cityDao;
    }
}
