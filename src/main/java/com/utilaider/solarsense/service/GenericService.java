/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilaider.solarsense.service;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Dhiren
 * @param <PK>
 * @param <T>
 */
public interface GenericService<PK, T> {

    public Serializable save(T entity) throws Exception;

    public void update(T entity) throws Exception;

    public void delete(T entity) throws Exception;

    public T getById(PK key) throws Exception;

    public List<T> getAll() throws Exception;
}
