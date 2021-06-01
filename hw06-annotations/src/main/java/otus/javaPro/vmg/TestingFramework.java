package otus.javaPro.vmg;


import otus.javaPro.vmg.customAnnotations.After;
import otus.javaPro.vmg.customAnnotations.Before;
import otus.javaPro.vmg.customAnnotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestingFramework {

    public static void main(String[] args) throws Exception {

        String testClassName = "otus.javaPro.vmg.AnnotationsTest";
        TestRunner.runTestsForClass(testClassName);
    }

}
