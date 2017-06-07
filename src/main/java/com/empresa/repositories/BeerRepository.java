package com.empresa.repositories;

import com.empresa.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by cyborg on 5/28/17.
 */
@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
}
