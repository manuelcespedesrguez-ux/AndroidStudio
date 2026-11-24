package com.example.b20cardview

/*
    TODO

    -¿PARA QUE SIRVE Y POR QUE SE CREA? -> Define la estructura de un unico elemento de la lista. Se crea para  agrupar distintos tipos de datos (imagenes, texto y numeros) bajo un mismo objeto en lugar de manejar variables sueltas

     -PARTES CLAVE -> Sus tres atributos: IMAGEN(identificador numerico del recurso), NOMBRE(texto) y VISTAS(entero)

     -EJEMPLO SENCILLO -> Es como la ficha tecnica de un producto en un catalogo:
     Anime(R.drawable.naruto, "Naruto", 500)
 */

class Anime( // * Declaracion de la clase Anime y abre su constructor principal
    @JvmField val imagen: Int, @JvmField val nombre: String?, @JvmField val visitas: Int

    // ? @JvmField val imagen: Int -> Crea una propiedad de solo lectura(val) para el ID numerico de la imagen. @JvmField permite leer directamente desde Java sin usar metodos getter.

    // ? @JvmField val nombre: String? -> Crea la propiedad de texto para el nombre; el simbolo "?" indica que acepta valores nulos (null)

    // ? @JvmField val visitas: Int -> Crea la propiedad numerica para el contador de vistas.
)