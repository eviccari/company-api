package br.com.my_corp.company.company_api.app.exceptions;

public class NotFoundException extends Exception implements HTTPException<Integer>{

    public static final String NOT_FOUND_MESSAGE = "not found";

    public NotFoundException() {
        super(NOT_FOUND_MESSAGE);
    }

    public NotFoundException(String message) {
        super(message != null ? message : NOT_FOUND_MESSAGE);
    }

    @Override
    public Integer getErrorCode() {
        return 404;
    }
    
}
