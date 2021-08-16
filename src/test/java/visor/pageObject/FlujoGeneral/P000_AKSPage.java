package visor.pageObject.FlujoGeneral;

import visor.helpers.BasePage;

public class P000_AKSPage extends BasePage {

    String btn_Configure = "//*[@id=\"details-button\"]";
    String link = "//*[@id=\"proceed-link\"]";

    public void getAccess() throws Exception {
        try {
            System.out.println("antes de validar config");
            doclickByJS("Click al botón de permiso", btn_Configure, false);
            doclickByJS("Click a la url", link, false);
            System.out.println("di click al permiso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
