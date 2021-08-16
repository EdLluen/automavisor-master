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
import visor.pageObject.FlujoReporteAccesoUsuario.P002_CardReporteAccesoUsuarioPage;
import visor.pageObject.FlujoReporteAccesoUsuario.P003_DescargarReporteAccesoUsuarioPage;
import visor.pageObject.FlujoSolicitudSx.P004_ValidacionDataPage;
import visor.pageObject.FlujoSolicitudSx.P005_FormSxPage;
import visor.utility.ExcelUtils;

import java.util.ArrayList;

public class T008_FlujoSolicitudSx {
    public WebDriver driver;
    private Hook hook = new Hook();
    BasePage basepage = new BasePage();
    P001_LoginPage p001loginPage = new P001_LoginPage();
    P002_HomePage p002HomePage = new P002_HomePage();
    P003_ConsultasPage p003ConsultasPage = new P003_ConsultasPage();
    P004_ValidacionDataPage p004ValidacionDataPage = new P004_ValidacionDataPage();
    P005_FormSxPage p005FormSxPage = new P005_FormSxPage();

    public String featureName;
    public String testername;

    public String sUrl, sBrowser, user, pass, escenario, token;
    public String number, option;
    public boolean validation;
    public String problem, channel, holder, reason, name, lastName, document, documentNumber, date, days;

    public Object[][] LoadURL() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Login");
        return (testObjArray);
    }

    public Object[][]GetSx() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Sx");
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

    @Given("^Solicitud de Sx - Se carga web Visor e inicio sesion como administrador \"([^\"]*)\"$")
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
    @When("^Busco el numero fijo y selecciono opcion Pedidos y solicitudes \"([^\"]*)\"$")
    public void selecciono_card_pedidos_y_solicitudes(int id) throws Throwable {
        try {
            Object[][] Parameters = GetSx();
            number = Parameters[(id-1)][1].toString();
            option = Parameters[(id-1)][2].toString();
            p002HomePage.buscar_Numero(id, number);
            p003ConsultasPage.tipoConsulta(id, option);
        } catch (Exception e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al buscar numero o al abrir card de pedidos y solicitudes", e);
        }
    }

    @And("^Selecciono Registro de suspension y reconexion, verifico la información mostrada y doy click al botón SUSPENDER \"([^\"]*)\"$")
    public void verifico_data_click_boton_suspender(int id) throws Throwable {
        try {
            Object[][] Parameters = GetSx();
            problem = Parameters[(id-1)][3].toString();
            validation = p004ValidacionDataPage.validateData(id, problem);
        } catch (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al seleccionar opcion o insertar dato en form de tipificacion", e);
        }
    }

    @Then("^Completo el formulario y envio la solicitud de suspension fija \"([^\"]*)\"$")
    public void completo_formulario(int id) throws Throwable {
        try {
            Object[][] Parameters = GetSx();
            if (!validation) {
                channel = Parameters[(id-1)][4].toString();
                holder = Parameters[(id-1)][5].toString();
                reason = Parameters[(id-1)][6].toString();
                name = Parameters[(id-1)][7].toString();
                lastName = Parameters[(id-1)][8].toString();
                document = Parameters[(id-1)][9].toString();
                documentNumber = Parameters[(id-1)][10].toString();
                date = Parameters[(id-1)][11].toString();
                days = Parameters[(id-1)][12].toString();

                ArrayList<String> dataForm = new ArrayList<>();
                dataForm.add(channel);
                dataForm.add(holder);
                dataForm.add(reason);
                dataForm.add(name);
                dataForm.add(lastName);
                dataForm.add(document);
                dataForm.add(documentNumber);
                dataForm.add(date);
                dataForm.add(days);
                p005FormSxPage.formSx(id, dataForm);
            }
            hook.closeBrowser(basepage.handleDriver(), testername, id, "EXITO");
        } catch (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al seleccionar opcion o insertar dato en form de tipificacion", e);
        }
    }
}
