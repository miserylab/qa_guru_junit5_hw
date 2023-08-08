package guru.qa.helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;

public class ExcelHelper {
    public static String getStringCellValue(Sheet sheet, String address){
        CellReference cellReference1 = new CellReference(address);
        Row row = sheet.getRow(cellReference1.getRow());
        Cell cell = row.getCell(cellReference1.getCol());
        String value = cell.getStringCellValue();

        return value;
    }
    public static Double getDoubleCellValue(Sheet sheet, String address){
        CellReference cellReference1 = new CellReference(address);
        Row row = sheet.getRow(cellReference1.getRow());
        Cell cell = row.getCell(cellReference1.getCol());
        Double value = cell.getNumericCellValue();

        return value;
    }
}
