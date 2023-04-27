package com.edu.greenwich.managementsystem.Repository.impl;

import com.edu.greenwich.managementsystem.Repository.CustomUserRepository;
import com.edu.greenwich.managementsystem.dto.response.ReactionWithIdeaIdResponse;
import com.edu.greenwich.managementsystem.dto.response.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<UserDto> getAll() {
        Query query = entityManager.createNativeQuery("select users.*, d.name as deptName, r.name as roleName from users  join department d on users.department_id  join user_roles ur on users.user_id = ur.user_id  join roles r on ur.role_id = r.id = d.department_id");
        List<Object[]> rows = query.getResultList();
        List<UserDto> result = new ArrayList<>(rows.size());
        for(Object[] row: rows){
            result.add(UserDto.builder().userId(BigInteger.valueOf(Long.parseLong(row[0].toString())))
                    .email(row[1].toString())
                    .password(row[2] == null?String.valueOf("null"):row[2].toString())
                    .username(row[3] == null?String.valueOf("null"):row[3].toString())
                    .deptName((row[5] == null?String.valueOf("null"):row[5].toString()))
                    .role((row[6] == null?String.valueOf("null"):row[6].toString()))
                    .build());
        }
        return result;
    }
}
