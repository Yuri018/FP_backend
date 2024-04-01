package de.aittr.team24_FP_backend.parsing.pars_services;

import de.aittr.team24_FP_backend.parsing.pars_models.BerlinDeCultEventsParsObj;
import de.aittr.team24_FP_backend.parsing.pars_models.BerlinDeExhibitionsParsObj;
import de.aittr.team24_FP_backend.parsing.pars_repositories.BerlinDeExhibitionsParsingRepository;
import de.aittr.team24_FP_backend.parsing.pars_services.interfaces_and_abstract_classes.BerlinCommonParsingTextService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BerlinDeExhibitionsParsingService extends BerlinCommonParsingTextService {

    private BerlinDeExhibitionsParsingRepository berlExhibitParsingRepository;

    public BerlinDeExhibitionsParsingService(BerlinDeExhibitionsParsingRepository berlExhibitParsingRepository) {
        this.berlExhibitParsingRepository = berlExhibitParsingRepository;
    }

    public BerlinDeExhibitionsParsObj parsingTextExhibitionEvent3() {

        BerlinDeExhibitionsParsObj exhibitEvent3 = new BerlinDeExhibitionsParsObj();

        try {
            Document basDoc = getDocument(URL, REFERRER);
            Elements elsCult = basDoc.select("body > div#page-wrapper > div#layout-grid >div#layout-grid__area--herounit > div > div > div.mainbar__right > ul > li:nth-child(2) > div > div > div.image__overlay.align--bottom ");
            String urlToCult = URL + getURLToPage(elsCult, 0);
            Document docCulture = getDocument(urlToCult, URL);
            Elements elsExhibitions = docCulture.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > div:nth-child(5) > article:nth-child(2) > h3 ");
            String urlToExhibitions = URL + getURLToPage(elsExhibitions, 0);
            Document docExhibitions = getDocument(urlToExhibitions, urlToCult);
            Element elExhibitHighlights3 = docExhibitions.getElementsByClass("modul-teaser span4").get(0);
            String urlToExhibitEvent3 = URL + elExhibitHighlights3.child(1).child(0).attr("href");
            Document docExhibitEvent3 = getDocument(urlToExhibitEvent3, urlToExhibitions);
            Element elsImg = docExhibitEvent3.getElementsByClass("js-imageblur").first();
            String imageUrl = URL + elsImg.attr("src");
            Element elImgCopyright = docExhibitEvent3.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            Elements elsTitleExhibitEvent3 = docExhibitEvent3.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > h1");
            String titleExhibitEvent3 = elsTitleExhibitEvent3.text();
            Element elsTextP1 = docExhibitEvent3.getElementsByClass("article__introtext").first();
            String firstParagraph = elsTextP1.ownText();
            Element elsTextP2 = docExhibitEvent3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0);
            String secondParagraph = elsTextP2.ownText();
            Element elsTextP3 = docExhibitEvent3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1);
            String thirdSubTitle = elsTextP3.select("h3.title").text();
            String thirdParagraph = elsTextP3.child(1).child(0).ownText();
            Elements elsLocation = docExhibitEvent3.getElementsByClass("info-container-list list--horizontal list--contact");
            String where = elsLocation.get(0).child(3).child(0).ownText();
            String from = elsLocation.get(0).child(5).ownText();
            String to = elsLocation.get(0).child(7).ownText();
            String when = elsLocation.get(0).child(9).ownText();
            String admission = elsLocation.get(0).child(10).ownText();
            String admissSyst = elsLocation.get(0).child(11).child(0).ownText();

            StringBuilder textExhibitionEvent3 = new StringBuilder();

            String contentExhibitEvent3 = textExhibitionEvent3
                    .append(firstParagraph)
                    .append("\n")
                    .append(secondParagraph)
                    .append("\n\n")
                    .append(thirdSubTitle)
                    .append("\n")
                    .append(thirdParagraph)
                    .append("\n")
                    .append("Location:\t").append(where)
                    .append("\n")
                    .append("Zeitraum:\t").append(from).append(" - ").append(to)
                    .append("\n")
                    .append("Wann:\t").append(when)
                    .append("\n")
                    .append(admission).append(":\t").append(admissSyst)
                    .toString();

            exhibitEvent3.setTitle(titleExhibitEvent3);
            exhibitEvent3.setContent(contentExhibitEvent3);
            exhibitEvent3.setImageUrl(imageUrl);
            exhibitEvent3.setImgCopyright(imgCopyright);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return exhibitEvent3;
    }

    public BerlinDeExhibitionsParsObj saveExhibitEvent3() {
        return saveObjectIfNotExists(
                this::parsingTextExhibitionEvent3,
                berlExhibitParsingRepository,
                BerlinDeExhibitionsParsObj::getTitle
        );
    }
}
