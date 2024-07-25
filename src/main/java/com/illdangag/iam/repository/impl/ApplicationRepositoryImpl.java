package com.illdangag.iam.repository.impl;

import com.illdangag.iam.data.entity.Application;
import com.illdangag.iam.repository.ApplicationRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public class ApplicationRepositoryImpl implements ApplicationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Application application) {
        if (application.getId() == null) {
            this.entityManager.persist(application);
        } else {
            this.entityManager.merge(application);
        }
    }

    @Override
    public Optional<Application> getApplication(Long id) {
        String jpql = "SELECt a FROM Application a WHERE a.id = :id";
        TypedQuery<Application> query = this.entityManager.createQuery(jpql, Application.class)
                .setParameter("id", id);
        try {
            Application application = query.getSingleResult();
            return Optional.of(application);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Application> getApplicationByName(String name) {
        String jpql = "SELECT a FROM Application a WHERE a.name = :name";
        TypedQuery<Application> query = this.entityManager.createQuery(jpql, Application.class)
                .setParameter("name", name);

        try {
            Application application = query.getSingleResult();
            return Optional.of(application);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
