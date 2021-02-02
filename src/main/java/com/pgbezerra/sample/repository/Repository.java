package com.pgbezerra.sample.repository;

import java.util.List;

public interface Repository<T, PK> {
    List<T> findAll();
    T findById(PK id);
    boolean insert(T t);
    boolean deleteById(PK id);
    boolean update(T t);
}
