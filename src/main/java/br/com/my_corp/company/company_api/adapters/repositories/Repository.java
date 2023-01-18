package br.com.my_corp.company.company_api.adapters.repositories;

import br.com.my_corp.company.company_api.app.exceptions.InternalServerErrorException;

public interface Repository<T> {
    void create(T dto) throws InternalServerErrorException;
    int update(T dto) throws InternalServerErrorException;
    T findById(String id) throws InternalServerErrorException;
    int delete(String id) throws InternalServerErrorException;    
}
