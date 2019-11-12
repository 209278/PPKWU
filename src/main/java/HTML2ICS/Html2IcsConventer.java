package HTML2ICS;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.DateStart;
import biweekly.property.Summary;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

public class Html2IcsConventer {
    public File getDataFromWeeia(String month, String year) throws IOException {
        ICalendar iCalendar = new ICalendar();
        try {
            Document doc = Jsoup.connect("http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month + "&lang=1%22").get();
            Elements elementsDay = doc.select("td.active a.active");
            Elements elementsText = doc.select("td.active div.InnerBox");



            for(int i = 0; i < elementsDay.size(); i++){
                System.out.println(elementsDay.get(i).text() + " " + elementsText.get(i).text());

                VEvent event = new VEvent();
                Summary summary = event.setSummary(elementsText.get(i).text());
                summary.setLanguage("en-us");

                Date start = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(elementsDay.get(i).text())).getTime();
                event.setDateStart(start);
                event.setDateEnd(start);

                iCalendar.addEvent(event);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("test.ics");
        Biweekly.write(iCalendar).go(file);
        return file;
    }
}
