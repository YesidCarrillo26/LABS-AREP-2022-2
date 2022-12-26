package edu.escuelaing.arep;

public class TestExample {
    @Test
    public static void m1(){}

    public static void m2() throws Exception{
        throw(new Exception("Error in m2"));
    }
    @Test
    public static void m3(){}
    @Test
    public static void m4() throws Exception{
        throw(new Exception("Error in m4"));
    }

    public static void m5(){}
    @Test
    public static void m6() throws Exception{
        throw(new Exception("Error in m6"));
    }
}
