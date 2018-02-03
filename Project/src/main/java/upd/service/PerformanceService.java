package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upd.model.Performance;
import upd.persistence.dao.GenericDao;
import upd.persistence.dao.PerformanceDao;

@Service
public class PerformanceService extends BaseService<Performance> {

    @Autowired
    public PerformanceDao performanceDao;

    @Override
    protected GenericDao<Performance> getPrimaryDao() {
        return performanceDao;
    }
}
