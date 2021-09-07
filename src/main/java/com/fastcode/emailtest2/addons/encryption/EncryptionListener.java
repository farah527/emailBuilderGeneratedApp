package com.fastcode.emailtest2.addons.encryption;

import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * 
 *         This class is used to listen the @encryptMe annotation and preInsert listener
 *         actions will be overridden by the update action.
 *         Hibernate generates a prepared statement and fills in the parameters
 *         from the 'state' array present in the event. Hence any changes made
 *         to the this 'state' array are reflected in the sql statement
 *         generated by the hibernate and finally on the database. The insert
 *         and update events have a different copy of this states array.
 *
 * 
 */
@Component
public class EncryptionListener implements PreInsertEventListener, PreUpdateEventListener, PostLoadEventListener {
    @Autowired
    private FieldEncrypter fieldEncrypter;

    @Autowired
    private FieldDecrypter fieldDecrypter;

    @Override
    public void onPostLoad(PostLoadEvent event) {
        fieldDecrypter.decrypt(event.getEntity());
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        Object[] state = event.getState();
        String[] propertyNames = event.getPersister().getPropertyNames();
        Object entity = event.getEntity();
        fieldEncrypter.encrypt(state, propertyNames, entity);
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        Object[] state = event.getState();
        String[] propertyNames = event.getPersister().getPropertyNames();
        Object entity = event.getEntity();
        fieldEncrypter.encrypt(state, propertyNames, entity);
        return false;
    }
}
