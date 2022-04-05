package input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements DocumentReader {
	//private String stateB ="excel"; 
	private String path;
	public ExcelReader(String Filename){
		this.path=Filename;
	}
	public ArrayList<String> read(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			FileInputStream stream = new FileInputStream(path);
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(stream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			int columns = sheet.getRow(0).getLastCellNum();
			for(int i=1;i<=rows;i++) {													//MAYBE PROBLEM WITH THE <=
				String x="";
				XSSFRow row = sheet.getRow(i-1);
				for(int y=0;y<columns;y++) { 											///MAYBE PROBLEM WITH THE <=
					XSSFCell cell = row.getCell(y);
					x+=cell.getStringCellValue();
					if(y!=columns-1) {
						x+=" ";	
					}
				}
				list.add(x);
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}	
}