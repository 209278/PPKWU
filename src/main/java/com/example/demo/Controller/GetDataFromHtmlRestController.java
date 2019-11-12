package com.example.demo.Controller;

import HTML2ICS.Html2IcsConventer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class GetDataFromHtmlRestController {
    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    void getData(HttpServletRequest request,
                 HttpServletResponse response,
                 @RequestParam(value = "month") String month, @RequestParam(value = "year") String year) throws IOException {
        Html2IcsConventer html2IcsConventer = new Html2IcsConventer();
        Path file = html2IcsConventer.getDataFromWeeia(month, year).toPath();
        response.setContentType("application/text");
        response.addHeader("Content-Disposition", "attachment; filename="+file);
        Files.copy(file, response.getOutputStream());
        response.getOutputStream().flush();
    }
}
