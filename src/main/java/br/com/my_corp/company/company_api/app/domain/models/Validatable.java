package br.com.my_corp.company.company_api.app.domain.models;

import br.com.my_corp.company.company_api.app.exceptions.UnprocessableEntityException;

public interface Validatable {
    void validate() throws UnprocessableEntityException;
}
