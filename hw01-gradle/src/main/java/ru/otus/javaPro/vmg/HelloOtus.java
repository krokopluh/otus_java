package ru.otus.javaPro.vmg;

import com.google.common.base.Splitter;

public class HelloOtus {

    public static void main(String[] args){

        String stringWithCommas = "one,two, , three,four,etc..";

        Iterable<String> result = Splitter.on(',')
                .split(stringWithCommas);

        System.out.println("--start--");
        for(String s: result){
            System.out.println(s);
        }
        System.out.println("--end--");
    }

}
