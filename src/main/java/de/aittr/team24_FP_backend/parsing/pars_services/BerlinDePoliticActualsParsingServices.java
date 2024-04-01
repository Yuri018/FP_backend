package de.aittr.team24_FP_backend.parsing.pars_services;

import de.aittr.team24_FP_backend.parsing.pars_models.BerlinDePoliticActualParsObj;
import de.aittr.team24_FP_backend.parsing.pars_repositories.BerlinDePoliticsParsingRepository;
import de.aittr.team24_FP_backend.parsing.pars_services.interfaces_and_abstract_classes.BerlinCommonParsingTextService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BerlinDePoliticActualsParsingServices extends BerlinCommonParsingTextService {

    BerlinDePoliticsParsingRepository berlinPoliticsParsingRepository;

    public BerlinDePoliticActualsParsingServices(BerlinDePoliticsParsingRepository berlinPoliticsParsingRepository) {
        this.berlinPoliticsParsingRepository = berlinPoliticsParsingRepository;
    }

    public BerlinDePoliticActualParsObj parsingTextPoliticActuals1() {

        BerlinDePoliticActualParsObj politicActuals2 = new BerlinDePoliticActualParsObj();

        try {
            Document basDoc = getDocument(URL, REFERRER);
            Elements elsPolitics = basDoc.select("body > div#page-wrapper > div#layout-grid >div#layout-grid__area--herounit > div > div > div.mainbar__right > ul > li:nth-child(1) > div > div > div.image__overlay.align--bottom ");
            String urlToPolitics = URL + getURLToPage(elsPolitics, 0);
            Document docPolitics = getDocument(urlToPolitics, URL);
            Elements elsActuals2 = docPolitics.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > div:nth-child(3) > article:nth-child(2) > h3 ");
            String urlToActuals2 = URL + getURLToPage(elsActuals2, 0);
            Document docToActuals2 = getDocument(urlToActuals2, urlToPolitics);
            Element elsImg = docToActuals2.getElementsByClass("image image--force-2x1 article-mainimage").first();
            String imgUrl = URL + elsImg.child(0).child(0).attr("src");
            Element elImgCopyright = elsImg.child(2);
            String imgCopyright = elImgCopyright.ownText();
            Elements elsTitleActuals2 = docToActuals2.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > h1");
            String titleActuals1 = elsTitleActuals2.text();
            Element elsTextP1 = docToActuals2.getElementsByClass("article__introtext").first();
            String firstParagraph = elsTextP1.ownText();
            Element elsTextP2 = docToActuals2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0);
            String secondParagraph = elsTextP2.ownText();
            Element elsTextP3 = docToActuals2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1);
            String thirdSubTitle = elsTextP3.select("h3.title").text();
            String thirdParagraph = elsTextP3.child(1).child(0).ownText();
            Element elsTextP4 = docToActuals2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2);
            String fourthSubTitle = elsTextP4.select("h3.title").text();
            String fourthParagraph = getTextFromNodes(elsTextP4.child(1).child(0));
            StringBuilder textActuals2 = new StringBuilder();

            String contentActuals2 = textActuals2
                    .append(firstParagraph)
                    .append("\n")
                    .append(secondParagraph)
                    .append("\n\n")
                    .append(thirdSubTitle)
                    .append("\n")
                    .append(thirdParagraph)
                    .append("\n")
                    .append(fourthSubTitle)
                    .append("\n")
                    .append(fourthParagraph)
                    .toString();

            politicActuals2.setTitle(titleActuals1);
            politicActuals2.setContent(contentActuals2);
            politicActuals2.setImgUrl(imgUrl);
            politicActuals2.setImgCopyright(imgCopyright);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return politicActuals2;
    }

    public BerlinDePoliticActualParsObj savePoliticActuals2() {
        return saveObjectIfNotExists(
//                () -> parsingTextPoliticActuals1(),
                this::parsingTextPoliticActuals1,
                berlinPoliticsParsingRepository,
                BerlinDePoliticActualParsObj::getTitle
//                BerlinDePoliticActualParsObj -> BerlinDePoliticActualParsObj.getTitle()
        );
    }

    /*static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";
    static final int TIMEOUT = 5000;
    static final String REFERRER = "https://www.google.com";
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String URL = "https://www.berlin.de";


    public static String getURLToPage(Elements elements) {
        String urlToPage = elements.get(0).child(0).attr("href");
        return urlToPage;
    }

    public static Document getDoc(String url, String referrer) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(TIMEOUT)
                .referrer(referrer)
                .get();
        return doc;
    }

    private static String getTextFromNodes(Node node) {
        StringBuilder sb = new StringBuilder();
        if (node instanceof TextNode) {
            sb.append(((TextNode) node).text());
        } else if (node instanceof Element) {
            Element element = (Element) node;
            if (element.tagName().equalsIgnoreCase("a")) {
                sb.append(element.text());
            } else {
                for (Node child : element.childNodes()) {
                    sb.append(getTextFromNodes(child));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        try {
            Document basDoc = getDoc(URL, REFERRER);
            Elements elsPolitics = basDoc.select("body > div#page-wrapper > div#layout-grid >div#layout-grid__area--herounit > div > div > div.mainbar__right > ul > li:nth-child(1) > div > div > div.image__overlay.align--bottom ");
            String urlToPolitics = URL + getURLToPage(elsPolitics);
            Document docPolitics = getDoc(urlToPolitics, URL);
            Elements elsActuals1 = docPolitics.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > div:nth-child(3) > article:nth-child(2) > h3 ");
            String urlToActuals1 = URL + getURLToPage(elsActuals1);
            Document docToActuals1 = getDoc(urlToActuals1, urlToPolitics);
            Element elsImg = docToActuals1.getElementsByClass("image image--force-2x1 article-mainimage").first();
            String imgUrl = URL + elsImg.child(0).child(0).attr("src");
            Element elImgCopyright = elsImg.child(2);
            String imgCopyright = elImgCopyright.ownText();
            Elements elsTitleActuals1 = docToActuals1.select("body > div#page-wrapper > div#layout-grid > div#layout-grid__area--maincontent > h1");
            String titleActuals1 = elsTitleActuals1.text();
            Element elsTextP1 = docToActuals1.getElementsByClass("article__introtext").first();
            String firstParagraph = elsTextP1.ownText();
            Element elsTextP2 = docToActuals1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0);
            String secondParagraph = elsTextP2.ownText();
            Element elsTextP3 = docToActuals1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1);
            String thirdSubTitle = elsTextP3.select("h3.title").text();
            String thirdParagraph = elsTextP3.child(1).child(0).ownText();
            Element elsTextP4 = docToActuals1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2);
            String fourthSubTitle = elsTextP4.select("h3.title").text();
            String fourthParagraph = getTextFromNodes(elsTextP4.child(1).child(0));

//            Elements elsLocation = docExhibitEvent3.getElementsByClass("info-container-list list--horizontal list--contact");
//            String where = elsLocation.get(0).child(3).child(0).ownText();
//            String from = elsLocation.get(0).child(5).ownText();
//            String to = elsLocation.get(0).child(7).ownText();
//            String when = elsLocation.get(0).child(9).ownText();
//            String admission = elsLocation.get(0).child(10).ownText();
//            String admissSyst = elsLocation.get(0).child(11).child(0).ownText();
//
            StringBuilder textActuals1 = new StringBuilder();

            String contentActuals1 = textActuals1
                    .append(firstParagraph)
                    .append("\n")
                    .append(secondParagraph)
                    .append("\n\n")
                    .append(thirdSubTitle)
                    .append("\n")
                    .append(thirdParagraph)
                    .append("\n")
                    .append(fourthSubTitle)
                    .append("\n")
                    .append(fourthParagraph)
                    .toString();
//                    .append("Location:\t").append(where)
//                    .append("\n")
//                    .append("Zeitraum:\t").append(from).append(" - ").append(to)
//                    .append("\n")
//                    .append("Wann:\t").append(when)
//                    .append("\n")
//                    .append(admission).append(":\t").append(admissSyst)
//                    .toString();
//
            System.out.println(contentActuals1);
//            System.out.println(urlToPolitics);
//            System.out.println(urlToActuals1);
//            System.out.println(urlToExhibitEvent3);
//            System.out.println(imgUrl);
//            System.out.println(imgCopyright);
//            System.out.println(titleActuals1);
//            System.out.println(firstParagraph);
//            System.out.println(secondParagraph);
//            System.out.println(thirdSubTitle);
//            System.out.println(thirdParagraph);
//            System.out.println(fourthSubTitle);
//            System.out.println(fourthParagraph);
//            System.out.println(where);
//            System.out.println(from);
//            System.out.println(to);
//            System.out.println(when);
//            System.out.println(admission);
//            System.out.println(admissSyst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
