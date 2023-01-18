package br.com.my_corp.company.company_api.adapters.repositories;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import br.com.my_corp.company.company_api.app.domain.dtos.CompanyDTO;
import br.com.my_corp.company.company_api.app.exceptions.InternalServerErrorException;

public class GenericSQLRepository implements Repository<CompanyDTO>{

    private DataSource dataSource;

    public GenericSQLRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(CompanyDTO dto) throws InternalServerErrorException {
        var sql = """
            insert into company.companies 
                   ( id,  name,  short_name, alias,  created_at)
            values (:id, :name, :shortName, :alias, :createdAt)
            """;
        
        this.update(sql, dto);
    }

    @Override
    public int update(CompanyDTO dto) throws InternalServerErrorException {
        var sql = """
            update company.companies
               set name       = :name,
                   short_name = :shortName,
                   alias      = :alias,
                   updated_at = :updatedAt 
             where id         = :id       
        """;

        return this.update(sql, dto);
    }

    @Override
    public CompanyDTO findById(String id) throws InternalServerErrorException {
        var sql = "select * from company.companies where id = :id";

        try {
            return new NamedParameterJdbcTemplate(this.dataSource)
                .queryForObject(
                    sql,
                    new MapSqlParameterSource().addValue("id", id),
                    BeanPropertyRowMapper.newInstance(CompanyDTO.class) 
            );
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        } catch (DataAccessException dae) {
            throw new InternalServerErrorException(dae);
        }
    }

    @Override
    public int delete(String id) throws InternalServerErrorException {
        var sql = "delete from company.companies where id = :id";
        return this.update(
            sql, 
            new MapSqlParameterSource().addValue("id", id)
        );
    }

    private int update(String sql, CompanyDTO dto) throws InternalServerErrorException {
        try {
            return new NamedParameterJdbcTemplate(this.dataSource).update(
                sql, 
                new BeanPropertySqlParameterSource(dto)
            );
        } catch (DataAccessException dae) {
            throw new InternalServerErrorException(dae);
        }        
    }

    private int update(String sql, MapSqlParameterSource parameters) throws InternalServerErrorException {
        try {
            return new NamedParameterJdbcTemplate(this.dataSource).update(
                sql, 
                parameters
            );
        } catch (DataAccessException dae) {
            throw new InternalServerErrorException(dae);
        }        
    }    
}
