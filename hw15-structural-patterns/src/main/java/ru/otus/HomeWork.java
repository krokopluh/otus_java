package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerPrinter;
import ru.otus.listener.homework.HistoryListener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.LoggerProcessor;
import ru.otus.processor.Processor;
import ru.otus.processor.ProcessorConcatFields;
import ru.otus.processor.ProcessorUpperField10;
import ru.otus.processor.homework.ProcessorSwapFields;
import ru.otus.processor.homework.ProcessorTimeDependant;

import java.time.Clock;
import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
            Секунда должна определяьться во время выполнения.
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
        Clock clock = Clock.systemDefaultZone();
        var processors = List.of((Processor) new LoggerProcessor(new ProcessorSwapFields()),
                new LoggerProcessor(new ProcessorTimeDependant(clock)));

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {System.out.println("Got an exeption: " + ex.getMessage());});
        var listenerHistory = new HistoryListener();
        complexProcessor.addListener(listenerHistory);

        var data = "33";
        var field13 =  new ObjectForMessage();
        field13.setData(List.of(data));

        var message = new Message.Builder(1L)
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .field13(field13)
                .build();

        var result = complexProcessor.handle(message);
        System.out.println(String.format("result: %s\n", result));

        complexProcessor.removeListener(listenerHistory);
        if (listenerHistory.findMessageById(1L).isPresent()){
            System.out.println("Original message was: " + listenerHistory.findMessageById(1L).get());
        }


    }
}
