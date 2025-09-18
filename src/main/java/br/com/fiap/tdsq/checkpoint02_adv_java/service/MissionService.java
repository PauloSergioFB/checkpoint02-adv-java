package br.com.fiap.tdsq.checkpoint02_adv_java.service;

import java.util.List;
import java.util.Optional;

public interface MissionService<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    T create(T drone);

    void removeById(ID id);

    boolean existsById(ID id);

}
