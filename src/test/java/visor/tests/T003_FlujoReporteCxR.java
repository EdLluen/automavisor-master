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
import visor.pageObject.FlujoReporteCxR.P004_ReporteCxRPage;
import visor.pageObject.FlujoSolicitudCxR.P004_CortePorRoboPage;
import visor.utility.ExcelUtils;
import visor.utility.ScreenshotUtility;


public class T003_FlujoReporteCxR {
    public WebDriver driver;
    private Hook hook = new Hook();
    BasePage basepage = new BasePage();
    P000_AKSPage p000AksPage = new P000_AKSPage();
    P001_LoginPage p001HomePage =new P001_LoginPage();
    P002_HomePage p002HomePage =new P002_HomePage();
    P003_ConsultasPage p003ConsultasPage =new P003_ConsultasPage();
    P004_ReporteCxRPage p004ReporteCxRPage = new P004_ReporteCxRPage();
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
    public String escenario;

    public String user;
    public String pass;

    /*String reason;
    String newStatusChange;*/

    public String month;
    public String year;

    public Object[][] LoadURL() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Login");
        return (testObjArray);
    }

    public Object[][]GetRepCxR() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "RepCxR");
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
    @Given("^Reporte de CxR - Se carga web Visor y realizo inicio de sesion como administrador \"([^\"]*)\"$")
    public void ingreso_visor_uno(int id) throws Throwable {
        Boolean validBrowser=true;
        Boolean validIngresoSupervisor;
        try{
            Object [][] Parameters=LoadURL();
            sAks = Parameters[(id-1)][1].toString();
            sUrl = Parameters[(id-1)][2].toString();
            sBrowser = Parameters[(id-1)][3].toString();
            user = Parameters[(id-1)][4].toString();
            pass = Parameters[(id-1)][5].toString();

            Object [][] Parameters3=GetTester();
            testername=Parameters3[(0)][1].toString();
            featureName=Parameters3[(0)][2].toString();

            driver = hook.browser(sBrowser,sUrl);
            System.out.println("escenario: " + escenario);
            //p000AksPage.getAccess();
            //driver = hook.newTab(sUrl);
            p001HomePage.dologin(id, user, pass);
        }catch  (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Falló validación del Given: Error en levantar Browser o boton Supervisor");
        }
    }

    @When("^Buscar el numero y seleccionar card Pedidos y solicitudes \"([^\"]*)\"$")
    public void busco_numero_opcion_Pedidos_y_solicitudes(int id) throws Throwable {
        try {
            Object [][] Parameters2 = GetRepCxR();
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

    @Then("^Seleccionar card Reporte de corte por robo, elijo mes, año y dar click al boton DESCARGAR \"([^\"]*)\"$")
    public void selecciono_Corte_por_robo_click_nueva_solicitud(int id) throws Throwable {
        try{
            Object [][] Parameters2 = GetRepCxR();
            opcionPedido2 = Parameters2[(id-1)][3].toString();
            month = Parameters2[(id-1)][4].toString();
            year = Parameters2[(id-1)][5].toString();
            p004ReporteCxRPage.tipoOperacion(id, opcionPedido2, month, year);
            hook.closeBrowser(basepage.handleDriver(), testername, id, "EXITO");
        }catch  (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo validacion de del boton problemas con internet", e);
            utilitarios.takeScreenshot(" Prueba ID: "+id+"-ERROR_Suspension",BasePage.handleDriver());
            ScreenshotUtility.saveAccion(" Prueba ID: "+id+"-ERROR_Suspension");

        }
    }
}
