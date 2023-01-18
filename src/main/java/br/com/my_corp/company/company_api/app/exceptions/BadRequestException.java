package br.com.my_corp.company.company_api.app.exceptions;

public class BadRequestException extends Exception implements HTTPException<Integer>{

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public Integer getErrorCode() {
        return 400;
    }
    
}
