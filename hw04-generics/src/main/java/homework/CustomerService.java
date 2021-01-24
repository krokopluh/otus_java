package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    //create map as a field
    private Comparator<Customer> comparator = Customer::compareTo;
    private TreeMap<Customer,String> customerDataMap = new TreeMap<>(comparator);

    public Map.Entry<Customer, String> getSmallest(){
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map.Entry<Customer,String> smallestEntry = customerDataMap.firstEntry();
        return new CustomerDataMapEntry(new Customer(smallestEntry.getKey()),smallestEntry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return customerDataMap.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        customerDataMap.put(customer,data);
    }
}
