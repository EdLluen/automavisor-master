package visor.pageObject.FlujoReporteCxR;

import visor.helpers.BasePage;

public class P004_ReporteCxRPage extends BasePage {

    String btn_solicitud = "//*[text()='";
    String img_loader ="body > visor-root > visor-loader > div > div";
    String cb_month_root = "#layout > visor-form-report-robbery-cut > form > div.row > div:nth-child(1) > tdp-st-select";
    String cb_month = "#opt_";
    String cb_year_root = "#layout > visor-form-report-robbery-cut > form > div.row > div:nth-child(2) > tdp-st-select";
    String cb_year = "#opt_";
    String btn_download_root = "#layout > visor-form-report-robbery-cut > form > div.row > div:nth-child(3) > tdp-st-button";
    String btn_download = "button";
    String btn_error_root = "body > modal-container > div > div > div.modal-footer.text-center > div > tdp-st-button";
    String btn_error = "button";
    String txt_error = "//p[contains(text(),'No hay registros con los filtros empleados')]";

    public void tipoOperacion(int id, String opcionPedido2, String month, String year) throws Exception {
        System.out.println("P4");
        try {
            System.out.println("mes: " + month + " | año: " + year);
            doclickByJS("Click en boton Reporte de corte por robo", btn_solicitud + opcionPedido2 + "']", true);
            Thread.sleep(1000);
            System.out.println("mes: " + cb_month + evaluateMonth(month));
            doClickRootElementListCssSelectorConcat("Selecciono mes " + month, cb_month_root, cb_month + evaluateMonth(month), false);
            Thread.sleep(1000);
            doClickRootElementListCssSelectorConcat("Selecciono año " + month, cb_year_root, cb_year + evaluateYear(year), false);
            Thread.sleep(1000);
            doClickRootElementByCssSelector("Seleccionar mes, año y dar click al botón Descargar", btn_download_root, btn_download, true);
            waitInvisibilityLoader("Espero loader", img_loader, false);
            Thread.sleep(1000);
            if (validateObjExistXpath(txt_error)) {
                doClickRootElementByCssSelector("Error al descargar reporte", btn_error_root, btn_error, true);
            } else {
                Thread.sleep((1000));
                validateObjectCssSelector("Se descarga el reporte", btn_download_root, "DISPLAYED", true);
            }
            Thread.sleep(1000);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
