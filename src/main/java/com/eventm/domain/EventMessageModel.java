package com.eventm.domain;

import org.springframework.data.annotation.Id;


import java.util.Date;

/**
 * @author visweswarar
 */
public class EventMessageModel {

    @Id
    private String id;

    private String text;
    
    private String to;
    
    
    private String from;
    private Date createDate;

    public EventMessageModel() {
    }

    public EventMessageModel(String text, String from, Date createDate,String to) {
        this.text = text;
        this.from = from;
        this.createDate = createDate;
        this.to = to;
    }

    public String getText() {
        return text;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ",\"to\":\"" + to + '\"' +
                ",\"text\":\"" + text + '\"' +
                ",\"from\":\"" + from + '\"' +
                ",\"createDate\":\"" + createDate + "\"" +
                '}';
    }
}
