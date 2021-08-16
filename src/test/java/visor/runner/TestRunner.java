package visor.runner;

//import org.junit.runner.RunWith;
//import cucumber.api.junit.Cucumber;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import visor.utility.ScreenshotUtility;

import java.io.File;


@RunWith(Cucumber.class)
@CucumberOptions(
 //      features = "src/test/resources/feature/T001_FlujoNoNavegaFija.feature"
//        features = "src/test/resources/feature/T002_FlujoSolicitudCxR.feature"
//        features = "src/test/resources/feature/T003_FlujoReporteCxR.feature"
//        features = "src/test/resources/feature/T004_FlujoReporteSxRx.feature"
 //        features = "src/test/resources/feature/T005_FlujoNoNavegaMovil.feature"
//        features = "src/test/resources/feature/T006_FlujoTipificaciones.feature"
//        features = "src/test/resources/feature/T007_FlujoReporteAccesoUsuario.feature"
//        features = "src/test/resources/feature/T008_FlujoSolicitudSx.feature"
//        features = "src/test/resources/feature/T009_FlujoToolResetVoz.feature"
//        features = "src/test/resources/feature/T010_FlujoToolResetDatos.feature"
  //     features = "src/test/resources/feature/T011_FlujoToolResetTotal.feature"
        features = "src/test/resources/feature/T012_FlujoLentitudFija.feature"

        , plugin = {"com.cucumber.listener.ExtentCucumberFormatter:results/cucumber-reports/report.html",
        "junit:results/cucumber-reports/Cucumber.xml",
        "html:results/cucumber-reports"} //Formato de reporte generado por Cucumber
        , glue = {"visor.tests", "visor.helpers"} //Ubicación package de la clase steps
        , tags = {"@SmokeTest"} //Fi--ltra por tags los escenarios a ejecutar
//		,tags= {"@RegressionTest"}
        , dryRun = false //Verifica que todos los pasos en el feature esten implementados en los steps
        , monochrome = true //Muestra en consola el output de las pruebas de Cucumber
)

public class TestRunner extends AbstractTestNGCucumberTests {
//public class TestRunner {
@AfterClass
public static void writeExtentReport() {

    Reporter.loadXMLConfig(new File("./src/extent-config.xml"));
   //Reporter.loadXMLConfig(new File("D:\\Automation\\FrWk_BDD_OpenCart_BK\\BK4\\FrWk_BDD_OpenCart\\src\\extent-config.xml"));
}
    @BeforeTest
    public static void cleanFolder(){
        String sCarpAct = "./results/screenshot/";
        ScreenshotUtility.cleanScreenshot(sCarpAct);
    }

}

