package upd.persistence.dao;

import org.springframework.stereotype.Repository;
import upd.model.Location;

@Repository
public class LocationDao extends BaseDao<Location> {

    protected LocationDao() {
        super(Location.class);
    }
}
