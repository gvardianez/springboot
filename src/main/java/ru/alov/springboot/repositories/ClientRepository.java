package ru.alov.springboot.repositories;

import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alov.springboot.entities.Client;

import javax.persistence.NamedNativeQueries;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
