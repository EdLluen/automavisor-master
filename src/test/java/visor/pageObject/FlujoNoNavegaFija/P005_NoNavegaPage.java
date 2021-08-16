package visor.pageObject.FlujoNoNavegaFija;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

public class P005_NoNavegaPage extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();
    String img_loader ="body > visor-root > visor-loader > div > div";
    String dir_noNavega_root = "#noNavega > tdp-st-button";
    String btn_noNavega = "#btn_noNavega";
    String dir_Lentitud_root = "#lentitud > tdp-st-button";
    String btn_Lentitud = "#btn_lentitud";



    String dir = "noNavega";


    public Boolean doFlux(int id){
        System.out.println("P5");
        boolean btnExist = false;
        try {
            waitInvisibilityLoader("Espero loader ", img_loader,false);
            Thread.sleep(1000);
            doClickRootElementByCssSelector("Click al botón NO NAVEGA", dir_noNavega_root, btn_noNavega, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fin P5");
        return btnExist;
    }

    public Boolean doFluxLentitud(int id){
        System.out.println("P5");
        boolean btnExist = false;
        try {
            waitInvisibilityLoader("Espero loader ", img_loader,false);
            Thread.sleep(1000);
            doClickRootElementByCssSelector("Click al botón LENTITUD", dir_Lentitud_root, btn_Lentitud , true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fin P5");
        return btnExist;
    }


}
