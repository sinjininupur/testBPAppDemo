package  com.ibm.bp.testapp.utilities;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcellReading {

	// public Workbook workbook= null;

	// public Sheet firstSheet= null;

	public  String INPUT_XLS = "TestData.xlsx";

	public ExcellReading() {

	}

	/*public ExcellReading(String filepath) {

		INPUT_XLS = filepath;

	}*/
	
	public static String[][] ReadExcel(String fpath, int sheetNumber) throws IOException {

		FileInputStream inputStream = new FileInputStream(new File(fpath));

		// String arr[][]=null;
		Map<Integer, List<String>> data = new HashMap<Integer, List<String>>();

		Workbook workbook = new XSSFWorkbook(inputStream);

		Sheet firstSheet = workbook.getSheetAt(sheetNumber);

		Iterator<Row> iterator = firstSheet.iterator();

		// Test test=new Test();

		int rowCnt = 0;

		String[][] arr = new String[firstSheet.getPhysicalNumberOfRows()][firstSheet.getRow(0)
				.getPhysicalNumberOfCells()];

		int i = 0;
		while (iterator.hasNext()) {

			Row nextRow = iterator.next();

			Iterator<Cell> cellIterator = nextRow.cellIterator();

			List<String> obj = new ArrayList<String>();

			int j = 0;
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				// String cellobj = cell.getStringCellValue();

				// if ("".equals(cell.getStringCellValue())) {
				//
				// obj.add("Missing");
				//
				//
				//
				// } else if (cellobj.equals(null)) {
				//
				// obj.add("");
				//
				//
				//
				// } else {
				//
				// obj.add(cell.getStringCellValue());
				//
				// }
				//
				//
				//
				// }

				switch (cell.getCellType()) {

				case Cell.CELL_TYPE_STRING:

					System.out.print(cell.getStringCellValue());
					obj.add(cell.getStringCellValue());
					arr[i][j] = cell.getStringCellValue();
					break;

				case Cell.CELL_TYPE_BOOLEAN:

					System.out.print(cell.getBooleanCellValue());
					obj.add(cell.getStringCellValue());
					arr[i][j] = cell.getStringCellValue();
					break;

				case Cell.CELL_TYPE_NUMERIC:

					System.out.print(cell.getNumericCellValue());
					double a = cell.getNumericCellValue();
					int b = (int) a;
					String s = String.valueOf(b);
					// String newValue = a.toString(Math.floor(1));
					obj.add(s);
					arr[i][j] = s;
					break;

				}
				j++;

			}

			i++;
			data.put(rowCnt, obj);

			rowCnt++;

		}

		// return data;
		return arr;
	}

	public static void main(String[] args) throws IOException {
		int sheetNumber=0;
		String fpath="";
		ReadExcel(fpath, sheetNumber);
	}

	public String getData(String sheetname, int rowNumber, String columnName) throws IOException {

		FileInputStream inputStream = new FileInputStream(new File(INPUT_XLS));

		//Map<Integer, List<String>> data = new HashMap<Integer, List<String>>();

		Workbook workbook = new XSSFWorkbook(inputStream);

		Sheet sheet = workbook.getSheet(sheetname);

		int flag = 0;
		String cellValue = "";
		Row headerRow = sheet.getRow(0);
		Row row = sheet.getRow(rowNumber);
		
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			//Iterator<Cell> cellIterator = row.cellIterator();
			Iterator<Cell> headerIterator = headerRow.cellIterator();
			while (headerIterator.hasNext()) {
				Cell headerCell = headerIterator.next();
				if(headerCell.getStringCellValue().compareTo(columnName)==0){
					Cell cell = row.getCell(headerCell.getColumnIndex());
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:

						cellValue = cell.getStringCellValue();
						break;

					case Cell.CELL_TYPE_BOOLEAN:

						cellValue = cell.getStringCellValue();
						break;

					case Cell.CELL_TYPE_NUMERIC:
						//cellValue = cell.getStringCellValue();
						//break;

						cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
						break;
						
					case Cell.CELL_TYPE_FORMULA:

						cellValue = evaluator.evaluateInCell(cell).toString();
						//Utilities.printLineToReport("Verification", "Date:"+cellValue, "pass");
						
						
							
						
					
						
						/*String da = cellValue;
						SimpleDateFormat ft = new SimpleDateFormat("d-m-yyyy");
						try {
							Date dcellValue=(Date) ft.parse(da);
							cellValue =dcellValue.toString();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						break;

					}

					flag = 1;
					break;
				}			
			}	
		if(flag==0){
			System.err.println("Column doesn't exist");
		}
		return cellValue;
	} 

	public String getDataFromPath(String fpath, String sheetname, int rowNumber, String columnName) throws IOException {

		FileInputStream inputStream = new FileInputStream(new File(fpath));

		//Map<Integer, List<String>> data = new HashMap<Integer, List<String>>();

		Workbook workbook = new XSSFWorkbook(inputStream);

		Sheet sheet = workbook.getSheet(sheetname);

		int flag = 0;
		String cellValue = "";
		Row headerRow = sheet.getRow(0);
		Row row = sheet.getRow(rowNumber);

			//Iterator<Cell> cellIterator = row.cellIterator();
			Iterator<Cell> headerIterator = headerRow.cellIterator();
			while (headerIterator.hasNext()) {
				Cell headerCell = headerIterator.next();
				if(headerCell.getStringCellValue().compareTo(columnName)==0){
					Cell cell = row.getCell(headerCell.getColumnIndex());
					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:

						cellValue = cell.getStringCellValue();
						break;

					case Cell.CELL_TYPE_BOOLEAN:

						cellValue = cell.getStringCellValue();
						break;

					case Cell.CELL_TYPE_NUMERIC:

						cellValue = String.valueOf((int)cell.getNumericCellValue());
				
						
						break;

					}

					flag = 1;
					break;
				}			
			}	
		if(flag==0){
			System.err.println("Column doesn't exist");
		}
		return cellValue;
	} 

	public void setData(String sheetname, int rowNumber, String columnName,String data,String excelFileName) throws IOException {

		FileInputStream inputStream = new FileInputStream(new File(INPUT_XLS));


		//Map<Integer, List<String>> data = new HashMap<Integer, List<String>>();

		Workbook workbook = new XSSFWorkbook(inputStream);
		//workbook.
		Sheet sheet = workbook.getSheet(sheetname);

		int flag = 0;
		String cellValue = "";
		Row headerRow = sheet.getRow(0);
		Row row = sheet.getRow(rowNumber);
		
			//Iterator<Cell> cellIterator = row.cellIterator();
			Iterator<Cell> headerIterator = headerRow.cellIterator();
			Byte[] outputData;
			while (headerIterator.hasNext()) {
				Cell headerCell = headerIterator.next();
				if(headerCell.getStringCellValue().compareTo(columnName)==0){
					Cell cell = row.getCell(headerCell.getColumnIndex());
					
					cell.setCellValue(data);
					//workbook.write(inputStream);
					
					//inputStream.flush();
					//inputStream.close();
					/*outputData = Byte.parseByte(data);
					flag = 1;
					//FileOutputStream fileOut = new FileOutputStream(excelFileName);
					fileOut.write(outputData);
					//write this workbook to an Outputstream.
					workbook.write(fileOut);
					workbook.
					fileOut.flush();
					fileOut.close();*/

					}

					
					
				}			
			
		if(flag==0){
			System.err.println("Column doesn't exist");
		}
		
	} 
	
	
public static void writeData(String fpath,String sheetname, int rowNumber, String columnName, String data) throws IOException, OpenXML4JException {
        
	
	FileInputStream inputStream = new FileInputStream(new File(fpath));

	//Map<Integer, List<String>> data = new HashMap<Integer, List<String>>();

	Workbook workbook = new XSSFWorkbook(inputStream);

	Sheet sheet = workbook.getSheet(sheetname);

	int flag = 0;
	String cellValue = "";
	Row headerRow = sheet.getRow(0);
	Row row = sheet.getRow(rowNumber);
//	HSSFRow row2 = (HSSFRow) sheet.getRow(rowNumber);
	//Row row2 = sheet.getRow(rowNumber+1);
		//Iterator<Cell> cellIterator = row.cellIterator();
		Iterator<Cell> headerIterator = headerRow.cellIterator();
		//Iterator<Cell> dataIterator = row.cellIterator();
		while (headerIterator.hasNext()) {
			Cell headerCell = headerIterator.next();
			if(headerCell.getStringCellValue().compareTo(columnName)==0){
				Cell cell = row.getCell(headerCell.getColumnIndex());
				 if (!(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)) {   //cell is empty
					 cell.setCellValue(data);
			        }
				 else
				 {
					XSSFCell cellx =  (XSSFCell) row.createCell(headerCell.getColumnIndex()); 
					 cellx.setCellValue(data);
				 }
				
				//cell.setCellValue(data);
			}		
		}	
	
		inputStream.close();
	
		FileOutputStream fileOut = new FileOutputStream(new File(fpath));
		workbook.write(fileOut);
		//close the stream
		fileOut.close();
	
      /*  OPCPackage pkg = OPCPackage.open(new File(fpath));
          
        Workbook workbook = new XSSFWorkbook(pkg);
        
        Sheet sheet = workbook.getSheet(sheetname);
        int flag = 0;
        String cellValue = "";
        Row headerRow = sheet.getRow(0);
        Row row = sheet.getRow(rowNumber);
        //Iterator<Cell> cellIterator = row.cellIterator();
        Iterator<Cell> headerIterator = headerRow.cellIterator();
        Byte[] outputData;
        while (headerIterator.hasNext()) {
        
            Cell headerCell = headerIterator.next();
            
            if(headerCell.getStringCellValue().compareTo(columnName)==0){
                
                Cell cell = row.getCell(headerCell.getColumnIndex());
        
                cell.setCellValue(data);
            }
          
        }
        
        pkg.close();*/
        
    }

	
public int getRowCount(String sheetName, String columnName ) throws FileNotFoundException, IOException
{
    Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(INPUT_XLS)));
    
    Sheet sheet = workbook.getSheet(sheetName);
    
    Iterator<Cell> headerIterator = sheet.getRow(0).cellIterator();
    
    int flag=0;
    Cell headerCell= null;
    while (headerIterator.hasNext())
    {        
        headerCell = headerIterator.next();
        if(headerCell.getStringCellValue().contentEquals(columnName))
        {
            flag=1;
            break;
        }      
    }
    
    int count = 0;
    if(flag==1)
    {
        Iterator<Row> rowIterator = sheet.rowIterator();
        while(rowIterator.hasNext())
        {
            if(rowIterator.next().getCell(headerCell.getColumnIndex()).getStringCellValue()!=null)
                count++;
        }
    }
    //workbook.
    return (count-1);
}

public String[] getCellsInColumn(String sheetName, String columnname) throws FileNotFoundException, IOException
{
    
    Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(INPUT_XLS)));
    Sheet sheet = workbook.getSheet(sheetName);
    
    Iterator<Cell> headerIterator = sheet.getRow(0).cellIterator();
    int flag=0;
    Cell headerCell= null;
    while (headerIterator.hasNext())
    {        
        headerCell = headerIterator.next();
        if(headerCell.getStringCellValue().contentEquals(columnname))
        {
            flag=1;
            break;
        }      
    }
    
    int nrows = sheet.getPhysicalNumberOfRows();
    String data[] = new String[nrows];
    int count = 0;
    if(flag==1)
    {
        Iterator<Row> rowIterator = sheet.rowIterator();
        while(rowIterator.hasNext())
        {
            String cellvalue = rowIterator.next().getCell(headerCell.getColumnIndex()).getStringCellValue();
            if(cellvalue!=null)
            {
                data[count] = cellvalue;
                count++;
            }
                
        }
    }
    
    return data;
    
}



}
