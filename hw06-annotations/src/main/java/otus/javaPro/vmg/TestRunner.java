package otus.javaPro.vmg;

import otus.javaPro.vmg.customAnnotations.After;
import otus.javaPro.vmg.customAnnotations.Before;
import otus.javaPro.vmg.customAnnotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {


    public static void runTestsForClass(String testClassName) throws Exception{

        var clazz = Class.forName(testClassName);

        List<Method> beforeMethods = findAnnotatedMethods(clazz, Before.class);
        List<Method> testMethods = findAnnotatedMethods(clazz, Test.class);
        List<Method> afterMethods = findAnnotatedMethods(clazz, After.class);

        ArrayList<TestExecutionError> errorsList = new ArrayList<>();

        for (Method testCase:testMethods){

            var annotationsTest = ReflectionHelper.instantiate(clazz);

            executeMethods(annotationsTest, beforeMethods);
            try{
                ReflectionHelper.callMethod(annotationsTest,testCase.getName());
            }catch (RuntimeException ex){
                errorsList.add(new TestExecutionError(testCase.getName(),ex.getMessage()));
            }
            executeMethods(annotationsTest, afterMethods);
        }

        outputResults(testClassName, errorsList, testMethods);
    }

    public static List<Method> findAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        Method[] methods = clazz.getMethods();
        List<Method> annotatedMethods = new ArrayList<>(methods.length);
        for (Method method : methods) {
            if( method.isAnnotationPresent(annotationClass)){
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

    public static void executeMethods(Object testClassObject, List<Method> methodsList){

        for(Method method : methodsList){
            ReflectionHelper.callMethod(testClassObject, method.getName());
        }

    }

    public static void outputResults(String testClassName, ArrayList<TestExecutionError> errorsList, List<Method> testCases ){

        int testCasesAmount = testCases.size();
        int failedTestCasesAmount = errorsList.size();
        int successfulTestsAmount = testCasesAmount - failedTestCasesAmount;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("=============\n Tests execution results for ")
                .append(testClassName)
                .append(" are: \n")
                .append("Statistics: \n\n")
                .append("overall tests amount: ").append(testCasesAmount).append("\n")
                .append("successful tests amount: ").append(successfulTestsAmount).append("\n")
                .append("failed tests amount: ").append(failedTestCasesAmount).append("\n\n");

        if(failedTestCasesAmount>0){
            stringBuilder.append("Failed tests details:\n");
            for(TestExecutionError testError : errorsList){
                stringBuilder.append(testError.getErrorInfo()).append("\n");
            }
        }
        System.out.println(stringBuilder.toString());
    }

}
