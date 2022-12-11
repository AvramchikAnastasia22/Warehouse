package warehouse.service;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExcelExporter {
    void createCell(Row row, int columnCount, Object value, CellStyle style);
    void writeHeaderLine();
    void export(HttpServletResponse response) throws IOException;
    void writeDataLines();
}
