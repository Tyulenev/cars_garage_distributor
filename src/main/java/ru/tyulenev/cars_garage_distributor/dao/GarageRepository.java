package ru.tyulenev.cars_garage_distributor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tyulenev.cars_garage_distributor.model.entity.Garage;

@Repository
public interface GarageRepository extends JpaRepository<Garage,Long> {

}
