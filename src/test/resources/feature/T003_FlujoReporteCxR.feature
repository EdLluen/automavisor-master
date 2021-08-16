  Feature: Flujo Reporte de corte por robo

  @SmokeTest
  Scenario Outline: Como usuario activo, realizo el flujo de reporte de corte por robo movil
    Given Reporte de CxR - Se carga web Visor y realizo inicio de sesion como administrador "<Id>"
    When Buscar el numero y seleccionar card Pedidos y solicitudes "<Id>"
    Then Seleccionar card Reporte de corte por robo, elijo mes, año y dar click al boton DESCARGAR "<Id>"
    Examples:
      | Id |
      |1|
      |2|