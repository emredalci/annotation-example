package example1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;

        Class<?> testClass = Class.forName("example1.TestSample");

        for (Method method: testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                tests ++;
                try {
                    method.invoke(null); //For static methods
                    passed ++;
                } catch (InvocationTargetException exception) {
                    Throwable cause = exception.getCause();
                    System.out.println(method + " failed " + cause);
                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + method);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests-passed);
    }
}
