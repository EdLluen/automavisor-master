package visor.pageObject.FlujoSolicitudSx;

import visor.helpers.BasePage;

public class P004_ValidacionDataPage extends BasePage {

    String loader = "body > visor-root > visor-loader > div > div";
    String txt_Migrated = "//h3[text()='Error consulta Migrado a + Simple']";
    String txt_Upgrade = "//p[text()='Se actualizaron los datos de la suspensión']";
    String txt_pendingRequest = "//p[contains(text(),'Solicitud en vuelo,')]";
    String txt_disponibleDays = "//p[contains(text(),'No tiene días suficientes para registrar una solicitud,')]";
    String card_Problem = "//*[text()='";
    String btn_validateData = "btn_confirm_modal";
    String btn_suspend_root = "#layout > visor-main-suspension > div.cont-button > tdp-st-button:nth-child(1)";
    String btn_suspend = "button";

    public boolean validateData(int id, String problem) throws Exception {
        boolean validation = false;
        System.out.println("P4");
        try{
            Thread.sleep(1000);
            validateObject("Validar existencia del card " + problem, card_Problem + problem + "']", "DISPLAYED", false);
            doclickByJS("Click al card " + problem, card_Problem + problem + "']", true);
            waitInvisibilityLoader("Loader", loader, false);
            if(validateObjExistXpath(txt_Migrated)) {
                System.out.println("dentro texto");
                validation = true;
                doClickRootElementById("Click al boton CONTINUAR", btn_validateData, btn_validateData, true);
                Thread.sleep(1000);
            } else {
                if (validateObjExistXpath(txt_Upgrade)) {
                    System.out.println("dentro upgrade");
                    doClickRootElementById("Se actualiza el registro de suspensiones", btn_validateData, btn_validateData, true );
                    Thread.sleep(500);
                }
                doClickRootElementByCssSelector("Click al boton SUSPENDER", btn_suspend_root, btn_suspend, true);
                waitInvisibilityLoader("Loader", loader, false);
                if (validateObjExistXpath(txt_pendingRequest)) {
                    validation = true;
                    System.out.println("dentro pending!");
                    doClickRootElementById("Se valida suspensiones en vuelo", btn_validateData, btn_validateData, true );
                } else if (validateObjExistXpath(txt_disponibleDays)) {
                    validation = true;
                    System.out.println("dentro disponble!");
                    doClickRootElementById("Se valida dias disponibles", btn_validateData, btn_validateData, true );
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Fin P4");
        return validation;
    }
}
