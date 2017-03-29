package com.epam.core.after.injection;

import org.springframework.format.datetime.DateFormatter;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * Created by Ermek_Abyshev on 3/27/2017.
 */
public class Event {

    private String id;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
        this.id = UUID.randomUUID().toString();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
               "id='" + id + '\'' +
               ", msg='" + msg + '\'' +
               ", date=" + df.format(date) +
               '}' +'\n';
    }
}
