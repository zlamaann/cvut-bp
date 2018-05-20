package upd.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import upd.exception.NotFoundException;
import upd.model.Message;
import upd.model.Person;
import upd.rest.exception.DataConflictException;
import upd.rest.util.RestUtils;
import upd.service.MessageService;
import upd.service.PersonService;

import java.util.List;

import static upd.rest.BaseController.LOG;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getAll() {
        return messageService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Message create(@RequestBody Message message) {
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Person current = personService.getByEmail(email);
        message.setAuthor(current);*/
        messageService.persist(message);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Message {} successfully created.", message);
        }
        final HttpHeaders headers = RestUtils
                .createLocationHeaderFromCurrentUri("/{name}", message.getSubject());
        //return new ResponseEntity<>(headers, HttpStatus.CREATED);
        return message;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Message update(@PathVariable("id") Integer messageId, @RequestBody Message message) {
        if (!messageId.equals(message.getId())) {
            throw new DataConflictException(
                    "Message id " + messageId + " in the URL does not match the message id " + message.getId() +
                            " in the data.");
        }
        if (!messageService.exists(messageId)) {
            throw NotFoundException.create("Message", messageId);
        }
        messageService.update(message);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Message {} updated.", message);
        }
        return message;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("id") Integer messageId) {
        final Message message = messageService.find(messageId);
        if (message == null) {
            throw NotFoundException.create("Message", messageId);
        }
        messageService.remove(message);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Message {} successfully removed.", message);
        }
        return message;
    }

}
