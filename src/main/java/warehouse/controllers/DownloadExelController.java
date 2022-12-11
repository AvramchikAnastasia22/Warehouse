package warehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import warehouse.model.Supplier;
import warehouse.repository.RepositorySupplier;
import warehouse.service.ExcelFileExporter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DownloadExelController {
    @Autowired
    RepositorySupplier repositorySupplier;
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=suppliers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Supplier> supplierList = repositorySupplier.findAll();

        ExcelFileExporter excelFileExporter = new ExcelFileExporter(supplierList);

        excelFileExporter.export(response);
    }

    /*@GetMapping("/suppliers/export")
    public void downloadExelFile(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = suppliers.xlsx";
        response.setHeader(headerKey, headerValue);
        List<Supplier> supplierList = repositorySupplier.findAll();
        ExcelFileExporter excelFileExporter = new ExcelFileExporter(supplierList);
        try {
            excelFileExporter.export(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }*/
}
