package ru.tyulenev.cars_garage_distributor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tyulenev.cars_garage_distributor.model.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    public List<Car> findCarByGarageId(Long id);
}
