package com.edu.greenwich.managementsystem.Repository.impl;

import com.edu.greenwich.managementsystem.Repository.RoleRepository;
import com.edu.greenwich.managementsystem.dto.request.RoleDto;
import com.edu.greenwich.managementsystem.model.ERole;
import com.edu.greenwich.managementsystem.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<Role> findByName(ERole name) {
        return Optional.empty();
    }

    @Override
    public List<RoleDto> findAll() {
        Query query = entityManager.createNativeQuery("select * from roles");
        List<Object[]> rows = query.getResultList();
        List<RoleDto> result = new ArrayList<>(rows.size());
        for(Object[] row: rows){
            result.add(RoleDto.builder()
                    .id((int)row[0])
                    .name(row[1].toString())
                    .build());
        }
        return result;
    }

    @Override
    public Optional<RoleDto> findById(int id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public RoleDto save(RoleDto role) {
        Query query = entityManager.createNativeQuery("update roles set name=? where id=?");
        query.setParameter(1, role.getName());
        query.setParameter(2, role.getId());
        int isUpdate = query.executeUpdate();
        if(isUpdate == 0){
            Query insert = entityManager.createNativeQuery("insert into roles (name) values (?)");

            insert.setParameter(1, role.getName());

            insert.executeUpdate();
        }
        return role;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Query delete = entityManager.createNativeQuery("delete from roles where id = ?");

        delete.setParameter(1, id);

        delete.executeUpdate();
    }
}
