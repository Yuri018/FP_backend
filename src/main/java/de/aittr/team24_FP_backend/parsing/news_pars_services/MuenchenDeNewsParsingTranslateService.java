package de.aittr.team24_FP_backend.parsing.news_pars_services;

import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.parsing.news_pars_models.LocalNewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.MuenchenDeNewsParsObj;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.LocalNewsCategoryRepository;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.MuenchenDeNewsParsingRepository;
import de.aittr.team24_FP_backend.services.translation_service.TranslationService;
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
            String urlToEinblick1 = MUENCHEN_NEWS_URL + elEinblicke.child(0).attr("href");
            Document docEinblick1 = getDocument(urlToEinblick1, MUENCHEN_NEWS_URL);
            Elements elsArticleFirstPart = docEinblick1.getElementsByClass("m-intro-editorial-service__content");
            String title = elsArticleFirstPart.get(0).child(0).text();
            String p1 = elsArticleFirstPart.get(0).child(1).text();
            Elements elsImage = docEinblick1.getElementsByClass("m-intro-editorial-service__image");
            String srcset = elsImage.select("picture").first().select("source").first().attr("srcset");
            int index = srcset.indexOf("jpg");
            String imgUrl = MUENCHEN_NEWS_URL + srcset.substring(0, index + 3);
            String p2subTitle = docEinblick1.getElementsByClass("m-component m-component-textplus").get(0).select("h2").text();

            Elements p2a = docEinblick1.getElementsByClass("m-component m-component-textplus").get(0).select("p");
            String p2 = "";
            for (Element paragraph : p2a) {
                Elements strongTags = paragraph.select("strong");
                String paragraphText = paragraph.text();
                String lastStrongText = strongTags.last().text();
                p2 += paragraphText.substring(0, paragraphText.lastIndexOf(lastStrongText));
            }

            StringBuilder textEinblicke1 = new StringBuilder();

            String contentTextEinblicke1 = textEinblicke1
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

    public void muenchenEvent2ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(MUENCHEN_NEWS_URL, REFERRER);
            Element elEinblick6 = basDoc.getElementsByClass("m-teaser-list__list").get(3);
            String urlToEinblick6 = MUENCHEN_NEWS_URL + elEinblick6.child(6).select("a").attr("href");
            String shortDescription = elEinblick6.child(6).text();
            Document docEinblick6 = getDocument(urlToEinblick6, MUENCHEN_NEWS_URL);
            String title = docEinblick6.select("h1").text();
            String p1 = docEinblick6.getElementsByAttributeValue("itemprop","description").text();
            Elements elsImage = docEinblick6.getElementsByClass("m-media-image__image");
            String srcset = elsImage.select("picture").first().select("source").first().attr("srcset");
            int index = srcset.indexOf("jpeg");
            String imgUrl = MUENCHEN_NEWS_URL + srcset.substring(0, index + 4);
            String p2subTitle = docEinblick6.getElementsByClass("m-callout__body__inner").get(0).select("h2").text();
            String p2 = docEinblick6.getElementsByClass("m-callout__content").get(0).select("p").text();

            StringBuilder textEinblick2 = new StringBuilder();

            String contentTextEinblick2 = textEinblick2
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
            muenchenDeNewsParsObj.setContent(translationService.translateText(contentTextEinblick2));
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

    public void muenchenEvent3ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(MUENCHEN_NEWS_URL, REFERRER);
            Element elEinblick7 = basDoc.getElementsByClass("m-teaser-list__list").get(3);
            String urlToEinblick7 = MUENCHEN_NEWS_URL + elEinblick7.child(7).select("a").attr("href");
            String shortDescription = elEinblick7.child(7).text();
            Document docEinblick7 = getDocument(urlToEinblick7, MUENCHEN_NEWS_URL);
            Elements elsArticleFirstPart = docEinblick7.getElementsByAttributeValue("role", "article");
            String title = docEinblick7.select("h1").text();
            String p1 = docEinblick7.getElementsByClass("m-intro-vertical__content").select("p").text();
            Elements elsImage = docEinblick7.getElementsByClass("m-media-image__image");
            String srcset = elsImage.select("picture").first().select("source").first().attr("srcset");
            String imgCopyright = "© " + docEinblick7.getElementsByClass("m-media-image__credits").first().text();
            int index = srcset.indexOf("jpg");
            String imgUrl = MUENCHEN_NEWS_URL + srcset.substring(0, index + 3);
            String p2subTitle = docEinblick7.getElementsByClass("m-content").get(0).select("h2").text();
            String p2 = docEinblick7.getElementsByClass("m-content").get(1).select("p").text();

            StringBuilder textEinblick7 = new StringBuilder();

            String contentTextEinblick7= textEinblick7
                    .append(p1)
                    .append("\n")
                    .append(p2subTitle)
                    .append("\n\n")
                    .append(p2)
                    .append("\n")
                    .toString();
            String categoryTitle = "ACTUAL_NEWS";

            saveNewsObject(title, shortDescription, imgUrl, imgCopyright, contentTextEinblick7, categoryTitle);

//            MuenchenDeNewsParsObj muenchenDeNewsParsObj = new MuenchenDeNewsParsObj();
//            LocalNewsCategory muenchenNewsCategory = localNewsCategoryRepository.findByTitle("ACTUAL_NEWS");
//
//            muenchenDeNewsParsObj.setTitle(translationService.translateText(title));
//            muenchenDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
//            muenchenDeNewsParsObj.setImgUrl(imgUrl);
//            muenchenDeNewsParsObj.setImgUrl(imgCopyright);
//            muenchenDeNewsParsObj.setContent(translationService.translateText(contentTextEinblick7));
//            muenchenDeNewsParsObj.setLocalNewsCategory(muenchenNewsCategory);
//
//            saveObjectIfNotExists(
//                    () -> muenchenDeNewsParsObj,
//                    muenchenDeNewsParsingRepository,
//                    MuenchenDeNewsParsObj::getTitle
//            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MuenchenDeNewsParsObj saveNewsObject(String title, String shortDescription, String imgUrl, String imgCopyright, String contextArticle, String categoryTitle) {
        MuenchenDeNewsParsObj muenchenDeNewsParsObj = new MuenchenDeNewsParsObj();
        LocalNewsCategory muenchenNewsCategory = localNewsCategoryRepository.findByTitle(categoryTitle);

        muenchenDeNewsParsObj.setTitle(translationService.translateText(title));
        muenchenDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
        muenchenDeNewsParsObj.setImgUrl(imgUrl);
        muenchenDeNewsParsObj.setImgCopyright(imgCopyright);
        muenchenDeNewsParsObj.setContent(translationService.translateText(contextArticle));
        muenchenDeNewsParsObj.setLocalNewsCategory(muenchenNewsCategory);

        saveObjectIfNotExists(
                () -> muenchenDeNewsParsObj,
                muenchenDeNewsParsingRepository,
                MuenchenDeNewsParsObj::getTitle
        );
        return muenchenDeNewsParsObj;
    }
}
   /* static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";
    static final int TIMEOUT = 5000;
    static final String REFERRER = "https://www.google.com";
    static final String MUENCHEN_NEWS_URL = "https://www.muenchen.de";


    public static String getURLToBerlinNewsSects(Element element, int index) {
        String urlToNewsSection = MUENCHEN_NEWS_URL + element.child(index).child(0).child(0).child(1).child(0).attr("href");
        return urlToNewsSection;
    }

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
            Element elEinblick7 = basDoc.getElementsByClass("m-teaser-list__list").get(3);
            String urlToEinblick7 = MUENCHEN_NEWS_URL + elEinblick7.child(7).select("a").attr("href");
            String shortDescription = elEinblick7.child(7).text();
            Document docEinblick7 = getDoc(urlToEinblick7, MUENCHEN_NEWS_URL);
            Elements elsArticleFirstPart = docEinblick7.getElementsByAttributeValue("role", "article");
            String title = docEinblick7.select("h1").text();
            String p1 = docEinblick7.getElementsByClass("m-intro-vertical__content").select("p").text();
            Elements elsImage = docEinblick7.getElementsByClass("m-media-image__image");
            String srcset = elsImage.select("picture").first().select("source").first().attr("srcset");
            String imgCopyright = "© " + docEinblick7.getElementsByClass("m-media-image__credits").first().text();
            int index = srcset.indexOf("jpg");
            String imgUrl = MUENCHEN_NEWS_URL + srcset.substring(0, index + 3);
            String p2subTitle = docEinblick7.getElementsByClass("m-content").get(0).select("h2").text();
            String p2 = docEinblick7.getElementsByClass("m-content").get(1).select("p").text();
//

//            Elements p2a = docEinblick7.getElementsByClass("m-component m-component-textplus").get(0).select("p");
//            String p2 = "";
//            for (Element paragraph : p2a) {
//                Elements strongTags = paragraph.select("strong");
//                String paragraphText = paragraph.text();
//                String lastStrongText = strongTags.last().text();
//                p2 += paragraphText.substring(0, paragraphText.lastIndexOf(lastStrongText));
//            }
//
            StringBuilder textEinblick7 = new StringBuilder();

            String contentTextEinblick7= textEinblick7
                    .append(p1)
                    .append("\n")
                    .append(p2subTitle)
                    .append("\n\n")
                    .append(p2)
                    .append("\n")
                    .toString();

               System.out.println(contentTextEinblick7);

//            System.out.println(urlToEinblick7);
//            System.out.println(urlToActualNews3);
//            System.out.println(shortDescription);
//            System.out.println(urlToEventActualHighlights4);
//            System.out.println(imgUrl);
//            System.out.println(imgCopyright);
//            System.out.println(title);
//            System.out.println(p1);
//            System.out.println(p2subTitle);
//            System.out.println(p2);
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
