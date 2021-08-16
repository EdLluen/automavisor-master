package visor.pageObject.FlujoReporteSxRx;

import visor.helpers.BasePage;

import java.io.IOException;

public class P004_ReporteSxRxPage extends BasePage {
    String btn_solicitud = "//*[text()='";
    String img_loader ="/html/body/visor-root/visor-loader/div/div";
    String btn_mes_root_css ="#layout > visor-form-report-suspension > form > div:nth-child(3) > div:nth-child(1) > tdp-st-select";
    String btn_mes_css ="#opt_1";
    String btn_año_root_css ="#layout > visor-form-report-suspension > form > div:nth-child(3) > div:nth-child(2) > tdp-st-select";
    String btn_año_css ="#opt_1";
    String btn_tipo_root_css = "#layout > visor-form-report-suspension > form > div:nth-child(3) > div:nth-child(3) > tdp-st-select";
    String btn_tipo_css = "#opt_1";
    String btn_descargar_root ="#layout > visor-form-report-suspension > form > div:nth-child(5) > div > tdp-st-button";
    String btn_descargar ="button";
    String btn_root_error ="body > modal-container > div > div > div.modal-footer.text-center > div > tdp-st-button";
    String btn_error ="button";

    public void tipoOperacion(int id, String opcionPedido2) throws Exception {
        System.out.println("P4");
        String ultimoCodCorrelativo=null;
        try{

            doclickByJS("Registro_"+id+"_Click en boton Reporte de suspensión y reconexión", btn_solicitud+opcionPedido2+"']",true);
            Thread.sleep(500);
            waitInvisibility("Registro_"+id+"_Invisibility loader", img_loader, false);
            doClickRootElementListCssSelectorClass("Seleciono mes ", btn_mes_root_css, btn_mes_css, false);
            Thread.sleep(500);
            doClickRootElementListCssSelectorClass("Seleciono año ", btn_año_root_css, btn_año_css, false);
            Thread.sleep(500);
            doClickRootElementListCssSelectorClass("Seleciono tipo ", btn_tipo_root_css, btn_tipo_css, false);
            Thread.sleep(500);
            doClickRootElementByCssSelector("Seleccionar mes, año, tipo y dar click al botón DESCARGAR ", btn_descargar_root, btn_descargar, true);
            try {
                while (validateObjExistCssSelector(btn_root_error)){
                    Thread.sleep((1500));
                    doClickRootElementByCssSelector("Click botón error", btn_root_error, btn_error, true);
                    Thread.sleep((1500));
                    validateObject("Despues de dar clic al botón REGRESAR ",btn_descargar_root, "DISPLAYED", true);
                }
            }catch (Throwable e) {
                e.printStackTrace();
            }
            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
