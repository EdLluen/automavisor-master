package visor.pageObject.FlujoToolResetTotal;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

public class P005_ResetTotalPage extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();
    String img_loader ="body > visor-root > visor-loader > div > div";
    String card_dataReset = "//*[text()='";
    String btn_doReset_root = "body > modal-container > div > div > ng-component > div > div > div.btn-rigth > tdp-st-button";
    String btn_doReset = "button";
    String txt_error = "//p[contains(text(),'No se pudo realizar el reset de datos.')]";
    String txt_ok = "//h3[contains(text(),'¡Reset realizado!')]";

    public void dataTool(int id, String tool){
        System.out.println("P5");
        boolean btnExist = false;
        try {
            validateObject("Validar existencia del card " + tool, card_dataReset + tool + "']", "DISPLAYED", false);
            doclickByJS("Click al card " + tool, card_dataReset + tool + "']", true);
            Thread.sleep(1500);
            doClickRootElementByCssSelector("Click al boton REALIZAR RESET ", btn_doReset_root, btn_doReset, true);
            Thread.sleep(10000);
            waitInvisibilityLoader("Espero loader ", img_loader, false);
            if (validateObjExistXpath(txt_error)) {
                doScreenshot("Error al ejecutar tool de Reset de voz");
            } else if (validateObjExistXpath(txt_ok)){
                doScreenshot("Reset realizado");
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Fin P5");
    }

}
