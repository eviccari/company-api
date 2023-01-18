package br.com.my_corp.company.company_api.app.domain.services;

import java.util.Date;

import br.com.my_corp.company.company_api.adapters.repositories.Repository;
import br.com.my_corp.company.company_api.app.domain.dtos.CompanyDTO;
import br.com.my_corp.company.company_api.app.domain.factories.DomainFactory;
import br.com.my_corp.company.company_api.app.exceptions.BadRequestException;
import br.com.my_corp.company.company_api.app.exceptions.InternalServerErrorException;
import br.com.my_corp.company.company_api.app.exceptions.NotFoundException;
import br.com.my_corp.company.company_api.app.exceptions.UnprocessableEntityException;
import br.com.my_corp.company.company_api.utils.StringUtils;
import br.com.my_corp.company.company_api.utils.TimeUtils;

public class CompanyService implements Service<CompanyDTO>{

    private Repository<CompanyDTO> repo;

    public CompanyService(Repository<CompanyDTO> repo) {
        this.repo = repo;
    }

    @Override
    public CompanyDTO create(CompanyDTO dto) throws UnprocessableEntityException, InternalServerErrorException {
        var model = DomainFactory.buildCompany(dto);
        model.generateId();
        model.validate();
        model.setCreatedAt(Date.from(TimeUtils.now()));

        var updatedDTO = DomainFactory.buildCompanyDTO(model);
        this.repo.create(updatedDTO);
        return updatedDTO;
    }

    @Override
    public int update(CompanyDTO dto) throws UnprocessableEntityException, InternalServerErrorException {
        var model = DomainFactory.buildCompany(dto);
        model.validate();
        model.setUpdatedAt(Date.from(TimeUtils.now()));
        
        var updatedDTO = DomainFactory.buildCompanyDTO(model);
        return this.repo.update(updatedDTO);
    }

    @Override
    public CompanyDTO findById(String id) throws NotFoundException, BadRequestException, InternalServerErrorException {
        checkParam(id);
        var result = this.repo.findById(id);

        if(result != null) {
            return result;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public int delete(String id) throws BadRequestException, InternalServerErrorException {
        checkParam(id);
        return this.repo.delete(id);
    }

    private void checkParam(String id) throws BadRequestException{
        if(StringUtils.isEmpty(id)) 
            throw new BadRequestException("id is required");
    } 

}
