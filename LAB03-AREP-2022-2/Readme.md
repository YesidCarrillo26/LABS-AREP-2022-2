# Taller Cliente-Servicio-AREP

API con el uso de Heroku para visualizar un servicio web que soporte multiples solicitudes seguidas 

## Preparacion

Se bajo el repositorio creado en clase localmente para empezar a trabajarlo con el comando

```
git clone https://github.com/YesidCarrillo26/Cliente-Servicio-AREP.git
```


### Prerrequisitos

Necesitamos de:
* Maven
* Intellij IDEA (O cualquier IDE de Java)
* Visual Studio Code

Para una correcta ejecucion del aplicativo.

### Instalacion

Ejecutamos la siguiente instruccion en consola:

```
mvn package
```

con esto maven se encargara de descargar todos los recursos necesarios para la ejecucion del aplicativo.

Para ejecutar la clase donde esta todo el trabajo realizado para el manejo de los datos del json se utiliza

```
mvn exec:java -Dexec.mainClass="edu.escuelaing.arep.HttpServer"
```

Y para acceder a la navegacion web para visualizar lo realizado:

```
http://localhost:5000
```



### Ejecucion

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://pure-cliffs-09030.herokuapp.com/index.html)





## Autor

**Yesid Santiago Carrillo Almeida**


## License

Este proyecto esta bajo la licencia [GNU General Public License v2.0](https://github.com/ronis97/ARSW-T1/blob/master/LICENSE) de uso libre. 




