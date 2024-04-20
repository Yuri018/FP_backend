package de.aittr.team24_FP_backend.parsing.news_pars_services;

import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.parsing.news_pars_models.BerlinDeNewsParsObj;
import de.aittr.team24_FP_backend.parsing.news_pars_models.LocalNewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.MuenchenDeNewsParsObj;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.LocalNewsCategoryRepository;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.MuenchenDeNewsParsingRepository;
import de.aittr.team24_FP_backend.services.translation_service.TranslationService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MuenchenDeNewsParsingTranslateService extends CommonParsingTextService {

    MuenchenDeNewsParsingRepository muenchenDeNewsParsingRepository;
    LocalNewsCategoryRepository localNewsCategoryRepository;
    TranslationService translationService;

    public MuenchenDeNewsParsingTranslateService(
            MuenchenDeNewsParsingRepository muenchenDeNewsParsingRepository,
            LocalNewsCategoryRepository localNewsCategoryRepository,
            TranslationService translationService) {
        this.muenchenDeNewsParsingRepository = muenchenDeNewsParsingRepository;
        this.localNewsCategoryRepository = localNewsCategoryRepository;
        this.translationService = translationService;
    }

    public void muenchenEvent1ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(MUENCHEN_NEWS_URL, REFERRER);
            Element elEinblicke = basDoc.getElementsByClass("m-teaser-vertical__headline").get(3);
            String shortDescription = elEinblicke.text();
            String urlToEinblicke = MUENCHEN_NEWS_URL + elEinblicke.child(0).attr("href");
            Document docEinblicke = getDocument(urlToEinblicke, MUENCHEN_NEWS_URL);
            Elements elsArticleFirstPart = docEinblicke.getElementsByClass("m-intro-editorial-service__content");
            String title = elsArticleFirstPart.get(0).child(0).text();
            String p1 = elsArticleFirstPart.get(0).child(1).text();
            Elements elsImage = docEinblicke.getElementsByClass("m-intro-editorial-service__image");
            String srcset = elsImage.select("picture").first().select("source").first().attr("srcset");
            int index = srcset.indexOf("jpg");
            String imgUrl = MUENCHEN_NEWS_URL + srcset.substring(0, index + 3);
            String p2subTitle = docEinblicke.getElementsByClass("m-component m-component-textplus").get(0).select("h2").text();

            Elements p2a = docEinblicke.getElementsByClass("m-component m-component-textplus").get(0).select("p");
            String p2 = "";
            for (Element paragraph : p2a) {
                Elements strongTags = paragraph.select("strong");
                String paragraphText = paragraph.text();
                String lastStrongText = strongTags.last().text();
                p2 += paragraphText.substring(0, paragraphText.lastIndexOf(lastStrongText));
            }

            StringBuilder textEinblicke1 = new StringBuilder();

            String contentTextEinblicke1= textEinblicke1
                    .append(p1)
                    .append("\n")
                    .append(p2subTitle)
                    .append("\n\n")
                    .append(p2)
                    .toString();

            MuenchenDeNewsParsObj muenchenDeNewsParsObj = new MuenchenDeNewsParsObj();
            LocalNewsCategory muenchenNewsCategory = localNewsCategoryRepository.findByTitle("ACTUAL_NEWS");

            muenchenDeNewsParsObj.setTitle(translationService.translateText(title));
            muenchenDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
            muenchenDeNewsParsObj.setImgUrl(imgUrl);
            muenchenDeNewsParsObj.setContent(translationService.translateText(contentTextEinblicke1));
            muenchenDeNewsParsObj.setLocalNewsCategory(muenchenNewsCategory);

            saveObjectIfNotExists(
                    () -> muenchenDeNewsParsObj,
                    muenchenDeNewsParsingRepository,
                    MuenchenDeNewsParsObj::getTitle
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
    /*static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";
    static final int TIMEOUT = 5000;
    static final String REFERRER = "https://www.google.com";
    static final String MUENCHEN_NEWS_URL = "https://www.muenchen.de";


   *//* public static String getURLToBerlinNewsSects(Element element, int index) {
        String urlToNewsSection = MUENCHEN_NEWS_URL + element.child(index).child(0).child(0).child(1).child(0).attr("href");
        return urlToNewsSection;
    }*//*

    public static Document getDoc(String url, String referrer) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(TIMEOUT)
                .referrer(referrer)
                .get();
        return doc;

    }

    public static void main(String[] args) {


        try {
            Document basDoc = getDoc(MUENCHEN_NEWS_URL, REFERRER);
            Element elEinblicke = basDoc.getElementsByClass("m-teaser-vertical__headline").get(3);
            String urlToEinblicke = MUENCHEN_NEWS_URL + elEinblicke.child(0).attr("href");
            Document docEinblicke = getDoc(urlToEinblicke, MUENCHEN_NEWS_URL);
            Elements elsArticleFirstPart = docEinblicke.getElementsByClass("m-intro-editorial-service__content");
            String title = elsArticleFirstPart.get(0).child(0).text();
            String p1 = elsArticleFirstPart.get(0).child(1).text();
            Elements elsImage = docEinblicke.getElementsByClass("m-intro-editorial-service__image");
            String srcset = elsImage.select("picture").first().select("source").first().attr("srcset");
            int index = srcset.indexOf("jpg");
            String imgUrl = MUENCHEN_NEWS_URL + srcset.substring(0, index + 3);
            String p2subTitle = docEinblicke.getElementsByClass("m-component m-component-textplus").get(0).select("h2").text();

            Elements p2a = docEinblicke.getElementsByClass("m-component m-component-textplus").get(0).select("p");
            String p2 = "";
            for (Element paragraph : p2a) {
                Elements strongTags = paragraph.select("strong");
                String paragraphText = paragraph.text();
                String lastStrongText = strongTags.last().text();
                p2 += paragraphText.substring(0, paragraphText.lastIndexOf(lastStrongText));
            }
//            Element elActualNews3 = docPolitic.getElementsByClass("modul-teaser span4").get(2);
//            String shortDescription = elActualNews3.child(2).child(0).selectFirst("p.text").ownText();
//            Document docActualNews3 = getDoc(urlToActualNews3, urlToEinblicke);
//            Element elImg = docActualNews3.getElementsByClass("image image--force-2x1 article-mainimage").first();
//            String imageUrl = BERLIN_NEWS_URL  + elImg.child(0).child(0).attr("src");
//            Element elImgCopyright = elImg.select(".image__copyright").first();
//            String imgCopyright = elImgCopyright.ownText();
//            String title = docActualNews3.getElementsByClass("article__title heading--article").first().ownText();
//            String firstParagraph = docActualNews3.getElementsByClass("article__introtext").first().ownText();
//            String secondParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0).ownText();
//            String thirdSubTitle = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(0).text();
//            String thirdParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(1).child(0).ownText();
//            String fourthSubTitle = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(0).text();
//            String fourthParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(1).child(0).ownText();
//            String fifthSubTitle = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(3).child(0).text();
//            String fifthParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(3).child(1).child(0).ownText();
////            Elements elsLocation = docActualNews3.getElementsByClass("info-container-list list--horizontal list--contact");
////            String where = elsLocation.get(0).child(3).ownText();
////            String from = elsLocation.get(0).child(5).ownText();
////            String to = elsLocation.get(0).child(7).ownText();
////            String when = elsLocation.get(0).child(9).ownText();
////            String when1 = elsLocation.get(0).child(11).ownText();
////            String admission = elsLocation.get(0).child(12).ownText();
////            String admissSyst = elsLocation.get(0).child(13).child(0).text();
////
            StringBuilder textEinblicke1 = new StringBuilder();

            String contentTextEinblicke1= textEinblicke1
                    .append(p1)
                    .append("\n")
                    .append(p2subTitle)
                    .append("\n\n")
                    .append(p2)
//                    .append("\n")
//                    .append(thirdParagraph)
//                    .append("\n")
//                    .append(fourthSubTitle)
//                    .append("\n")
//                    .append(fourthParagraph)
//                    .append("\n")
//                    .append(fifthSubTitle)
//                    .append("\n")
//                    .append(fifthParagraph)
//                    .append("\n\n")
//                    .append("Location:\t").append(where)
//                    .append("\n")
//                    .append("Zeitraum:\t").append(from).append(" - ").append(to)
//                    .append("\n")
//                    .append("Wann:\t").append(when).append("\n").append(when1)
//                    .append("\n")
//                    .append(admission).append(":\t").append(admissSyst)
                    .toString();
//
                System.out.println(contentTextEinblicke1);

//            System.out.println(urlToEinblicke);
//            System.out.println(urlToActualNews3);
//            System.out.println(shortDescription);
//            System.out.println(urlToEventActualHighlights4);
//            System.out.println(imageUrl);
//            System.out.println(imgCopyright);
//            System.out.println(title);
//            System.out.println(firstParagraph);
//            System.out.println(secondParagraph);
//            System.out.println(thirdSubTitle);
//            System.out.println(thirdParagraph);
//            System.out.println(fourthSubTitle);
//            System.out.println(fourthParagraph);
//            System.out.println(fifthSubTitle);
//            System.out.println(fifthParagraph);
//            System.out.println(where);
//            System.out.println(from);
//            System.out.println(to);
//            System.out.println(when);
//            System.out.println(when1);
//            System.out.println(admission);
//            System.out.println(admissSyst);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
*/

