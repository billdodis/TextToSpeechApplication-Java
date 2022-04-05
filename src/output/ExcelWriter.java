package output;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter implements DocumentWriter{
	private String path;
	private String word="";
	public ExcelWriter(String filepath,ArrayList<String> array) {
		this.path = filepath;
	}
	public void write(ArrayList<String> arrayz) {
		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet sheet = workbook.createSheet();
			for(int y=0;y<arrayz.size();y++) {
				XSSFRow row = sheet.createRow(y);
				String[] linesplitted = arrayz.get(y).split(" ");
				for(int i=0;i<linesplitted.length;i++) {
					XSSFCell cell = row.createCell(i);
					word=linesplitted[i];
					cell.setCellValue(word);
					word="";
				}
			}
			try (FileOutputStream out = new FileOutputStream(path)) {
				workbook.write(out);
				out.close();
				workbook.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
