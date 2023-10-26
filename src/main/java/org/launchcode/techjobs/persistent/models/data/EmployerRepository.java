package org.launchcode.techjobs.persistent.models.data;

import jakarta.transaction.Transactional;
import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
}
