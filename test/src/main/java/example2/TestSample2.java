package example2;

public class TestSample2 {

    @ExceptionTest(ArithmeticException.class)
    public static void test1(){
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void test2(){
        int[] intArray = new int[0];
        int i = intArray[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void test3(){

    }

    @ExceptionTest(ArrayIndexOutOfBoundsException.class)
    public static void test4(){
        int[] intArray = new int[0];
        int i = intArray[1];
    }
}
