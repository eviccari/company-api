package br.com.my_corp.company.company_api.utils;

public class StringUtils {
    
    private StringUtils(){}

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty() || value.isBlank();
    }
}
