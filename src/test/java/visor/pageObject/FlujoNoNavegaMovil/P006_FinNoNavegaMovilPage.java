package visor.pageObject.FlujoNoNavegaMovil;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

public class P006_FinNoNavegaMovilPage extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();
    String dir_root_final = "over-masives-breakdown-init";
    String btn_final = "over-masives-breakdown-init";
    String dir_root_next = "btn_siguiente_noNavega";
    String img_loader ="body > visor-root > visor-loader > div > div";
    String btn_next = "btn_siguiente_noNavega";
    String val_next = "//*[@id=\"btn_siguiente_noNavega\"]";
    String dir_root_finStep2 = "#layout > visor-data-problem > visor-diagnostico-uso > div > div.diagnostico-buttons > visor-data-problem-buttons > div > tdp-st-button";
    String dir_root_discard = "#layout > visor-diagnostico-uso > div > div.diagnostico-buttons > visor-container-btns > div > tdp-st-button";
    String btn_discard = "button";
    String dir_root_regAvg = "//*[@id=\"layout\"]/visor-diagnostico-uso/div/div[2]/visor-container-btns/div/tdp-st-button[1]";
    String btn_nav = "#btn_siguiente_noNavega";
    String reason1;

    public void endFlux(int id) {
        System.out.println("P6");
        try {
            if (validateObjExistId(dir_root_final)){
                System.out.println("final");
                validateObjectId("Fin del árbol de decisiones", dir_root_final, "DISPLAYED", true);
            } else {
                Thread.sleep(500);
                waitInvisibilityLoader("Espero finalización del loader", img_loader, true);
            }
            if (validateObjExistId(dir_root_next)) {
                System.out.println("next");
                validateObject("Click al botón SIGUIENTE", val_next, "DISPLAYED", false);
                doClickRootElementById("Click al botón SIGUIENTE", dir_root_next, btn_next, true);
                    /*reason = getlabelByXpath("Reason: ", "//*[@id=\"layout\"]/visor-diagnostico-uso/div/visor-contenedor-mensajes/div/div/div/div");
                    System.out.println("Reason: "+reason);*/
                validateObjectCssSelector("Validar informacion step 3", dir_root_finStep2, "DISPLAYED", true);
                System.out.println("Existe");
                Thread.sleep(500);
            } else if (validateObjExistCssSelector(dir_root_discard)) {
                System.out.println("discard");
                    /*reason = getlabelByXpath("Reason: ", "//*[@id=\"layout\"]/visor-diagnostico-uso/div/visor-contenedor-mensajes/div/div/div/div/span[3]");
                    System.out.println("Reason: "+reason);*/
                doClickRootElementByCssSelector("Click al botón DESCARTES MANUALES ", dir_root_discard, btn_discard, true);
                Thread.sleep(500);
                validateObject("Fin flujo no navega", dir_root_regAvg, "DISPLAYED", true);
                Thread.sleep(500);
            }
            System.out.println("sali del loop");
        } catch(Throwable e){
            e.printStackTrace();
        }
        System.out.println("Fin P6");
    }
}
