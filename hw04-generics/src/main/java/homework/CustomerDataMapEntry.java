package homework;

import java.util.Map;

public class CustomerDataMapEntry implements Map.Entry<Customer,String> {

    private Customer key;
    private String value;

    CustomerDataMapEntry(Customer key,String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public Customer getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String setValue(String value) {
        String oldVal = this.value;
        this.value = value;
        return oldVal;
    }
}
