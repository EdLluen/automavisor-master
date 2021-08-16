/**************************************************
 * WIPRO PROPIEDAD INTELECTUAL
 #Autor : Wipro Automation Team
 #Description : Agendamiento - Trazabilidad
 #Fecha de creación: Feb 2020
 #Nombre que modifica : --
 #Fecha modificación: --
 **************************************************/
package visor.helpers;
import cucumber.api.java.Before;
import gherkin.formatter.model.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import visor.utility.GlobalVariable;
import visor.utility.*;
import visor.utility.WordUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Hook {
    public WebDriver driver;
    public BasePage basePage;
    public static String sFeatureName;
    public static String sEscenario;
    public static String sEstadoPrueba;
    private String token = "AAIkMzcxZDFhNmYtYTM1YS00ZDA1LWE2ODYtMzU2OTM3MzVhYWFhrTQ-agpqIKJhDpxkr8WwfFYevELjzU_GwQDHXQWvFKf4vWAbE08OHVpYIShwmgGggtdPXKhiFN8o8mzgqx-GiAnTIlcKFycf7uKM5l3Nb-o";

    Scenario sce;
    protected  ScreenshotUtility utilitarios = new ScreenshotUtility();

    @Before
    public void getFeatureFileNameFromScenarioId(cucumber.api.Scenario scenario) {
        String featureName = "Feature ";
        String rawFeatureName = scenario.getId().split(";")[0].replace("-"," ");
        featureName = featureName + rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);
        System.out.println("FeatureName_:"+featureName);
        System.out.println("Escenario:"+scenario.getName());
        sFeatureName = featureName;
        sEscenario = scenario.getName();
    }
    /**
     * Borra todos los coockies e instancia el browser indicado
     */

  //  @Before
    public  WebDriver  browser(String sbrowser, String strURL) throws Exception {
 /*       String sCarpAct = "./results/screenshot/";
        ScreenshotUtility.cleanScreenshot(sCarpAct);

   */
        try{
        boolean browserFound = true;
        if (sbrowser.toUpperCase().equals("FIREFOX")) {
            System.setProperty("webdriver.chrome.driver", GlobalVariable.AUT_BROWSER_SERVER_PATH_FF);
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } else if (sbrowser.toUpperCase().equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", GlobalVariable.AUT_BROWSER_SERVER_PATH_CHROME);
            driver = new ChromeDriver();
            System.out.println("levanta chrome");
        } else if (sbrowser.toUpperCase().equals("IE")) {
            System.setProperty("webdriver.ie.driver", GlobalVariable.AUT_BROWSER_SERVER_PATH_IE);
            driver = new InternetExplorerDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } else {
            browserFound = false;
        }
        if (browserFound) {
            driver.manage().window().maximize();
            //driver.manage().deleteAllCookies();
            driver.get(strURL);
            basePage = new BasePage();
            basePage.setWebDriver(driver);
            System.out.println("Browser Inicializado "+driver.getTitle());
        } else {
            System.out.println("No se inicio driver");
            return null;
        }
    }catch(AssertionError e){
            System.out.println("Fallo levantar browser");
            Assert.fail("Fallo en cargar browser",e);
            utilitarios.takeScreenshot("ERROR_en_cargar_browser", driver);
            ScreenshotUtility.saveAccion("ERROR_en_cargar_browser");
        }
        return driver;

    }

    public WebDriver newTab(String sAks) throws Exception {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(sAks);
        } catch (AssertionError e) {
            Assert.fail("Fallo en cargar tab",e);
        }
        return driver;
    }

    public void sessionStorage() {
        try {
            SessionStorage s = ((WebStorage) driver).getSessionStorage();
            s.setItem("token-onpremise", token);
        } catch (Exception e) {
            System.out.println("Error insertar token: "+e);
        }
    }


    /**
     * Cierra el browser y termina la sesion del WebDriver
     */

    //@After
    public static void closeBrowser(WebDriver iDriver, String testername,int id, String sEstadoPrueba) throws Exception {
        System.out.println("en cerrar browser");
        generarWordFile(testername, id, sEstadoPrueba);
        iDriver.quit();
    }

    //public static void generarWordFile(String featureName, String testername) throws Exception {
    public static void generarWordFile(String testername,int id, String sEstadoPrueba) throws Exception {
        String sCarpAct = "./results/screenshot/";
        List<String> listScreen = ScreenshotUtility.listScreenshot(sCarpAct);
      //  List <String> listaEscenario= ScreenshotUtility.saveScenario();

        if(GlobalVariable.IMPRIMIR_WORD){
        if (listScreen != null && listScreen.size() > 0) {
            System.out.println("Generar Word");
            WordUtils.createDoc(listScreen, testername, id, sEstadoPrueba);
        } }else {
            System.out.println("No hay Data");
        }
    }

}