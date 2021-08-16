package visor.pageObject.FlujoGeneral;

import visor.utility.ScreenshotUtility;
import visor.helpers.BasePage;

import java.awt.*;
import java.io.IOException;

public class P003_ConsultasPage  extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();
    String dir_exit_root = "close-modal-message";
    String btn_exit = "close-modal-message";
    String btn_tipoConsulta = "//*[text()='";
    String img_loader ="body > visor-root > visor-loader > div > div";
    String btn_close_root = "#principal > div:nth-child(1) > visor-nav-bar > div > mat-toolbar > div:nth-child(3) > visor-typing > tdp-st-button";
    String btn_close = "button";
    String btn_doInteraction_root = "body > modal-container > div > div > visor-typify-decision > div.modal-footer.content > tdp-st-button:nth-child(1)";
    String btn_doInteraction = "button";

    public void tipoConsulta(int id, String tipoConsulta) {
        System.out.println("P3");
        try{
            Thread.sleep(1000);
            if (validateObjExistId(dir_exit_root)) {
                Thread.sleep(1000);
                doClickRootElementById("Click al botón SALIR", dir_exit_root, btn_exit, true);
                Thread.sleep(500);
            }
            if (tipoConsulta.equals("CIERRA ATENCIÓN")){
                validateObjExistCssSelector(btn_close_root);
                doClickRootElementByCssSelector("Click opcion "+tipoConsulta, btn_close_root, btn_close, true);
                validateObjExistCssSelector(btn_doInteraction_root);
                doClickRootElementByCssSelector("Click opción HACER TIPIFICACION", btn_doInteraction_root, btn_doInteraction, true);
            } else {
                validateObject("Validar existencia de card "+tipoConsulta,btn_tipoConsulta+tipoConsulta+"']","DISPLAYED", false);
                doclickByJS("Click al card "+tipoConsulta, btn_tipoConsulta+tipoConsulta+"']",true);
            }
            waitInvisibilityLoader("Espero loader", img_loader, false );
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Fin P3");
    }

}
