package org.haimoud.izicap_homework.repository;

import org.haimoud.izicap_homework.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author AYOUB BENHAIMOUD
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
