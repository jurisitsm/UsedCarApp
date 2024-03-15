package com.jurisitsm.test.repository;

import com.jurisitsm.test.model.CarAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<CarAdvertisement, String>, JpaSpecificationExecutor<CarAdvertisement> {
}
