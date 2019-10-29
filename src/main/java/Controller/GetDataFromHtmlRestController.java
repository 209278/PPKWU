package Controller;

import HTML2ICS.Html2IcsConventer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetDataFromHtmlRestController {
    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    String getData(){
        Html2IcsConventer html2IcsConventer = new Html2IcsConventer();
        return html2IcsConventer.getDataFromWeeia();
    }
}
