package br.com.my_corp.company.company_api.app.domain.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;

import br.com.my_corp.company.company_api.app.exceptions.UnprocessableEntityException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
public class Company implements Identifiable, Validatable, Serializable {

    public static final String IS_REQUIRED_MESSAGE = " id required";

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String shortName;

    @Getter
    @Setter
    private String alias;

    @Getter
    @Setter
    private Date createdAt;

    @Getter
    @Setter
    private Date updatedAt;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public void validate() throws UnprocessableEntityException {
        var sb = new StringBuilder("");

        var policyManager = new PolicyManager();
        var validations = policyManager.applyPolicies(this);

        if(!validations.isEmpty()) {
            validations.forEach(sb::append);
            throw new UnprocessableEntityException(sb.toString());
        }
    }
}
