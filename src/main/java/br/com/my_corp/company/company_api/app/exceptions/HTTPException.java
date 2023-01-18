package br.com.my_corp.company.company_api.app.exceptions;

public interface HTTPException<T> {
    T getErrorCode();
    String getMessage();
}
