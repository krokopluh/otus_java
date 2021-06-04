package ru.otus.listener.homework;

import ru.otus.model.Message;

public class MessageDataStorage {

    private final Message originalMessage;
    private final Message updatedMessage;

    public MessageDataStorage(Message originalMessage, Message updatedMessage){
        this.originalMessage = originalMessage.toBuilder().build();
        this.updatedMessage = updatedMessage.toBuilder().build();
    }

    public Message getOriginalMessage() {
        return originalMessage;
    }

    public Message getUpdatedMessage() {
        return updatedMessage;
    }
}
