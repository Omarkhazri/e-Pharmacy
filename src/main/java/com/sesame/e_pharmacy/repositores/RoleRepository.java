package com.sesame.e_pharmacy.repositores;

import com.sesame.e_pharmacy.entity.Role;
import com.sesame.e_pharmacy.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
