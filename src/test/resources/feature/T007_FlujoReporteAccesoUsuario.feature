Feature: Flujo reporte de acceso de usuario

@SmokeTest
Scenario Outline: Como usuario activo realizo el flujo de reporte de acceso de usuario
	Given Reporte acceso de usuario - Se carga web Visor e inicio sesion como administrador "<Id>"
	When Doy click al card de Descarga de reporte ubicado en el home "<Id>"
	Then Ingreso las fechas de inicio y fin y doy click al boton DESCARGAR "<Id>"

	Examples:
	| Id |
	|1|
	|2|
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
