package visor.pageObject.FlujoReporteAccesoUsuario;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

public class P002_CardReporteAccesoUsuarioPage extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();


    public void selecionarCardReporteAccesoUsuario(int id, String opcion) {
        System.out.println("P2");
        try{
            validateObject("Validar existencia del card "+opcion, evaluateCardHome(opcion), "DISPLAYED", false);
            System.out.println("validado");
            doclickByJS("Click al card "+opcion, evaluateCardHome(opcion), true);
            Thread.sleep(500);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Fin P2");
    }

}
