package br.com.my_corp.company.company_api.app.exceptions;

public class UnprocessableEntityException extends Exception implements HTTPException<Integer>{

    public UnprocessableEntityException(String message) {
        super(message);
    }

    @Override
    public Integer getErrorCode() {
        return 422;
    }

}
