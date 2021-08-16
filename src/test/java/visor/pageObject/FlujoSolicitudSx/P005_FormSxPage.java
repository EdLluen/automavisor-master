package visor.pageObject.FlujoSolicitudSx;

import visor.helpers.BasePage;

import java.util.ArrayList;

public class P005_FormSxPage extends BasePage {

    String loader = "body > visor-root > visor-loader > div > div";
    String cb_channel_root = "#canal";
    String cb_reason_root = "#motivoSuspensionCMS";
    String tb_name_root = "nombreSolicitante";
    String tb_lastName_root = "apellidoSolicitante";
    String cb_document_root = "#tipoDocumento";
    String tb_document_root = "numberDocumento";
    String dp_period_root = "/html/body/modal-container/div/div/visor-add-suspension/div/div[2]/form/div[6]/div[1]/visor-datepicker/mat-form-field/div/div[1]/div[4]/mat-datepicker-toggle/button/div[1]";
    String dp_date = "//*[@id=\"mat-datepicker-0\"]/mat-calendar-header/div/div/button[1]/div[1]";
    String tb_days_root = "body > modal-container > div > div > visor-add-suspension > div > div.modal-body > form > div:nth-child(6) > div:nth-child(2) > tdp-st-input-text";
    String tb_days = "div > div > div > input";
    String btn_save_root = "btguardar";
    String btn_confirm_root = "body > modal-container:nth-child(11) > div > div > ng-component > div > div > div.btn-rigth > tdp-st-button";
    String btn_confirm = "button";
    String txt_sxCode = "//p[contains(text(),'Se ha generado la solicitud de suspensión con éxito:')]";
    String btn_validateData = "btn_confirm_modal";
    String txt_errorCode = "//p[contains(text(),'¡Atención!')]";

    public void formSx(int id, ArrayList<String> dataForm) throws Exception {
        System.out.println("P5");
        String valueChannel = "", valueReason = "", valueDocument = "";
        try{
            Thread.sleep(1000);
            switch (dataForm.get(0)) {
                case "Call": valueChannel = "1"; break;
                case "Presencial": valueChannel = "2"; break;
                case "Canales escritos": valueChannel = "3"; break;
                default: break;
            }
            switch (dataForm.get(2)) {
                case "SUSPENSIÓN APC": valueReason = "1"; break;
                case "SUSPENSIÓN LÍNEA EN BAJA": valueReason = "2"; break;
                default: break;
            }
            switch (dataForm.get(5)) {
                case "DNI": valueDocument = "1"; break;
                case "Carnet de Extranjería": valueDocument = "2"; break;
                case "Pasaporte": valueDocument = "3"; break;
                case "RUC": valueDocument = "4"; break;
                default: break;
            }
            doClickRootElementListCssSelectorClass("Selecciono la opcion " + dataForm.get(0) + " del campo Seleccione canal", cb_channel_root, valueChannel, false);
            doClickRootElementListCssSelectorClass("Selecciono la opcion " + dataForm.get(2) + " del campo Motivos de suspension", cb_reason_root, valueReason, false);
            doClickRootElementById("Click al campo Nombre del solicitante ", tb_name_root, tb_name_root, false);
            doAddTextFieldOutWait("Ingreso nombre del solicitante ", tb_name_root, dataForm.get(3), false);
            doClickRootElementById("Click al campo Apellido del solicitante ", tb_lastName_root, tb_lastName_root, false);
            doAddTextFieldOutWait("Ingreso apellido del solicitante ", tb_lastName_root, dataForm.get(4), false);
            doClickRootElementListCssSelectorClass("Selecciono la opcion " + dataForm.get(5) + " del campo Tipo de documento", cb_document_root, valueDocument, false);
            doClickRootElementById("Click al campo Digitos del documento ", tb_document_root, tb_document_root, false);
            doAddTextFieldOutWait("Ingreso numero de documento" , tb_document_root, dataForm.get(6), false);
            doclickByJS("Click datepicker Periodo ", dp_period_root, false);
            selectDate(dataForm.get(7), dp_date, true);
            insertTextRootCssSelector("Ingreso dias a suspender", tb_days_root, tb_days, dataForm.get(8), false);
            doClickRootElementById("Completar el formulario y dar click a la opcion GUARDAR", btn_save_root, btn_save_root, true);
            doClickRootElementByCssSelector("Confirmar la solicitud", btn_confirm_root, btn_confirm, true);
            waitInvisibilityLoader("Espero generacion de solicitud", loader, false);
            if (validateObjExistXpath(txt_sxCode)) {
                doClickRootElementById("Se actualiza el registro de suspensiones", btn_validateData, btn_validateData, true );
            } else if (validateObjExistXpath(txt_errorCode)) {
                doClickRootElementById("Error al enviar registro de suspension", btn_validateData, btn_validateData, true );
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Fin P5");
    }
}
