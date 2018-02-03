package upd.persistence.dao;

import org.springframework.stereotype.Repository;
import upd.model.PersonType;

@Repository
public class PersonTypeDao extends BaseDao<PersonType> {

    protected PersonTypeDao() {
        super(PersonType.class);
    }
}
