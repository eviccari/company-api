package br.com.my_corp.company.company_api.app.domain.factories;

import br.com.my_corp.company.company_api.app.domain.dtos.CompanyDTO;
import br.com.my_corp.company.company_api.app.domain.models.Company;

public class CompanyFactory {

    private CompanyFactory(){}

    public static Company buildFrom(CompanyDTO dto) {
        return Company.builder()
            .id(dto.getId())
            .name(dto.getName())
            .alias(dto.getAlias())
            .shortName(dto.getShortName())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())
        .build();
    }
    
    public static CompanyDTO buildFrom(Company model) {
        return CompanyDTO.builder()
            .id(model.getId())
            .name(model.getName())
            .alias(model.getAlias())
            .shortName(model.getShortName())
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
        .build();
    }
}
