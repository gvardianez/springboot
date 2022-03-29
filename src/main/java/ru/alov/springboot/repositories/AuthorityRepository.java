package ru.alov.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alov.springboot.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
