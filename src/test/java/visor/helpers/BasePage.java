/**************************************************
 * WIPRO PROPIEDAD INTELECTUAL
 #Autor : Wipro Automation Team
 #Description : Portal de Venta Fija
 #Fecha de creación: Feb 2020
 #Nombre que modifica : --
 #Fecha modificación: --
 **************************************************/
package visor.helpers;

import java.awt.*;
import java.io.IOException;
import java.security.Key;
import java.util.*;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Action;
import visor.utility.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage{

    public static WebDriver driver;
//    public  WebDriver driver;
    public int defaultWaitingTime = 120;
    public int defaultWaitingLongTime = 500;
    protected  ScreenshotUtility utilitarios = new ScreenshotUtility();
    public  String stepTestName;
    protected Reports reporte = new Reports();
    public String parentHandle= "";

    //Este método se usa para aceptar los alert que aparezcan en los sites.
    public void checkAlert_Accept() throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en check Alert",e);
            utilitarios.takeScreenshot("ERROR_En_Aceptar_Alert", driver);
            ScreenshotUtility.saveAccion("ERROR_En_Aceptar_Alert");
        }
    }

    public void doScreenshot (String UIName) throws IOException, AWTException {
        try {
            utilitarios.takeScreenshot(UIName, driver);
            ScreenshotUtility.saveAccion(UIName);
            System.out.println("taked!");
        } catch (AssertionError e) {
            utilitarios.takeScreenshot("ERROR_En_Agregar_El_Texto_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_En_Agregar_El_Texto_" + UIName);
        }
    }

    //Este método ingresa el texto.
    public void doAddTextFieldOutWait(String UIName,String objTechName_ByName,String text,boolean screenshot) throws IOException, AWTException {
        try {
            WebElement element=driver.findElement(By.name(objTechName_ByName));
            element.click();
            String selectAll = Keys.chord(Keys.CONTROL,"a");
            element.sendKeys(selectAll);
            element.sendKeys(text);
            Thread.sleep(1000);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en agregar texto",e);
            utilitarios.takeScreenshot("ERROR_En_Agregar_El_Texto_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_En_Agregar_El_Texto_" + UIName);
        } catch (AWTException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void doAddTextFieldOutWaitCssSelector(String UIName,String objTechName_ByName,String text,boolean screenshot) throws IOException, AWTException {
        try {
            WebElement element = driver.findElement(By.cssSelector(objTechName_ByName));
            element.click();
            String selectAll = Keys.chord(Keys.CONTROL,"a");
            element.sendKeys(selectAll);
            element.clear();
            element.sendKeys(text);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en agregar texto",e);
            utilitarios.takeScreenshot("ERROR_En_Agregar_El_Texto_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_En_Agregar_El_Texto_" + UIName);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

    public void devTools(String UIName, boolean screenshot){
/*      String openDevTools = Keys.chord(Keys.CONTROL,Keys.SHIFT+ "j");
        System.out.println("antes cons");
        driver.findElement(By.tagName("img")).sendKeys(openDevTools);
        System.out.println("mande el comando");*/
        System.out.println("pulse");
        Actions act = new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys("j").keyUp(Keys.CONTROL).perform();
        System.out.println("solte");
    }

    public void doAddTextField(String UIName, String objTechName, String text, Boolean screenshot) throws IOException, AWTException {
        try {
            WebElement element =wait(UIName,objTechName);
            element.clear();
            element.sendKeys(text);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en agregar texto",e);
            utilitarios.takeScreenshot("ERROR_En_Agregar_El_Texto_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_En_Agregar_El_Texto_" + UIName);
        }
    }
    //Método para hacer click al objeto.
    public void doClick(String UIName, String objTechName, Boolean screenshot) throws IOException, AWTException {
        try {
            WebElement element=wait(UIName,objTechName);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            element.click();
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en hacer click",e);
            utilitarios.takeScreenshot("ERROR_Click_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_" + UIName);
        }
    }

    //Método para seleccionar objeto combo
    public void doSelectDropdown(String UIName, String objTechName, String Value, Boolean screenshot)
            throws IOException, AWTException {
        try {
            WebElement element =wait(UIName,objTechName);
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(Value);
            //dropdown.selectByValue(Value);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en agregar texto",e);
            utilitarios.takeScreenshot("ERROR_SelectDropdown" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_SelectDropdown" + UIName);
        }
    }

    //Método para elegir la opción index del combo
    public void doSelectDropDownByindex(String UIName, String objTechName, int data, Boolean screenshot) throws Exception {
        try {
            WebElement element =wait(UIName,objTechName);
            Select dropdown = new Select(element);
            dropdown.selectByIndex(data);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en agregar seleccionar el dropdown",e);
            utilitarios.takeScreenshot("ERROR_SelectDropdown" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_SelectDropdown" + UIName);
        }
    }

    //Método para seleccionar el texto de la opción del elemento
    public void select(String UIName, String objTechName, String data) throws Exception {
        try {
            WebElement element =wait(UIName,objTechName);
            Select selectBox = new Select(element);
            selectBox.selectByVisibleText(data);
            utilitarios.takeScreenshot(UIName, driver);
            ScreenshotUtility.saveAccion(UIName);
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en el select",e);
            utilitarios.takeScreenshot("ERROR_SelectDropdown" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_SelectDropdown" + UIName);
        }
    }

    //Método para cambiar de frame
    public void switchiframe(String UIName, String objTechName) throws IOException, AWTException  {
        try {
            WebElement element =wait(UIName,objTechName);
            driver.switchTo().frame(element);
			utilitarios.takeScreenshot(UIName, driver);
			ScreenshotUtility.saveAccion(UIName);
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en switch frame",e);
            utilitarios.takeScreenshot("ERROR_switchFrame" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_switchFrame" + UIName);
        }
    }
    public void EnterIntoView(){
        try{
            Actions builder = new Actions(driver);
            builder.sendKeys(Keys.ENTER).build().perform();
        }catch (AssertionError e){
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en dar ENTER",e);
        }

    }
    public void setWebDriver(WebDriver driver) {
        BasePage.driver=driver;
    }

    public enum properties {
        DISPLAYED, VISIBLE, SELECTED;
    }
    //Valida que un objeto exista
    public boolean validateObject(String UIName, String objTechName, String PropertyToBeVerified, Boolean screenshot) throws Exception {
        boolean ActualPropertyValue = false;
     //   Thread.sleep(7000);
        String prop = PropertyToBeVerified.toUpperCase();
        try {
            System.out.println("antes wait");

            if (screenshot) {
                utilitarios.takeScreenshot(UIName+" ", driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            Thread.sleep(500);
            System.out.println("antes validate" + "|" + UIName + "|" + objTechName);
            WebElement element =wait(UIName,objTechName);
            // WebElement element =driver.findElement(By.xpath(objTechName));

            System.out.println("despues wait");

            switch (properties.valueOf(prop)) {

                case DISPLAYED:
                    System.out.println("en displayed");
                    ActualPropertyValue = element.isDisplayed();
                    break;
                case VISIBLE:
                    ActualPropertyValue = element.isEnabled();
                    break;
                case SELECTED:
                    ActualPropertyValue = element.isSelected();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.getStackTrace();
            e.printStackTrace();
                utilitarios.takeScreenshot("ERROR_Validar_Objeto_" + UIName, driver);
                ScreenshotUtility.saveAccion("ERROR_Validar_Objeto_" + UIName);
            Assert.fail("Fallo la validacion del objeto",e);

        }
        if (ActualPropertyValue) {
            return true;
        }
        return false;
    }

    public boolean validateObjectXpath(String UIName, String objTechName, String PropertyToBeVerified, Boolean screenshot) throws Exception {
        boolean ActualPropertyValue = false;
        //   Thread.sleep(7000);
        String prop = PropertyToBeVerified.toUpperCase();
        try {
            System.out.println("antes wait");

            if (screenshot) {
                utilitarios.takeScreenshot(UIName+" ", driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            Thread.sleep(500);
            System.out.println("antes validate" + "|" + UIName + "|" + objTechName);
            WebElement element = driver.findElement(By.xpath(objTechName));
            // WebElement element =driver.findElement(By.xpath(objTechName));

            System.out.println("despues wait");

            switch (properties.valueOf(prop)) {

                case DISPLAYED:
                    System.out.println("en displayed");
                    ActualPropertyValue = element.isDisplayed();
                    break;
                case VISIBLE:
                    ActualPropertyValue = element.isEnabled();
                    break;
                case SELECTED:
                    ActualPropertyValue = element.isSelected();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.getStackTrace();
            e.printStackTrace();
            utilitarios.takeScreenshot("ERROR_Validar_Objeto_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Validar_Objeto_" + UIName);
            Assert.fail("Fallo la validacion del objeto",e);

        }
        if (ActualPropertyValue) {
            return true;
        }
        return false;
    }

    public boolean validateObjectName(String UIName, String objTechName, String PropertyToBeVerified, Boolean screenshot) throws Exception {
        boolean ActualPropertyValue = false;
        //   Thread.sleep(7000);
        String prop = PropertyToBeVerified.toUpperCase();
        try {
            System.out.println("antes wait");

            if (screenshot) {
                utilitarios.takeScreenshot(UIName+" ", driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            //WebElement element =wait(UIName,objTechName);
            WebElement element = driver.findElement(By.name(objTechName));

            System.out.println("despues wait");

            switch (properties.valueOf(prop)) {

                case DISPLAYED:
                    System.out.println("en displayed");

                    ActualPropertyValue = element.isDisplayed();
                    break;
                case VISIBLE:
                    ActualPropertyValue = element.isEnabled();
                    break;
                case SELECTED:
                    ActualPropertyValue = element.isSelected();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.getStackTrace();
            e.printStackTrace();
            utilitarios.takeScreenshot("ERROR_Validar_Objeto_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Validar_Objeto_" + UIName);
            Assert.fail("Fallo la validacion del objeto",e);

        }
        if (ActualPropertyValue) {
            return true;
        }
        return false;
    }

    public boolean validateObjectCssSelector(String UIName, String objTechName, String PropertyToBeVerified, Boolean screenshot) throws Exception {
        boolean ActualPropertyValue = false;
        //   Thread.sleep(7000);
        String prop = PropertyToBeVerified.toUpperCase();
        try {
            System.out.println("antes wait");

            if (screenshot) {
                utilitarios.takeScreenshot(UIName+" ", driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            //WebElement element =wait(UIName,objTechName);
            WebElement element =driver.findElement(By.cssSelector(objTechName));

            System.out.println("despues wait");

            switch (properties.valueOf(prop)) {

                case DISPLAYED:
                    System.out.println("en displayed");

                    ActualPropertyValue = element.isDisplayed();
                    break;
                case VISIBLE:
                    ActualPropertyValue = element.isEnabled();
                    break;
                case SELECTED:
                    ActualPropertyValue = element.isSelected();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.getStackTrace();
            e.printStackTrace();
            utilitarios.takeScreenshot("ERROR_Validar_Objeto_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Validar_Objeto_" + UIName);
            Assert.fail("Fallo la validacion del objeto",e);

        }
        if (ActualPropertyValue) {
            return true;
        }
        return false;
    }

    public boolean validateObjectId(String UIName, String objTechName, String PropertyToBeVerified, Boolean screenshot) throws Exception {
        boolean ActualPropertyValue = false;
        //   Thread.sleep(7000);
        String prop = PropertyToBeVerified.toUpperCase();
        try {
            System.out.println("antes wait");

            if (screenshot) {
                utilitarios.takeScreenshot(UIName+" ", driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            //WebElement element =wait(UIName,objTechName);
            WebElement element =driver.findElement(By.id(objTechName));

            System.out.println("despues wait");

            switch (properties.valueOf(prop)) {

                case DISPLAYED:
                    System.out.println("en displayed");

                    ActualPropertyValue = element.isDisplayed();
                    break;
                case VISIBLE:
                    ActualPropertyValue = element.isEnabled();
                    break;
                case SELECTED:
                    ActualPropertyValue = element.isSelected();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.getStackTrace();
            e.printStackTrace();
            utilitarios.takeScreenshot("ERROR_Validar_Objeto_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Validar_Objeto_" + UIName);
            Assert.fail("Fallo la validacion del objeto",e);

        }
        if (ActualPropertyValue) {
            return true;
        }
        return false;
    }

    //Valida que el texto mostrado corresponda al texto comparado
	//No valida mayúsculas, ni minúsculas
    public boolean verifyText(String strText, String objTechName, String indice, Boolean screenshot) throws Exception {
        WebElement element = null;
        element =wait(strText,objTechName);

        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);
            element =wait(strText, objTechName);
            System.out.println("text:" + strText);
            System.out.println("text:" + element.getText());
            if (screenshot) {

                utilitarios.takeScreenshot("[Registro_" + indice + "]Validacion_Texto_" + strText, driver);
                ScreenshotUtility.saveAccion("[Registro_" + indice + "]Validacion_Texto_" + strText);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo la verificacion del texto",e);
            utilitarios.takeScreenshot("[Registro_"+indice+"]ERROR_Este_Texto_No_Fue_Encontrado_"+strText, driver);
            ScreenshotUtility.saveAccion("[Registro_"+indice+"]ERROR_Este_Texto_No_Fue_Encontrado_"+strText);
        }
        return element.getText().equalsIgnoreCase(strText);
    }

    //Método que obtiene el texto del label
    public String getlabel(String UIName, String objTechName) throws Exception {
        try {
            WebElement element =wait(UIName, objTechName);
            return element.getText();
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en obtener el label",e);
            utilitarios.takeScreenshot("ERROR_obtenerTexto" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_obtenerTexto" + UIName);

        }
        return null;
    }

    public String getlabelByXpath(String UIName, String objTechName) throws Exception {
        try {
            WebElement element = driver.findElement(By.id(objTechName));
            String valor = element.getText();
            return valor;
            //return element.getText();
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo en obtener el label",e);
            utilitarios.takeScreenshot("ERROR_obtenerTexto" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_obtenerTexto" + UIName);

        }
        return null;
    }

    //Verifica el contenido del texto
    public boolean verifyContainText(String strText, String objTechName) throws Exception {
        WebElement element=null;
        try {
            element =wait(strText, objTechName);

        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo la verificar el texto que contiene un elemento",e);

            utilitarios.takeScreenshot("ERROR_verificar_texto_contain", driver);
            ScreenshotUtility.saveAccion("ERROR_verificar_texto_contain");

        }
        return element.getText().contains(strText);
    }
    public WebElement returnWebElement (String objTechName) throws Exception {
        WebElement element=null;

        try {
            element =wait(objTechName,objTechName);

        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el retorno del elemento",e);
            utilitarios.takeScreenshot("ERROR_Retornar_WebElement", driver);
            ScreenshotUtility.saveAccion("ERROR_Retornar_WebElement");

        }
        return element;
    }
    public static WebDriver handleDriver(){
        return driver;
    }
    //*************JavaScript**************
    public void doclickByJS(String UIName, String objTechName, boolean screenshot) throws IOException, AWTException {

        try {
            WebElement element = wait(objTechName, objTechName);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName+" ", driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            Thread.sleep(500);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", element);
        } catch (AssertionError | InterruptedException e) {
//            e.getStackTrace();
            e.printStackTrace();
            utilitarios.takeScreenshot("ERROR_Click_en_Boton_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_en_Boton_" + UIName);
            Assert.fail("Fallo el click del boton",e);

            /*
                System.out.println("Error BasePage:"+e.getMessage());

            e.getStackTrace();
            e.printStackTrace();
                System.out.println("En validacion ActualPropertyValue");
                utilitarios.takeScreenshot("ERROR_Validar_Objeto_" + UIName, driver);
                ScreenshotUtility.saveAccion("ERROR_Validar_Objeto_" + UIName);
            Assert.fail("Fallo la validacion del objeto",e);
             */
        }
    }

    public void doclickByJSID(String UIName, String objTechName, boolean screenshot) throws IOException, AWTException {

        try {
            WebElement element =wait(objTechName, objTechName);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", element);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName+" ", driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
//            e.getStackTrace();
            e.printStackTrace();
            utilitarios.takeScreenshot("ERROR_Click_en_Boton_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_en_Boton_" + UIName);
            Assert.fail("Fallo el click del boton",e);

            /*
                System.out.println("Error BasePage:"+e.getMessage());

            e.getStackTrace();
            e.printStackTrace();
                System.out.println("En validacion ActualPropertyValue");
                utilitarios.takeScreenshot("ERROR_Validar_Objeto_" + UIName, driver);
                ScreenshotUtility.saveAccion("ERROR_Validar_Objeto_" + UIName);
            Assert.fail("Fallo la validacion del objeto",e);
             */
        }
    }

    public void doclickByJS2(String UIName, WebElement element, boolean screenshot) throws IOException, AWTException {

        try {

            ////
           Thread.sleep(5000);
            ////


            JavascriptExecutor jse = (JavascriptExecutor) driver;

            jse.executeScript("arguments[0].click()", element);
       /*     if (screenshot) {
                utilitarios.takeScreenshot(UIName+" ", driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        */} catch (AssertionError | InterruptedException e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_js",e);
            utilitarios.takeScreenshot("ERROR_Click_JS_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_JS_" + UIName);
        }
    }

    public void jsScrollIntoView(String UIName, String objTechName, boolean screenshot) throws Exception {
        try {
            WebElement   element =wait(UIName, objTechName);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView()", element);
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el scrolldown_js",e);
            utilitarios.takeScreenshot("ERROR_ScrollDown_JS_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_ScrollDown_JS_" + UIName);
        }
    }
    public void RedirigirPage(String url){
        try{
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("location.replace('"+url+"')");
        }catch (AssertionError e){
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el scrolldown_js",e);
        }


    }

    public void updatepage(String str) throws Throwable {
        try{
            Thread.sleep(5000);
            while (driver.findElements(By.xpath(str)).size()!=0){
                driver.navigate().refresh();
                Thread.sleep(7000);
            }
        }catch (AssertionError e){
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo al actualizar la pagina",e);
        }


    }

    public boolean validateObj(String objTechName) throws Throwable {
        boolean respuesta=false;
        try{
            Thread.sleep(1500);
            System.out.println(ObjectMap.getLocator(objTechName));
            System.out.println("before");
            if (driver.findElements(ObjectMap.getLocator(objTechName)).size()!=0){
                System.out.println("dentro if");
                respuesta=true;
                System.out.println("this is it!");
            }
        }catch (AssertionError e){
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo la validacion de existencia de objeto",e);
        }

        return respuesta;
    }

    public boolean validateObjExistXpath(String str) throws Throwable {
        boolean respuesta=false;
        try{
            Thread.sleep(1500);
            if (driver.findElements(By.xpath(str)).size()!=0){
                respuesta=true;
            }
        }catch (AssertionError e){
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo la validacion de existencia de objeto",e);
        }

        return respuesta;
    }

    public boolean validateObjExistCssSelector(String str) throws Throwable {
        boolean respuesta=false;
        try{
            Thread.sleep(1000);
            if (driver.findElements(By.cssSelector(str)).size()!=0){
                respuesta=true;
            }
        }catch (AssertionError e){
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo la validacion de existencia de objeto",e);
        }

        return respuesta;
    }

    public boolean validateObjExistId(String str) throws Throwable {
        boolean respuesta=false;
        try{
            Thread.sleep(2000);
            System.out.println("cond validar");
            System.out.println(driver.findElements(By.id(str)).size());
            if (driver.findElements(By.id(str)).size()!=0){
                respuesta=true;
                System.out.println("existe!");
            }
        }catch (AssertionError e){
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo la validacion de existencia de objeto",e);
        }

        return respuesta;
    }

    public boolean validateObjExistClass() throws Throwable {
        boolean respuesta = false;
        try{
            System.out.println(driver.findElements(By.cssSelector(".disabled")).size());
            Thread.sleep(1000);
            if (driver.findElements(By.cssSelector(".disabled")).size()!= 0) {
                respuesta = true;
            }
        }catch (AssertionError e){
            Assert.fail("Fallo la validacion de existencia de objeto",e);
        }
        return respuesta;
    }

    public void UpdatepageNoexistElement(String str) throws Throwable{
        try{
            Thread.sleep(5000);
            while (driver.findElements(By.xpath(str)).size()==0){
                driver.navigate().refresh();
                Thread.sleep(7000);
            }
        }catch(AssertionError e){
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo la actualizacion",e);
        }

    }

    public WebElement wait(String UIName, String objTechName) throws IOException, AWTException {
        WebDriverWait wait1 = new WebDriverWait(driver,defaultWaitingTime);
        WebElement element = null;

        try {
            element = wait1.until(ExpectedConditions.visibilityOfElementLocated(ObjectMap.getLocator(objTechName)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("En validacion Wait");
            utilitarios.takeScreenshot("ERROR_En_" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_En_" + UIName);
            Assert.fail("Fallo la validacion del objeto",e);

            /*
                System.out.println("Error BasePage:"+e.getMessage());

            e.getStackTrace();
            e.printStackTrace();
                System.out.println("En validacion ActualPropertyValue");
                utilitarios.takeScreenshot("ERROR_Validar_Objeto_" + UIName, driver);
                ScreenshotUtility.saveAccion("ERROR_Validar_Objeto_" + UIName);
            Assert.fail("Fallo la validacion del objeto",e);

             */
        }
        return element;
    }
    public WebElement expandRootElement(String objtElement) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver,defaultWaitingTime);
        WebElement element = null;
        element=wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectMap.getLocator(objtElement)));
        WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                element);
        return ele;
    }

    public void waitInvisibility(String UIName, String objTechName, Boolean screenshot) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(ObjectMap.getLocator(objTechName)));
        } catch (Exception e) {
            e.printStackTrace();
            utilitarios.takeScreenshot("Error en  " + UIName, driver);
            ScreenshotUtility.saveAccion("Error en " + UIName);
        }
    }

    public void waitInvisibilityLoader(String UIName, String objTechName, Boolean screenshot) throws Exception {
        try {
            Thread.sleep(2000);
            System.out.println("Waiting");
            if (screenshot) {
                utilitarios.takeScreenshot(UIName+" ", driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            Thread.sleep(500);
            while (validateObjExistCssSelector(objTechName)) {
                waitInvisibility( UIName, objTechName, screenshot);
            }
            System.out.println("Out!");
        } catch (Exception e) {
            e.printStackTrace();
            utilitarios.takeScreenshot("Error en  " + UIName, driver);
            ScreenshotUtility.saveAccion("Error en " + UIName);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void waitInvisibilityByCssSelector(String UIName, String objTechName, Boolean screenshot) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultWaitingLongTime);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(objTechName)));
        } catch (Exception e) {
            e.printStackTrace();
            utilitarios.takeScreenshot("Error en  " + UIName, driver);
            ScreenshotUtility.saveAccion("Error en " + UIName);
        }
    }

    public void ManejoDeExepciones(Exception f, String UIName, String message) throws Exception {
        f.getMessage();
        f.getStackTrace();
        f.printStackTrace();
        TakeScreenshot(UIName);
   //     Hook.closeBrowser(BasePage.handleDriver());
    }
    public  void TakeScreenshot(String UIName) throws IOException, AWTException {
        utilitarios.takeScreenshot(UIName, BasePage.handleDriver());
        ScreenshotUtility.saveAccion(UIName);
    }

    public void doClickRootElementByNameId(String UIName,String rootname,String objTechName,boolean screenshot) throws Exception {

        try {
            WebElement root = driver.findElement(By.name(rootname));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            Thread.sleep(500);
            WebElement elementClick = element.findElement(By.id(objTechName));
            elementClick.click();
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT" + UIName);
        }

    }

    public void doClickRootElementByXPath(String UIName,String rootname,String objTechName,boolean screenshot) throws Exception {

        try {
            WebElement root = driver.findElement(By.xpath(rootname));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            System.out.println("padre");
            Thread.sleep(1000);
            WebElement elementClick = element.findElement(By.xpath(objTechName));
            elementClick.click();
            System.out.println("hijo");
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT" + UIName);
        }

    }

    public void doClickRootElementListCssSelectorClass(String UIName,String rootname_CSS,String objTechName_CSS,boolean screenshot) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(rootname_CSS)));
        System.out.println("doClickRootElement2_codigo_"+UIName);
        try {
            WebElement root = driver.findElement(By.cssSelector(rootname_CSS));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            //WebElement elementClick = element.findElement(By.cssSelector(objTechName_CSS));
            WebElement elementClick = element.findElement(By.cssSelector("ul.mdc-list li:nth-of-type("+objTechName_CSS+")"));
            doclickByJS2("Click Elemento_"+UIName,elementClick,true);
            Thread.sleep(1500);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT" + UIName);
        }

    }

    public void doClickRootElementListCssSelectorConcat(String UIName,String rootname_CSS,String objTechName_CSS,boolean screenshot) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(rootname_CSS)));
        try {
            WebElement root = driver.findElement(By.cssSelector(rootname_CSS));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            WebElement elementClick = element.findElement(By.cssSelector(objTechName_CSS));
            System.out.println("ul.mdc-list li:nth-of-type("+objTechName_CSS+")");
            doclickByJS2("Click Elemento_"+UIName,elementClick,true);
            Thread.sleep(1500);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT" + UIName);
        }

    }

    public String listValue(String data) {
        String cad = data;
        String datas = "";
        ArrayList<String> newParts = new ArrayList<>();
        String[] parts = cad.split(" ");
        for (int i = 0; i < parts.length; i++) {
            System.out.println(parts[i]);
            if (parts[i].endsWith("+") || parts[i].endsWith("/") || parts[i].endsWith("-") || parts[i].endsWith("\\(")) {
                newParts.add("\\" + parts[i]);
            } else if (parts[i].startsWith("(") && parts[i].endsWith(",")) {
                newParts.add("\\"+(parts[i].substring(0, parts[i].length()-1))+"\\,");
            } else if (parts[i].endsWith(",")) {
                newParts.add((parts[i].substring(0, parts[i].length()-1))+"\\,");
            } else if (parts[i].endsWith(")")) {
                newParts.add((parts[i].substring(0, parts[i].length()-1))+"\\)");
            } else if (parts[i].endsWith(".")) {
                newParts.add((parts[i].substring(0, parts[i].length()-1))+"\\.");
            } else if (parts[i].contains("/")) {
                String[] newPart = parts[i].split("/");
                newParts.add(newPart[0]+"\\/"+newPart[1]);
            } else {
                newParts.add(parts[i]);
            }
        }
        StringBuilder newCad = new StringBuilder();
        for (String lastCad : newParts) {
            newCad.append(lastCad);
            newCad.append("\\ ");
        }
        System.out.println(newCad.substring(0, newCad.length()-2));
        return (newCad.substring(0, newCad.length()-2));
    }

    public void doClickRootElementListXpath(String UIName,String rootname,String objTechName_xpath,boolean screenshot) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rootname)));
        System.out.println("doClickRootElement2_codigo_"+UIName);
        try {
            WebElement root = driver.findElement(By.xpath(rootname));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            WebElement elementClick = element.findElement(By.xpath(objTechName_xpath));
            doclickByJS2("Click Elemento_"+UIName,elementClick,true);
            Thread.sleep(500);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT" + UIName);
        }

    }

    public void fe(String datas) {
        WebElement test = driver.findElement(By.cssSelector(datas+"[name='servicioCombo']"));
        test.click();
        WebElement t = driver.findElement(By.cssSelector(datas+"[name='servicioCombo_Móvil']"));
        t.click();
        System.out.println("test: "+test);
    }
    /*public static List<WebElement> findElement( String datas) {
        try {
            WebElement test = driver.findElement(By.name("tdp-st-selector[name='servicioCombo']"));
            return el;
        } catch (WebDriverException w) {
            System.out.println("Error W: "+w);
        }
        return null;
    }*/

    public static WebElement expand(WebElement element) {
        WebElement shadowRoot = (WebElement)((JavascriptExecutor)driver).executeScript(
                "return arguments[0].shadowRoot", element);
        return shadowRoot;
    }

    public void doClickRootElementListNameXpath(String UIName,String rootname,String objTechName_xpath,boolean screenshot) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(rootname)));
        System.out.println("doClickRootElement2_codigo_"+UIName);
        try {
            WebElement root = driver.findElement(By.name(rootname));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            System.out.println("before!");
            WebElement elementClick = element.findElement(By.xpath(objTechName_xpath));
            //  elementClick.click();
            doclickByJS2("Click Elemento_"+UIName,elementClick,true);
            Thread.sleep(500);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT" + UIName);
        }

    }

    public void insertTextRootCssSelector(String UIName, String rootname, String objTechName, String text, boolean screenshot) throws Exception {

        try {
            WebElement root = driver.findElement(By.cssSelector(rootname));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            Thread.sleep(500);
            WebElement elementClick = element.findElement(By.cssSelector(objTechName));
            elementClick.click();
            System.out.println("inicio insertar text");
            String selectAll = Keys.chord(Keys.CONTROL,"a");
            elementClick.sendKeys(selectAll);
            elementClick.clear();
            elementClick.sendKeys(text);
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root_by_id",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT_ByID" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT_ByID" + UIName);
        }
    }

    public void doClickRootElementByCssSelector(String UIName,String rootname,String objTechName,boolean screenshot) throws Exception {

        try {
            WebElement root = driver.findElement(By.cssSelector(rootname));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            Thread.sleep(500);
            WebElement elementClick = element.findElement(By.cssSelector(objTechName));
            elementClick.click();
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root_by_id",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT_ByID" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT_ByID" + UIName);
        }
    }

    public void doClickRootElementById(String UIName,String rootname,String objTechName,boolean screenshot) throws Exception {

        try {
            WebElement root = driver.findElement(By.id(rootname));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            Thread.sleep(500);
            System.out.println("antes del channel");
            WebElement elementClick = element.findElement(By.id(objTechName));
            elementClick.click();
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root_by_id",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT_ByID" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT_ByID" + UIName);
        }
    }

    public void doClickRootElementByName(String UIName,String rootname,String objTechName,boolean screenshot) throws Exception {

        try {
            WebElement root = driver.findElement(By.name(rootname));
            WebElement element = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
                    root);
            if (screenshot) {
                utilitarios.takeScreenshot(UIName, driver);
                ScreenshotUtility.saveAccion(UIName);
            }
            Thread.sleep(500);
            WebElement elementClick = element.findElement(By.name(objTechName));
            elementClick.click();
        } catch (AssertionError e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
            Assert.fail("Fallo el click del boton_shallow_root_by_id",e);
            utilitarios.takeScreenshot("ERROR_Click_SHALLOW_ROOT_ByID" + UIName, driver);
            ScreenshotUtility.saveAccion("ERROR_Click_SHALLOW_ROOT_ByID" + UIName);
        }
    }

    public String newWindow(boolean screenshot) throws Exception {
        try {
            Thread.sleep(2000);
            parentHandle = driver.getWindowHandle();
            for (String winHandle : driver.getWindowHandles()) {
                Thread.sleep(1500);
                driver.switchTo().window(winHandle);//cambio a la ventana del login
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parentHandle;
    }

    public void returnWindow(String parentHandle) throws Exception {

        try {
            Thread.sleep(500);
            driver.switchTo().window(parentHandle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return driver;
    }
    public String obtenerFechaYHora() throws Exception {
        String resultado="";
        try {
            Calendar calendario = Calendar.getInstance();
            calendario = new GregorianCalendar();
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int mes = calendario.get(Calendar.MONTH);
            mes = mes + 1;
            int year = calendario.get(Calendar.YEAR);
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minutos = calendario.get(Calendar.MINUTE);
            int segundos = calendario.get(Calendar.SECOND);
            resultado = hora + ":" + minutos + ":" + segundos + " " + dia + "/" + mes + "/" + year;

        } catch (AssertionError e) {
            e.printStackTrace();
            Assert.fail("Fallo obtener fecha y hora",e);
        }
        return resultado;
    }

    public String evaluatePlat(String servicio, String plataforma) {
        String plat = "";
        switch (servicio.toLowerCase()) {
            case "fija": switch (plataforma.toLowerCase()) {
                case "facturación fija": plat = "1"; break;
                case "residencial fija": plat = "2"; break;
                case "tecnico negocios empresas": plat = "3"; break;
                case "tecnico residencial fija": plat = "4"; break;
                case "tiendas": plat = "5"; break;
                default: break;
            }; break;
            case "móvil": switch (plataforma.toLowerCase()) {
                case "contrato": plat = "1"; break;
                case "corte por robo": plat = "2"; break;
                case "facturacion movil": plat = "3"; break;
                case "prepago": plat = "4"; break;
                case "roaming": plat = "5"; break;
                case "técnico móvil": plat = "6"; break;
                case "tiendas": plat = "7"; break;
                default: break;
            }; break;
            case "movistar total": switch (plataforma.toLowerCase()) {
                case "movistar total premium": plat = "1"; break;
                case "tiendas": plat = "2"; break;
                default: break;
            }; break;
            default: break;
        }
        System.out.println(servicio + " | " + plataforma + " | " + plat);
        return plat;
    }

    public String evaluateCardHome(String opcion) {
        String card = "";
        switch (opcion) {
            case "Sx/Rx no procesadas": card = "//*[@id=\"div_card_reprocesar\"]"; break;
            case "Gestión de usuarios": card = "//*[@id=\"div_card_gestion\"]/div[2]"; break;
            case "Carga masiva de usuarios": card = "//*[@id=\"div_card_carga-masiva\"]/div[1]"; break;
            case "Descarga de reporte": card = "//*[@id=\"div_card_reporte\"]";break;
            default: break;
        }
        return card;
    }

    public String evaluateMonth(String month) {
        String mes = "";
        switch (month) {
            case "Enero": mes = "1"; break;
            case "Febrero": mes = "2"; break;
            case "Marzo": mes = "3"; break;
            case "Abril": mes = "4"; break;
            case "Mayo": mes = "5"; break;
            case "Junio": mes = "6"; break;
            case "Julio": mes = "7"; break;
            case "Agosto": mes = "8"; break;
            case "Setiembre": mes = "9"; break;
            case "Octubre": mes = "10"; break;
            case "Noviembre": mes = "11"; break;
            case "Diciembre": mes = "12"; break;
            default: break;
        }
        return mes;
    };

    public String evaluateYear(String year) {
        String anio = "";
        switch (year) {
            case "2019": anio = "1"; break;
            case "2020": anio = "2"; break;
            case "2021": anio = "3"; break;
            case "2022": anio = "4"; break;
            case "2023": anio = "5"; break;
            default: break;
        }
        return anio;
    };

    public String evaluateMonthForDatePicker(String month) {
        String mes = "";
        switch (month) {
            case "01": mes = "ENE."; break;
            case "02": mes = "FEB."; break;
            case "03": mes = "MAR."; break;
            case "04": mes = "ABR."; break;
            case "05": mes = "MAY."; break;
            case "06": mes = "JUN."; break;
            case "07": mes = "JUL."; break;
            case "08": mes = "AGO."; break;
            case "09": mes = "SET."; break;
            case "10": mes = "OCT."; break;
            case "11": mes = "NOV."; break;
            case "12": mes = "DIC."; break;
            default: break;
        }
        return mes;
    }

    public void selectDate(String fecha, String btn_date, boolean screenshot) {
        try {
            System.out.println(fecha);
            String[] parts = fecha.split("/");
            Thread.sleep(500);
            System.out.println("año |"+parts[0]+"|"+parts[1]+"|"+parts[2]);
            System.out.println("primero");
            doclickByJS("Click opciones de fechas ", btn_date, screenshot);
            System.out.println("segundo");
            doclickByJS("Seleccionar año", "//div[contains(text(),'"+parts[2]+"')]", screenshot);
            System.out.println("tercero");
            doclickByJS("Seleccionar mes", "//*[contains(text(),'"+evaluateMonthForDatePicker(parts[1])+"')]", screenshot);
            System.out.println("cuarto");
            Thread.sleep(500);
            doclickByJS("Seleccionar dia", "//div[contains(text(),'"+parts[0]+"')]", screenshot);
        } catch (Exception e) {
            System.out.println("ERROR validacion fecha " + e);
        }
    }

    public static String[] evaluateTab(String tipoServicio, String tab) {
        String cadena = null;
        String position = null;
        String searchTab = null;
        if (tipoServicio.equals("movil")) {
            switch (tab) {
                case "General": position = "1"; break;
                case "Datos": position = "2"; break;
                case "Voz": position = "3"; break;
                default: break;
            }

            switch (position) {
                case "1": searchTab = "#btn_GEN"; break;
                case "2": searchTab = "#btn_DT"; break;
                case "3": searchTab = "#btn_VZ"; break;
            }
            cadena = "#layout > visor-container-tools > visor-tab > div > div:nth-child(" + position + ") > tdp-st-button";
        } else if (tipoServicio.equals("fija")) {
            System.out.println("under construction!");
        }
        System.out.println("Cadena: " + cadena + "| Tab: " + searchTab);
        return new String[]{cadena, searchTab};
    }
}
