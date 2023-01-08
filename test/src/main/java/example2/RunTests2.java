package example2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests2 {

    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;

        Class<?> testClass = Class.forName("example2.TestSample2");

        for (Method method: testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ExceptionTest.class) || method.isAnnotationPresent(ExceptionTestContainer.class)) {
                tests ++;
                try {
                    method.invoke(null); //For static methods
                    System.out.printf("Passed: %s, Failed: no exception%n", method);
                } catch (InvocationTargetException exception) {
                    Throwable cause = exception.getCause();
                    int oldPassed = passed;
                    ExceptionTest[] exceptions = method.getAnnotationsByType(ExceptionTest.class);
                    for (ExceptionTest exceptionTest: exceptions) {
                        if (exceptionTest.value().isInstance(cause)){
                            passed ++;
                            break;
                        }
                    }
                    if (passed == oldPassed){
                        System.out.printf("Test %s failed: %s%n", method, cause);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid @Test: " + method);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests-passed);
    }


}
