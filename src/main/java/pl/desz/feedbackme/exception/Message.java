package pl.desz.feedbackme.exception;

import lombok.Value;

@Value
public class Message {

    private String msg;

    public Message(String msg) {
        this.msg = msg;
    }
}
