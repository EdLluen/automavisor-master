$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/feature/T012_FlujoLentitudFija.feature");
formatter.feature({
  "line": 1,
  "name": "Flujo Lentitud fija",
  "description": "",
  "id": "flujo-lentitud-fija",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Como usuario activo realizo el flujo de lentitud fija",
  "description": "",
  "id": "flujo-lentitud-fija;como-usuario-activo-realizo-el-flujo-de-lentitud-fija",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@SmokeTest"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "No navega fija - Se carga web Visor e inicio sesion como administrador \"\u003cId\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Busco el numero y selecciono opcion Avería \"\u003cId\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Selecciono Problemas con internet \"\u003cId\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Selecciono Lentitud \"\u003cId\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Verifico la información mostrada y doy click al botón en pantalla \"\u003cId\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 11,
  "name": "",
  "description": "",
  "id": "flujo-lentitud-fija;como-usuario-activo-realizo-el-flujo-de-lentitud-fija;",
  "rows": [
    {
      "cells": [
        "Id"
      ],
      "line": 12,
      "id": "flujo-lentitud-fija;como-usuario-activo-realizo-el-flujo-de-lentitud-fija;;1"
    },
    {
      "cells": [
        "1"
      ],
      "line": 13,
      "id": "flujo-lentitud-fija;como-usuario-activo-realizo-el-flujo-de-lentitud-fija;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 316000,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Como usuario activo realizo el flujo de lentitud fija",
  "description": "",
  "id": "flujo-lentitud-fija;como-usuario-activo-realizo-el-flujo-de-lentitud-fija;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@SmokeTest"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "No navega fija - Se carga web Visor e inicio sesion como administrador \"1\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Busco el numero y selecciono opcion Avería \"1\"",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Selecciono Problemas con internet \"1\"",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Selecciono Lentitud \"1\"",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Verifico la información mostrada y doy click al botón en pantalla \"1\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 72
    }
  ],
  "location": "T001_FlujoNoNavegaFija.ingreso_visor(int)"
});
formatter.result({
  "duration": 38487569600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 44
    }
  ],
  "location": "T001_FlujoNoNavegaFija.busco_numero_opcion_averia(int)"
});
formatter.result({
  "duration": 20969912901,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 35
    }
  ],
  "location": "T001_FlujoNoNavegaFija.selecciono_Problemas_con_Internet(int)"
});
formatter.result({
  "duration": 20726554201,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 21
    }
  ],
  "location": "T001_FlujoNoNavegaFija.selecciono_Lentitud(int)"
});
formatter.result({
  "duration": 5496594300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 67
    }
  ],
  "location": "T001_FlujoNoNavegaFija.verifico_y_doy_click_en_el_boton_mostrado(int)"
});
formatter.result({
  "duration": 12268827201,
  "status": "passed"
});
});