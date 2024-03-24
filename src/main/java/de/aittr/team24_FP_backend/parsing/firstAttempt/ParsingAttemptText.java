package de.aittr.team24_FP_backend.parsing.firstAttempt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParsingAttemptText {
    public static void main(String[] args) throws IOException {

        String url = "https://www.berlin.de";

        Document baseDoc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .timeout(5000)
                .referrer("https://www.google.com")
                .get();

        Elements cultureEls = baseDoc.select("body > div#page-wrapper > div#layout-grid >div#layout-grid__area--herounit > div > div > div.mainbar__right > ul > li:nth-child(2) > div > div > div.image__overlay.align--bottom ");

        System.out.println(cultureEls.get(0).child(0).text());
        System.out.println(cultureEls.get(0).child(0).attr("href"));

        String urlToCulture = url + cultureEls.get(0).child(0).attr("href");

        System.out.println(urlToCulture);

        Document cultDoc = Jsoup.connect(urlToCulture)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .timeout(5000)
                .get();

        Elements contents = cultDoc.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > div:nth-child(3) > article:nth-child(1) > h3 ");

        System.out.println(contents.get(0).child(0).text());
        System.out.println(contents.get(0).child(0).attr("href"));

        String urlToEvents = url + contents.get(0).child(0).attr("href");

        System.out.println(urlToEvents);

        Document eventsDoc = Jsoup.connect(urlToEvents)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .timeout(5000)
                .get();

        Elements events = eventsDoc.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > div > div.modul-teaser.nolink.span12 > div") ;

        String descr = events.get(0).child(0).text();

        System.out.println(descr);
//



    }
}
