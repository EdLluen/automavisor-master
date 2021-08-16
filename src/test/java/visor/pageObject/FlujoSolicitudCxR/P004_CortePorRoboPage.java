package visor.pageObject.FlujoSolicitudCxR;

import visor.helpers.BasePage;
import visor.utility.ScreenshotUtility;

import java.io.IOException;

public class P004_CortePorRoboPage extends BasePage {
    //ScreenshotUtility utilitarios = new ScreenshotUtility();
    String btn_solicitud = "//*[text()='";
    String img_loader ="/html/body/visor-root/visor-loader/div/div";
    String dir_new_root = "#buscar > tdp-st-button";
    String btn_new = "button";
    String dir_listChannel_root = "motivoCorte";
    String list_Channel = "motivoCorte";
    String channel = "";
    String dir_ani_root = "/html/body/modal-container/div/div/visor-new-robbery-cut/div[2]/form/div[2]/div[2]/tdp-st-input-text";
    String txt_ani = "/html/body/modal-container/div/div/visor-new-robbery-cut/div[2]/form/div[2]/div[2]/tdp-st-input-text//div/div/div[1]/input";
    String dir_listImei_root = "body > modal-container > div > div > visor-new-robbery-cut > div.modal-body > form > div:nth-child(3) > div:nth-child(2) > tdp-st-select";
    String list_Imei = "#equipment_0";
    String txt_name = "firstNameApplicant";
    String txt_lastName = "lastNameApplicant";
    String dir_listDocumentType_root = "body > modal-container > div > div > visor-new-robbery-cut > div.modal-body > form > div:nth-child(5) > div:nth-child(1) > tdp-st-select";
    String list_documentType = "#documentTypeApplicant_";
    String txt_document = "documentNumberApplicant";
    String dir_listNotifyType_root = "body > modal-container > div > div > visor-new-robbery-cut > div.modal-body > form > div:nth-child(6) > div:nth-child(1) > tdp-st-select";
    String list_notifyType = "#contactChanel_";
    String txt_notify = "notificationChanel";
    String not = "//*[@id=\"documentTypeApplicant_Carnet de Extranjería\"]";

    public void tipoProblema(int id, String tipoProblema, String frmChannel, String frmANI, String frmHolder ,String frmName, String frmLastName, String frmDocumentType, String frmDocument, String frmNotifyType, String frmNotify) throws Exception {
        System.out.println("P4");
        //String ultimoCodCorrelativo=null;
        try{
            Thread.sleep(4500);
            validateObject("Validar existencia del card "+tipoProblema,btn_solicitud+tipoProblema+"']","DISPLAYED", false);
            doclickByJS("Click al card "+tipoProblema, btn_solicitud+tipoProblema+"']",true);
            System.out.println("antes de nueva solicitud");
            waitInvisibility("Invisibility loader ", img_loader, false );
            Thread.sleep(500);
            doClickRootElementByCssSelector("Click al botón NUEVA SOLICITUD", dir_new_root, btn_new, true);
            Thread.sleep(1000);
            //doClickRootElementByName("Click a la opcion ");
            System.out.println("despues de nueva solicitud");
            switch (frmChannel) {
                case "Call": channel = list_Channel+"[1]"; break;
                case "Presencial": channel = list_Channel+"[2]"; break;
                case "Canales escritos": channel = list_Channel+"[3]"; break;
            }
            Thread.sleep(6000);
            //doClickRootElementListNameXpath("Selecciono opción Presencial del campo Canal", dir_listChannel_root, channel, false);
            Thread.sleep(500);
            if (validateObjExistXpath(txt_ani)) {
                System.out.println("ANI enabled!");
                doClickRootElementByXPath("Click al campo Ingrese ANI", dir_ani_root, txt_ani, false);
            }
            /*doClickRootElementListCssSelector("Selecciono primer IMEI de la lista", dir_listImei_root, list_Imei, false);
            Thread.sleep(500);
            doAddTextFieldOutWait("Ingreso Nombre solicitante", txt_name, frmName, false);
            doAddTextFieldOutWait("Ingreso Nombre solicitante", txt_lastName, frmLastName, false);
            Thread.sleep(1000);
            doClickRootElementListCssSelector("Selecciono opción "+frmDocumentType+" del campo Tipo documento del solicitante",
                    dir_listDocumentType_root, list_documentType+frmDocumentType, false);
            Thread.sleep(500);
            doAddTextFieldOutWait("Ingreso número de documento", txt_document, frmDocument, false);
            Thread.sleep(1000);
            doClickRootElementListCssSelector("Selecciono opción "+frmNotifyType+" del campo Tipo de notificación",
                    dir_listNotifyType_root, list_notifyType+frmNotifyType, false);
            doAddTextFieldOutWait("Ingreso medio a notificar ", txt_notify, frmNotify, true);*/
            Thread.sleep(2000);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
