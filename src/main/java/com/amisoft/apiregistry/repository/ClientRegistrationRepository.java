package com.amisoft.apiregistry.repository;

import com.amisoft.apiregistry.entity.ClientRegistation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRegistrationRepository extends JpaRepository<ClientRegistation,Long> {

    public List<ClientRegistation> findByClientApplicationName(String name);

}
