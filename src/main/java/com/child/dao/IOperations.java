package com.child.dao;

import java.util.List;
import java.util.Optional;

public interface IOperations<T> {
    Optional<T> findById(final long id);

    List<T> findAll();

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);
}
