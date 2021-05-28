package br.com.hudson.devsuperior.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hudson.devsuperior.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
