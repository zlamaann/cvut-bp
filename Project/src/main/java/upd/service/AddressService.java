package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upd.model.Address;
import upd.persistence.dao.AddressDao;
import upd.persistence.dao.GenericDao;

@Service
public class AddressService extends BaseService<Address> {

    @Autowired
    public AddressDao addressDao;


    @Override
    protected GenericDao<Address> getPrimaryDao() {
        return addressDao;
    }
}
