package dev.Mahathir.JwtSecurity.service.test;

import java.util.Optional;

public interface CrudService<T, Id> {
    Iterable<T> getAll();
    Optional<T> getById(Id id);

    T create(T entity);

    T update(T entity, Id id);

    void delete(Id id);
}
