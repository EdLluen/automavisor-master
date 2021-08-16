**************************PRE-REQUISITOS**********************
- Tener instalado plugins de "Cucumber for Java" y "Gherkin"

**************************ABRIR PROYECTO***********************
1. Abrir IntelliJ
2. Ir a "File" y luego a "Open"
3. Buscar ruta en donde se encuentre el proyecto
4. Seleccionar proyecto y dar click en "Ok"
5. Esperar a que proyecto se abra y se descarguen todas las librerías de Maven

**************************PRECONDICIONES DE EJECUCION DEL PROYECTO***********************
1. Validar la existencia de la carpeta "screenshot" (Ruta: results/)
2. En el archivo TestData.xlsx (Ruta: src/test/resources/data), Revisar los datos del numero de celular del cliente y que las filas "NroOrden"	y "NuevoCodCorrelativo" esten vacias 
3. En la clase P006_FormularioPage, comentar si se queire grabar un nuevo registro o si solo se desea salir del  formulario de Corte por Robo

**************************FLUJOS PARA COE-DEVOPS***********************
-  Flujos funcionales hasta el momento:
1. FlujoReporteCxR
2. FlujoReporteAccesoUsuario

-  Ir al archivo TestRunner (Ruta: test/java/runner/TestRunner.java) quitar comenario al flujo que se desee ejecutar y correr el archivo