package visor.pageObject.FlujoNoNavegaMovil;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

public class P005_NoNavegaMovilPage extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();
    String img_loader ="body > visor-root > visor-loader > div > div";
    String dir_noNavega_root = "#noNavega > tdp-st-button";
    String btn_noNavega = "#btn_noNavega";
    String dir_opt1Yes = "yes-masives-breakdown-init";
    String dir_opt1No = "no-masives-breakdown-init";
    String dir_opt2Yes = "yes-provious-cases-init";
    String dir_opt2No = "no-previous-cases-init";
    String dir_opt3Yes = "yes-provious-cases-init";
    String dir_opt3No = "#noNavega-init > tdp-st-button";
    String btn_opt1Yes = "yes-masives-breakdown-init";
    String btn_opt1No = "no-masives-breakdown-init";
    String btn_opt2Yes = "yes-provious-cases-init";
    String btn_opt2No = "no-previous-cases-init";
    String btn_opt3Yes = "yes-provious-cases-init";
    String btn_opt3No = "#btn_noNavega-init";
    String dir = "noNavega";
    String flux;


    public Boolean doFlux(int id, String opt1, String opt2, String opt3){
        System.out.println("P5");
        boolean btnExist = false;
        try {
            waitInvisibilityLoader("Espero loader ", img_loader,false);
            Thread.sleep(1000);
            doClickRootElementByCssSelector("Click al botón NO NAVEGA", dir_noNavega_root, btn_noNavega, true);
            Thread.sleep(1000);
            System.out.println("antes de arbol movil");
            Thread.sleep(1000);
            if (opt1.equals("Si")) {
                doClickRootElementById("Click a opción "+opt1+" del paso 1", dir_opt1Yes, btn_opt1Yes, true);
                System.out.println("Paso 1 - Si");
            } else if (opt1.equals("No")) {
                Thread.sleep(500);
                doClickRootElementById("Click a opción "+opt1+" del paso 1", dir_opt1No, btn_opt1No, true);
                System.out.println("Paso 1 - No");
                if (opt2.equals("Si")) {
                    Thread.sleep(500);
                    doClickRootElementById("Click a opción "+opt2+" del paso 2", dir_opt2Yes, btn_opt2Yes, true);
                    System.out.println("Paso 2 - Si");
                } else if (opt2.equals("No")) {
                    Thread.sleep(500);
                    doClickRootElementById("Click a opción "+opt2+" del paso 2", dir_opt2No, btn_opt2No, true);
                    System.out.println("Paso 2 - No");
                    if (opt3.equals("Si")) {
                        Thread.sleep(500);
                        doClickRootElementById("Click a opción "+opt3+" del paso 3", dir_opt3Yes, btn_opt3Yes, true);
                        System.out.println("Paso 3 - Si");
                    } else if (opt3.equals("No")) {
                        Thread.sleep(500);
                        doClickRootElementByCssSelector("Click a opción "+opt3+" del paso 3", dir_opt3No, btn_opt3No, true);
                        System.out.println("Paso 3 - No");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fin P5");
        return btnExist;
    }

}
