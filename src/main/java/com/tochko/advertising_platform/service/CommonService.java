package com.tochko.advertising_platform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface CommonService<T> {
    Optional<T> get(long id);
    Page<T> getAll(Pageable pageable);
    void add(T t);
    T update(long id, T t);
    void delete(long id);
}
