package com.amisoft.apiregistry.repository;

import com.amisoft.apiregistry.entity.ApplicationApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationApiRepository extends JpaRepository<ApplicationApi,Long> {

    public ApplicationApi findByApplicationName(String name);
}
