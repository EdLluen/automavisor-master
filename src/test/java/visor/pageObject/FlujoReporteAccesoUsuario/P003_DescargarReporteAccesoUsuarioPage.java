package visor.pageObject.FlujoReporteAccesoUsuario;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

public class P003_DescargarReporteAccesoUsuarioPage extends BasePage{

    ScreenshotUtility utilitarios = new ScreenshotUtility();
    String img_loader ="body > visor-root > visor-loader > div > div";
    String dp_inicio = "//*[@id=\"content\"]/div[4]/form/div/div[1]/visor-datepicker/mat-form-field/div/div[1]/div[4]/mat-datepicker-toggle/button";
    String dp_fin = "//*[@id=\"content\"]/div[4]/form/div/div[2]/visor-datepicker/mat-form-field/div/div[1]/div[4]/mat-datepicker-toggle/button";
    String btn_date_inicio = "/html/body/div[1]/div[2]/div/mat-datepicker-content/mat-calendar/mat-calendar-header/div/div/button[1]";
    String btn_date_fin = "/html/body/div[1]/div[2]/div/mat-datepicker-content/mat-calendar/mat-calendar-header/div/div/button[1]";
    String btn_descargar_root = "btnGetListLoginAudit";
    String btn_descargar =  "btnGetListLoginAudit";
    String btn_error_root = "body > modal-container > div > div > div.modal-footer.text-center > div > tdp-st-button";
    String btn_error = "button";
    String txt_error = "//p[contains(text(),'No hay registros con los filtros empleados')]";

    public void descargarReporteAccesoUsuario(int id, String fchInicio, String fchFin) {
        System.out.println("P3");
        try{
            doclickByJS("Click datepicker inicio", dp_inicio, false);
            Thread.sleep(500);
            selectDate(fchInicio, btn_date_inicio, true);
            Thread.sleep(500);
            doclickByJS("Click datepicker fin", dp_fin, true);
            Thread.sleep(500);
            selectDate(fchFin, btn_date_fin, true);
            Thread.sleep(500);
            doClickRootElementById("Click botón descargar", btn_descargar_root, btn_descargar, true);
            waitInvisibilityLoader("Espero loader", img_loader, false);
            Thread.sleep(1000);
            if (validateObjExistXpath(txt_error)) {
                doClickRootElementByCssSelector("Error al descargar reporte", btn_error_root, btn_error, true);
            } else {
                Thread.sleep((1000));
                validateObjectId("Se descarga el reporte", btn_descargar_root, "DISPLAYED", true);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Fin P3");
    }

}
