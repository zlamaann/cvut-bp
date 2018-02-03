package upd.persistence.dao;

import org.springframework.stereotype.Repository;
import upd.model.Message;

@Repository
public class MessageDao extends BaseDao<Message> {

    protected MessageDao() {
        super(Message.class);
    }
}
