package com.example.demo.config;

import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import java.io.Serializable;

public class CleanSaveRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {
    public CleanSaveRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
    @Override
    public <S extends T> S save(S entity) {
        if (entity.getClass().isAnonymousClass()) {
            try {
                Class<?> superclass = entity.getClass().getSuperclass();
                S clean = (S) superclass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(entity, clean);
                return super.save(clean);
            } catch (Exception e) { e.printStackTrace(); }
        }
        return super.save(entity);
    }
}
