package HTML2ICS;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Html2IcsConventer {
    public String getDataFromWeeia(){
        try {
            Document doc = Jsoup.connect("http://www.weeia.p.lodz.pl/").get();
            Elements elements = doc.select("td.active");
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
