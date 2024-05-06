package com.example.manageaid.repository;

import com.example.manageaid.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query(value =
            "SELECT m.* " +
                    "FROM modules m JOIN module_roles mr on m.id = mr.module_id " +
                    "     JOIN roles r on r.id = mr.role_id " +
                    "WHERE r.name = ?1", nativeQuery = true)
    List<Module> getModulesByRole(String role);

    @Override
    Optional<Module> findById(Long id);
}
