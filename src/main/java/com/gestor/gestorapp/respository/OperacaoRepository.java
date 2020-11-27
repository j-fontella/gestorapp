package com.gestor.gestorapp.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestor.gestorapp.models.Operacao;



public interface OperacaoRepository extends CrudRepository<Operacao, String> {

}
