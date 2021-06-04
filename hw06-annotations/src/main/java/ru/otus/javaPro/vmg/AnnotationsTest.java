package ru.otus.javaPro.vmg;

import ru.otus.javaPro.vmg.customAnnotations.After;
import ru.otus.javaPro.vmg.customAnnotations.Before;
import ru.otus.javaPro.vmg.customAnnotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnnotationsTest {

    private ArrayList<Integer> integersArray = new ArrayList<>();
    private ArrayList<String> stringArray = new ArrayList<>();
    private HashMap<Integer,String> testMap = new HashMap<>();

    @Before
    public void fillStringsArray(){

        System.out.println("Filling up the strings array");

        stringArray.add("zero");
        stringArray.add("one");
        stringArray.add("two");
        stringArray.add("three");
        stringArray.add("four");

    }

    @Before
    public void filIntegersArray(){

        System.out.println("Filling up the integers array");

        for (int i=0;i<4;i++){
            integersArray.add(i);
        }

    }

    @Test
    public void testStringArrayAppend(){

        System.out.println("Appending element to the strings array");

        stringArray.add("five");
        assertThat(stringArray.get(5)).isEqualTo("five");

    }

    @Test
    public void testIntegerArrayInsertion(){

        System.out.println("Appending element to the integers array");

        integersArray.remove(3);
        assertThat(integersArray.size()).isEqualTo(4);
    }

    @Test
    public void testMapCreation(){

        System.out.println("Creating a map out of arrays");

        for (int i=0;i<4;i++){
            testMap.put(integersArray.get(i),stringArray.get(i));
        }

        assertThat(testMap.get(3)).isEqualTo("Three");
    }

    @After
    public void clearUpMap(){

        System.out.println("Cleaning testMap");
        testMap.clear();

    }

    @After
    public void clearUpStringsArray(){

        System.out.println("Cleaning up strings array");
        stringArray.clear();
    }

    @After
    public void clearUpIntegerArray(){

        System.out.println("Cleaning up integers array");
        integersArray.clear();
    }


}
