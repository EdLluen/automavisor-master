package visor.tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import visor.helpers.BasePage;
import visor.helpers.Hook;
import visor.pageObject.FlujoGeneral.P001_LoginPage;
import visor.pageObject.FlujoGeneral.P002_HomePage;
import visor.pageObject.FlujoReporteAccesoUsuario.P002_CardReporteAccesoUsuarioPage;
import visor.pageObject.FlujoReporteAccesoUsuario.P003_DescargarReporteAccesoUsuarioPage;
import visor.utility.ExcelUtils;

public class T007_FlujoReporteAccesoUsuario {
    public WebDriver driver;
    private Hook hook = new Hook();
    BasePage basepage = new BasePage();
    P001_LoginPage p001loginPage = new P001_LoginPage();
    P002_CardReporteAccesoUsuarioPage p002ReporteAccesoUsuarioPage = new P002_CardReporteAccesoUsuarioPage();
    P003_DescargarReporteAccesoUsuarioPage p003DescargarReporteAccesoUsuarioPage = new P003_DescargarReporteAccesoUsuarioPage();

    public String featureName;
    public String testername;

    public String sUrl, sBrowser, user, pass, escenario, token;
    public String opcion, fchInicio, fchFin;

    public Object[][] LoadURL() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Login");
        return (testObjArray);
    }

    public Object[][]GetReporteAU() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "RepAU");
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

    @Given("^Reporte acceso de usuario - Se carga web Visor e inicio sesion como administrador \"([^\"]*)\"$")
    public void ingreso_visor(int id) throws Throwable {
        try {
            Object[][] Parameters = LoadURL();
            Object[][] Parameters3 = GetTester();
            sUrl = Parameters[(id-1)][2].toString();
            sBrowser = Parameters[(id-1)][3].toString();
            user = Parameters[(id-1)][4].toString();
            pass = Parameters[(id-1)][5].toString();
            testername = Parameters3[(0)][1].toString();
            featureName = Parameters3[(0)][2].toString();

            driver = hook.browser(sBrowser, sUrl);
            System.out.println("escenario: " + escenario);
            p001loginPage.dologin(id, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al loguear", e);
        }
    }
    @When("^Doy click al card de Descarga de reporte ubicado en el home \"([^\"]*)\"$")
    public void selecciono_card_reporte_usuario(int id) throws Throwable {
        try {
            Object[][] Parameters2 = GetReporteAU();
            opcion = Parameters2[(id-1)][1].toString();
            p002ReporteAccesoUsuarioPage.selecionarCardReporteAccesoUsuario(id, opcion);
        } catch (Exception e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al buscar numero o al abrir formulario de tipificacion", e);
        }
    }

    @Then("^Ingreso las fechas de inicio y fin y doy click al boton DESCARGAR \"([^\"]*)\"$")
    public void completo_formulario(int id) throws Throwable {
        try {
            Object[][] Parameters2 = GetReporteAU();
            fchInicio = Parameters2[(id-1)][2].toString();
            fchFin = Parameters2[(id-1)][3].toString();
            p003DescargarReporteAccesoUsuarioPage.descargarReporteAccesoUsuario(id, fchInicio, fchFin);
            hook.closeBrowser(basepage.handleDriver(), testername, id, "EXITO");
        } catch (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al seleccionar opcion o insertar dato en form de tipificacion", e);
        }
    }
}
