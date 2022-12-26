package edu.escuelaing.arep;

public class HelloController {
    @RequestMapping("/index.html")
    public static String index() {
        return "Greetings from Spring Boot!";
    }
}
