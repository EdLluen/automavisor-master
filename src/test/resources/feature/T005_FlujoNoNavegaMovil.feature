Feature: Flujo No navega fija

@SmokeTest
Scenario Outline: Como usuario activo realizo el flujo de no navega movil
	Given No navega movil - Se carga web Visor e inicio sesion como administrador "<Id>"
	When Busco el numero movil y selecciono opcion Avería "<Id>"
	And Selecciono Problemas con datos "<Id>"
	And Selecciono opcion No navega "<Id>"
	Then Verifico la información mostrada y doy click al botón mostrado en pantalla "<Id>"

	Examples:
	| Id |
	|1|
	|2|
	|3|
	|4|
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
