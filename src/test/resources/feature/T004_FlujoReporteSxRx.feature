  Feature: Flujo Reporte de suspensión y reconexión

  @SmokeTest
  Scenario Outline: Como usuario activo, realizo el flujo de reporte de suspensión y reconexión
    Given Reporte de SxRx - Se carga web Visor y realizo inicio de sesion como administrador "<Id>"
    When Buscar el numero y dar click al card Pedidos y solicitudes "<Id>"
    Then Seleccionar card Reporte de suspensión y reconexión, elijo mes, año, tipo y dar click al boton DESCARGAR "<Id>"
    Examples:
      | Id |
      |1|
      |2|
