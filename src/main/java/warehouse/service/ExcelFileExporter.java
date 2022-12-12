package warehouse.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import warehouse.model.Supplier;
import warehouse.service.impl.ExcelExporter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelFileExporter implements ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Supplier> supplierList;

    public ExcelFileExporter(List<Supplier> supplierList){
        this.supplierList = supplierList;
        workbook = new XSSFWorkbook();
        sheet  = workbook.createSheet("Suppliers");
    }



    @Override
    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }else if (value instanceof Integer){
            cell.setCellValue((Integer)value);
        } else {
            cell.setCellValue((String) value);

        }
        cell.setCellStyle(style);
    }

    @Override
    public void writeHeaderLine() {
        sheet = workbook.createSheet("Supplier");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Имя", style);
        createCell(row, 2, "Фамилия", style);
        createCell(row, 3, "Отчество", style);
        createCell(row, 4, "Телефон", style);
        createCell(row, 5, "Категория поставляемого товара", style);
    }

 /*   public void export(HttpServletResponse response) throws IOException{
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }*/

    @Override
    public void writeDataLines() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Supplier supplier: supplierList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, supplier.getId(), style);
            createCell(row, columnCount++, supplier.getName(), style);
            createCell(row, columnCount++, supplier.getSecond_name(), style);
            createCell(row, columnCount++, supplier.getPatronymic(), style);
            createCell(row, columnCount++, supplier.getPhone(), style);
            createCell(row, columnCount++, supplier.getCategory(), style);
        }


    }
    @Override
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
