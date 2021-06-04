package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.Clock;

public class ProcessorTimeDependant implements Processor {

    private final Clock clock;

    public ProcessorTimeDependant(Clock clock) {
        this.clock = clock;
    }

    @Override
    public Message process(Message message) throws Exception{

        long millis = clock.millis();
        System.out.println("Current time is: " + millis);

        if ((millis/1000)%2==0){
            throw new Exception("Oh, it's even second. This processor doesn't like it.");
        }

        return message;
    }

}
