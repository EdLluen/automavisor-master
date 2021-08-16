Feature: Flujo tipificaciones

@SmokeTest
Scenario Outline: Como usuario activo realizo el flujo de registro de tipificacion
	Given Tipificacion - Se carga web Visor e inicio sesion como administrador "<Id>"
	When Busco el numero, doy click al boton CIERRA ATENCION y al boton HACER TIPIFICACION "<Id>"
	Then Completo el formulario y envio el registro de tipificacion "<Id>"

	Examples:
	| Id |
	|1|
	|2|
	|3|
	|4|
	|5|
	|6|
	|7|
	|8|
	|9|
	|10|
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
