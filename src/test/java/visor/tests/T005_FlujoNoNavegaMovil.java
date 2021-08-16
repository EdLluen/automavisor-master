/**************************************************
 #Autor : Wipro
 #Description : Pruebas de diagnóstico no navega fija
 #Fecha de creación:
 #Venta Fija Version: XXXX
 #Input Parameters: --
 #Nombre que modifica : --
 #Fecha modificación: --
 **************************************************/
package visor.tests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import visor.helpers.BasePage;
import visor.helpers.Hook;
import visor.pageObject.FlujoGeneral.P001_LoginPage;
import visor.pageObject.FlujoGeneral.P002_HomePage;
import visor.pageObject.FlujoGeneral.P003_ConsultasPage;
import visor.pageObject.FlujoNoNavegaMovil.P004_ProblemasMovilPage;
import visor.pageObject.FlujoNoNavegaMovil.P005_NoNavegaMovilPage;
import visor.pageObject.FlujoNoNavegaMovil.P006_FinNoNavegaMovilPage;
import visor.utility.ExcelUtils;
import visor.utility.ScreenshotUtility;

public class T005_FlujoNoNavegaMovil {
    public WebDriver driver;
    private Hook hook = new Hook();
    BasePage basepage = new BasePage();
    P001_LoginPage p001HomePage =new P001_LoginPage();
    P002_HomePage p002HomePage =new P002_HomePage();
    P003_ConsultasPage p003ConsultasPage =new P003_ConsultasPage();
    P004_ProblemasMovilPage p004problemasMovilPage = new P004_ProblemasMovilPage();
    P005_NoNavegaMovilPage p005noNavegaMovilPage =new P005_NoNavegaMovilPage();
    P006_FinNoNavegaMovilPage p006FinNoNavegaMovilPage = new P006_FinNoNavegaMovilPage();
    ScreenshotUtility utilitarios=new ScreenshotUtility();

    public String featureName;
    public String testername;

    public String sAks;
    public String sUrl;
    public String sBrowser;
    public String number;
    public String opcionPedido1;
    public String opcionPedido2;

    public String frmName;
    public String frmPhone;
    public String escenario;

    public String user;
    public String pass;

    public String opt1;
    public String opt2;
    public String opt3;
    String reason;
    String newStatusChange;


    public Object[][] LoadURL() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Login");
        return (testObjArray);
    }

    public Object[][]GetCliente() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "NoNavegaMovil");
        return (testObjArray);
    }

    public Object[][]GetTester() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Tester");
        return (testObjArray);
    }
    public void SetExcel(String SheetName, String ColName,String SrowNum, String sData){
        ExcelUtils.setCellData("./src/test/resources/data/TestData.xlsx",SheetName,ColName,SrowNum,sData);
    }

    String ultimoCodCorrelativo;
    @Given("^No navega movil - Se carga web Visor e inicio sesion como administrador \"([^\"]*)\"$")
    public void ingreso_visor(int id) throws Throwable {
        Boolean validBrowser=true;
        Boolean validIngresoSupervisor;
        try{
            Object [][] Parameters=LoadURL();
            sUrl=Parameters[(id-1)][2].toString();
            sBrowser=Parameters[(id-1)][3].toString();
            user=Parameters[(id-1)][4].toString();
            pass=Parameters[(id-1)][5].toString();
            Object [][] Parameters3=GetTester();
            testername=Parameters3[(0)][1].toString();
            featureName=Parameters3[(0)][2].toString();

            driver=hook.browser(sBrowser,sUrl);
            System.out.println("escenario: "+escenario);
            p001HomePage.dologin(id, user, pass);
       }catch  (AssertionError e) {
               e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Falló validación del Given: Error en levantar Browser o boton Supervisor");
        }
    }

    @When("^Busco el numero movil y selecciono opcion Avería \"([^\"]*)\"$")
    public void busco_numero_opcion_averia(int id) throws Throwable {
        try {
            Object [][] Parameters2=GetCliente();
            number=Parameters2[(id-1)][1].toString();
            opcionPedido1=Parameters2[(id-1)][2].toString();

            p002HomePage.buscar_Numero(id, number);
            p003ConsultasPage.tipoConsulta(id, opcionPedido1);

        }catch  (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo validacion de Busqueda numero o navegacion de botones Solicitudes y pedidos", e);
         }
    }

    @And("^Selecciono Problemas con datos \"([^\"]*)\"$")
    public void selecciono_tipo_problema(int id) throws Throwable {
        try{
            Object [][] Parameters2=GetCliente();
            opcionPedido2=Parameters2[(id-1)][3].toString();
            frmName=Parameters2[(id-1)][4].toString();
            frmPhone=Parameters2[(id-1)][5].toString();
            p004problemasMovilPage.tipoProblema(id, opcionPedido2, frmName, frmPhone);
            //ultimoCodCorrelativo = p004ProblemasPage.tipoProblema(id, opcionPedido2);
            //SetExcel("Cliente","AnteriorCodCorrelativo",String.valueOf(id),ultimoCodCorrelativo);

       }catch  (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo validacion de del boton problemas con internet", e);
           /* utilitarios.takeScreenshot(" Prueba ID: "+id+"-ERROR_Suspension",BasePage.handleDriver());
            ScreenshotUtility.saveAccion(" Prueba ID: "+id+"-ERROR_Suspension");*/
        }
    }

    @And("^Selecciono opcion No navega \"([^\"]*)\"$")
    public void selecciono_opcion_diagnostico(int id) throws Throwable {
        try{
            Object [][] Params = GetCliente();
            opt1 = Params[(id-1)][6].toString();
            opt2 = Params[(id-1)][7].toString();
            opt3 = Params[(id-1)][8].toString();
            p005noNavegaMovilPage.doFlux(id, opt1, opt2, opt3);
        }catch  (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo", e);
            utilitarios.takeScreenshot(" Prueba ID: "+id+"-ERROR",BasePage.handleDriver());
            ScreenshotUtility.saveAccion(" Prueba ID: "+id+"-ERROR");
        }
    }

    @Then("^Verifico la información mostrada y doy click al botón mostrado en pantalla \"([^\"]*)\"$")
    public void verifico_y_doy_click_en_el_boton_mostrado(int id) throws Throwable {

        try{
            p006FinNoNavegaMovilPage.endFlux(id);
            Thread.sleep(1000);
            hook.closeBrowser(basepage.handleDriver(), testername, id, "EXITO");
        }catch  (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo", e);
            utilitarios.takeScreenshot(" Prueba ID: "+id+"-ERROR",BasePage.handleDriver());
            ScreenshotUtility.saveAccion(" Prueba ID: "+id+"-ERROR");
        }
    }

}



