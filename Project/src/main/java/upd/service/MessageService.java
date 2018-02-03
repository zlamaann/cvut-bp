package upd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upd.model.Message;
import upd.persistence.dao.BaseDao;
import upd.persistence.dao.GenericDao;
import upd.persistence.dao.MessageDao;

@Service
public class MessageService extends BaseService<Message> {

    @Autowired
    public MessageDao messageDao;


    @Override
    protected GenericDao<Message> getPrimaryDao() {
        return messageDao;
    }
}
