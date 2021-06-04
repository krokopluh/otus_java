package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.HashMap;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    HashMap<Long,MessageDataStorage> inMemoryDb = new HashMap<>();


    @Override
    public void onUpdated(Message oldMsg, Message newMsg){
        inMemoryDb.put(oldMsg.getId(), new MessageDataStorage(oldMsg,newMsg));
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.of(inMemoryDb.get(id).getOriginalMessage());
    }
}
