package visor.pageObject.FlujoGeneral;

import visor.helpers.BasePage;

import java.awt.*;
import java.io.IOException;


public class P002_HomePage extends BasePage {

    String txt_buscarTelefono="//input[contains(@placeholder,'Buscar')]";
    String img_loader ="body > visor-root > visor-loader > div > div";
    String btn_Buscar="//*[@id=\"contenedor-buscar\"]/visor-search-form/form/visor-search-bar/div/button";
    //String btn_root_error = "body > modal-container > div > div > div.modal-footer.text-center > div > tdp-st-button";
    String btn_root_error = "#btn_confirm_modal";
    //String btn_error = "button";
    String btn_error = "#btn_confirm_modal";

    String campo_busqueda = "//*[@id=\"contenedor-buscar\"]/visor-search-form/form/visor-search-bar/div/input";

    String num_error = "13013609";

    public void buscar_Numero(int id, String numero) throws Exception {
        System.out.println("P2");
        try{
            Thread.sleep(1000);
            validateObject("Validar existencia de boton Buscar",btn_Buscar,"DISPLAYED", false);
            doclickByJS("Click en el campo de busqueda",campo_busqueda,false);
            doAddTextField("Ingreso el número de servicio",campo_busqueda, numero,false);
            Thread.sleep(2000);
            doclickByJS("Ingreso el número de servicio y realizo la búsqueda",btn_Buscar,true);
            waitInvisibilityLoader("Espero loader", img_loader, false);
            try{
                while (validateObjExistCssSelector(btn_root_error)){
                    Thread.sleep((1500));
                    doClickRootElementByCssSelector("Click botón error", btn_root_error, btn_error, false);
                    Thread.sleep((1500));
                    doclickByJS("Click en buscar",btn_Buscar,false);
                    waitInvisibilityLoader("Espero loader", img_loader, false );
                    Thread.sleep(2000);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fin P2");
    }
}
