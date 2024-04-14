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
public class GeneralInfoParsingService extends CommonParsingTextService {

    final String URL = "https://aussiedlerbote.de";

    GeneralNewsParsingRepository genNewsRepository;
    GenNewsCategoryRepository genNewsCategoryRepository;

    public GeneralInfoParsingService(GeneralNewsParsingRepository genNewsRepository, GenNewsCategoryRepository genNewsCategoryRepository) {
        this.genNewsRepository = genNewsRepository;
        this.genNewsCategoryRepository = genNewsCategoryRepository;
    }

    public Map<Document, String> getDocumentGeneralInfo(String url, String referrer) throws IOException {
        Map<Document, String> genInfoMap = new HashMap<>();
        Document basDoc = getDocument(url, REFERRER);
        Element elColumnNews = basDoc.getElementsByClass("footer-top").first();
        Element el2ColumnNews = elColumnNews.child(1).child(1);
        String urlToGeneralInfo = url + el2ColumnNews.child(10).select("a").attr("href");
        Document docGeneralInfo = getDocument(urlToGeneralInfo, url);
        genInfoMap.put(docGeneralInfo, urlToGeneralInfo);
        return genInfoMap;
    }

    @Transactional
    public GeneralNewsParsObj parsingGeneralInfo1() {

        GeneralNewsParsObj generalInfo1 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("GENERAL_INFO");

        try {
            Document basDoc = getDocument(URL, REFERRER);
            Elements elsColumnNews = basDoc.getElementsByClass("post new");
            Element elUrlToGeneralInfoNews = elsColumnNews.get(5);
            String urlToGeneralInfoNews = elUrlToGeneralInfoNews.child(0).attr("href");
            Document docGeneralInfo = getDocument(urlToGeneralInfoNews, URL);

            Element elGeneralInfoAllContent = docGeneralInfo.getElementsByClass("single-content").get(0);
            String title = elGeneralInfoAllContent.child(0).text();
            Element elGeneralInfoContent = docGeneralInfo.getElementsByClass("main-content").first();
            String shortDescription = elGeneralInfoContent.child(0).text();

            Element elGeneralInfoTextContent = elGeneralInfoContent.getElementsByClass("wp-content").first();
            String imgUrl = elGeneralInfoTextContent.child(0).child(0).attr("src");
            String generalInfoArtParagr1 = elGeneralInfoTextContent.child(1).text();
            String generalInfoArtParagr2 = elGeneralInfoTextContent.child(3).text();
            String generalInfoArtParagr3 = elGeneralInfoTextContent.child(4).text();
            String generalInfoArtParagr4 = elGeneralInfoTextContent.child(5).text();
            String generalInfoArtParagr5 = elGeneralInfoTextContent.child(6).text();
            String generalInfoArtParagr6 = elGeneralInfoTextContent.child(7).text();
            String generalInfoArtParagr7 = elGeneralInfoTextContent.child(8).text();
            String generalInfoArtParagr8 = elGeneralInfoTextContent.child(9).text();
            String generalInfoArtParagr9 = elGeneralInfoTextContent.child(10).text();
            String generalInfoArtParagr10 = elGeneralInfoTextContent.child(11).text();
            String generalInfoArtParagr11 = elGeneralInfoTextContent.child(12).text();
            String generalInfoArtParagr12 = elGeneralInfoTextContent.child(13).text();
            String generalInfoArtParagr13SubTitle = elGeneralInfoTextContent.child(14).text();
            String generalInfoArtParagr13 = elGeneralInfoTextContent.child(15).text();
            String generalInfoArtParagr14 = elGeneralInfoTextContent.child(16).text();
            String generalInfoArtParagr15 = elGeneralInfoTextContent.child(17).text();

            StringBuilder sb = new StringBuilder();

            String generalInfoArtContent = sb
                    .append(" " + generalInfoArtParagr1)
                    .append(" " + generalInfoArtParagr2)
                    .append(" " + generalInfoArtParagr3)
                    .append(" " + generalInfoArtParagr4)
                    .append(" " + generalInfoArtParagr5)
                    .append(" " + generalInfoArtParagr6)
                    .append(" " + generalInfoArtParagr7)
                    .append(" " + generalInfoArtParagr8)
                    .append(" " + generalInfoArtParagr9)
                    .append(" " + generalInfoArtParagr10)
                    .append(" " + generalInfoArtParagr11)
                    .append(" " + generalInfoArtParagr12)
                    .append("\n")
                    .append(" " + generalInfoArtParagr13SubTitle)
                    .append("\n")
                    .append(" " + generalInfoArtParagr13)
                    .append(" " + generalInfoArtParagr14)
                    .append(" " + generalInfoArtParagr15)
                    .toString();

            generalInfo1.setTitle(title);
            generalInfo1.setShortDescription(shortDescription);
            generalInfo1.setContent(generalInfoArtContent);
            generalInfo1.setImgUrl(imgUrl);
            generalInfo1.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return generalInfo1;
    }

    @Transactional
    public GeneralNewsParsObj parsingMinorGeneralInfo2() {

        GeneralNewsParsObj minorGeneralInfo2 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("GENERAL_INFO");

        try {
            Document basDoc = getDocument(URL, REFERRER);
            Element elColumnNews = basDoc.getElementsByClass("footer-top").first();
            Element el2ColumnNews = elColumnNews.child(1).child(1);
            String urlToGeneralInfo = URL + el2ColumnNews.child(10).select("a").attr("href");
            Document docGeneralInfo = getDocument(urlToGeneralInfo, URL);

            Elements elsThirdColumnInfo = docGeneralInfo.getElementsByClass("news-grid-column third");

            String urlMinorGeneralInfo2 = elsThirdColumnInfo.get(0).child(1).attr("href");
            Document docMinorGeneralInfo2 = getDocument(urlMinorGeneralInfo2, urlToGeneralInfo);

            Element elGeneralInfo2Title = docMinorGeneralInfo2.getElementsByClass("single-content").get(0);
            String title = elGeneralInfo2Title.child(0).text();
            Element elGeneralInfo2Content = docMinorGeneralInfo2.getElementsByClass("main-content").first();
            String shortDescription = elGeneralInfo2Content.child(0).text();

            Element elGeneralInfo2TextContent = docMinorGeneralInfo2.getElementsByClass("wp-content").first();
            String imgUrl = elGeneralInfo2TextContent.child(0).child(0).attr("src");

            String generalInfo2TextContentParagr1 = elGeneralInfo2TextContent.child(1).text();
            String generalInfo2TextContentParagr2 = elGeneralInfo2TextContent.child(4).text();
            String generalInfo2TextContentParagr3 = elGeneralInfo2TextContent.child(5).text();
            String generalInfo2TextContentParagr4 = elGeneralInfo2TextContent.child(6).text();
            String generalInfo2TextContentParagr5SubTitle = elGeneralInfo2TextContent.child(7).text();
            String generalInfo2TextContentParagr5 = elGeneralInfo2TextContent.child(8).text();
            String generalInfo2TextContentParagr6 = elGeneralInfo2TextContent.child(9).text();
            String generalInfo2TextContentParagr7 = elGeneralInfo2TextContent.child(11).text();
            String generalInfo2TextContentParagr8 = elGeneralInfo2TextContent.child(12).text();
            String generalInfo2TextContentParagr9SubTitle = elGeneralInfo2TextContent.child(13).text();
            String generalInfo2TextContentParagr9 = elGeneralInfo2TextContent.child(14).text();
            String generalInfo2TextContentParagr10 = elGeneralInfo2TextContent.child(15).text();
            String generalInfo2TextContentParagr11 = elGeneralInfo2TextContent.child(16).text();
            String generalInfo2TextContentParagr12SubTitle = elGeneralInfo2TextContent.child(17).text();
            String generalInfo2TextContentParagr12 = elGeneralInfo2TextContent.child(18).text();
            String generalInfo2TextContentParagr13 = elGeneralInfo2TextContent.child(19).text();
            String generalInfo2TextContentParagr14 = elGeneralInfo2TextContent.child(20).text();
            String generalInfo2TextContentParagr15SubTitle = elGeneralInfo2TextContent.child(21).text();
            String generalInfo2TextContentParagr15 = elGeneralInfo2TextContent.child(22).text();
            String generalInfo2TextContentParagr16 = elGeneralInfo2TextContent.child(23).text();
            String generalInfo2TextContentParagr17 = elGeneralInfo2TextContent.child(24).text();
            String generalInfo2TextContentParagr18SubTitle = elGeneralInfo2TextContent.child(25).text();
            String generalInfo2TextContentParagr18 = elGeneralInfo2TextContent.child(26).text();
            String generalInfo2TextContentParagr19 = elGeneralInfo2TextContent.child(27).text();
            String generalInfo2TextContentParagr20SubTitle = elGeneralInfo2TextContent.child(28).text();
            String generalInfo2TextContentParagr20 = elGeneralInfo2TextContent.child(29).text();
            String generalInfo2TextContentParagr21 = elGeneralInfo2TextContent.child(30).text();
            String generalInfo2TextContentParagr22 = elGeneralInfo2TextContent.child(31).text();
            String generalInfo2TextContentParagr23 = elGeneralInfo2TextContent.child(32).text();
            String generalInfo2TextContentParagr24SubTitle = elGeneralInfo2TextContent.child(33).text();
            String generalInfo2TextContentParagr24 = elGeneralInfo2TextContent.child(34).text();
            String generalInfo2TextContentParagr25 = elGeneralInfo2TextContent.child(35).text();
            String generalInfo2TextContentParagr26SubTitle = elGeneralInfo2TextContent.child(36).text();
            String generalInfo2TextContentParagr26 = elGeneralInfo2TextContent.child(37).text();
            String generalInfo2TextContentParagr27 = elGeneralInfo2TextContent.child(38).text();
            String generalInfo2TextContentParagr28SubTitle = elGeneralInfo2TextContent.child(39).text();
            String generalInfo2TextContentParagr28 = elGeneralInfo2TextContent.child(40).text();
            String generalInfo2TextContentParagr29 = elGeneralInfo2TextContent.child(41).text();
            String generalInfo2TextContentParagr30 = elGeneralInfo2TextContent.child(42).text();
            String generalInfo2TextContentParagr31SubTitle = elGeneralInfo2TextContent.child(43).text();
            String generalInfo2TextContentParagr31 = elGeneralInfo2TextContent.child(44).text();
            String generalInfo2TextContentParagr32 = elGeneralInfo2TextContent.child(45).text();

            StringBuilder sb = new StringBuilder();

            String minorGeneralInfo2ArtContent = sb
                    .append(generalInfo2TextContentParagr1)
                    .append(" " + generalInfo2TextContentParagr2)
                    .append(" " + generalInfo2TextContentParagr3)
                    .append(" " + generalInfo2TextContentParagr4)
                    .append("\n")
                    .append(generalInfo2TextContentParagr5SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr5)
                    .append(" " + generalInfo2TextContentParagr6)
                    .append(" " + generalInfo2TextContentParagr7)
                    .append(" " + generalInfo2TextContentParagr8)
                    .append("\n")
                    .append(generalInfo2TextContentParagr9SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr9)
                    .append(" " + generalInfo2TextContentParagr10)
                    .append(" " + generalInfo2TextContentParagr11)
                    .append("\n")
                    .append(generalInfo2TextContentParagr12SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr12)
                    .append(" " + generalInfo2TextContentParagr13)
                    .append(" " + generalInfo2TextContentParagr14)
                    .append("\n")
                    .append(generalInfo2TextContentParagr15SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr15)
                    .append(" " + generalInfo2TextContentParagr16)
                    .append(" " + generalInfo2TextContentParagr17)
                    .append("\n")
                    .append(generalInfo2TextContentParagr18SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr18)
                    .append(" " + generalInfo2TextContentParagr19)
                    .append("\n")
                    .append(generalInfo2TextContentParagr20SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr20)
                    .append(" " + generalInfo2TextContentParagr21)
                    .append(" " + generalInfo2TextContentParagr22)
                    .append(" " + generalInfo2TextContentParagr23)
                    .append("\n")
                    .append(generalInfo2TextContentParagr24SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr24)
                    .append(" " + generalInfo2TextContentParagr25)
                    .append("\n")
                    .append(generalInfo2TextContentParagr26SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr26)
                    .append(" " + generalInfo2TextContentParagr27)
                    .append("\n")
                    .append(generalInfo2TextContentParagr28SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr28)
                    .append(" " + generalInfo2TextContentParagr29)
                    .append(" " + generalInfo2TextContentParagr30)
                    .append("\n")
                    .append(generalInfo2TextContentParagr31SubTitle)
                    .append("\n")
                    .append(" " + generalInfo2TextContentParagr31)
                    .append(" " + generalInfo2TextContentParagr32)
                    .toString();

            minorGeneralInfo2.setTitle(title);
            minorGeneralInfo2.setShortDescription(shortDescription);
            minorGeneralInfo2.setContent(minorGeneralInfo2ArtContent);
            minorGeneralInfo2.setImgUrl(imgUrl);
            minorGeneralInfo2.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return minorGeneralInfo2;
    }

    @Transactional
    public GeneralNewsParsObj parsingMinorGeneralInfo4() {

        GeneralNewsParsObj minorGeneralInfo4 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("GENERAL_INFO");

        try {
            Map<Document, String> genInfoMap = getDocumentGeneralInfo(URL, REFERRER);
            Document docGeneralInfo = genInfoMap.keySet().iterator().next();
            String urlToGeneralInfo = genInfoMap.get(docGeneralInfo);

            Elements elsThirdColumnInfo = docGeneralInfo.getElementsByClass("news-grid-column third");

            String urlMinorGeneralInfo4 = elsThirdColumnInfo.get(0).child(3).attr("href");
            Document docMinorGeneralInfo4 = getDocument(urlMinorGeneralInfo4, urlToGeneralInfo);

            Element elGeneralInfo4Title = docMinorGeneralInfo4.getElementsByClass("single-content").get(0);
            String title = elGeneralInfo4Title.child(0).text();
            Element elGeneralInfo4Content = docMinorGeneralInfo4.getElementsByClass("main-content").first();
            String shortDescription = elGeneralInfo4Content.child(0).text();

            Element elGeneralInfo4TextContent = docMinorGeneralInfo4.getElementsByClass("wp-content").first();
            String imgUrl = elGeneralInfo4TextContent.child(0).child(0).attr("src");

            String generalInfo4TextContentParagr1 = elGeneralInfo4TextContent.child(1).text();
            String generalInfo4TextContentParagr2 = elGeneralInfo4TextContent.child(2).text();
            String generalInfo4TextContentParagr3 = elGeneralInfo4TextContent.child(3).text();
            String generalInfo4TextContentParagr4 = elGeneralInfo4TextContent.child(5).text();
            String generalInfo4TextContentParagr5 = elGeneralInfo4TextContent.child(6).text();
            String generalInfo4TextContentParagr6 = elGeneralInfo4TextContent.child(7).text();
            String generalInfo4TextContentParagr7 = elGeneralInfo4TextContent.child(8).text();
            String generalInfo4TextContentParagr8SubTitle = elGeneralInfo4TextContent.child(9).text();
            String generalInfo4TextContentParagr8 = elGeneralInfo4TextContent.child(10).text();
            String generalInfo4TextContentParagr9 = elGeneralInfo4TextContent.child(11).text();
            String generalInfo4TextContentParagr10 = elGeneralInfo4TextContent.child(12).text();
            String generalInfo4TextContentParagr11SubTitle = elGeneralInfo4TextContent.child(13).text();
            String generalInfo4TextContentParagr11 = elGeneralInfo4TextContent.child(14).text();
            String generalInfo4TextContentParagr12SubTitle = elGeneralInfo4TextContent.child(15).text();
            String generalInfo4TextContentParagr12 = elGeneralInfo4TextContent.child(16).text();
            String generalInfo4TextContentParagr13 = elGeneralInfo4TextContent.child(17).text();
            String generalInfo4TextContentParagr14SubTitle = elGeneralInfo4TextContent.child(18).text();
            String generalInfo4TextContentParagr14 = elGeneralInfo4TextContent.child(19).text();
            String generalInfo4TextContentParagr15 = elGeneralInfo4TextContent.child(20).text();
            String generalInfo4TextContentParagr16SubTitle = elGeneralInfo4TextContent.child(21).text();
            String generalInfo4TextContentParagr16 = elGeneralInfo4TextContent.child(22).text();
            String generalInfo4TextContentParagr17SubTitle = elGeneralInfo4TextContent.child(23).text();
            String generalInfo4TextContentParagr17 = elGeneralInfo4TextContent.child(24).text();
            String generalInfo4TextContentParagr18 = elGeneralInfo4TextContent.child(25).text();
            String generalInfo4TextContentParagr19 = elGeneralInfo4TextContent.child(26).text();

            StringBuilder sb = new StringBuilder();

            String minorGeneralInfo4ArtContent = sb
                    .append(generalInfo4TextContentParagr1)
                    .append(" " + generalInfo4TextContentParagr2)
                    .append(" " + generalInfo4TextContentParagr3)
                    .append(" " + generalInfo4TextContentParagr4)
                    .append(" " + generalInfo4TextContentParagr5)
                    .append(" " + generalInfo4TextContentParagr6)
                    .append(" " + generalInfo4TextContentParagr7)
                    .append("\n")
                    .append(generalInfo4TextContentParagr8SubTitle)
                    .append("\n")
                    .append(" " + generalInfo4TextContentParagr8)
                    .append(" " + generalInfo4TextContentParagr9)
                    .append(" " + generalInfo4TextContentParagr10)
                    .append("\n")
                    .append(generalInfo4TextContentParagr11SubTitle)
                    .append("\n")
                    .append(" " + generalInfo4TextContentParagr11)
                    .append("\n")
                    .append(generalInfo4TextContentParagr12SubTitle)
                    .append("\n")
                    .append(" " + generalInfo4TextContentParagr12)
                    .append(" " + generalInfo4TextContentParagr13)
                    .append("\n")
                    .append(generalInfo4TextContentParagr14SubTitle)
                    .append("\n")
                    .append(" " + generalInfo4TextContentParagr14)
                    .append(" " + generalInfo4TextContentParagr15)
                    .append("\n")
                    .append(generalInfo4TextContentParagr16SubTitle)
                    .append("\n")
                    .append(" " + generalInfo4TextContentParagr16)
                    .append("\n")
                    .append(generalInfo4TextContentParagr17SubTitle)
                    .append("\n")
                    .append(" " + generalInfo4TextContentParagr17)
                    .append(" " + generalInfo4TextContentParagr18)
                    .append(" " + generalInfo4TextContentParagr19)
                    .toString();

            minorGeneralInfo4.setTitle(title);
            minorGeneralInfo4.setShortDescription(shortDescription);
            minorGeneralInfo4.setContent(minorGeneralInfo4ArtContent);
            minorGeneralInfo4.setImgUrl(imgUrl);
            minorGeneralInfo4.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return minorGeneralInfo4;
    }

    public GeneralNewsParsObj parsingMinorGeneralInfo6() {

        GeneralNewsParsObj minorGeneralInfo6 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("GENERAL_INFO");

        try {
            Map<Document, String> genInfoMap = getDocumentGeneralInfo(URL, REFERRER);
            Document docGeneralInfo = genInfoMap.keySet().iterator().next();
            String urlToGeneralInfo = genInfoMap.get(docGeneralInfo);

            Elements elsThirdColumnInfo = docGeneralInfo.getElementsByClass("news-grid-column third");

            String urlMinorGeneralInfo6 = elsThirdColumnInfo.get(0).child(5).attr("href");
            Document docMinorGeneralInfo6 = getDocument(urlMinorGeneralInfo6, urlToGeneralInfo);

            Element elGeneralInfo6Title = docMinorGeneralInfo6.getElementsByClass("single-content").get(0);
            String title = elGeneralInfo6Title.child(0).text();
            Element elGeneralInfo4Content = docMinorGeneralInfo6.getElementsByClass("main-content").first();
            String shortDescription = elGeneralInfo4Content.child(0).text();

            Element elGeneralInfo6TextContent = docMinorGeneralInfo6.getElementsByClass("wp-content").first();
            String imgUrl = elGeneralInfo6TextContent.child(0).child(0).attr("src");

            String generalInfo6TextContentParagr1 = elGeneralInfo6TextContent.child(1).text();
            String generalInfo6TextContentParagr2 = elGeneralInfo6TextContent.child(2).text();
            String generalInfo6TextContentParagr3 = elGeneralInfo6TextContent.child(4).text();
            String generalInfo6TextContentParagr4 = elGeneralInfo6TextContent.child(5).text();
            String generalInfo6TextContentParagr5 = elGeneralInfo6TextContent.child(6).text();
            String generalInfo6TextContentParagr6SubTitle = elGeneralInfo6TextContent.child(7).text();
            String generalInfo6TextContentParagr6 = elGeneralInfo6TextContent.child(8).text();
            String generalInfo6TextContentParagr7 = elGeneralInfo6TextContent.child(9).text();
            String generalInfo6TextContentParagr8 = elGeneralInfo6TextContent.child(10).text();
            String generalInfo6TextContentParagr9SubTitle = elGeneralInfo6TextContent.child(11).text();
            String generalInfo6TextContentParagr9 = elGeneralInfo6TextContent.child(12).text();
            String generalInfo6TextContentParagr10 = elGeneralInfo6TextContent.child(13).text();
            String generalInfo6TextContentParagr11SubTitle = elGeneralInfo6TextContent.child(14).text();
            String generalInfo6TextContentParagr11 = elGeneralInfo6TextContent.child(15).text();
            String generalInfo6TextContentParagr12 = elGeneralInfo6TextContent.child(16).text();
            String generalInfo6TextContentParagr13 = elGeneralInfo6TextContent.child(17).text();
            String generalInfo6TextContentParagr14 = elGeneralInfo6TextContent.child(18).text();
            String generalInfo6TextContentParagr15 = elGeneralInfo6TextContent.child(19).text();
            String generalInfo6TextContentParagr16 = elGeneralInfo6TextContent.child(20).text();
            String generalInfo6TextContentParagr17 = elGeneralInfo6TextContent.child(21).text();
            String generalInfo6TextContentParagr18 = elGeneralInfo6TextContent.child(22).text();
            String generalInfo6TextContentParagr19 = elGeneralInfo6TextContent.child(23).text();

            StringBuilder sb = new StringBuilder();

            String minorGeneralInfo6ArtContent = sb
                    .append(generalInfo6TextContentParagr1)
                    .append(" " + generalInfo6TextContentParagr2)
                    .append(" " + generalInfo6TextContentParagr3)
                    .append(" " + generalInfo6TextContentParagr4)
                    .append(" " + generalInfo6TextContentParagr5)
                    .append("\n")
                    .append(generalInfo6TextContentParagr6SubTitle)
                    .append("\n")
                    .append(" " + generalInfo6TextContentParagr6)
                    .append(" " + generalInfo6TextContentParagr7)
                    .append(" " + generalInfo6TextContentParagr8)
                    .append("\n")
                    .append(generalInfo6TextContentParagr9SubTitle)
                    .append("\n")
                    .append(" " + generalInfo6TextContentParagr9)
                    .append(" " + generalInfo6TextContentParagr10)
                    .append("\n")
                    .append(generalInfo6TextContentParagr11SubTitle)
                    .append("\n")
                    .append(" " + generalInfo6TextContentParagr11)
                    .append(" " + generalInfo6TextContentParagr12)
                    .append(" " + generalInfo6TextContentParagr13)
                    .append(" " + generalInfo6TextContentParagr14)
                    .append(" " + generalInfo6TextContentParagr15)
                    .append(" " + generalInfo6TextContentParagr16)
                    .append(" " + generalInfo6TextContentParagr17)
                    .append(" " + generalInfo6TextContentParagr18)
                    .append(" " + generalInfo6TextContentParagr19)
                    .toString();


            minorGeneralInfo6.setTitle(title);
            minorGeneralInfo6.setShortDescription(shortDescription);
            minorGeneralInfo6.setContent(minorGeneralInfo6ArtContent);
            minorGeneralInfo6.setImgUrl(imgUrl);
            minorGeneralInfo6.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return minorGeneralInfo6;
    }

    public GeneralNewsParsObj parsingMinorGeneralInfo1() {

        GeneralNewsParsObj minorGeneralInfo1 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("GENERAL_INFO");

        try {
            Map<Document, String> genInfoMap = getDocumentGeneralInfo(URL, REFERRER);
            Document docGeneralInfo = genInfoMap.keySet().iterator().next();
            String urlToGeneralInfo = genInfoMap.get(docGeneralInfo);

            Elements elsThirdColumnInfo = docGeneralInfo.getElementsByClass("news-grid-column third");

            String urlMinorGeneralInfo1 = elsThirdColumnInfo.get(0).child(0).attr("href");
            Document docMinorGeneralInfo1 = getDocument(urlMinorGeneralInfo1, urlToGeneralInfo);

            Element elMinorGeneralInfo1Title = docMinorGeneralInfo1.getElementsByClass("single-content").get(0);
            String title = elMinorGeneralInfo1Title.child(0).text();
            Element elMinorGeneralInfo1Content = docMinorGeneralInfo1.getElementsByClass("main-content").first();
            String shortDescription = elMinorGeneralInfo1Content.child(0).text();

            Element elMinorGeneralInfo1TextContent = docMinorGeneralInfo1.getElementsByClass("wp-content").first();
            String imgUrl = elMinorGeneralInfo1TextContent.child(0).child(0).attr("src");

            String generalInfo1TextContentParagr1 = elMinorGeneralInfo1TextContent.child(1).text();
            String generalInfo1TextContentParagr2 = elMinorGeneralInfo1TextContent.child(3).text();
            String generalInfo1TextContentParagr3 = elMinorGeneralInfo1TextContent.child(4).text();
            String generalInfo1TextContentParagr4SubTitle = elMinorGeneralInfo1TextContent.child(5).text();
            String generalInfo1TextContentParagr4 = elMinorGeneralInfo1TextContent.child(6).text();
            String generalInfo1TextContentParagr5 = elMinorGeneralInfo1TextContent.child(7).text();
            String generalInfo1TextContentParagr6 = elMinorGeneralInfo1TextContent.child(8).text();
            String generalInfo1TextContentParagr7 = elMinorGeneralInfo1TextContent.child(9).text();
            String generalInfo1TextContentParagr8 = elMinorGeneralInfo1TextContent.child(10).text();
            String generalInfo1TextContentParagr9 = elMinorGeneralInfo1TextContent.child(11).text();
            String generalInfo1TextContentParagr10 = elMinorGeneralInfo1TextContent.child(12).text();
            String generalInfo1TextContentParagr11 = elMinorGeneralInfo1TextContent.child(13).text();
            String generalInfo1TextContentParagr12SubTitle = elMinorGeneralInfo1TextContent.child(14).text();
            String generalInfo1TextContentParagr12 = elMinorGeneralInfo1TextContent.child(15).text();
            String generalInfo1TextContentParagr13 = elMinorGeneralInfo1TextContent.child(16).text();
            String generalInfo1TextContentParagr14 = elMinorGeneralInfo1TextContent.child(17).text();
            String generalInfo1TextContentParagr15 = elMinorGeneralInfo1TextContent.child(18).text();
            String generalInfo1TextContentParagr16SubTitle = elMinorGeneralInfo1TextContent.child(19).text();
            String generalInfo1TextContentParagr16 = elMinorGeneralInfo1TextContent.child(20).text();
            String generalInfo1TextContentParagr17 = elMinorGeneralInfo1TextContent.child(21).text();
            String generalInfo1TextContentParagr18 = elMinorGeneralInfo1TextContent.child(22).text();
            String generalInfo1TextContentParagr19 = elMinorGeneralInfo1TextContent.child(23).text();
            String generalInfo1TextContentParagr20SubTitle = elMinorGeneralInfo1TextContent.child(24).text();
            String generalInfo1TextContentParagr20 = elMinorGeneralInfo1TextContent.child(25).text();
            String generalInfo1TextContentParagr21 = elMinorGeneralInfo1TextContent.child(26).text();
            String generalInfo1TextContentParagr22 = elMinorGeneralInfo1TextContent.child(27).text();

            StringBuilder sb = new StringBuilder();

            String minorGeneralInfo1ArtContent = sb
                    .append(generalInfo1TextContentParagr1)
                    .append(" " + generalInfo1TextContentParagr2)
                    .append(" " + generalInfo1TextContentParagr3)
                    .append("\n")
                    .append(generalInfo1TextContentParagr4SubTitle)
                    .append("\n")
                    .append(" " + generalInfo1TextContentParagr4)
                    .append(" " + generalInfo1TextContentParagr5)
                    .append(" " + generalInfo1TextContentParagr6)
                    .append(" " + generalInfo1TextContentParagr7)
                    .append(" " + generalInfo1TextContentParagr8)
                    .append(" " + generalInfo1TextContentParagr9)
                    .append(" " + generalInfo1TextContentParagr10)
                    .append(" " + generalInfo1TextContentParagr11)
                    .append("\n")
                    .append(generalInfo1TextContentParagr12SubTitle)
                    .append("\n")
                    .append(" " + generalInfo1TextContentParagr12)
                    .append(" " + generalInfo1TextContentParagr13)
                    .append(" " + generalInfo1TextContentParagr14)
                    .append(" " + generalInfo1TextContentParagr15)
                    .append("\n")
                    .append(generalInfo1TextContentParagr16SubTitle)
                    .append("\n")
                    .append(" " + generalInfo1TextContentParagr16)
                    .append(" " + generalInfo1TextContentParagr17)
                    .append(" " + generalInfo1TextContentParagr18)
                    .append(" " + generalInfo1TextContentParagr19)
                    .append("\n")
                    .append(generalInfo1TextContentParagr20SubTitle)
                    .append("\n")
                    .append(" " + generalInfo1TextContentParagr20)
                    .append(" " + generalInfo1TextContentParagr21)
                    .append(" " + generalInfo1TextContentParagr22)
                    .toString();

            minorGeneralInfo1.setTitle(title);
            minorGeneralInfo1.setShortDescription(shortDescription);
            minorGeneralInfo1.setContent(minorGeneralInfo1ArtContent);
            minorGeneralInfo1.setImgUrl(imgUrl);
            minorGeneralInfo1.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return minorGeneralInfo1;
    }

    public GeneralNewsParsObj parsingMinorGeneralInfo8() {

        GeneralNewsParsObj minorGeneralInfo8 = new GeneralNewsParsObj();
        NewsCategory newsCategory = genNewsCategoryRepository.findByTitle("GENERAL_INFO");

        try {
            Map<Document, String> genInfoMap = getDocumentGeneralInfo(URL, REFERRER);
            Document docGeneralInfo = genInfoMap.keySet().iterator().next();
            String urlToGeneralInfo = genInfoMap.get(docGeneralInfo);

            Elements elsThirdColumnInfo = docGeneralInfo.getElementsByClass("news-grid-column third");

            String urlMinorGeneralInfo8 = elsThirdColumnInfo.get(0).child(7).attr("href");
            Document docMinorGeneralInfo8 = getDocument(urlMinorGeneralInfo8, urlToGeneralInfo);

            Element elMinorGeneralInfo8Title = docMinorGeneralInfo8.getElementsByClass("single-content").get(0);
            String title = elMinorGeneralInfo8Title.child(0).text();
            Element elMinorGeneralInfo8Content = docMinorGeneralInfo8.getElementsByClass("main-content").first();
            String shortDescription = elMinorGeneralInfo8Content.child(0).text();

            Element elMinorGeneralInfo8TextContent = docMinorGeneralInfo8.getElementsByClass("wp-content").first();
            String imgUrl = elMinorGeneralInfo8TextContent.child(0).child(0).attr("src");

            String generalInfo8TextContentParagr1 = elMinorGeneralInfo8TextContent.child(1).text();
            String generalInfo8TextContentParagr2 = elMinorGeneralInfo8TextContent.child(3).text();
            String generalInfo8TextContentParagr3 = elMinorGeneralInfo8TextContent.child(4).text();
            String generalInfo8TextContentParagr4 = elMinorGeneralInfo8TextContent.child(5).text();
            String generalInfo8TextContentParagr5 = elMinorGeneralInfo8TextContent.child(6).text();
            String generalInfo8TextContentParagr6 = elMinorGeneralInfo8TextContent.child(7).text();
            String generalInfo8TextContentParagr7 = elMinorGeneralInfo8TextContent.child(8).text();
            String generalInfo8TextContentParagr8SubTitle = elMinorGeneralInfo8TextContent.child(9).text();
            String generalInfo8TextContentParagr8 = elMinorGeneralInfo8TextContent.child(10).text();
            String generalInfo8TextContentParagr9SubPargr = elMinorGeneralInfo8TextContent.child(11).text();
            String generalInfo8TextContentParagr10 = elMinorGeneralInfo8TextContent.child(12).text();
            String generalInfo8TextContentParagr11 = elMinorGeneralInfo8TextContent.child(13).text();
            String generalInfo8TextContentParagr12SubPargr = elMinorGeneralInfo8TextContent.child(14).text();
            String generalInfo8TextContentParagr13 = elMinorGeneralInfo8TextContent.child(15).text();
            String generalInfo8TextContentParagr14SubPargr = elMinorGeneralInfo8TextContent.child(16).text();
            String generalInfo8TextContentParagr15 = elMinorGeneralInfo8TextContent.child(17).text();
            String generalInfo8TextContentParagr16 = elMinorGeneralInfo8TextContent.child(18).text();
            String generalInfo8TextContentParagr17SubPargr = elMinorGeneralInfo8TextContent.child(19).text();
            String generalInfo8TextContentParagr18 = elMinorGeneralInfo8TextContent.child(20).text();
            String generalInfo8TextContentParagr19 = elMinorGeneralInfo8TextContent.child(21).text();
            String generalInfo8TextContentParagr20SubTitle = elMinorGeneralInfo8TextContent.child(22).text();
            String generalInfo8TextContentParagr20 = elMinorGeneralInfo8TextContent.child(23).text();
            String generalInfo8TextContentParagr21 = elMinorGeneralInfo8TextContent.child(24).text();
            String generalInfo8TextContentParagr22 = elMinorGeneralInfo8TextContent.child(25).text();
            String generalInfo8TextContentParagr23 = elMinorGeneralInfo8TextContent.child(26).text();

            StringBuilder sb = new StringBuilder();

            String minorGeneralInfo8ArtContent = sb
                    .append(generalInfo8TextContentParagr1)
                    .append(" " + generalInfo8TextContentParagr2)
                    .append(" " + generalInfo8TextContentParagr3)
                    .append(" " + generalInfo8TextContentParagr4)
                    .append(" " + generalInfo8TextContentParagr5)
                    .append(" " + generalInfo8TextContentParagr6)
                    .append(" " + generalInfo8TextContentParagr7)
                    .append("\n")
                    .append(generalInfo8TextContentParagr8SubTitle)
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr8)
                    .append("\n")
                    .append("\t" + "- " + generalInfo8TextContentParagr9SubPargr + ":")
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr10)
                    .append(" " + generalInfo8TextContentParagr11)
                    .append("\n")
                    .append("\t" + "- " + generalInfo8TextContentParagr12SubPargr + ":")
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr13)
                    .append("\n")
                    .append("\t" + "- " + generalInfo8TextContentParagr14SubPargr + ":")
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr15)
                    .append(generalInfo8TextContentParagr16)
                    .append(" " + generalInfo8TextContentParagr16)
                    .append("\n")
                    .append("\t" + "- " + generalInfo8TextContentParagr17SubPargr + ":")
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr18)
                    .append(" " + generalInfo8TextContentParagr19)
                    .append("\n")
                    .append(generalInfo8TextContentParagr20SubTitle)
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr20)
                    .append(" " + generalInfo8TextContentParagr21)
                    .append(" " + generalInfo8TextContentParagr22)
                    .append(" " + generalInfo8TextContentParagr23)
                    .toString();

            minorGeneralInfo8.setTitle(title);
            minorGeneralInfo8.setShortDescription(shortDescription);
            minorGeneralInfo8.setContent(minorGeneralInfo8ArtContent);
            minorGeneralInfo8.setImgUrl(imgUrl);
            minorGeneralInfo8.setCategory(newsCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return minorGeneralInfo8;
    }

    @Transactional
    public GeneralNewsParsObj saveGeneralInfo1() {
        return saveObjectIfNotExists(
                this::parsingGeneralInfo1,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj saveMinorGeneralInfo2() {
        return saveObjectIfNotExists(
                this::parsingMinorGeneralInfo2,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj saveMinorGeneralInfo4() {
        return saveObjectIfNotExists(
                this::parsingMinorGeneralInfo4,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj saveMinorGeneralInfo6() {
        return saveObjectIfNotExists(
                this::parsingMinorGeneralInfo6,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj saveMinorGeneralInfo1() {
        return saveObjectIfNotExists(
                this::parsingMinorGeneralInfo1,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }

    @Transactional
    public GeneralNewsParsObj saveMinorGeneralInfo8() {
        return saveObjectIfNotExists(
                this::parsingMinorGeneralInfo8,
                genNewsRepository,
                GeneralNewsParsObj::getTitle
        );
    }
}


    /*static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";
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

    public static Map<Document, String> getDocGeneralInfo(String url, String referrer) throws IOException {
        Map<Document, String> genInfoMap = new HashMap<>();
        Document basDoc = getDoc(url, REFERRER);
        Element elColumnNews = basDoc.getElementsByClass("footer-top").first();
        Element el2ColumnNews = elColumnNews.child(1).child(1);
        String urlToGeneralInfo = url + el2ColumnNews.child(10).select("a").attr("href");
        Document docGeneralInfo = getDoc(urlToGeneralInfo, url);
        genInfoMap.put(docGeneralInfo, urlToGeneralInfo);
        return genInfoMap;
    }

    public static void main(String[] args) {
        final String URL = "https://aussiedlerbote.de";
        try {
            Map<Document, String> genInfoMap = getDocGeneralInfo(URL, REFERRER);
            Document docGeneralInfo = genInfoMap.keySet().iterator().next();
            String urlToGeneralInfo = genInfoMap.get(docGeneralInfo);

            Elements elsThirdColumnInfo = docGeneralInfo.getElementsByClass("news-grid-column third");

            String urlMinorGeneralInfo8 = elsThirdColumnInfo.get(0).child(7).attr("href");
            Document docMinorGeneralInfo8 = getDoc(urlMinorGeneralInfo8, urlToGeneralInfo);

            Element elMinorGeneralInfo8Title = docMinorGeneralInfo8.getElementsByClass("single-content").get(0);
            String title = elMinorGeneralInfo8Title.child(0).text();
            Element elMinorGeneralInfo8Content = docMinorGeneralInfo8.getElementsByClass("main-content").first();
            String shortDescription = elMinorGeneralInfo8Content.child(0).text();

            Element elMinorGeneralInfo8TextContent = docMinorGeneralInfo8.getElementsByClass("wp-content").first();
            String imgUrl = elMinorGeneralInfo8TextContent.child(0).child(0).attr("src");

            String generalInfo8TextContentParagr1 = elMinorGeneralInfo8TextContent.child(1).text();
            String generalInfo8TextContentParagr2 = elMinorGeneralInfo8TextContent.child(3).text();
            String generalInfo8TextContentParagr3 = elMinorGeneralInfo8TextContent.child(4).text();
            String generalInfo8TextContentParagr4= elMinorGeneralInfo8TextContent.child(5).text();
            String generalInfo8TextContentParagr5 = elMinorGeneralInfo8TextContent.child(6).text();
            String generalInfo8TextContentParagr6 = elMinorGeneralInfo8TextContent.child(7).text();
            String generalInfo8TextContentParagr7 = elMinorGeneralInfo8TextContent.child(8).text();
            String generalInfo8TextContentParagr8SubTitle = elMinorGeneralInfo8TextContent.child(9).text();
            String generalInfo8TextContentParagr8 = elMinorGeneralInfo8TextContent.child(10).text();
            String generalInfo8TextContentParagr9SubPargr = elMinorGeneralInfo8TextContent.child(11).text();
            String generalInfo8TextContentParagr10 = elMinorGeneralInfo8TextContent.child(12).text();
            String generalInfo8TextContentParagr11 = elMinorGeneralInfo8TextContent.child(13).text();
            String generalInfo8TextContentParagr12SubPargr = elMinorGeneralInfo8TextContent.child(14).text();
            String generalInfo8TextContentParagr13 = elMinorGeneralInfo8TextContent.child(15).text();
            String generalInfo8TextContentParagr14SubPargr = elMinorGeneralInfo8TextContent.child(16).text();
            String generalInfo8TextContentParagr15 = elMinorGeneralInfo8TextContent.child(17).text();
            String generalInfo8TextContentParagr16 = elMinorGeneralInfo8TextContent.child(18).text();
            String generalInfo8TextContentParagr17SubPargr = elMinorGeneralInfo8TextContent.child(19).text();
            String generalInfo8TextContentParagr18 = elMinorGeneralInfo8TextContent.child(20).text();
            String generalInfo8TextContentParagr19 = elMinorGeneralInfo8TextContent.child(21).text();
            String generalInfo8TextContentParagr20SubTitle = elMinorGeneralInfo8TextContent.child(22).text();
            String generalInfo8TextContentParagr20 = elMinorGeneralInfo8TextContent.child(23).text();
            String generalInfo8TextContentParagr21 = elMinorGeneralInfo8TextContent.child(24).text();
            String generalInfo8TextContentParagr22 = elMinorGeneralInfo8TextContent.child(25).text();
            String generalInfo8TextContentParagr23 = elMinorGeneralInfo8TextContent.child(26).text();
//            String generalInfo8TextContentParagr24 = elMinorGeneralInfo8TextContent.child(27).text();


            StringBuilder sb = new StringBuilder();

            String minorGeneralInfo8ArtContent = sb
                    .append(generalInfo8TextContentParagr1)
                    .append(" " + generalInfo8TextContentParagr2)
                    .append(" " + generalInfo8TextContentParagr3)
                    .append(" " + generalInfo8TextContentParagr4)
                    .append(" " + generalInfo8TextContentParagr5)
                    .append(" " + generalInfo8TextContentParagr6)
                    .append(" " + generalInfo8TextContentParagr7)
                    .append("\n")
                    .append(generalInfo8TextContentParagr8SubTitle)
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr8)
                    .append("\n")
                    .append("\t"+"- " + generalInfo8TextContentParagr9SubPargr +":")
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr10)
                    .append(" " + generalInfo8TextContentParagr11)
                    .append("\n")
                    .append("\t"+"- " +generalInfo8TextContentParagr12SubPargr  +":")
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr13)
                    .append("\n")
                    .append("\t"+"- " +generalInfo8TextContentParagr14SubPargr +":")
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr15)
                    .append(generalInfo8TextContentParagr16)
                    .append(" " + generalInfo8TextContentParagr16)
                    .append("\n")
                    .append("\t"+"- " + generalInfo8TextContentParagr17SubPargr +":")
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr18)
                    .append(" " + generalInfo8TextContentParagr19)
                    .append("\n")
                    .append(generalInfo8TextContentParagr20SubTitle)
                    .append("\n")
                    .append(" " + generalInfo8TextContentParagr20)
                    .append(" " + generalInfo8TextContentParagr21)
                    .append(" " + generalInfo8TextContentParagr22)
                    .append(" " + generalInfo8TextContentParagr23)
//                    .append(" " + generalInfo8TextContentParagr24)
//                    .append(" " + generalInfo8TextContentParagr25)
//
                    .toString();


//            System.out.println(urlToGeneralInfo);
//            System.out.println(urlMinorGeneralInfo8);
//
            System.out.println(title);
            System.out.println(imgUrl);
            System.out.println(shortDescription);
            System.out.println("**********************************");
            System.out.println(minorGeneralInfo8ArtContent);

//            System.out.println(generalInfo8TextContentParagr1);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr2);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr3);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr4);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr5);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr6);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr7);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr8SubTitle);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr8);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr9SubPargr);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr10);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr11);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr12SubPargr);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr13);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr14SubPargr);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr15);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr16);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr17SubPargr);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr18);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr19);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr20SubTitle);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr20);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr21);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr22);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr23);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr24);
//            System.out.println("**********************************");
//            System.out.println(generalInfo8TextContentParagr25);
//            System.out.println("**********************************");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
