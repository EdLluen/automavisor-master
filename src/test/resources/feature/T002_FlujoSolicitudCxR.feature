	Feature: Flujo Corte por robo movil

	@SmokeTest
	Scenario Outline: Como usuario activo, realizo el flujo de corte por robo movil
		Given Solicitud de CxR - Se carga web Visor y realizo inicio de sesion como administrador "<Id>"
		When Busco el numero y selecciono opcion Pedidos y solicitudes "<Id>"
		And Selecciono Corte por robo, verifico la información mostrada y doy click al botón NUEVA SOLICITUD "<Id>"
		Then Completo el formulario y envio la solicitud de corte por robo "<Id>"

		Examples:
		| Id |
#		|1|
#		|2|
		|3|
#		|4|
##		|5|
#		|6|
