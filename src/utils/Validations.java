package utils;

public class Validations {
    public static boolean isLettersOnly(String inputText) {
        System.out.println(inputText.matches("^[a-zA-Z]+$"));
        
        return inputText.matches("^[a-zA-Z]+$");
    }
}
