package de.aittr.team24_FP_backend.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ParsingTextService {

    public Map<String, String> parsingAttemptText() {
        Map<String, String> resultMap = new HashMap<>();

        try {
            String url = "https://www.berlin.de";

            Document baseDoc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .timeout(5000)
                    .referrer("https://www.google.com")
                    .get();

            Elements cultureEls = baseDoc.select("body > div#page-wrapper > div#layout-grid >div#layout-grid__area--herounit > div > div > div.mainbar__right > ul > li:nth-child(2) > div > div > div.image__overlay.align--bottom ");

            String urlToCulture = url + cultureEls.get(0).child(0).attr("href");
            Document cultDoc = Jsoup.connect(urlToCulture)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .timeout(5000)
                    .get();

            Elements contents = cultDoc.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > div:nth-child(3) > article:nth-child(1) > h3 ");

            String title = contents.get(0).child(0).text();

            String urlToEvents = url + contents.get(0).child(0).attr("href");

            Document eventsDoc = Jsoup.connect(urlToEvents)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .timeout(5000)
                    .get();

            Elements events = eventsDoc.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > div > div.modul-teaser.nolink.span12 > div");

            String description = events.get(0).child(0).text();

            resultMap.put("title", title);
            resultMap.put("description", description);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

}
