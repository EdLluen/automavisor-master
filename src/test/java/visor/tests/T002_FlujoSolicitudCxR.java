/**************************************************
 #Autor : Wipro
 #Description : Pruebas solicitud de corte por robo movil
 #Fecha de creación: Ago 2020
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
import visor.pageObject.FlujoGeneral.P000_AKSPage;
import visor.pageObject.FlujoGeneral.P001_LoginPage;
import visor.pageObject.FlujoGeneral.P002_HomePage;
import visor.pageObject.FlujoGeneral.P003_ConsultasPage;
import visor.pageObject.FlujoSolicitudCxR.P004_CortePorRoboPage;
import visor.utility.ExcelUtils;
import visor.utility.ScreenshotUtility;

public class T002_FlujoSolicitudCxR {
    public WebDriver driver;
    private Hook hook = new Hook();
    BasePage basepage = new BasePage();
    P001_LoginPage p001HomePage =new P001_LoginPage();
    P002_HomePage p002HomePage =new P002_HomePage();
    P003_ConsultasPage p003ConsultasPage =new P003_ConsultasPage();
    P004_CortePorRoboPage p004CortePorRoboPage = new P004_CortePorRoboPage();
    ScreenshotUtility utilitarios=new ScreenshotUtility();

    public String featureName;
    public String testername;

    public String sAks;
    public String sUrl;
    public String sBrowser;
    public String number;
    public String opcionPedido1;
    public String opcionPedido2;

    public String frmChannel;
    public String frmANI;
    public String frmHolder;
    public String frmName;
    public String frmLastName;
    public String frmDocument;
    public String frmDocumentType;
    public String frmNotify;
    public String frmNotifyType;
    public String escenario;

    public String user;
    public String pass;

    public String algo;

    String reason;
    String newStatusChange;


    public Object[][] LoadURL() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Login");
        return (testObjArray);
    }

    public Object[][]GetCxR() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "CxR");
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
    @Given("^Solicitud de CxR - Se carga web Visor y realizo inicio de sesion como administrador \"([^\"]*)\"$")
    public void ingreso_visor_uno(int id) throws Throwable {
        Boolean validBrowser=true;
        Boolean validIngresoSupervisor;
        try{
            Object [][] Parameters=LoadURL();
            //algo = Parameters[(id-1)][6].toString();
            sAks = Parameters[(id-1)][1].toString();
            sUrl = Parameters[(id-1)][2].toString();
            sBrowser = Parameters[(id-1)][3].toString();
            user = Parameters[(id-1)][4].toString();
            pass = Parameters[(id-1)][5].toString();
            Object [][] Parameters3=GetTester();
            testername=Parameters3[(0)][1].toString();
            featureName=Parameters3[(0)][2].toString();

            driver=hook.browser(sBrowser,sUrl);
            System.out.println("escenario: "+escenario);
            driver=hook.newTab(sUrl);
            p001HomePage.dologin(id, user, pass);
       }catch  (AssertionError e) {
               e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Falló validación del Given: Error en levantar Browser o boton Supervisor");
        }
    }

    @When("^Busco el numero y selecciono opcion Pedidos y solicitudes \"([^\"]*)\"$")
    public void busco_numero_opcion_Pedidos_y_solicitudes(int id) throws Throwable {
        try {
            Object [][] Parameters2=GetCxR();
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

    @And("^Selecciono Corte por robo, verifico la información mostrada y doy click al botón NUEVA SOLICITUD \"([^\"]*)\"$")
    public void selecciono_Corte_por_robo_click_nueva_solicitud(int id) throws Throwable {
        try{
            Object [][] Parameters2=GetCxR();
            opcionPedido2 = Parameters2[(id-1)][3].toString();
            frmChannel = Parameters2[(id-1)][4].toString();
            frmANI = Parameters2[(id-1)][5].toString();
            frmHolder = Parameters2[(id-1)][6].toString();
            frmName = Parameters2[(id-1)][7].toString();
            frmLastName = Parameters2[(id-1)][8].toString();
            frmDocumentType = Parameters2[(id-1)][9].toString();
            frmDocument = Parameters2[(id-1)][10].toString();
            frmNotifyType = Parameters2[(id-1)][11].toString();
            frmNotify = Parameters2[(id-1)][12].toString();
            p004CortePorRoboPage.tipoProblema(id, opcionPedido2, frmChannel, frmANI, frmHolder, frmName, frmLastName, frmDocumentType, frmDocument, frmNotifyType, frmNotify);
            //ultimoCodCorrelativo = p004ProblemasPage.tipoProblema(id, opcionPedido2);
            //SetExcel("Cliente","AnteriorCodCorrelativo",String.valueOf(id),ultimoCodCorrelativo);

       }catch  (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo validacion de del boton problemas con internet", e);
 utilitarios.takeScreenshot(" Prueba ID: "+id+"-ERROR_Suspension",BasePage.handleDriver());
            ScreenshotUtility.saveAccion(" Prueba ID: "+id+"-ERROR_Suspension");

        }
    }

    @Then("^Completo el formulario y envio la solicitud de corte por robo \"([^\"]*)\"$")
    public void completar_formulario_envio_solicitud(int id) throws Throwable {
        try{
            hook.closeBrowser(basepage.handleDriver(), testername, id, "EXITO");
        }catch (AssertionError e){
            e.getMessage();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo", e);
            utilitarios.takeScreenshot(" Prueba ID: "+id+"-ERROR",BasePage.handleDriver());
            ScreenshotUtility.saveAccion(" Prueba ID: "+id+"-ERROR");
        }
    }

}



