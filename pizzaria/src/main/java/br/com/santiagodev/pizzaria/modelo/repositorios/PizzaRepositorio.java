package br.com.santiagodev.pizzaria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.santiagodev.pizzaria.modelo.entidades.Pizza;

@Repository
public interface PizzaRepositorio extends CrudRepository<Pizza, Long>{

}
