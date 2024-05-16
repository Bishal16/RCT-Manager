package dev.Mahathir.JwtSecurity.service.test;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@AllArgsConstructor
public abstract class CrudServiceImpl<T, Id> implements CrudService<T, Id>{

    private final JpaRepository<T, Id> crudRepository;

    @Override
    public Iterable<T> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public Optional<T> getById(Id id) {
        return crudRepository.findById(id);
    }

    @Override
    public T create(T entity) {
        return crudRepository.save(entity);
    }

    @Override
    public T update(T entity, Id id) {
        return null;
    }

    @Override
    public void delete(Id id) {
        crudRepository.deleteById(id);
    }
}
