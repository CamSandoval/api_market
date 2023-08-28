package com.platzi.market.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

//La notación @Entity es la encargada de decirle a java que esta es una de las tablas de nuestra base de datos
@Entity
//Esta notacion le hace saber al programa que la clase Producto hace referencia a la tabla "productos de nuestra base de datos"
@Table(name="productos")
public class Producto {
    //Esta es la notacion para identificar que esta es la clave primaria de la tabla
    @Id
    //
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Como cambiamos el nombre de la columna  id_producto dentro de la variable que la representa
        //adicionamos la notacion @Column para que referenciar su nombre dentro de la tabla
    @Column(name="id_producto")
    private Integer idProducto;
    private String nombre;
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Column(name = "codigo_barras")
    private String codigoBarras;
    @Column(name = "precio_venta")
    private Double precioVenta;
    @Column(name = "cantidad_stock")
    private Integer cantidadStock;
    private Boolean estado;

    //Esta es la forma de poder adicionar relaciones dentro de la base de datos
    //@ManyToOne es la notacion que define que tipo de relacion existe, en este caso es de muchos a uno
    @ManyToAny
    //@JoinColumn es el referenciar que campos som los que representan la relación de las tablas
    @JoinColumn(name = "id_categoria", insertable = false,updatable = false)
    private Categoria categoria;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
