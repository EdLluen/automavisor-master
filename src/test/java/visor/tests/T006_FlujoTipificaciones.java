package visor.tests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import visor.helpers.BasePage;
import visor.helpers.Hook;
import visor.pageObject.FlujoGeneral.P000_AKSPage;
import visor.pageObject.FlujoGeneral.P001_LoginPage;
import visor.pageObject.FlujoGeneral.P002_HomePage;
import visor.pageObject.FlujoGeneral.P003_ConsultasPage;
import visor.pageObject.FlujoTipificaciones.P004_FormTipificacion;
import visor.utility.ExcelUtils;
import visor.utility.ObjectMap;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class T006_FlujoTipificaciones {
    public WebDriver driver;
    private Hook hook = new Hook();
    BasePage basepage = new BasePage();
    P001_LoginPage p001loginPage = new P001_LoginPage();
    P002_HomePage p002HomePage = new P002_HomePage();
    P003_ConsultasPage p003ConsultasPage = new P003_ConsultasPage();
    P004_FormTipificacion p004FormTipificacion = new P004_FormTipificacion();

    public String featureName;
    public String testername;

    public String sAks, sUrl, sBrowser, user, pass, escenario, token;
    public String number, opcion1;
    public String servicio, plataforma, ani, ticket, titular, usuario, motivo, submotivo, solucion, observacion;


    public Object[][] LoadURL() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Login");
        return (testObjArray);
    }

    public Object[][]GetTipificacion() throws Exception {
        Object[][] testObjArray = null;
        testObjArray = ExcelUtils.getTableArray("./src/test/resources/data/TestData.xlsx", "Tipificacion");
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

    @Given("^Tipificacion - Se carga web Visor e inicio sesion como administrador \"([^\"]*)\"$")
    public void ingreso_visor(int id) throws Throwable {
        try {
            Object[][] Parameters = LoadURL();
            Object[][] Parameters3 = GetTester();
            sAks = Parameters[(id)][1].toString();
            sUrl = Parameters[(id-1)][2].toString();
            sBrowser = Parameters[(id-1)][3].toString();
            user = Parameters[(id-1)][4].toString();
            pass = Parameters[(id-1)][5].toString();
            testername = Parameters3[(0)][1].toString();
            featureName = Parameters3[(0)][2].toString();

            driver = hook.browser(sBrowser, sUrl);
            hook.sessionStorage();
            System.out.println("escenario: " + escenario);
            p001loginPage.dologin(id, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al loguear", e);
        }
    }
    @When("^Busco el numero, doy click al boton CIERRA ATENCION y al boton HACER TIPIFICACION \"([^\"]*)\"$")
    public void busco_numero_opcion_cierra_atencion(int id) throws Throwable {
        try {
            Object[][] Parameters2 = GetTipificacion();
            number = Parameters2[(id-1)][1].toString();
            opcion1 = Parameters2[(id-1)][2].toString();

            p002HomePage.buscar_Numero(id, number);
            p003ConsultasPage.tipoConsulta(id, opcion1);
        } catch (Exception e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al buscar numero o al abrir formulario de tipificacion", e);
        }
    }

    @Then("^Completo el formulario y envio el registro de tipificacion \"([^\"]*)\"$")
    public void completo_formulario(int id) throws Throwable {
        try {
            Object[][] Parameters2 = GetTipificacion();
            servicio = Parameters2[(id-1)][4].toString();
            plataforma = Parameters2[(id-1)][5].toString();
            ani = Parameters2[(id-1)][6].toString();
            ticket = Parameters2[(id-1)][7].toString();
            titular = Parameters2[(id-1)][8].toString();
            usuario = Parameters2[(id-1)][9].toString();
            motivo = Parameters2[(id-1)][10].toString();
            submotivo = Parameters2[(id-1)][11].toString();
            solucion = Parameters2[(id-1)][12].toString();
            observacion = Parameters2[(id-1)][13].toString();

            ArrayList<String> dataForm = new ArrayList<>();
            dataForm.add(servicio);
            dataForm.add(plataforma);
            dataForm.add(ani);
            dataForm.add(ticket);
            dataForm.add(titular);
            dataForm.add(usuario);
            dataForm.add(motivo);
            dataForm.add(submotivo);
            dataForm.add(solucion);
            dataForm.add(observacion);
            p004FormTipificacion.doFormInteraction(id, dataForm);
            Thread.sleep(1000);
            hook.closeBrowser(basepage.handleDriver(), testername, id, "EXITO");
        } catch (AssertionError e) {
            e.printStackTrace();
            hook.closeBrowser(basepage.handleDriver(), testername, id, "FALLO");
            Assert.fail("Fallo al seleccionar opcion o insertar dato en form de tipificacion", e);
        }
    }
}
