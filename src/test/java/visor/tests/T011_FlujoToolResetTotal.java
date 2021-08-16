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
import visor.pageObject.FlujoGeneral.P004_HerramientasMovilPage;
import visor.pageObject.FlujoToolResetDatos.P005_ResetDatosPage;
import visor.pageObject.FlujoToolResetTotal.P005_ResetTotalPage;
import visor.utility.ExcelUtils;

public class T011_FlujoToolResetTotal {
    public WebDriver driver;
    private Hook hook = new Hook();
    BasePage basepage = new BasePage();
    P001_LoginPage p001loginPage = new P001_LoginPage();
    P002_HomePage p002HomePage = new P002_HomePage();
    P003_ConsultasPage p003ConsultasPage = new P003_ConsultasPage();
    P004_HerramientasMovilPage p004HerramientasMovilPage = new P004_HerramientasMovilPage();
    P005_ResetTotalPage p005_resetTotalPage = new P005_ResetTotalPage();

    public String featureName;
    public String testername;

    public String sUrl, sBrowser, user, pass, escenario, token;
    public String number, consultation, failure, tab, tool;

    public Object[][] LoadURL() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Login");
        return (testObjArray);
    }

    public Object[][]GetToolResetTotal() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "ToolResetTotal");
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

    @Given("^Tool Reset total - Se carga web Visor e inicio sesion como administrador \"([^\"]*)\"$")
    public void ingreso_visor(int id) throws Throwable {
        try {
            Object[][] Parameters = LoadURL();
            Object[][] Parameters1 = GetTester();
            sUrl = Parameters[(id-1)][2].toString();
            sBrowser = Parameters[(id-1)][3].toString();
            user = Parameters[(id-1)][4].toString();
            pass = Parameters[(id-1)][5].toString();
            testername = Parameters1[(0)][1].toString();
            featureName = Parameters1[(0)][2].toString();

            driver = hook.browser(sBrowser, sUrl);
            System.out.println("escenario: " + escenario);
            p001loginPage.dologin(id, user, pass);
            hook.sessionStorage();
        } catch (Exception e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al loguear", e);
        }
    }
    @When("^Busco numero movil y selecciono el card Averia \"([^\"]*)\"$")
    public void selecciono_card_averia(int id) throws Throwable {
        try {
            Object[][] Parameters = GetToolResetTotal();
            number = Parameters[(id-1)][1].toString();
            consultation = Parameters[(id-1)][2].toString();
            p002HomePage.buscar_Numero(id, number);
            p003ConsultasPage.tipoConsulta(id, consultation);
        } catch (Exception e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al buscar numero o al abrir card de pedidos y solicitudes", e);
        }
    }

    @And("^Doy click al card Herramientas y dar click a la pestaña GENERAL \"([^\"]*)\"$")
    public void click_card_herramienta_y_click_a_pestaña(int id) throws Throwable {
        try {
            Object[][] Parameters = GetToolResetTotal();
            failure = Parameters[(id-1)][3].toString();
            tab = Parameters[(id-1)][4].toString();
            p004HerramientasMovilPage.problemType(id, failure, tab);
        } catch (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al seleccionar opcion o insertar dato en form de tipificacion", e);
        }
    }

    @Then("^Seleccionar card de Reset total y valido la respuesta obtenida para el tool \"([^\"]*)\"$")
    public void click_card_reset_datos_y_validar_respuesta(int id) throws Throwable {
        try {
            Object[][] Parameters = GetToolResetTotal();
            tool = Parameters[(id-1)][5].toString();
            p005_resetTotalPage.dataTool(id, tool);
            hook.closeBrowser(basepage.handleDriver(), testername, id, "EXITO");
        } catch (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al seleccionar opcion o insertar dato en form de tipificacion", e);
        }
    }
}
