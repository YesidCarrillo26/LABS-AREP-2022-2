package edu.escuelaing.arep;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Spring {

    private static Map<String, Method> services = new HashMap<String, Method>();

    public static void main(String[] args) throws Exception {
        for (Method m : Class.forName(args[0]).getMethods()) {
            if (m.isAnnotationPresent(RequestMapping.class)) {
                try {
                    String uri = m.getAnnotation(RequestMapping.class).value();
                    System.out.println(uri + ":" + m.invoke(null));
                    services.put(uri, m);
                } catch (Throwable ex) {
                    System.out.println("Error: "+ ex);
                }
            }
        }
    }
}
