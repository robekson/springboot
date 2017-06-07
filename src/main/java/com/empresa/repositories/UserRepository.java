package com.empresa.repositories;

/**
 * Created by cyborg on 6/3/17.
 */

import com.empresa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
