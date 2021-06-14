package ru.otus.listener.homework;


import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.otus.listener.homework.HistoryListener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class HistoryListenerTest {

    @Test
    void listenerTest() {
        //given
        var historyListener = new HistoryListener();

        var id = 100L;
        var data = "33";
        var field13 =  new ObjectForMessage();
        field13.setData(List.of(data));

        var message = new Message.Builder(id)
                .field10("field10")
                .field13(field13)
                .build();

        var messageB = new Message.Builder(12L)
                .field10("field10")
                .build();

        //when
        historyListener.onUpdated(message, messageB);
        message.getField13().setData(new ArrayList<>()); //меняем исходное сообщение

        //then
        var messageFromHistory = historyListener.findMessageById(id);
        AssertionsForClassTypes.assertThat(messageFromHistory).isPresent();
        assertThat(messageFromHistory.get().getField13().getData()).containsExactly(data);
    }
}