package de.aittr.team24_FP_backend.parsing.pars_services;

import de.aittr.team24_FP_backend.parsing.pars_models.BerlinDeCultEventsParsObj;
import de.aittr.team24_FP_backend.parsing.pars_repositories.BerlinDeCultEventsParsingRepository;
import de.aittr.team24_FP_backend.parsing.pars_services.interfaces_and_abstract_classes.BerlinCommonParsingTextService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BerlinDeCultEventsParsingService extends BerlinCommonParsingTextService {

    private BerlinDeCultEventsParsingRepository berlinDeCultEventsParsingRepository;

    public BerlinDeCultEventsParsingService(BerlinDeCultEventsParsingRepository berlinDeCultEventsParsingRepository) {
        this.berlinDeCultEventsParsingRepository = berlinDeCultEventsParsingRepository;
    }

        public BerlinDeCultEventsParsObj parsingTextCultEvent1() {

        BerlinDeCultEventsParsObj cultEvent1 = new BerlinDeCultEventsParsObj();

        try {
            Document basDoc = getDocument(URL, REFERRER);
            Elements elsCult = basDoc.select("body > div#page-wrapper > div#layout-grid >div#layout-grid__area--herounit > div > div > div.mainbar__right > ul > li:nth-child(2) > div > div > div.image__overlay.align--bottom ");
            String urlToCult = URL + getURLToPage(elsCult, 0);
            Document docCulture = getDocument(urlToCult, URL);
            Elements elsCultEvents = docCulture.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > div:nth-child(3) > article:nth-child(1) > h3 ");
            String urlToEvents = URL + getURLToPage(elsCultEvents, 0);
            Document docCultEvents = getDocument(urlToEvents, urlToCult);
            Elements elsCultEvent1 = docCultEvents.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > div > article:nth-child(5) > h3");
            String urlToEvent1 = URL + getURLToPage(elsCultEvent1, 0);
            Document docEvent1 = getDocument(urlToEvent1, urlToEvents);
            Element elsImg = docEvent1.getElementsByClass("js-imageblur").first();
            String imageUrl = URL + elsImg.attr("src");
            Element elImgCopyright = docEvent1.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            Elements elsTitEvent1 = docEvent1.select("body:nth-child(2) div.foxtrot:nth-child(1) div:nth-child(2) div:nth-child(2) > h1.article__title.heading--article:nth-child(2)");
            String titleEvent1 = elsTitEvent1.text();
            Element elsTextP1 = docEvent1.getElementsByClass("article__introtext").first();
            String firstParagraph = elsTextP1.ownText();
            Element elsTextP2 = docEvent1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0);
            String secondParagraph = elsTextP2.ownText();
            Element elsTextP3 = docEvent1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1);
            String thirdSubTitle = elsTextP3.select("h3.title").text();
            String thirdParagraph = elsTextP3.child(1).child(0).ownText();
            Elements elsLocation = docEvent1.getElementsByClass("info-container-list list--horizontal list--contact");
            String where = elsLocation.get(0).child(3).ownText();
            String from = elsLocation.get(0).child(5).ownText();
            String to = elsLocation.get(0).child(7).ownText();
            String when = elsLocation.get(0).child(9).ownText();
            String admission = elsLocation.get(0).child(10).ownText();
            String admissSyst = elsLocation.get(0).child(11).child(0).ownText();

            StringBuilder textEvent1 = new StringBuilder();

            String descrEvent1 = textEvent1
                    .append(firstParagraph).append("\n")
                    .append(secondParagraph).append("\n\n")
                    .append(thirdSubTitle).append("\n")
                    .append(thirdParagraph).append("\n")
                    .append("Location:\t").append(where).append("\n")
                    .append("Zeitraum:\t").append(from).append(" - ").append(to).append("\n")
                    .append("Wann:\t").append(when).append("\n")
                    .append(admission).append(":\t").append(admissSyst)
                    .toString();

            cultEvent1.setTitle(titleEvent1);
            cultEvent1.setContent(descrEvent1);
            cultEvent1.setImageUrl(imageUrl);
            cultEvent1.setImgCopyright(imgCopyright);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cultEvent1;
    }

    public BerlinDeCultEventsParsObj saveCultEvent1() {
        return saveObjectIfNotExists(
                this::parsingTextCultEvent1,
                berlinDeCultEventsParsingRepository,
                BerlinDeCultEventsParsObj::getTitle
        );
    }
}
