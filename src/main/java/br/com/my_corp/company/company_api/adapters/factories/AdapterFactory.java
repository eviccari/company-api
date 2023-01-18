package br.com.my_corp.company.company_api.adapters.factories;

import javax.sql.DataSource;

import br.com.my_corp.company.company_api.adapters.repositories.GenericSQLRepository;
import br.com.my_corp.company.company_api.adapters.repositories.Repository;
import br.com.my_corp.company.company_api.app.domain.dtos.CompanyDTO;

public class AdapterFactory {

    private AdapterFactory(){}

    public static final Repository<CompanyDTO> buildRepository(DataSource dataSource) {
        return new GenericSQLRepository(dataSource);
    }
    
}
