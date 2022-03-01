package com.example.operasales.repository.interfaces;

import java.util.Collection;

public interface CommonRepository<T> {
    T save(T domain);

    void save(Collection<T> domains);

    void delete(T domain);

    T findById(String id);

    Collection<T> findAll();
}
