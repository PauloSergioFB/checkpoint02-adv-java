package br.com.fiap.tdsq.checkpoint02_adv_java.service;

import java.util.List;

public interface DroneService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T create(T drone);

    void removeById(ID id);

    boolean existsById(ID id);

}