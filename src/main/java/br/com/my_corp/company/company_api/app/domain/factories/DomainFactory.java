package br.com.my_corp.company.company_api.app.domain.factories;

import br.com.my_corp.company.company_api.adapters.repositories.Repository;
import br.com.my_corp.company.company_api.app.domain.dtos.CompanyDTO;
import br.com.my_corp.company.company_api.app.domain.models.Company;
import br.com.my_corp.company.company_api.app.domain.services.CompanyService;
import br.com.my_corp.company.company_api.app.domain.services.Service;

public class DomainFactory {

    private DomainFactory(){}

    public static final Company buildCompany(CompanyDTO dto) {
        return Company.builder()
            .id(dto.getId())
            .name(dto.getName())
            .alias(dto.getAlias())
            .shortName(dto.getShortName())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())
        .build();
    }
    
    public static final CompanyDTO buildCompanyDTO(Company model) {
        return CompanyDTO.builder()
            .id(model.getId())
            .name(model.getName())
            .alias(model.getAlias())
            .shortName(model.getShortName())
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
        .build();
    }

    public static final Service<CompanyDTO> buildCompanyService(Repository<CompanyDTO> repo) {
        return new CompanyService(repo);
    }
}
