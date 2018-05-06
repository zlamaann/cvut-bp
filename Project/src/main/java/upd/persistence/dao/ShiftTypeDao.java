package upd.persistence.dao;

import org.springframework.stereotype.Repository;
import upd.model.ShiftType;

@Repository
public class ShiftTypeDao extends BaseDao<ShiftType> {

    protected ShiftTypeDao() {
        super(ShiftType.class);
    }
}
