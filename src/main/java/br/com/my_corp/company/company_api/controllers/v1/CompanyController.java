package br.com.my_corp.company.company_api.controllers.v1;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.my_corp.company.company_api.adapters.factories.AdapterFactory;
import br.com.my_corp.company.company_api.app.domain.dtos.CompanyDTO;
import br.com.my_corp.company.company_api.app.domain.factories.DomainFactory;
import br.com.my_corp.company.company_api.app.domain.services.Service;
import br.com.my_corp.company.company_api.app.exceptions.BadRequestException;
import br.com.my_corp.company.company_api.app.exceptions.InternalServerErrorException;
import br.com.my_corp.company.company_api.app.exceptions.NotFoundException;
import br.com.my_corp.company.company_api.app.exceptions.UnprocessableEntityException;
import br.com.my_corp.company.company_api.controllers.ControllerConfiguration;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController extends ControllerConfiguration{

    @Autowired
    DataSource dataSource;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> create(@RequestBody CompanyDTO dto) throws UnprocessableEntityException, InternalServerErrorException{
        var createdDTO = this.buildService().create(dto);
        return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> findById(@PathVariable String id) throws BadRequestException, NotFoundException, UnprocessableEntityException, InternalServerErrorException{
        var dto = this.buildService().findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdatePayload> update(@PathVariable String id, @RequestBody CompanyDTO dto) throws BadRequestException, NotFoundException, UnprocessableEntityException, InternalServerErrorException{
        dto.setId(id);
        var updatedRowCount = this.buildService().update(dto);

        return new ResponseEntity<>(
            UpdatePayload.builder()
                .companyId(dto.getId())
                .updatedRowCount(updatedRowCount)
            .build(),
            HttpStatus.OK
        );
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdatePayload> delete(@PathVariable String id) throws BadRequestException, UnprocessableEntityException, InternalServerErrorException{
        var updatedRowCount = this.buildService().delete(id);

        return new ResponseEntity<>(
            UpdatePayload.builder()
                .companyId(id)
                .updatedRowCount(updatedRowCount)
            .build(),
            HttpStatus.OK
        );
    }

    private Service<CompanyDTO> buildService() {
        var repo = AdapterFactory.buildRepository(this.dataSource);
        return DomainFactory.buildCompanyService(repo);
    }
    
}
