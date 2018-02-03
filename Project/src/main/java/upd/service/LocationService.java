package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upd.model.Location;
import upd.persistence.dao.GenericDao;
import upd.persistence.dao.LocationDao;

@Service
public class LocationService extends BaseService<Location> {

    @Autowired
    public LocationDao locationDao;


    @Override
    protected GenericDao<Location> getPrimaryDao() {
        return locationDao;
    }
}
