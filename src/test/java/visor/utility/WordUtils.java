package visor.utility;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import visor.helpers.BasePage;
import visor.helpers.Hook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WordUtils {

    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public static void createDoc(List<String> listScreen, String testername, int id, String sEstadoPrueba) throws Exception { //String testCaseName,
        try {
            BasePage basePage = new BasePage();
            // Blank Document
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            // Path to Word
            String evidencePath = "./results/";
            int format = XWPFDocument.PICTURE_TYPE_PNG;
            System.out.println("Documento en blanco");
            run.setText("Informacion general:");
            run.setBold(true);
            run.setFontSize(14);
            run.addBreak();
            // run.addBreak();
            run.addTab();
            run.setText("Escenario:  "+ Hook.sEscenario);
            run.setBold(false);
            run.setFontSize(12);
            // run.addBreak();
            run.addBreak();
            run.addTab();
            run.setText("Registro Nro:  "+id);
            run.setBold(false);
            run.setFontSize(12);
            //run.addBreak();
            run.addBreak();
            run.setText("Status Prueba:  "+sEstadoPrueba);
            run.setBold(false);
            run.setFontSize(12);
            //     run.addBreak();
            run.addBreak();
            run.addBreak();
            // Insert screenshoots
            int val = 0;
            for (String file : listScreen) {
                String imgFile = "./results/screenshot/" + file;
                run.setText(val + 1 + ". " + ScreenshotUtility.acciones.get(val) + " - Hora y Fecha de Ejecución - "+ basePage.obtenerFechaYHora()+":");
                System.out.println(val);
                val++;
                run.addBreak();
                run.addBreak();
                run.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(420), Units.toEMU(230));
                run.addBreak();
                run.addBreak();
            }

            // Insert Header
            CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
            XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);

            XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

            paragraph = header.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            CTTabStop tabStop = paragraph.getCTP().getPPr().addNewTabs().addNewTab();
            tabStop.setVal(STTabJc.CENTER);
            int twipsPerInch = 1440;
            tabStop.setPos(BigInteger.valueOf(6 * twipsPerInch));

            run = paragraph.createRun();
            run.setText("SUSTENTO DE PRUEBAS TESTING PROYECTO VISOR");
            run.setBold(true);
            run.setFontSize(14);
            run.setTextPosition(100);
            run.addTab();

            //run = paragraph.createRun();
            String imgLogo = "./src/test/resources/logo/Logo_wipro.jpg";
            run.addPicture(new FileInputStream(imgLogo), XWPFDocument.PICTURE_TYPE_PNG, imgLogo, Units.toEMU(30), Units.toEMU(30));

            // Insert Footer
            CTP ctp = CTP.Factory.newInstance();
            CTText t = ctp.addNewR().addNewT();
            t.setStringValue("Tester: "+ testername);
            XWPFParagraph pars[] = new XWPFParagraph[1];
            pars[0] = new XWPFParagraph(ctp, document);
            XWPFHeaderFooterPolicy hfp = document.createHeaderFooterPolicy();
            hfp.createFooter(XWPFHeaderFooterPolicy.DEFAULT, pars);

            // Write the Document in file system
            FileOutputStream out = new FileOutputStream(new File(evidencePath + Hook.sFeatureName + " "+timestamp() + ".doc"));

            System.out.println("Write the Document in file system");
            document.write(out);
            out.close();

            // Print a confirmation image to console
            System.out.println("Word document created successfully!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
