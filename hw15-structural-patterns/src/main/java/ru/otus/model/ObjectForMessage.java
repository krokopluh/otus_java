package ru.otus.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectForMessage implements Cloneable{
    private List<String> data = new ArrayList<>();

    public List<String> getData() {
        return new ArrayList<>(data);
    }

    public void setData(List<String> data) {
        this.data.clear();
        this.data.addAll(data);
    }

    protected ObjectForMessage clone(){
        ObjectForMessage clonedMessage = new ObjectForMessage();
        clonedMessage.setData(data);
        return clonedMessage;
    }
}
