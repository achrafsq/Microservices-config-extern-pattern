package com.sqli.paymentservice.repositories;

import com.sqli.paymentservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository  extends JpaRepository<Client, Long> {
}
