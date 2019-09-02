package com.brave.service;

import com.brave.model.Applicant;
import com.brave.model.Product;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired KieContainer kieContainer;

   public Product getProductDiscount(Product product) {
        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.insert(product);
        kieSession.fireAllRules();
        kieSession.dispose();
        return product;
    }

    public Applicant getApplicant(Applicant applicant) {
        StatelessKieSession kieSession = kieContainer.newStatelessKieSession("rulesSession");
        kieSession.execute(applicant);
        return applicant;

    }
}
