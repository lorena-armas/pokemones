package org.example.commons.repository;

import java.util.List;

public interface CrudRepository<T extends Entity> {

    List<T> findAll();

    default T findByCode(Integer code) {
        if (code == null)
            throw new IllegalArgumentException("Id must not be null");

        for (T entity: this.findAll()) {
            if(code.equals(entity.getCode()))
                return entity;
        }
        throw new IllegalArgumentException("No such element with id " + code);
    }
}