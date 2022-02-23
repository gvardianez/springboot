package ru.alov.springboot.repositories;

import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alov.springboot.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
