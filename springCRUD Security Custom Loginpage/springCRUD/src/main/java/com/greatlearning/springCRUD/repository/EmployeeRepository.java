package com.greatlearning.springCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.springCRUD.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
