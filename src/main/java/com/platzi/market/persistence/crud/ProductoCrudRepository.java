package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Cliente;
import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
//Esta interfaz es la encargada de proveernos de los diferentes metodos con los cuales vamos a hacer las operaciones de
//CRUD dentro de la DB
    //Recive el operador de diamante y como primer parametro esta el tipo de obejeto que relaciona la blase con la tabla de la base de datos
    /// y como segundo parametro recibe el tipo de dato de la clave primaria de esta tabla

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {
    //Formas para hacer methodos que representen las querys dentro de la base de datos
        //Forma explicita: En esta usamos las utilidades de la interfaz para crear una adaptación de un query
        //Se puede entrar a CrudRepository o a https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods para entendrelos mejor
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

        //Forma de query nativo: en este nos apoyamos de la notación @Query para escribir la query directamnete
        // y podemos asignar el nombre que deseemos al metodo
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ? ",nativeQuery = true)
    List<Producto> searchByCategoria(int idCategoria);


    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock,boolean estado);
    
}
