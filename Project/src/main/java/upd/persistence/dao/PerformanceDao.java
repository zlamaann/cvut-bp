package upd.persistence.dao;

import org.springframework.stereotype.Repository;
import upd.model.Performance;

@Repository
public class PerformanceDao extends BaseDao<Performance> {

    protected PerformanceDao() {
        super(Performance.class);
    }
}
