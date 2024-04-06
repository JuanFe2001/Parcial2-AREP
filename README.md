## Escuela Colombiana de Ingeniería
### Arquitecturas Empresariales – AREP
# Parcial2-AREP

Diseñe un prototipo de sistema de microservicios que tenga un servicio (En la figura se representa con el nombre Math Services) para computar las funciones de ordenamiento.  El servicio de las funciones de ordenamiento debe estar desplegado en al menos dos instancias virtuales de EC2. Adicionalmente, debe implementar un service proxy que reciba las solicitudes de llamado desde los clientes  y se las delega a las dos instancias del servicio de ordenamiento usando un algoritmo de round-robin. El proxy deberá estar desplegado en otra máquina EC2. Asegúrese que se pueden configurar las direcciones y puertos de las instancias del servicio en el proxy usando variables de entorno del sistema operativo.  Finalmente, construya un cliente Web mínimo con un formulario que reciba el valor y de manera asíncrona invoke el servicio en el PROXY. Puede hacer un formulario para cada una de las funciones. El cliente debe ser escrito en HTML y JS.

 ## El prototipo cuenta con 2 clases principales las cuales son:

  **SearchFunctions**: Esta clase proporciona un servidor web que permite realizar búsquedas lineales y binarias a través de dos rutas HTTP /linear-search y /binary-search, respectivamente. Los resultados se devuelven en formato JSON.
  **ServiceProxy**: proporciona funcionalidad para agregar usuarios, calcular y verificar contraseñas hash, así como para configurar y ejecutar un servidor seguro de autenticación de usuario utilizando HTTPS.

  ## Pasos Para Ejecucion (En consola):
  - En consola nos vamos a la carpeta Parcial2 del proyecto
- Una vez alli compilamos el proyecto con el siguiente comando:
  
  ``` 
  mvn clean install
  ```
  - Luego ejecutamos el servicio con los siguientes comando:
 
  ``` 
  mvn exec:java -Dexec.mainClass=edu.eci.arep.parcial2.SearchFunctions
  ```
- Luego ejecutamos este otro comando en otro CMD

    ``` 
   mvn exec:java -Dexec.mainClass=edu.eci.arep.parcial2.ServiceProxy
  ```
- Una vez corriendo el servicio nos dirigimos a nuestro browser de preferencia y accedemos a la pagina web aqui:
  http://localhost:4567/Page.html

- Deberiamos ver la siguiente pagina si seguimos los pasos correctamente:
![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/3d6e7905-d73e-4918-a551-64416760dd07)

## Demostracion:

**PRUEBA BUSQUEDA LINEAL**:
* Caso correcto(valor encontrado):
  **lista**: 4,8,7,3
  **Valor**: 3

![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/0c98af57-0f58-408e-a411-f07399f857d9)
![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/1fac4b69-4b86-468d-807a-c2d221c48282)


**TERMINAL EN PROXY**:
![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/b985af81-aa40-42e9-86bf-7f1471667e65)


* Caso incorrecto(valor no encontrado):
  **lista**: 4,8,7,3
  **Valor**: 1

![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/d146f411-cb9c-4047-8d13-22e94bc8a22f)
![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/68a9861c-1edb-49b7-8dbb-1d5755f5f360)


**TERMINAL EN PROXY**:
![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/afa3a43e-43db-4418-a457-c2fb1ae018c4)

**PRUEBA BUSQUEDA BINARIA**:
* Caso correcto(valor encontrado):
  **lista**: 4,5,6,7
  **Valor**: 6

![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/9eca1305-2a37-473b-b80c-98e37e4bbe31)
![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/96740563-1ce6-4348-8011-8779e7461eaf)

**TERMINAL EN PROXY**:
![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/ab3c6127-cc68-42e1-ae0d-950c334d0b07)

* Caso incorrecto(valor no encontrado):
  **lista**: 1,2,3,4,5
  **Valor**: 7

![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/8c426225-3199-4f55-8875-649070b55c02)
![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/ad3479ba-a3c8-405c-8c8a-634dc0dd7ca5)

**TERMINAL EN PROXY**:
![image](https://github.com/JuanFe2001/Parcial2_AREP/assets/123691538/f77d6ec8-827d-4944-b8e8-8fbba724f308)

**Video Despliegue Azure**

https://youtu.be/E_RfGy4CGgg







  
  
