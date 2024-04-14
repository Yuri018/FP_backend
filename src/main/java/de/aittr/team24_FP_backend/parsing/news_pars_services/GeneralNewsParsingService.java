package de.aittr.team24_FP_backend.parsing.news_pars_services;

import de.aittr.team24_FP_backend.parsing.news_pars_models.NewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.GeneralNewsParsObj;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.GenNewsCategoryRepository;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.GeneralNewsParsingRepository;
import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import jakarta.transaction.Transactional;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GeneralNewsParsingService extends CommonParsingTextService {

    final String URL = "https://aussiedlerbote.de";

    GeneralNewsParsingRepository genNewsRepository;
    GenNewsCategoryRepository genNewsCategoryRepository;

    public GeneralNewsParsingService(GeneralNewsParsingRepository genNewsRepository, GenNewsCategoryRepository genNewsCategoryRepository) {
        this.genNewsRepository = genNewsRepository;
        this.genNewsCategoryRepository = genNewsCategoryRepository;
    }

    public Map<Document, String> getDocumentGeneralNews(String url, String referrer, int index) throws IOException {
        Map<Document, String> genInfoMap = new HashMap<>();
        Document basDoc = getDocument(url, REFERRER);
        Element elColumnNews = basDoc.getElementsByClass("footer-top").first();
        Element el2ColumnNews = elColumnNews.child(1).child(1);
        String urlToGeneralInfo = el2ColumnNews.child(index).select("a").attr("href");
        Document docGeneralInfo = getDocument(urlToGeneralInfo, url);
        genInfoMap.put(docGeneralInfo, urlToGeneralInfo);
        return genInfoMap;
    }

    @Transactional
    public GeneralNewsParsObj parsingPolitNews1() {

        GeneralNewsParsObj politNews1 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("POLITICS");

        try {
            Document basDoc = getDocument(URL, REFERRER);
            Elements elsColumnNews = basDoc.getElementsByClass("newsCategory-columns");
            Element elUrlToPoliticNews = elsColumnNews.get(0).child(1);
            String urlToPoliticNews = elUrlToPoliticNews.child(0).attr("href");
            Document docPolitics = getDocument(urlToPoliticNews, URL);
            Element elUrlToTopPolitNews = docPolitics.getElementsByClass("top-news-section").get(0).child(0);
            String urlToTopPolitNews = elUrlToTopPolitNews.child(0).child(0).attr("href");
            Document docTopPolitNews = getDocument(urlToTopPolitNews, urlToPoliticNews);
            Element elTopPolitNewsGeneralContent = docTopPolitNews.getElementsByClass("single-content").get(0);
            String title = elTopPolitNewsGeneralContent.child(0).text();
            Element elTopPolitNewsMainContent = docTopPolitNews.getElementsByClass("wp-content").first();

            String imgUrl = elTopPolitNewsMainContent.child(0).child(0).attr("src");
            String topPolitNewsArtParagr1 = elTopPolitNewsMainContent.child(1).text();
            String topPolitNewsArtParagr2 = elTopPolitNewsMainContent.child(3).text();
            String topPolitNewsArtParagr3 = elTopPolitNewsMainContent.child(4).text();
            String topPolitNewsArtParagr4SubTitle = elTopPolitNewsMainContent.child(5).text();
            String topPolitNewsArtParagr4 = elTopPolitNewsMainContent.child(6).text();
            String topPolitNewsArtParagr5 = elTopPolitNewsMainContent.child(8).text();
            String topPolitNewsArtParagr6 = elTopPolitNewsMainContent.child(9).text();
            String topPolitNewsArtParagr7 = elTopPolitNewsMainContent.child(10).text();
            String topPolitNewsArtParagr8 = elTopPolitNewsMainContent.child(11).text();


            StringBuilder sb = new StringBuilder();

            String topPolitNewsArtContent = sb
                    .append(topPolitNewsArtParagr1)
                    .append(topPolitNewsArtParagr2)
                    .append(topPolitNewsArtParagr3)
                    .append("\n")
                    .append(topPolitNewsArtParagr4SubTitle)
                    .append("\n")
                    .append(topPolitNewsArtParagr4)
                    .append(topPolitNewsArtParagr5)
                    .append(topPolitNewsArtParagr6)
                    .append(topPolitNewsArtParagr7)
                    .append(topPolitNewsArtParagr8)
                    .toString();

            politNews1.setTitle(title);
            politNews1.setContent(topPolitNewsArtContent);
            politNews1.setImgUrl(imgUrl);
            politNews1.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return politNews1;
    }

    @Transactional
    public GeneralNewsParsObj parsingPolitNews2() {

        GeneralNewsParsObj politNews2 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("POLITICS");

        try {
            Document basDoc = getDocument(URL, REFERRER);
            Elements elsColumnNews = basDoc.getElementsByClass("newsCategory-columns");
            Element elUrlToPoliticNews = elsColumnNews.get(0).child(1);
            String urlToPoliticNews = elUrlToPoliticNews.child(0).attr("href");
            Document docPolitics = getDocument(urlToPoliticNews, URL);

            Elements elsUrlToMinorPolitNews = docPolitics.getElementsByClass("news-grid-column third");
            String urlToMinorPolitNews1 = elsUrlToMinorPolitNews.get(0).child(0).attr("href");
            Document docMinorPolitNews1 = getDocument(urlToMinorPolitNews1, urlToPoliticNews);
            Element elMinorPolitNews1 = docMinorPolitNews1.getElementsByClass("single-content").get(0);
            String title = elMinorPolitNews1.child(0).text();
            Element elMonorPolitNews1MainContent = elMinorPolitNews1.getElementsByClass("wp-content").first();

            String imgUrl = elMonorPolitNews1MainContent.child(0).child(0).attr("src");
            String minorPolitNews1Paragr1 = elMonorPolitNews1MainContent.child(1).text();
            String minorPolitNews1Paragr2 = elMonorPolitNews1MainContent.child(3).text();
            String minorPolitNews1Paragr3 = elMonorPolitNews1MainContent.child(4).text();
            String minorPolitNews1Paragr4 = elMonorPolitNews1MainContent.child(5).text();
            String minorPolitNews1Paragr5SubTitle = elMonorPolitNews1MainContent.child(6).text();
            String minorPolitNews1Paragr5 = elMonorPolitNews1MainContent.child(7).text();
            String minorPolitNews1Paragr6 = elMonorPolitNews1MainContent.child(8).text();
            String minorPolitNews1Paragr7 = elMonorPolitNews1MainContent.child(9).text();
            String minorPolitNews1Paragr8 = elMonorPolitNews1MainContent.child(11).text();
            String minorPolitNews1Paragr9 = elMonorPolitNews1MainContent.child(12).text();
            String minorPolitNews1Paragr10 = elMonorPolitNews1MainContent.child(13).text();
            String minorPolitNews1Paragr11 = elMonorPolitNews1MainContent.child(14).text();

            StringBuilder sb = new StringBuilder();

            String minorPolitNews1ArtContent = sb
                    .append(minorPolitNews1Paragr1)
                    .append(minorPolitNews1Paragr2)
                    .append(minorPolitNews1Paragr3)
                    .append(minorPolitNews1Paragr4)
                    .append("\n")
                    .append(minorPolitNews1Paragr5SubTitle)
                    .append("\n")
                    .append(minorPolitNews1Paragr5)
                    .append(minorPolitNews1Paragr6)
                    .append(minorPolitNews1Paragr7)
                    .append(minorPolitNews1Paragr8)
                    .append(minorPolitNews1Paragr9)
                    .append(minorPolitNews1Paragr10)
                    .append(minorPolitNews1Paragr11)
                    .toString();

            politNews2.setTitle(title);
            politNews2.setContent(minorPolitNews1ArtContent);
            politNews2.setImgUrl(imgUrl);
            politNews2.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return politNews2;
    }

    @Transactional
    public GeneralNewsParsObj parsingEconomicNews1() {

        GeneralNewsParsObj economicNews1 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("ECONOMICS");

        try {
            Document basDoc = getDocument(URL, REFERRER);
            Elements elsColumnNews = basDoc.getElementsByClass("newsCategory-columns");
            Element elUrlToEconomicNews = elsColumnNews.get(0).child(3);
            String urlToEconomicNews = elUrlToEconomicNews.child(0).attr("href");
            Document docEconomics = getDocument(urlToEconomicNews, URL);


            Element elUrlToTopEconomicNews = docEconomics.getElementsByClass("top-news-section").get(0).child(0);
            String urlToTopEconomicNews = elUrlToTopEconomicNews.child(0).child(0).attr("href");
            Document docTopEconomicNews = getDocument(urlToTopEconomicNews, urlToEconomicNews);
            Element elTopEconomicNewsGeneralContent = docTopEconomicNews.getElementsByClass("single-content").get(0);
            String title = elTopEconomicNewsGeneralContent.child(0).text();

            Element elTopEconomicNewsContent = docTopEconomicNews.getElementsByClass("main-content").first();
            String shortDescription = elTopEconomicNewsContent.child(0).text();

            Element elTopEconomicNewsMainContent = docTopEconomicNews.getElementsByClass("wp-content").first();

            String imgUrl = elTopEconomicNewsMainContent.child(0).child(0).attr("src");
            String topEconomicNewsArtParagr1 = elTopEconomicNewsMainContent.child(1).text();
            String topEconomicNewsArtParagr2 = elTopEconomicNewsMainContent.child(3).text();
            String topEconomicNewsArtParagr3 = elTopEconomicNewsMainContent.child(4).text();
            String topEconomicNewsArtParagr4 = elTopEconomicNewsMainContent.child(5).text();
            String topEconomicNewsArtParagr5 = elTopEconomicNewsMainContent.child(6).text();
            String topEconomicNewsArtParagr6 = elTopEconomicNewsMainContent.child(7).text();
            String topEconomicNewsArtParagr7a = elTopEconomicNewsMainContent.child(8).child(0).text();
            String topEconomicNewsArtParagr7b = elTopEconomicNewsMainContent.child(8).child(1).text();
            String topEconomicNewsArtParagr7c = elTopEconomicNewsMainContent.child(8).child(2).text();
            String topEconomicNewsArtParagr7d = elTopEconomicNewsMainContent.child(8).child(3).text();
            String topEconomicNewsArtParagr7e = elTopEconomicNewsMainContent.child(8).child(4).text();
            String topEconomicNewsArtParagr7f = elTopEconomicNewsMainContent.child(8).child(5).text();
            String topEconomicNewsArtParagr8 = elTopEconomicNewsMainContent.child(9).text();
            String topEconomicNewsArtParagr9 = elTopEconomicNewsMainContent.child(10).text();
            String topEconomicNewsArtParagr10 = elTopEconomicNewsMainContent.child(11).text();


            StringBuilder sb = new StringBuilder();

            String topEconomicNewsArtContent = sb
                    .append(topEconomicNewsArtParagr1)
                    .append(" " + topEconomicNewsArtParagr2)
                    .append(" " + topEconomicNewsArtParagr3)
                    .append(" " + topEconomicNewsArtParagr4)
                    .append(" " + topEconomicNewsArtParagr5)
                    .append(" " + topEconomicNewsArtParagr6)
                    .append("\n")
                    .append(topEconomicNewsArtParagr7a + ";")
                    .append("\n")
                    .append(topEconomicNewsArtParagr7b + ";")
                    .append("\n")
                    .append(topEconomicNewsArtParagr7c + ";")
                    .append("\n")
                    .append(topEconomicNewsArtParagr7d + ";")
                    .append("\n")
                    .append(topEconomicNewsArtParagr7e + ";")
                    .append("\n")
                    .append(topEconomicNewsArtParagr7f + ";")
                    .append("\n")
                    .append(" " + topEconomicNewsArtParagr8)
                    .append(" " + topEconomicNewsArtParagr9)
                    .append("\n")
                    .append(" " + topEconomicNewsArtParagr10)
                    .toString();

            economicNews1.setTitle(title);
            economicNews1.setShortDescription(shortDescription);
            economicNews1.setContent(topEconomicNewsArtContent);
            economicNews1.setImgUrl(imgUrl);
            economicNews1.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return economicNews1;
    }

    @Transactional
    public GeneralNewsParsObj parsingEconomicNews2() {

        GeneralNewsParsObj economicNews2 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("ECONOMICS");

        try {
            Document basDoc = getDocument(URL, REFERRER);
            Elements elsColumnNews = basDoc.getElementsByClass("newsCategory-columns");
            Element elUrlToEconomicNews = elsColumnNews.get(0).child(3);
            String urlToEconomicNews = elUrlToEconomicNews.child(0).attr("href");
            Document docEconomics = getDocument(urlToEconomicNews, URL);
            Elements elsUrlToMinorEconomicNews = docEconomics.getElementsByClass("news-grid-column third");
            String urlToMinorEconomicNews1 = elsUrlToMinorEconomicNews.get(0).child(0).attr("href");
            Document docMinorEconomicNews1 = getDocument(urlToMinorEconomicNews1, urlToEconomicNews);
            Element elMinorEconomicNews1 = docMinorEconomicNews1.getElementsByClass("single-content").get(0);
            String title = elMinorEconomicNews1.child(0).text();
            String shortDescription = docMinorEconomicNews1.getElementsByClass("main-content").first().child(0).text();
            Element elMonorEconomicNews1MainContent = elMinorEconomicNews1.getElementsByClass("wp-content").first();

            String imgUrl = elMonorEconomicNews1MainContent.child(0).child(0).attr("src");

            String minorEconomicNews1Paragr1 = elMonorEconomicNews1MainContent.child(1).text();
            String minorEconomicNews1Paragr2 = elMonorEconomicNews1MainContent.child(3).text();
            String minorEconomicNews1Paragr3 = elMonorEconomicNews1MainContent.child(4).text();
            String minorEconomicNews1Paragr4 = elMonorEconomicNews1MainContent.child(5).text();
            String minorEconomicNews1Paragr5 = elMonorEconomicNews1MainContent.child(6).text();
            String minorEconomicNews1Paragr6 = elMonorEconomicNews1MainContent.child(7).text();
            String minorEconomicNews1Paragr7 = elMonorEconomicNews1MainContent.child(8).text();
            String minorEconomicNews1Paragr8 = elMonorEconomicNews1MainContent.child(9).text();
            String minorEconomicNews1Paragr9 = elMonorEconomicNews1MainContent.child(10).text();
            String minorEconomicNews1Paragr10 = elMonorEconomicNews1MainContent.child(11).text();

            StringBuilder sb = new StringBuilder();

            String minorEconomicNews1ArtContent = sb
                    .append(minorEconomicNews1Paragr1)
                    .append(" " + minorEconomicNews1Paragr2)
                    .append(" " + minorEconomicNews1Paragr3)
                    .append(" " + minorEconomicNews1Paragr4)
                    .append(" " + minorEconomicNews1Paragr5)
                    .append(" " + minorEconomicNews1Paragr6)
                    .append(" " + minorEconomicNews1Paragr7)
                    .append(" " + minorEconomicNews1Paragr8)
                    .append(" " + minorEconomicNews1Paragr9)
                    .append(" " + minorEconomicNews1Paragr10)
                    .toString();

            economicNews2.setTitle(title);
            economicNews2.setShortDescription(shortDescription);
            economicNews2.setContent(minorEconomicNews1ArtContent);
            economicNews2.setImgUrl(imgUrl);
            economicNews2.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return economicNews2;
    }

    @Transactional
    public GeneralNewsParsObj parsingHealthNews7() {

        GeneralNewsParsObj healthNews7 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("HEALTH");

        try {
            Map<Document, String> genNewsMap = getDocumentGeneralNews(URL, REFERRER, 5);
            Document docGeneralNews = genNewsMap.keySet().iterator().next();
            String urlToGeneralNews = genNewsMap.get(docGeneralNews);

            Elements elsThirdColumnInfo = docGeneralNews.getElementsByClass("news-grid-column third");

            String urlMinorGeneralNews7 = elsThirdColumnInfo.get(0).child(6).attr("href");
            Document docMinorGeneralNews7 = getDocument(urlMinorGeneralNews7, urlToGeneralNews);

            Element elGeneralNews7Title = docMinorGeneralNews7.getElementsByClass("single-content").get(0);
            String title = elGeneralNews7Title.child(0).text();
            Element elGeneralNews7Content = docMinorGeneralNews7.getElementsByClass("main-content").first();
            String shortDescription = elGeneralNews7Content.child(0).text();

            Element elGeneralNews7TextContent = docMinorGeneralNews7.getElementsByClass("wp-content").first();
            String imgUrl = elGeneralNews7TextContent.child(0).child(0).attr("src");

            String generalNews7TextContentParagr1 = elGeneralNews7TextContent.child(1).text();
            String generalNews7TextContentParagr2 = elGeneralNews7TextContent.child(3).text();
            String generalNews7TextContentParagr3SubTitle = elGeneralNews7TextContent.child(4).text();
            String generalNews7TextContentParagr4 = elGeneralNews7TextContent.child(5).text();
            String generalNews7TextContentParagr5 = elGeneralNews7TextContent.child(6).text();
            String generalNews7TextContentParagr6 = elGeneralNews7TextContent.child(7).text();
            String generalNews7TextContentParagr7 = elGeneralNews7TextContent.child(9).text();
            String generalNews7TextContentParagr8 = elGeneralNews7TextContent.child(10).text();
            String generalNews7TextContentParagr9 = elGeneralNews7TextContent.child(11).text();
            String generalNews7TextContentParagr10 = elGeneralNews7TextContent.child(12).text();
            String generalNews7TextContentParagr11 = elGeneralNews7TextContent.child(13).text();
            String generalNews7TextContentParagr12 = elGeneralNews7TextContent.child(14).text();

            StringBuilder sb = new StringBuilder();

            String minorGeneralNews7ArtContent = sb
                    .append(generalNews7TextContentParagr1)
                    .append(" " + generalNews7TextContentParagr2)
                    .append("\n")
                    .append("\n")
                    .append(generalNews7TextContentParagr3SubTitle)
                    .append("\n")
                    .append("\n")
                    .append(" " + generalNews7TextContentParagr4)
                    .append(" " + generalNews7TextContentParagr5)
                    .append(" " + generalNews7TextContentParagr6)
                    .append(" " + generalNews7TextContentParagr7)
                    .append(" " + generalNews7TextContentParagr8)
                    .append(" " + generalNews7TextContentParagr9)
                    .append(" " + generalNews7TextContentParagr10)
                    .append(" " + generalNews7TextContentParagr11)
                    .append(" " + generalNews7TextContentParagr12)
                    .toString();

            healthNews7.setTitle(title);
            healthNews7.setShortDescription(shortDescription);
            healthNews7.setContent(minorGeneralNews7ArtContent);
            healthNews7.setImgUrl(imgUrl);
            healthNews7.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return healthNews7;
    }

    @Transactional
    public GeneralNewsParsObj parsingSocNews2() {

        GeneralNewsParsObj socialNews2 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("SOCIETY");

        try {
            Map<Document, String> genNewsMap = getDocumentGeneralNews(URL, REFERRER, 2);
            Document docGeneralNews = genNewsMap.keySet().iterator().next();
            String urlToGeneralNews = genNewsMap.get(docGeneralNews);

            Elements elsThirdColumnInfo = docGeneralNews.getElementsByClass("news-grid-column third");

            String urlMinorGeneralSocNews2 = elsThirdColumnInfo.get(0).child(0).attr("href");
            Document docMinorGeneralSocNews2 = getDocument(urlMinorGeneralSocNews2, urlToGeneralNews);

            Element elGeneralSocNews2Title = docMinorGeneralSocNews2.getElementsByClass("single-content").get(0);
            String title = elGeneralSocNews2Title.child(0).text();

            Element elGeneralSocNews2TextContent = docMinorGeneralSocNews2.getElementsByClass("wp-content").first();
            String imgUrl = elGeneralSocNews2TextContent.child(0).child(0).attr("src");
            String shortDescription  = elGeneralSocNews2TextContent.child(1).text();

            String generalSocNews2TextContentParagr1 = elGeneralSocNews2TextContent.child(3).text();
            String generalSocNews2TextContentParagr2 = elGeneralSocNews2TextContent.child(5).text();
            String generalSocNews2TextContentParagr3 = elGeneralSocNews2TextContent.child(6).text();
            String generalSocNews2TextContentParagr4 = elGeneralSocNews2TextContent.child(7).text();
            String generalSocNews2TextContentParagr5 = elGeneralSocNews2TextContent.child(8).text();
            String generalSocNews2TextContentParagr6 = elGeneralSocNews2TextContent.child(9).text();
            String generalSocNews2TextContentParagr7 = elGeneralSocNews2TextContent.child(11).text();
            String generalSocNews2TextContentParagr8 = elGeneralSocNews2TextContent.child(12).text();
            String generalSocNews2TextContentParagr9 = elGeneralSocNews2TextContent.child(13).text();
            String generalSocNews2TextContentParagr10 = elGeneralSocNews2TextContent.child(14).text();

            StringBuilder sb = new StringBuilder();

            String minorGeneralSocNews2ArtContent = sb
                    .append(generalSocNews2TextContentParagr1)
                    .append(" " + generalSocNews2TextContentParagr2)
                    .append(generalSocNews2TextContentParagr3)
                    .append(" " + generalSocNews2TextContentParagr4)
                    .append(" " + generalSocNews2TextContentParagr5)
                    .append(" " + generalSocNews2TextContentParagr6)
                    .append(" " + generalSocNews2TextContentParagr7)
                    .append(" " + generalSocNews2TextContentParagr8)
                    .append(" " + generalSocNews2TextContentParagr9)
                    .append(" " + generalSocNews2TextContentParagr10)
                    .toString();


            socialNews2.setTitle(title);
            socialNews2.setShortDescription(shortDescription);
            socialNews2.setContent(minorGeneralSocNews2ArtContent);
            socialNews2.setImgUrl(imgUrl);
            socialNews2.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return socialNews2;
    }
    @Transactional
    public GeneralNewsParsObj savePolitNews1() {
        return saveObjectIfNotExists(
                this::parsingPolitNews1,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj savePolitNews2() {
        return saveObjectIfNotExists(
                this::parsingPolitNews2,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj saveEconomicNews1() {
        return saveObjectIfNotExists(
                this::parsingEconomicNews1,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj saveEconomicNews2() {
        return saveObjectIfNotExists(
                this::parsingEconomicNews2,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj saveHealthNews7() {
        return saveObjectIfNotExists(
                this::parsingHealthNews7,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj saveSocNews2() {
        return saveObjectIfNotExists(
                this::parsingSocNews2,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }
}

   /* static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";
    static final int TIMEOUT = 5000;
    static protected final String REFERRER = "https://www.google.com";


    public static Document getDoc(String url, String referrer) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(TIMEOUT)
                .referrer(referrer)
                .get();
        return doc;
    }

    public static Map<Document, String> getDocGeneralNews(String url, String referrer, int index ) throws IOException {
        Map<Document, String> genNewsMap = new HashMap<>();
        Document basDoc = getDoc(url, REFERRER);
        Element elColumnNews = basDoc.getElementsByClass("footer-top").first();
        Element el2ColumnNews = elColumnNews.child(1).child(1);
        String urlToGeneralNews = el2ColumnNews.child(index).select("a").attr("href");
        Document docGeneralInfo = getDoc(urlToGeneralNews, url);
        genNewsMap.put(docGeneralInfo, urlToGeneralNews);
        return genNewsMap;
    }

    public static void main(String[] args) {
        final String URL = "https://aussiedlerbote.de";
        try {
            Map<Document, String> genNewsMap = getDocGeneralNews(URL, REFERRER, 2);
            Document docGeneralNews = genNewsMap.keySet().iterator().next();
            String urlToGeneralNews = genNewsMap.get(docGeneralNews);

            Elements elsThirdColumnInfo = docGeneralNews.getElementsByClass("news-grid-column third");

            String urlMinorGeneralSocNews2 = elsThirdColumnInfo.get(0).child(0).attr("href");
            Document docMinorGeneralSocNews2 = getDoc(urlMinorGeneralSocNews2, urlToGeneralNews);

            Element elGeneralSocNews2Title = docMinorGeneralSocNews2.getElementsByClass("single-content").get(0);
            String title = elGeneralSocNews2Title.child(0).text();
//            Element elGeneralSocNews2Content = docMinorGeneralSocNews2.getElementsByClass("main-content").first();
//            String shortDescription = elGeneralSocNews2Content.child(0).text();

            Element elGeneralSocNews2TextContent = docMinorGeneralSocNews2.getElementsByClass("wp-content").first();
            String imgUrl = elGeneralSocNews2TextContent.child(0).child(0).attr("src");
            String shortDescription  = elGeneralSocNews2TextContent.child(1).text();

            String generalSocNews2TextContentParagr1 = elGeneralSocNews2TextContent.child(3).text();
            String generalSocNews2TextContentParagr2 = elGeneralSocNews2TextContent.child(5).text();
            String generalSocNews2TextContentParagr3 = elGeneralSocNews2TextContent.child(6).text();
            String generalSocNews2TextContentParagr4 = elGeneralSocNews2TextContent.child(7).text();
            String generalSocNews2TextContentParagr5 = elGeneralSocNews2TextContent.child(8).text();
            String generalSocNews2TextContentParagr6 = elGeneralSocNews2TextContent.child(9).text();
            String generalSocNews2TextContentParagr7 = elGeneralSocNews2TextContent.child(11).text();
            String generalSocNews2TextContentParagr8 = elGeneralSocNews2TextContent.child(12).text();
            String generalSocNews2TextContentParagr9 = elGeneralSocNews2TextContent.child(13).text();
            String generalSocNews2TextContentParagr10 = elGeneralSocNews2TextContent.child(14).text();
//            String generalSocNews2TextContentParagr11SubTitle = elGeneralSocNews2TextContent.child(14).text();
//            String generalSocNews2TextContentParagr11 = elGeneralSocNews2TextContent.child(15).text();
//            String generalSocNews2TextContentParagr12 = elGeneralSocNews2TextContent.child(16).text();
//            String generalSocNews2TextContentParagr13 = elGeneralSocNews2TextContent.child(17).text();
//            String generalSocNews2TextContentParagr14 = elGeneralSocNews2TextContent.child(18).text();
//            String generalSocNews2TextContentParagr15 = elGeneralSocNews2TextContent.child(19).text();
//            String generalSocNews2TextContentParagr16 = elGeneralSocNews2TextContent.child(20).text();
//            String generalSocNews2TextContentParagr17 = elGeneralSocNews2TextContent.child(21).text();
//            String generalSocNews2TextContentParagr18 = elGeneralSocNews2TextContent.child(22).text();
//            String generalSocNews2TextContentParagr19 = elGeneralSocNews2TextContent.child(23).text();
//            String generalNews7TextContentParagr20SubTitle = elGeneralSocNews2TextContent.child(28).text();
//            String generalNews7TextContentParagr20 = elGeneralSocNews2TextContent.child(29).text();
//            String generalNews7TextContentParagr21 = elGeneralSocNews2TextContent.child(30).text();
//            String generalNews7TextContentParagr22 = elGeneralSocNews2TextContent.child(31).text();
//            String generalNews7TextContentParagr23 = elGeneralSocNews2TextContent.child(32).text();
//            String generalNews7TextContentParagr24SubTitle = elGeneralSocNews2TextContent.child(33).text();
//            String generalNews7TextContentParagr24 = elGeneralSocNews2TextContent.child(34).text();
//            String generalNews7TextContentParagr25 = elGeneralSocNews2TextContent.child(35).text();
//            String generalNews7TextContentParagr26SubTitle = elGeneralSocNews2TextContent.child(36).text();
//            String generalNews7TextContentParagr26 = elGeneralSocNews2TextContent.child(37).text();
//            String generalNews7TextContentParagr27 = elGeneralSocNews2TextContent.child(38).text();
//            String generalNews7TextContentParagr28SubTitle = elGeneralSocNews2TextContent.child(39).text();
//            String generalNews7TextContentParagr28 = elGeneralSocNews2TextContent.child(40).text();
//            String generalNews7TextContentParagr29 = elGeneralSocNews2TextContent.child(41).text();
//            String generalNews7TextContentParagr30 = elGeneralSocNews2TextContent.child(42).text();
//            String generalNews7TextContentParagr31SubTitle = elGeneralSocNews2TextContent.child(43).text();
//            String generalNews7TextContentParagr31 = elGeneralSocNews2TextContent.child(44).text();
//            String generalNews7TextContentParagr32 = elGeneralSocNews2TextContent.child(45).text();
//
            StringBuilder sb = new StringBuilder();

            String minorGeneralSocNews2ArtContent = sb
                    .append(generalSocNews2TextContentParagr1)
                    .append(" " + generalSocNews2TextContentParagr2)
                    .append(generalSocNews2TextContentParagr3)
                    .append(" " + generalSocNews2TextContentParagr4)
                    .append(" " + generalSocNews2TextContentParagr5)
                    .append(" " + generalSocNews2TextContentParagr6)
                    .append(" " + generalSocNews2TextContentParagr7)
                    .append(" " + generalSocNews2TextContentParagr8)
                    .append(" " + generalSocNews2TextContentParagr9)
                    .append(" " + generalSocNews2TextContentParagr10)
//                    .append(" " + generalNews7TextContentParagr11)
//                    .append(" " + generalNews7TextContentParagr12)
//                    .append(" " + generalNews7TextContentParagr13)
//                    .append(" " + generalNews7TextContentParagr14)
//                    .append(" " + generalNews7TextContentParagr15)
//                    .append(" " + generalNews7TextContentParagr16)
//                    .append(" " + generalNews7TextContentParagr17)
//                    .append(" " + generalNews7TextContentParagr18)
//                    .append(" " + generalNews7TextContentParagr19)
//                    .append(" " + generalNews7TextContentParagr20)
//                    .append(" " + generalNews7TextContentParagr21)
//                    .append(" " + generalNews7TextContentParagr22)
//                    .append(" " + generalNews7TextContentParagr23)
//                    .append("\n")
//                    .append(generalNews7TextContentParagr24SubTitle)
//                    .append("\n")
//                    .append(" " + generalNews7TextContentParagr24)
//                    .append(" " + generalNews7TextContentParagr25)
//                    .append("\n")
//                    .append(generalNews7TextContentParagr26SubTitle)
//                    .append("\n")
//                    .append(" " + generalNews7TextContentParagr26)
//                    .append(" " + generalNews7TextContentParagr27)
//                    .append("\n")
//                    .append(generalNews7TextContentParagr28SubTitle)
//                    .append("\n")
//                    .append(" " + generalNews7TextContentParagr28)
//                    .append(" " + generalNews7TextContentParagr29)
//                    .append(" " + generalNews7TextContentParagr30)
//                    .append("\n")
//                    .append(generalNews7TextContentParagr31SubTitle)
//                    .append("\n")
//                    .append(" " + generalNews7TextContentParagr31)
//                    .append(" " + generalNews7TextContentParagr32)
                    .toString();


//            System.out.println(urlToGeneralNews);
//            System.out.println(urlMinorGeneralSocNews2);

            System.out.println(title);
            System.out.println(imgUrl);
            System.out.println(shortDescription);
            System.out.println("**********************************");
            System.out.println(minorGeneralSocNews2ArtContent);

//            System.out.println(generalSocNews2TextContentParagr1);
//            System.out.println("**********************************");
//            System.out.println(generalSocNews2TextContentParagr2);
//            System.out.println("**********************************");
//            System.out.println(generalSocNews2TextContentParagr3);
//            System.out.println("**********************************");
//            System.out.println(generalSocNews2TextContentParagr4);
//            System.out.println("**********************************");
//            System.out.println(generalSocNews2TextContentParagr5);
//            System.out.println("**********************************");
//            System.out.println(generalSocNews2TextContentParagr6);
//            System.out.println("**********************************");
//            System.out.println(generalSocNews2TextContentParagr7);
//            System.out.println("**********************************");
//            System.out.println(generalSocNews2TextContentParagr8);
//            System.out.println("**********************************");
//            System.out.println(generalSocNews2TextContentParagr9);
//            System.out.println("**********************************");
//            System.out.println(generalSocNews2TextContentParagr10);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr11);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr12);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr13);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr14);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr15);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr16);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr17);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr18);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr19);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr20SubTitle);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr20);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr21);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr22);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr23);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr24SubTitle);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr24);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr25);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr26SubTitle);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr26);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr27);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr28SubTitle);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr28);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr29);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr30);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr31SubTitle);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr31);
//            System.out.println("**********************************");
//            System.out.println(generalNews7TextContentParagr32);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
