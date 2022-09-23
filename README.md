# Introducción

El objetivo de este ejercicio es la refactorización de este simple proyecto Java incluyendo aquellas buenas prácticas
de programación y tecnologías que simplifiquen su uso y mantenimiento.

Se trata de una clase con un método público que devuelve la previsión del tiempo de una ciudad en una fecha concreta.

Para ello, esta clase utiliza una API externa (requiere conexión a internet): [www.open-meteo.com](https://www.open-meteo.com) 

Ejemplo:

```java
WeatherForecast weatherForecast = new WeatherForecast();
weatherForecast.getCityWeather("Madrid", new Date());
```


# Ejercicio

El ejercicio consiste en **refactorizar** el código para hacerlo más **mantenible**, ya que el código existente, aunque **funciona**, es muy difícil de entender. 
  
Para ello se pueden realizar múltiples modificaciones siempre que se mantenga el API público. Ejemplos de modificaciones: incluir tests automáticos, extraer métodos, renombrar variables, modificar tipos de datos, crear nuevas clases, añadir librerías, etc. 


# Documentación

La solución debería contener un fichero README donde se respondan estas preguntas:

- ¿Qué has empezado implementando y por qué?
  1. Lo primero que he hecho es revisar el código y ejecutar el programa. Me encontré con varios problemas:
	1.1. Problema con la API: com.google.api.client.http.HttpResponseException: 403 Forbidden --> Lo he solucionado obtiendo un API_KEY
	1.2. Problema en el casteo con la instrucción: return ForecastEnum.getEnumByCode((int) weatherCodeResults.get(i)).getDescription();
	     La solución que he pensando es hacer cast primero a "double" y posteriormente a "int". El funcionamiento ha sido correcto.
  2. Una vez solucionados los problemas en la ejecución, he empezado cambiando el nombre de las variables que no me parecían descriptivas. También he creado constantes
     para datos que no cambian durante la ejecución. 
  3. En el método "getCityWeather" parte de las instrucciones las he introducidos en métodos, ya sea porque la instrucción se repetía o para facilitar la legilibidad 
     dentro de la función. Las funciones que he generado son:
	3.1. String getDescriptionWeather (JSONArray dailyResults, JSONArray weatherCodeResult, Date datetime) --> Devuelve la descripción del tiempo.
	3.2. String getHttpRequest (String url) --> Realiza peticiones a la API y devuelve el resultado JSON en un String.
	3.3. boolean checkDateBeforeParameter (Date datetimeCheck, Date newDate) --> Comprueba si la fecha pasada como parámetro es anterior a la nueva fecha.
	3.4. Date checkDatetimeIsNull (Date datetime) --> Comprueba si el parámetro de tipo Date es Null, en tal caso realiza un "new Date()".
  4. He creado 2 clases adicionales para trabajar con los objetos JSON:
	4.1. CityCoordinates --> Extrae la longitud y latitud en formato String. 
   	4.2. DailyWeather --> Extrae los datos de los campos "weathercode" y "time". Puede devolver objetos JSONArray.

 	
- ¿Qué problemas te has encontrado al implementar los tests y cómo los has solventado?
  A parte de probar el método principal de la clase WeatherForecast (getCityWeather) quería probar los métodos mencionados en la primera pregunta. He cambiado al     atributo de acceso private por protected para poder acceder a ellos desde la clase de test.
  NOTA: He cambiado la versión de Junit 4 por la 5.


- ¿Qué componentes has creado y por qué?

- Si has utilizado dependencias externas, ¿por qué has escogido esas dependencias?
  He utilizado Junit5. El motivo es que principalmente he trabajado más con esa versión. También puede aprovechar características de Java8 o posterior, como funciones   lambda, usar más de una extensión a la vez, etc.

- ¿Has utilizado  streams, lambdas y optionals de Java 8? ¿Qué te parece la programación funcional?
  Sólo he utilizado lambdas en la clase de test, debido a que en Junit 5, los test que evaluan el lanzamiento de excepciones se realizan de esta manera. 

- ¿Qué piensas del rendimiento de la aplicación? 
  El rendimiento no es demasiado bueno, por lo menos en según que ejecuciones. Depende también de la respuesta por parte de la API.

- ¿Qué harías para mejorar el rendimiento si esta aplicación fuera a recibir al menos 100 peticiones por segundo?
  Haría uso de hilos, por ejemplo con la Clase Thread.

- ¿Cuánto tiempo has invertido para implementar la solución? 
  Entre 6.5 y 7 horas. He dedicado entre 2 y 3 horas después de mi jornada laboral durante 3 días.

- ¿Crees que en un escenario real valdría la pena dedicar tiempo a realizar esta refactorización?
  Por supuesto. Envita errores y simplifica la lectura del código. 

# A tener en cuenta

- Se valorará positivamente el uso de TDD, se revisarán los commits para comprobar su uso.
- Se valorará positivamente la aplicación de los principios SOLID y "código limpio".
- La aplicación ya tiene un API público: un método público que devuelve el tiempo en un String. No es necesario crear nuevas interfaces: REST, HTML, GraphQL, ni nada por el estilo.


# Entrega

La solución se puede subir a un repositorio de código público como [github](https://github.com/) --> https://github.com/pEDrOxZzz666/weather.git 

Otra opción sería comprimir la solución (incluyendo el directorio .git) en un fichero .zip/.tar.gz y enviarlo por email.
