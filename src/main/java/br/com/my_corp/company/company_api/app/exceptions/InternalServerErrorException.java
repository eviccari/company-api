package br.com.my_corp.company.company_api.app.exceptions;

public class InternalServerErrorException extends Exception implements HTTPException<Integer>{

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Integer getErrorCode() {
        return 500;
    }
    
}
