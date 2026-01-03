package com.api.backend.validator;
import org.springframework.stereotype.Component;

@Component
public class CPFValidator {

    public boolean isValid(String cpf) {

        if (cpf == null)
            return false;

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11)
            return false;
        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        int d1 = calculateDigit(cpf.substring(0, 9), 10);
        int d2 = calculateDigit(cpf.substring(0, 10), 11);

        return cpf.equals(cpf.substring(0, 9) + d1 + d2);
    }

    private int calculateDigit(String base, int weight) {
        int sum = 0;
        for (char c : base.toCharArray()) {
            sum += Character.getNumericValue(c) * weight--;
        }
        int result = 11 - (sum % 11);
        return result > 9 ? 0 : result;
    }

    // public boolean isValidCPF(String cpf) {

    //     String regex = "[.-]";
    //     List<String> digits = Arrays.asList(cpf.split(regex));
    //     List<Character> digitsArrayChar = new ArrayList<>();
    //     List<Integer> digitsArrayInt = new ArrayList<>();

    //     for(int i = 0; i < 3; i++){
    //         for(int j = 0; j < 3; j++) {

    //             digitsArrayChar.add(digits.get(i).toCharArray()[j]);

    //         }
    //     }

    //     for(int i = 0; i < 9; i++) {

    //         digitsArrayInt.add(Character.getNumericValue(digitsArrayChar.get(i)));

    //     }
        
        
    //     List<Character> getFinalDigits = new ArrayList<>();

    //     getFinalDigits.add(digits.get(3).toCharArray()[0]);
    //     int getLastDigit = Character.getNumericValue(getFinalDigits.get(0));
        

    //     String validateCPF = getFirstCPFDigit(digitsArrayInt) + "" + getSecondCPFDigit(digitsArrayInt, getLastDigit);

    //     if(validateCPF.equals(digits.get(3))) {
    //         return true;
    //     }

    //     return false;
        
    // }

    // public static int getFirstCPFDigit(List<Integer> digits) {

    //     int multiplier = 10;
    //     int totalSum = 0;
    //     for (int i = 0; i < 9; i++) {

    //         totalSum += digits.get(i) * multiplier;

    //         multiplier--;

    //     }

    //     int totalRest = totalSum % 11;
    //     int result = 11 - totalRest;

    //     return result > 9 ? 0 : result;
    // }

    // public static int getSecondCPFDigit(List<Integer> digits, int lastDigit) {
    //     int multiplier = 11;
    //     int totalSum = 0;
    //     digits.add(lastDigit);

    //     for (int i = 0; i < 10; i++) {

    //         totalSum += digits.get(i) * multiplier;

    //         multiplier--;

    //     }
    //     int totalRest = totalSum % 11;
    //     int result = 11 - totalRest;

    //     return result > 9 ? 0 : result;
    // }
    
}
