package com.eventm.domain;

import org.springframework.data.annotation.Id;


import java.util.Date;

/**
 * @author visweswarar
 */
public class EventMessageReadModel {

    private String to;   
    
    private String from;
    private Date createDate;

    public EventMessageReadModel() {
    }

    public EventMessageReadModel(String from,String to) {
      
        this.from = from;
      
        this.to = to;
    } 
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
      public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }  

    @Override
    public String toString() {
        return "{" +
                 "\"to\":\"" + to + '\"' +
                 ",\"from\":\"" + from + '\"' +
                '}';
    }
}
