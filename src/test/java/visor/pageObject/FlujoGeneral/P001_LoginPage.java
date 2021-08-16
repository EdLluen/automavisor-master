package visor.pageObject.FlujoGeneral;

import org.openqa.selenium.By;
import visor.helpers.BasePage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class P001_LoginPage extends BasePage {

    String img_loader ="body > visor-root > visor-loader > div > div";
    String btn_tipoPerfilSupervisor = "//*[contains(text(),'Ingresar')]";
    String txt_user = "//input[contains(@placeholder,'Sign in name')]";
    String txt_pass = "//input[contains(@placeholder,'Password')]";
    String btn_continue = "//*[@id=\"continue\"]";

    public Boolean dologin(int id, String user, String pass){
        System.out.println("P1");
        boolean elementoPresente = false;
        try {
            elementoPresente = validateObject("Registro número " +id,
                    btn_tipoPerfilSupervisor,"DISPLAYED", false);
            validateObject("Valida btn Ingresar", btn_tipoPerfilSupervisor, "DISPLAYED", false);
            doclickByJS("Click opción Ingresar", btn_tipoPerfilSupervisor, true );
            newWindow(false);
        /*Ingreso valores al login*/
            Thread.sleep(15000);
            doClick("click ",txt_user,false);
            doAddTextField("Ingreso usuario", txt_user, user, false);
            Thread.sleep(750);
            doClick("click ",txt_pass,false);
            doAddTextField("Ingreso contraseña", txt_pass, pass, false);
            Thread.sleep(750);
            doclickByJS("Inicio sesión", btn_continue, true);
            waitInvisibilityLoader("Espero loader", img_loader, false);
        /*termino de ingresar valores al login*/
            returnWindow(parentHandle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fin P1");
        return elementoPresente;
    }

}