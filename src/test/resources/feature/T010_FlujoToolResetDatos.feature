Feature: Flujo Tool Reset de datos

@SmokeTest
Scenario Outline: Como usuario activo realizo el flujo de tool de Reset de datos movil
	Given Tool Reset de datos - Se carga web Visor e inicio sesion como administrador "<Id>"
	When Busco numero movil y selecciono card Averia "<Id>"
	And Doy click al card Herramientas y dar click a la pestaña DATOS "<Id>"
	Then Seleccionar card de Reset de datos y valido la respuesta obtenida para el tool "<Id>"

	Examples:
	| Id |
	|1|
#	|2|
#	|3|
#	|4|
#	|5|
#	|6|
#	|7|
#	|8|
#	|9|
#	|10|
#	|11|
#	|12|
#	|13|
#	|14|
#	|15|
#	|16|
#	|17|
#	|18|
#	|19|
#	|20|
