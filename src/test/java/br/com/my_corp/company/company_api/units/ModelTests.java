package br.com.my_corp.company.company_api.units;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;

import br.com.my_corp.company.company_api.StarterTests;
import br.com.my_corp.company.company_api.app.domain.dtos.CompanyDTO;
import br.com.my_corp.company.company_api.app.domain.factories.DomainFactory;
import br.com.my_corp.company.company_api.app.exceptions.UnprocessableEntityException;

class ModelTests extends StarterTests{

    @Test
    void ShouldCreateNewCompany() {

        var dto = Instancio.of(CompanyDTO.class).create();
        var model = DomainFactory.buildCompany(dto);
        model.generateId();
        System.out.println(model.toString());

        assertDoesNotThrow(() -> model.validate());        
    }

    @Test
    void ShouldThrowUnprocessableEntityException() {
        var dto = Instancio.of(CompanyDTO.class)
            .set(Select.field(CompanyDTO.class, "name"), "")
            .set(Select.field(CompanyDTO.class, "shortName"), "")
            .set(Select.field(CompanyDTO.class, "alias"), "")
            .set(Select.field(CompanyDTO.class, "id"), "")
        .create();

        var model = DomainFactory.buildCompany(dto);

        assertThrows(UnprocessableEntityException.class, () -> model.validate());
    }
    
}
