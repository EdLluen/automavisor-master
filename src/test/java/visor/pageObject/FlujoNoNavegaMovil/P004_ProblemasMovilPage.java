package visor.pageObject.FlujoNoNavegaMovil;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

public class P004_ProblemasMovilPage extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();
    String btn_solicitud = "//*[text()='";
    String img_loader ="body > visor-root > visor-loader > div > div";
    String dir_newFlux_root = "btn-nuevo-diagnostico";
    String btn_newFlux = "btn-nuevo-diagnostico";
    String dir_save_root = "btnSaveFailureRegister";
    String btn_save = "btnSaveFailureRegister";
    String dir_name_root = "nombres";
    String txt_name = "nombres";
    String dir_lastname_root = "apellidos";
    String txt_lastname = "apellidos";
    String dir_phone_root = "numeroTelefono";
    String txt_phone = "numeroTelefono";
    String txt_frmName = "\"//input[contains(@placeholder,'Nombres y apellidos')]\"";
    String txt_frmPhone = "\"//input[contains(@placeholder,'Password')]\"Teléfono";

    public void tipoProblema(int id, String tipoProblema, String frmName, String frmPhone) throws Exception {
        String nombre = "";
        String apellido = "";
        System.out.println("P4");
        //String ultimoCodCorrelativo=null;
        try {
            validateObject("Validar existencia del card " + tipoProblema, btn_solicitud + tipoProblema + "']", "DISPLAYED", false);
            doclickByJS("Click al card " + tipoProblema, btn_solicitud + tipoProblema + "']", true);
            waitInvisibilityLoader("Espero loader ", img_loader, false);
            Thread.sleep(500);
            if (validateObjExistId(dir_newFlux_root)) {
                System.out.println("Last diagnostic");
                validateObjectName("Se valida existencia del botón DIAGNÓSTICO NUEVO", btn_newFlux, "DISPLAYED", false);
                doClickRootElementByName("Click al botón DIAGNÓSTICO NUEVO", dir_newFlux_root, btn_newFlux, true);
                Thread.sleep((500));
            }
            System.out.println("name");

            String[] parts = frmName.split(" ");
            if (parts.length>=1)   nombre=parts[0];
            if (parts.length>=2)   apellido=parts[1];

            doClickRootElementByName("Click al campo Nombre", dir_name_root, txt_name, false);
            doAddTextFieldOutWait("Ingreso nombre del cliente", txt_name, nombre, false);
            Thread.sleep((500));
            doClickRootElementByName("Click al campo apellidos", dir_lastname_root, txt_lastname, false);
            doAddTextFieldOutWait("Ingreso apellido del cliente", txt_lastname, apellido, false);
            Thread.sleep((500));

            Thread.sleep((500));
            System.out.println("phone");
            doClickRootElementByName("Click al campo Teléfono", dir_phone_root, txt_phone, false);
            doAddTextFieldOutWait("Ingreso teléfono", txt_phone, frmPhone, false);
            Thread.sleep((500));
            doClickRootElementById("Completo el formulario y guardo", dir_save_root, btn_save, true);
            waitInvisibilityLoader("Espero loader", img_loader, false);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Fin P4");
    }
}
