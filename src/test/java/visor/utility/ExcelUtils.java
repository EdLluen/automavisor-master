package visor.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Calendar;

import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@SuppressWarnings("deprecation")
public class ExcelUtils {


	public static String path;
	public static FileInputStream fis = null;
	public static FileOutputStream fileOut =null;

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	
	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	public static void setExcelFile(String Path,String SheetName) throws Exception {

		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
		} catch (Exception e){
			throw (e);
			
		}
	}

	public static String getTestCaseName(String sTestCase)throws Exception{

		String value = sTestCase;

		try{

			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");	
			value = value.substring(posi + 1);
			
			return value;

		}catch (Exception e){
			throw (e);

		}
	}

	public static int getRowContains(String sTestCaseName, int colNum) throws Exception{

		int i;
		
		try {
			int rowCount = ExcelUtils.getRowUsed();
		
			for ( i=0 ; i<rowCount; i++){
				if  (ExcelUtils.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
					break;
				}
			}

			return i;

		}catch (Exception e){

			throw(e);

		}

	}

	public static int getRowUsed() throws Exception {

		try{
			int RowCount = ExcelWSheet.getLastRowNum();
			return RowCount;

		}catch (Exception e){
			System.out.println(e.getMessage());
			throw (e);

		}
	}

	public static Object[][] getTableArray(String FilePath, String SheetName)    throws Exception{   

		String[][] tabArray = null;

		try{

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 1;
			int startCol = 0;
			int i=0,j=0;
			int ci=0,cj=0;
			int totalRows = getCountRow(SheetName);
			int totalCols = getCountColumn(SheetName);

//			System.out.println("Cantidad de filas y columnas: " + totalRows + " - " + totalCols + " - " + FilePath);
			tabArray=new String[totalRows-1][totalCols];

			for (i=startRow; i <=totalRows-1; i++, ci++ ) {
				for (j=startCol;j<=totalCols-1;j++, cj++){
					tabArray[ci][cj] = getCellData(i,j);
//					System.out.println("valor de tabArray[" + ci + "][" + cj + "]: " + getCellData(i,j));
				}
				j=0; 
				startCol = 0;
				cj=0;
			}

			
		}catch (FileNotFoundException e){

			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();

		}catch (IOException e){

			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();

		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Out of Bound");
			e.printStackTrace();
		}

		return(tabArray);
		
	}

	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public static String getCellData(int RowNum, int ColNum) throws Exception{
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		}catch (Exception e){
			return"";
		}
	}

	// returns the row count in a sheet
	public static int getCountRow(String sheetName){
		int index = ExcelWBook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		ExcelWSheet = ExcelWBook.getSheetAt(index);
		int number=ExcelWSheet.getLastRowNum()+1;
		return number;
		}
		
	}
	

	// returns number of columns in a sheet	
	public static int getCountColumn(String sheetName){
		// check if sheet exists
		if(!isSheetExist(sheetName))
		return -1;
		
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		Row = ExcelWSheet.getRow(0);
		
		if(Row==null)
			return -1;
		
		return Row.getLastCellNum();
		
	}
	
	
	  // find whether sheets exists	
	public static boolean isSheetExist(String sheetName){
		int index = ExcelWBook.getSheetIndex(sheetName);
		if(index==-1){
			index=ExcelWBook.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
					return false;
				else
					return true;
		}
		else
			return true;
	}
	
	

	// returns true if data is set successfully else false
	public static boolean setCellData(String path,String sheetName,String colName,String srowNum, String data){
		try{
			fis = new FileInputStream(path); 
			ExcelWBook = new XSSFWorkbook(fis);

			int rowNum = Integer.parseInt(srowNum.trim());
			if(rowNum<0)
				return false;
			
			int index = ExcelWBook.getSheetIndex(sheetName);
			int colNum=0;

			if(index==-1)
				return false;
		
			ExcelWSheet = ExcelWBook.getSheetAt(index);

			Row = ExcelWSheet.getRow(0);
			for(int i=0;i<Row.getLastCellNum();i++){
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if(Row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum=i;
			}
			
//System.out.println("Flag: OK");
			if(colNum==-1)
				return false;
			
			ExcelWSheet.autoSizeColumn(colNum); 
			Row = ExcelWSheet.getRow(rowNum);
			if (Row == null)
				Row = ExcelWSheet.createRow(rowNum-1);
			
			Cell = Row.getCell(colNum);	
			if (Cell == null)
				Cell = Row.createCell(colNum);

			Cell.setCellValue(data);
	
		    fileOut = new FileOutputStream(path);
	
		    ExcelWBook.write(fileOut);
	
		    fileOut.close();	
	
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	// returns the data from a cell
	/*public static String getCellDataXColumn(String sheetName,String colName,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
			int index = ExcelWBook.getSheetIndex(sheetName);
			int col_Num=-1;

			if(index==-1)
				return "";
		
			ExcelWSheet = ExcelWBook.getSheetAt(index);
			Row = ExcelWSheet.getRow(0);
			for(int i=0;i<Row.getLastCellNum();i++){
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if(Row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num=i;
			}
			
			if(col_Num==-1)
				return "";
		
			ExcelWSheet = ExcelWBook.getSheetAt(index);
//			Row = ExcelWSheet.getRow(rowNum-1);
			Row = ExcelWSheet.getRow(rowNum);
			if(Row==null)
				return "";
			
			Cell = Row.getCell(col_Num);
			
			if(Cell==null)
				return "";
		
			if(Cell.getCellType()==CellType.STRING)
				return Cell.getStringCellValue();
			else if(Cell.getCellType()==CellType.NUMERIC || Cell.getCellType()==CellType.FORMULA ){
				
				String cellText  = String.valueOf(Cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(Cell)) {
		           
					double d = Cell.getNumericCellValue();

					Calendar cal =Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
								cal.get(Calendar.MONTH)+1 + "/" + 
								cellText;

				}

				return cellText;
			}else if(Cell.getCellType()==CellType.BLANK)
				return ""; 
			else 
				return String.valueOf(Cell.getBooleanCellValue());
			
			}catch(Exception e){
			
				e.printStackTrace();
				return "row "+rowNum+" or column "+colName +" does not exist in xls";
			}
	}*/
	

	

}
