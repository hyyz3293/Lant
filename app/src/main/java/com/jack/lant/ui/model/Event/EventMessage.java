package com.jack.lant.ui.model.Event;

public class EventMessage {
    public String address;
    public String msg;
    public EventMessage(String msg) {
        this.msg = msg;
    }
    public EventMessage(String address, String msg) {
        this.address = address;
        this.msg = msg;
    }
}
