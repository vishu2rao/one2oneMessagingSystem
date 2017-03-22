package com.eventm.message;

import com.eventm.domain.EventMessageModel;

/**
 * @author viswesswarar
 */
public class EventMessageAlt {

    private EventMessageModel content;

    public EventMessageAlt(EventMessageModel content) {
        this.content = content;
    }

    public EventMessageModel getContent() {
        return content;
    }
}
