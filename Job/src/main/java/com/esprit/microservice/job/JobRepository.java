package com.esprit.microservice.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query("select j from Job j where j.service like :service")
    public Page<Job> jobByService(@Param("service") String service, Pageable pageable);

    List<Job> findByServiceContaining(String service);
}
