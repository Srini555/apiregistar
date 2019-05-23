package com.amisoft.apiregistry.repository;

import com.amisoft.apiregistry.entity.ClientRegistation;
import com.amisoft.apiregistry.model.ClientRegistrationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRegistrationRepository extends JpaRepository<ClientRegistation,Long> {

    public ClientRegistrationResponse findByClientApplicationName(String name);

}
