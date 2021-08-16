package visor.pageObject.FlujoNoNavegaFija;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

public class P006_FinNoNavegaPage extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();
    String dir_root_next = "btn_siguiente_noNavega";
    String img_loader ="body > visor-root > visor-loader > div > div";
    String btn_next = "btn_siguiente_noNavega";
    String val_next = "//*[@id=\"btn_siguiente_noNavega\"]";
    String dir_root_noSol = "//*[@id=\"btn_noSoluciono_noNavega\"]";
    String dir_root_discard = "#layout > visor-diagnostico-uso > div > div.diagnostico-buttons > visor-container-btns > div > tdp-st-button";
    String btn_discard = "button";
    String dir_root_regAvg = "//*[@id=\"layout\"]/visor-diagnostico-uso/div/div[2]/visor-container-btns/div/tdp-st-button[1]";
    String btn_nav = "#lentitud";
    String reason1;

    public void endFlux(int id,String ruta2) {
        System.out.println("P6");
        boolean btnExist = false;
        //String reason = "";
        try {
            Thread.sleep(2000);
            waitInvisibilityLoader("Espero finalización del loader", img_loader, true);
            Thread.sleep(1000);
            if (validateObjExistId(dir_root_next)) {
                System.out.println("next");
                validateObject("Click al botón SIGUIENTE", val_next, "DISPLAYED", true);
                doClickRootElementById("Click al botón SIGUIENTE", dir_root_next, btn_next, false);
                Thread.sleep(500);
                    /*reason = getlabelByXpath("Reason: ", "//*[@id=\"layout\"]/visor-diagnostico-uso/div/visor-contenedor-mensajes/div/div/div/div");
                    System.out.println("Reason: "+reason);*/
                validateObject("Fin flujo no navega", dir_root_noSol, "DISPLAYED", true);
                Thread.sleep(1000);
            } else if (validateObjExistCssSelector(dir_root_discard)) {
                System.out.println("discard");
                    /*reason = getlabelByXpath("Reason: ", "//*[@id=\"layout\"]/visor-diagnostico-uso/div/visor-contenedor-mensajes/div/div/div/div/span[3]");
                    System.out.println("Reason: "+reason);*/
                doClickRootElementByCssSelector("Click al botón DESCARTES MANUALES ", dir_root_discard, btn_discard, true);
                Thread.sleep(500);
                validateObject("Fin flujo no navega", dir_root_regAvg, "DISPLAYED", true);
                Thread.sleep(1000);
            }
            System.out.println("sali del loop");
        } catch(Throwable e){
            e.printStackTrace();
        }
        System.out.println("Fin P6");
    }
}
