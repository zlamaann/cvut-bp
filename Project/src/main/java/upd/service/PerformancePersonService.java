package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upd.model.PerformancePerson;
import upd.persistence.dao.GenericDao;
import upd.persistence.dao.PerformancePersonDao;

@Service
public class PerformancePersonService extends BaseService<PerformancePerson> {

    @Autowired
    public PerformancePersonDao performancePersonDao;


    @Override
    protected GenericDao<PerformancePerson> getPrimaryDao() {
        return performancePersonDao;
    }
}
