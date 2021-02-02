package com.pgbezerra.sample.service;

import java.util.List;

public interface Service<T, PK> {
    List<T> findAll();
    T findById(PK id);
    boolean insert(T t);
    boolean deleteById(PK id);
    boolean update(T t);
}
