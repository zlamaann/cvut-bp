package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upd.model.ShiftType;
import upd.persistence.dao.GenericDao;
import upd.persistence.dao.ShiftTypeDao;

@Service
public class ShiftTypeService extends BaseService<ShiftType> {

    @Autowired
    public ShiftTypeDao shiftTypeDao;

    @Override
    protected GenericDao<ShiftType> getPrimaryDao() {
        return shiftTypeDao;
    }
}
