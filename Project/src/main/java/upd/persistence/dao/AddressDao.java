package upd.persistence.dao;

import org.springframework.stereotype.Repository;
import upd.model.Address;

@Repository
public class AddressDao extends BaseDao<Address> {

    protected AddressDao() {
        super(Address.class);
    }
}
