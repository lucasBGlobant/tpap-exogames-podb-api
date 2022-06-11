package com.exogames.common_services.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonServiceImpl<E, R extends CrudRepository<E, Integer>> implements CommonService<E> {

    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() { return repository.findAll(); }
}
