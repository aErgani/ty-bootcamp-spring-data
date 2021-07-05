package com.tybootcamp.ecomm.repositories;
import com.tybootcamp.ecomm.entities.BasketEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketEntryRepository extends JpaRepository<BasketEntry, Long> {

}
