package otus.javaPro.vmg;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


public class ReflectionHelper {
    private ReflectionHelper() {
    }


    public static void callMethod(Object object, String name) {
        try {
            var method = object.getClass().getMethod(name);
            method.setAccessible(true);
            method.invoke(object);
        }catch (InvocationTargetException ex){
            throw new RuntimeException(ex.getCause());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }
}
