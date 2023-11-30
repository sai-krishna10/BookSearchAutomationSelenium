package MiniProject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static void excel(List<String> category,List<String> count) throws IOException {
		String filepath=System.getProperty("user.dir")+"\\ExcelSheet\\output.xlsx";
		FileOutputStream file=new FileOutputStream(filepath);
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("Sheet1");
		
		XSSFRow row=sheet.createRow(0);
		row.createCell(0).setCellValue("Books Category");
		row.createCell(1).setCellValue("Books Count");
		
		for(int i=2;i<category.size()+2;i++) {
			
			XSSFRow row1=sheet.createRow(i);
			XSSFCell cell1=row1.createCell(0);
			cell1.setCellValue(category.get(i-2));
				
			XSSFCell cell2=row1.createCell(1);
			cell2.setCellValue(count.get(i-2));
		}
		workbook.write(file);
		workbook.close();
		file.close();
	}
}
