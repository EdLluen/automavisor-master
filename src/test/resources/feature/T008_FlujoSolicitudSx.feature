Feature: Flujo Suspension fija

@SmokeTest
Scenario Outline: Como usuario activo realizo el flujo de suspension fija
	Given Solicitud de Sx - Se carga web Visor e inicio sesion como administrador "<Id>"
	When Busco el numero fijo y selecciono opcion Pedidos y solicitudes "<Id>"
	And Selecciono Registro de suspension y reconexion, verifico la información mostrada y doy click al botón SUSPENDER "<Id>"
	Then Completo el formulario y envio la solicitud de suspension fija "<Id>"

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
