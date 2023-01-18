package br.com.my_corp.company.company_api.app.domain.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import br.com.my_corp.company.company_api.utils.StringUtils;

public class PolicyManager {

    public static final String NAME_OK_POLICY_NAME = "name_is_empty";
    public static final String ALIAS_IS_OK_POLICY_NAME = "alias_is_empty";
    public static final String SHORT_NAME_IS_OK_POLICY_NAME = "short_name_is_empty";
    public static final String ID_IS_OK_POLICY_NAME = "id_is_empty";

    public Map<String, Predicate<Company>> getPolicies() {
        Map<String, Predicate<Company>> policiesMap = new HashMap<>();
        policiesMap.put(NAME_OK_POLICY_NAME, company -> StringUtils.isEmpty(company.getName()));
        policiesMap.put(ALIAS_IS_OK_POLICY_NAME, company -> StringUtils.isEmpty(company.getAlias()));
        policiesMap.put(SHORT_NAME_IS_OK_POLICY_NAME, company -> StringUtils.isEmpty(company.getShortName()));
        policiesMap.put(ID_IS_OK_POLICY_NAME, company -> StringUtils.isEmpty(company.getShortName()));

        return policiesMap;
    }

    public String buildPolicyErrorMessage(String policyName) {
        return String.format("policy: %s has failed", policyName);
    }

    public List<String> applyPolicies(Company company) {
        var result = new ArrayList<String>();

        this.getPolicies().forEach((policyName, policy) -> {
            if(policy.test(company)){
                result.add(this.buildPolicyErrorMessage(policyName));
            }
        });

        return result;
    }    
}
