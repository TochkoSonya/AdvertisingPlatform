package com.tochko.advertising_platform.repository;

import java.util.List;
import java.util.Optional;

public interface CommonRepository<T> {
    Optional<T> get(long id);
    List<T> getAll();
    void add(T t);
    void update(long id, T t);
    void delete(T t);
}
