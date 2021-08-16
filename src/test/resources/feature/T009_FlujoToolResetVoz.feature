Feature: Flujo Tool Reset de voz

@SmokeTest
Scenario Outline: Como usuario activo realizo el flujo de tool de Reset de voz movil
	Given Tool Reset de voz - Se carga web Visor e inicio sesion como administrador "<Id>"
	When Busco el numero movil y selecciono card Averia "<Id>"
	And Doy click al card Herramientas y dar click a la pestaña VOZ "<Id>"
	Then Seleccionar card de Reset de voz y valido la respuesta obtenida para el tool "<Id>"

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
