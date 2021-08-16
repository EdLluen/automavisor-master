package visor.pageObject.FlujoTipificaciones;

import visor.helpers.BasePage;

import java.util.ArrayList;

public class P004_FormTipificacion extends BasePage {

    String dir_ani_root = "aniLlamada";
    String dir_ticket_root = "ticket";
    String dir_name_root = "usuarioConsulta";
    String dir_obs_root = "observacion";
    String list_servicio_root = "body > modal-container > div > div > visor-typify-customer-contact > div.modal-body.tlf-rg-14 > form > div:nth-child(2) > div.col-md-4 > tdp-st-select";
    String list_plataforma_root = "body > modal-container > div > div > visor-typify-customer-contact > div.modal-body.tlf-rg-14 > form > div:nth-child(2) > div.col-md-8 > tdp-st-select";
    String list_motivo_root = "body > modal-container > div > div > visor-typify-customer-contact > div.modal-body.tlf-rg-14 > form > div:nth-child(5) > div > tdp-st-select";
    String list_submotivo_root = "body > modal-container > div > div > visor-typify-customer-contact > div.modal-body.tlf-rg-14 > form > div:nth-child(6) > div.col-md-8 > tdp-st-select";
    String selectServ = "#servicioCombo_";
    String selectMot = "#motivoAtencionCombo_";
    String selectSub = "#submotivoCombo_";

    public void doFormInteraction(int id, ArrayList<String> dataForm) {
        System.out.println("P4");
        try {
            Thread.sleep(500);
            doClickRootElementListCssSelectorConcat("Selecciono opción " + dataForm.get(0) + " del campo Servicio", list_servicio_root, selectServ+listValue(dataForm.get(0)), false);
            doClickRootElementListCssSelectorClass("Selecciono opción " + dataForm.get(1) + " del campo Plataforma", list_plataforma_root, evaluatePlat(dataForm.get(0), dataForm.get(1)), false);
            doClickRootElementByName("Click al campo ANI ", dir_ani_root, dir_ani_root, false);
            doAddTextFieldOutWait("Ingreso número en el campo ANI ", dir_ani_root, dataForm.get(2), false);
            doClickRootElementByName("Click al campo CONNID", dir_ticket_root, dir_ticket_root, false);
            doAddTextFieldOutWait("Ingreso valor CONNID", dir_ticket_root, dataForm.get(3), false);
            doClickRootElementByName("Click al campo Usuario consulta", dir_name_root, dir_name_root, false);
            doAddTextFieldOutWait("Ingreso nombre de usuario consulta", dir_name_root, dataForm.get(5), false);
            doClickRootElementListCssSelectorConcat("Selecciono opción " + dataForm.get(6) + " del campo Motivo", list_motivo_root, selectMot+listValue(dataForm.get(6)), false);
            doClickRootElementListCssSelectorConcat("Selecciono opción " + dataForm.get(7) + " del campo Submotivo", list_submotivo_root, selectSub+listValue(dataForm.get(7)), false);
            doClickRootElementByName("Click al campo Observación ", dir_obs_root, dir_obs_root, false);
            doAddTextFieldOutWait("Ingreso observación ", dir_obs_root, dataForm.get(9), false);
            Thread.sleep(2000);
            System.out.println("Fin P4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
