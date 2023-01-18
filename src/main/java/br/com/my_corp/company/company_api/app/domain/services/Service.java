package br.com.my_corp.company.company_api.app.domain.services;

import br.com.my_corp.company.company_api.app.exceptions.BadRequestException;
import br.com.my_corp.company.company_api.app.exceptions.InternalServerErrorException;
import br.com.my_corp.company.company_api.app.exceptions.NotFoundException;
import br.com.my_corp.company.company_api.app.exceptions.UnprocessableEntityException;

public interface Service<T> {
    T create(T dto) throws UnprocessableEntityException, InternalServerErrorException;
    int update(T dto) throws UnprocessableEntityException, InternalServerErrorException;
    T findById(String id) throws NotFoundException, BadRequestException, InternalServerErrorException;
    int delete(String id) throws BadRequestException, InternalServerErrorException;  
}
