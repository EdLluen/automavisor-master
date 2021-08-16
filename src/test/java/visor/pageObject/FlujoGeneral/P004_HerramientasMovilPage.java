package visor.pageObject.FlujoGeneral;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

public class P004_HerramientasMovilPage extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();
    String btn_solicitud = "//*[text()='";
    String img_loader ="body > visor-root > visor-loader > div > div";
    String tab_voz_root = "#layout > visor-container-tools > visor-tab > div > div:nth-child(3) > tdp-st-button";
    String tab_voz = "#btn_VZ";
    String[] newTab = null;

    public void problemType(int id, String tipoProblema, String tab) throws Exception {
        System.out.println("P4");
        try {
            validateObject("Validar existencia del card " + tipoProblema, btn_solicitud + tipoProblema + "']", "DISPLAYED", false);
            doclickByJS("Click al card " + tipoProblema, btn_solicitud + tipoProblema + "']", true);
            waitInvisibilityLoader("Espero loader ", img_loader, false);
            Thread.sleep(1000);
            newTab = evaluateTab("movil", tab);
            doClickRootElementByCssSelector("Click a la pestaña " + tab + " ", newTab[0], newTab[1], true);
            Thread.sleep(1000);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Fin P4");
    }
}
