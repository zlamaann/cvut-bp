package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upd.model.PersonType;
import upd.persistence.dao.BaseDao;
import upd.persistence.dao.GenericDao;
import upd.persistence.dao.PersonTypeDao;

@Service
public class PersonTypeService extends BaseService<PersonType> {

    @Autowired
    public PersonTypeDao personTypeDao;

    @Override
    protected GenericDao<PersonType> getPrimaryDao() {
        return personTypeDao;
    }
}
