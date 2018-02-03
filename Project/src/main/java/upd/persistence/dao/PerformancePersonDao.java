package upd.persistence.dao;

import org.springframework.stereotype.Repository;
import upd.model.PerformancePerson;

@Repository
public class PerformancePersonDao extends BaseDao<PerformancePerson> {

    protected PerformancePersonDao() {
        super(PerformancePerson.class);
    }
}
