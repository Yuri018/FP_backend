package de.aittr.team24_FP_backend.parsing.news_pars_services;

import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.parsing.news_pars_models.LocalNewsCategory;
import de.aittr.team24_FP_backend.parsing.news_pars_models.BerlinDeNewsParsObj;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.BerlinDeNewsCategoryRepository;
import de.aittr.team24_FP_backend.parsing.news_pars_repositories.BerlinDeNewsParsingRepository;
import de.aittr.team24_FP_backend.services.translation_service.TranslationService;
import jakarta.transaction.Transactional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BerlinDeNewsParsingTranslateService extends CommonParsingTextService {

    BerlinDeNewsParsingRepository berlinDeNewsParsingRepository;
    BerlinDeNewsCategoryRepository berlinDeNewsCategoryRepository;
    TranslationService translationService;

    public BerlinDeNewsParsingTranslateService(
            BerlinDeNewsParsingRepository berlinDeNewsParsingRepository,
            BerlinDeNewsCategoryRepository berlinDeNewsCategoryRepository,
            TranslationService translationService) {
        this.berlinDeNewsParsingRepository = berlinDeNewsParsingRepository;
        this.berlinDeNewsCategoryRepository = berlinDeNewsCategoryRepository;
        this.translationService = translationService;
    }

    @Transactional
    public void BerlinCultEvents2ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(BERLIN_NEWS_URL, REFERRER);
            Element elsCult = basDoc.getElementsByClass("tilesgrid").first();
            String urlToCult = getURLToBerlinNewsSections(elsCult, 1);
            Document docCulture = getDocument(urlToCult, BERLIN_NEWS_URL);
            Elements elsCultEvents = docCulture.getElementsByClass("flexgrid grid--3");
            String urlToActualHighlights = BERLIN_NEWS_URL + elsCultEvents.get(0).child(0).child(1).child(0).attr("href");
            Document docActualHighlights = getDocument(urlToActualHighlights, urlToCult);
            Element elEventHighlights2 = docActualHighlights.getElementsByClass("modul-teaser span4").get(1);
            String shortDescription = elEventHighlights2.child(3).selectFirst("p.text").ownText();
            String urlToEventHighlights2 = BERLIN_NEWS_URL + elEventHighlights2.child(1).child(0).attr("href");
            Document docEventHighlights2 = getDocument(urlToEventHighlights2, urlToActualHighlights);
            Element elsImg = docEventHighlights2.getElementsByClass("js-imageblur").first();
            String imageUrl = BERLIN_NEWS_URL + elsImg.attr("src");
            Element elImgCopyright = docEventHighlights2.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            String titleEventHighlights2 = docEventHighlights2.getElementsByClass("article__title heading--article").first().ownText();
            String firstParagraph = docEventHighlights2.getElementsByClass("article__introtext").first().ownText();
            String secondParagraph = docEventHighlights2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0).ownText();
            String thirdSubTitle = docEventHighlights2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(0).text();
            String thirdParagraph = docEventHighlights2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(1).child(0).ownText();
            Elements elsLocation = docEventHighlights2.getElementsByClass("info-container-list list--horizontal list--contact");
            String where = elsLocation.get(0).child(3).ownText();
            String from = elsLocation.get(0).child(5).ownText();
            String to = elsLocation.get(0).child(7).ownText();
            String when = elsLocation.get(0).child(9).ownText();
            String admission = elsLocation.get(0).child(10).ownText();
            String admissSyst = elsLocation.get(0).child(11).child(0).ownText();

            StringBuilder textEventHighlights2 = new StringBuilder();

            String contentEventHighlights2 = textEventHighlights2
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

            BerlinDeNewsParsObj berlinDeNewsParsObj = new BerlinDeNewsParsObj();
            LocalNewsCategory berlinNewsCategory = berlinDeNewsCategoryRepository.findByTitle("CULTURAL_EVENTS");

            berlinDeNewsParsObj.setTitle(translationService.translateText(titleEventHighlights2));
            berlinDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
            berlinDeNewsParsObj.setImgUrl(imageUrl);
            berlinDeNewsParsObj.setImgCopyright(imgCopyright);
            berlinDeNewsParsObj.setContent(translationService.translateText(contentEventHighlights2));
            berlinDeNewsParsObj.setBerlinNewsCategory(berlinNewsCategory);

            saveObjectIfNotExists(
                    () -> berlinDeNewsParsObj,
                    berlinDeNewsParsingRepository,
                    BerlinDeNewsParsObj::getTitle
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void BerlinExhibitHighlightsEvent3ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(BERLIN_NEWS_URL, REFERRER);
            Element elsCult = basDoc.getElementsByClass("tilesgrid").first();
            String urlToCult = getURLToBerlinNewsSections(elsCult, 1);
            Document docCulture = getDocument(urlToCult, BERLIN_NEWS_URL);
            Elements elsCultEvents = docCulture.getElementsByClass("flexgrid grid--3");
            String urlToExhibitionsHighlights = BERLIN_NEWS_URL + elsCultEvents.get(1).child(1).child(1).child(0).attr("href");
            Document docExhibitionsHighlights = getDocument(urlToExhibitionsHighlights, urlToCult);
            Element elEventExhibitHighlights3 = docExhibitionsHighlights.getElementsByClass("modul-teaser span4").get(0);
            String shortDescription = elEventExhibitHighlights3.child(3).selectFirst("p.text").ownText();
            String urlToEventExhibitHighlights3 = BERLIN_NEWS_URL + elEventExhibitHighlights3.child(1).child(0).attr("href");
            Document docEventExhibitHighlights3 = getDocument(urlToEventExhibitHighlights3, urlToExhibitionsHighlights);
            Element elsImg = docEventExhibitHighlights3.getElementsByClass("js-imageblur").first();
            String imageUrl = BERLIN_NEWS_URL + elsImg.attr("src");
            Element elImgCopyright = docEventExhibitHighlights3.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            String title = docEventExhibitHighlights3.getElementsByClass("article__title heading--article").first().ownText();
            String firstParagraph = docEventExhibitHighlights3.getElementsByClass("article__introtext").first().ownText();
            String secondParagraph = docEventExhibitHighlights3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0).ownText();
            String thirdSubTitle = docEventExhibitHighlights3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(0).text();
            String thirdParagraph = docEventExhibitHighlights3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(1).child(0).ownText();
            Elements elsLocation = docEventExhibitHighlights3.getElementsByClass("info-container-list list--horizontal list--contact");
            String where = elsLocation.get(0).child(3).child(0).ownText();
            String from = elsLocation.get(0).child(5).ownText();
            String to = elsLocation.get(0).child(7).ownText();
            String when = elsLocation.get(0).child(9).ownText();
            String admission = elsLocation.get(0).child(10).ownText();
            String admissSyst = elsLocation.get(0).child(11).child(0).ownText();

            StringBuilder textEventExhibitHighlights3 = new StringBuilder();

            String contentEventExhibitHighlights3 = textEventExhibitHighlights3
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

            BerlinDeNewsParsObj berlinDeNewsParsObj = new BerlinDeNewsParsObj();
            LocalNewsCategory berlinNewsCategory = berlinDeNewsCategoryRepository.findByTitle("EXHIBITIONS");

            berlinDeNewsParsObj.setTitle(translationService.translateText(title));
            berlinDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
            berlinDeNewsParsObj.setImgUrl(imageUrl);
            berlinDeNewsParsObj.setImgCopyright(imgCopyright);
            berlinDeNewsParsObj.setContent(translationService.translateText(contentEventExhibitHighlights3));
            berlinDeNewsParsObj.setBerlinNewsCategory(berlinNewsCategory);

            saveObjectIfNotExists(
                    () -> berlinDeNewsParsObj,
                    berlinDeNewsParsingRepository,
                    BerlinDeNewsParsObj::getTitle
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void BerlinExhibitHighlightsEvent4ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(BERLIN_NEWS_URL , REFERRER);
            Element elsCult = basDoc.getElementsByClass("tilesgrid").first();
            String urlToCult = getURLToBerlinNewsSections(elsCult, 1);
            Document docCulture = getDocument(urlToCult, BERLIN_NEWS_URL);
            Elements elsCultEvents = docCulture.getElementsByClass("flexgrid grid--3");
            String urlToExhibitionsHighlights = BERLIN_NEWS_URL + elsCultEvents.get(1).child(1).child(1).child(0).attr("href");
            Document docExhibitionsHighlights = getDocument(urlToExhibitionsHighlights, urlToCult);
            Element elEventExhibitHighlights4 = docExhibitionsHighlights.getElementsByClass("modul-teaser span4").get(1);
            String shortDescription = elEventExhibitHighlights4.child(3).selectFirst("p.text").ownText();
            String urlToEventExhibitHighlights4 = BERLIN_NEWS_URL  + elEventExhibitHighlights4.child(1).child(0).attr("href");
            Document docEventExhibitHighlights4 = getDocument(urlToEventExhibitHighlights4, urlToExhibitionsHighlights);
            Element elsImg = docEventExhibitHighlights4.getElementsByClass("js-imageblur").first();
            String imageUrl = BERLIN_NEWS_URL  + elsImg.attr("src");
            Element elImgCopyright = docEventExhibitHighlights4.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            String title = docEventExhibitHighlights4.getElementsByClass("article__title heading--article").first().ownText();
            String firstParagraph = docEventExhibitHighlights4.getElementsByClass("article__introtext").first().ownText();
            String secondParagraph = docEventExhibitHighlights4.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0).ownText();
            String thirdSubTitle = docEventExhibitHighlights4.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(0).text();
            String thirdParagraph = docEventExhibitHighlights4.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(1).child(0).ownText();
            String fourthSubTitle = docEventExhibitHighlights4.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(0).text();
            String fourthParagraph = docEventExhibitHighlights4.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(1).child(0).ownText();
            Elements elsLocation = docEventExhibitHighlights4.getElementsByClass("info-container-list list--horizontal list--contact");
            String where = elsLocation.get(0).child(3).child(0).ownText();
            String from = elsLocation.get(0).child(5).ownText();
            String to = elsLocation.get(0).child(7).ownText();
            String when = elsLocation.get(0).child(11).ownText();
            String admission = elsLocation.get(0).child(12).ownText();
            String admissSyst = elsLocation.get(0).child(13).child(1).attr("href");

            StringBuilder textEventExhibitHighlights4 = new StringBuilder();

            String contentEventExhibitHighlights4 = textEventExhibitHighlights4
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
                    .append("\n")
                    .append("Location:\t").append(where)
                    .append("\n")
                    .append("Zeitraum:\t").append(from).append(" - ").append(to)
                    .append("\n")
                    .append("Wann:\t").append(when)
                    .append("\n")
                    .append(admission).append(":\t").append(admissSyst)
                    .toString();

            BerlinDeNewsParsObj berlinDeNewsParsObj = new BerlinDeNewsParsObj();
            LocalNewsCategory berlinNewsCategory = berlinDeNewsCategoryRepository.findByTitle("EXHIBITIONS");

            berlinDeNewsParsObj.setTitle(translationService.translateText(title));
            berlinDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
            berlinDeNewsParsObj.setImgUrl(imageUrl);
            berlinDeNewsParsObj.setImgCopyright(imgCopyright);
            berlinDeNewsParsObj.setContent(translationService.translateText(contentEventExhibitHighlights4));
            berlinDeNewsParsObj.setBerlinNewsCategory(berlinNewsCategory);

            saveObjectIfNotExists(
                    () -> berlinDeNewsParsObj,
                    berlinDeNewsParsingRepository,
                    BerlinDeNewsParsObj::getTitle
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void BerlinCultEvents4ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(BERLIN_NEWS_URL , REFERRER);
            Element elsCult = basDoc.getElementsByClass("tilesgrid").first();
            String urlToCult = getURLToBerlinNewsSections(elsCult, 1);
            Document docCulture = getDocument(urlToCult, BERLIN_NEWS_URL);
            Elements elsCultEvents = docCulture.getElementsByClass("flexgrid grid--3");
            String urlToActualHighlights = BERLIN_NEWS_URL + elsCultEvents.get(0).child(0).child(1).child(0).attr("href");
            Document docActualHighlights = getDocument(urlToActualHighlights, urlToCult);
            Element elEventActualHighlights4 = docActualHighlights.getElementsByClass("modul-teaser span4").get(3);
            String shortDescription = elEventActualHighlights4.child(3).selectFirst("p.text").ownText();
            String urlToEventActualHighlights4 = BERLIN_NEWS_URL  + elEventActualHighlights4.child(1).child(0).attr("href");
            Document docEventActualHighlights4 = getDocument(urlToEventActualHighlights4, urlToActualHighlights);
            Element elsImg = docEventActualHighlights4.getElementsByClass("js-imageblur").first();
            String imageUrl = BERLIN_NEWS_URL  + elsImg.attr("src");
            Element elImgCopyright = docEventActualHighlights4.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            String title = docEventActualHighlights4.getElementsByClass("article__title heading--article").first().ownText();
            String firstParagraph = docEventActualHighlights4.getElementsByClass("article__introtext").first().ownText();
            String secondParagraph = docEventActualHighlights4.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0).ownText();
            Elements elsLocation = docEventActualHighlights4.getElementsByClass("info-container-list list--horizontal list--contact");
            String where = elsLocation.get(0).child(3).ownText();
            String from = elsLocation.get(0).child(5).ownText();
            String to = elsLocation.get(0).child(7).ownText();
            String when = elsLocation.get(0).child(9).ownText();
            String when1 = elsLocation.get(0).child(11).ownText();
            String admission = elsLocation.get(0).child(12).ownText();
            String admissSyst = elsLocation.get(0).child(13).child(0).text();

            StringBuilder textEventActualHighlights4 = new StringBuilder();

            String contentEventActualHighlights4 = textEventActualHighlights4
                    .append(firstParagraph)
                    .append("\n")
                    .append(secondParagraph)
                    .append("\n\n")
                    .append("Location:\t").append(where)
                    .append("\n")
                    .append("Zeitraum:\t").append(from).append(" - ").append(to)
                    .append("\n")
                    .append("Wann:\t").append(when).append("\n").append(when1)
                    .append("\n")
                    .append(admission).append(":\t").append(admissSyst)
                    .toString();

            BerlinDeNewsParsObj berlinDeNewsParsObj = new BerlinDeNewsParsObj();
            LocalNewsCategory berlinNewsCategory = berlinDeNewsCategoryRepository.findByTitle("CULTURAL_EVENTS");

            berlinDeNewsParsObj.setTitle(translationService.translateText(title));
            berlinDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
            berlinDeNewsParsObj.setImgUrl(imageUrl);
            berlinDeNewsParsObj.setImgCopyright(imgCopyright);
            berlinDeNewsParsObj.setContent(translationService.translateText(contentEventActualHighlights4));
            berlinDeNewsParsObj.setBerlinNewsCategory(berlinNewsCategory);

            saveObjectIfNotExists(
                    () -> berlinDeNewsParsObj,
                    berlinDeNewsParsingRepository,
                    BerlinDeNewsParsObj::getTitle
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void BerlinActualNews1ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(BERLIN_NEWS_URL , REFERRER);
            Element elsPolitic = basDoc.getElementsByClass("tilesgrid").first();
            String urlToPolitic = getURLToBerlinNewsSections(elsPolitic, 0);
            Document docPolitic = getDocument(urlToPolitic, BERLIN_NEWS_URL);
            Elements elsActualNews = docPolitic.getElementsByClass("flexgrid grid--3");
            String urlToActualNews1 = BERLIN_NEWS_URL + elsActualNews.get(0).child(0).child(1).child(0).attr("href");
            Element elActualNews1 = docPolitic.getElementsByClass("modul-teaser span4").first();
            String shortDescription = elActualNews1.child(2).child(0).selectFirst("p.text").ownText();
            Document docActualNews1 = getDocument(urlToActualNews1, urlToPolitic);
            Element elImg = docActualNews1.getElementsByClass("image image--force-2x1 article-mainimage").first();
            String imageUrl = BERLIN_NEWS_URL  + elImg.child(0).child(0).attr("src");
            Element elImgCopyright = elImg.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            String title = docActualNews1.getElementsByClass("article__title heading--article").first().ownText();
            String firstParagraph = docActualNews1.getElementsByClass("article__introtext").first().ownText();
            String secondParagraph = docActualNews1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0).ownText();
            String thirdSubTitle = docActualNews1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(0).text();
            String thirdParagraph = docActualNews1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(1).child(0).ownText();
            String fourthSubTitle = docActualNews1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(0).text();
            String fourthParagraph = docActualNews1.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(1).child(0).ownText();

            StringBuilder textActualNews1 = new StringBuilder();

            String contentActualNews1 = textActualNews1
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

            BerlinDeNewsParsObj berlinDeNewsParsObj = new BerlinDeNewsParsObj();
            LocalNewsCategory berlinNewsCategory = berlinDeNewsCategoryRepository.findByTitle("ACTUAL_NEWS");

            berlinDeNewsParsObj.setTitle(translationService.translateText(title));
            berlinDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
            berlinDeNewsParsObj.setImgUrl(imageUrl);
            berlinDeNewsParsObj.setImgCopyright(imgCopyright);
            berlinDeNewsParsObj.setContent(translationService.translateText(contentActualNews1));
            berlinDeNewsParsObj.setBerlinNewsCategory(berlinNewsCategory);

            saveObjectIfNotExists(
                    () -> berlinDeNewsParsObj,
                    berlinDeNewsParsingRepository,
                    BerlinDeNewsParsObj::getTitle
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void BerlinActualNews2ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(BERLIN_NEWS_URL , REFERRER);
            Element elsPolitic = basDoc.getElementsByClass("tilesgrid").first();
            String urlToPolitic = getURLToBerlinNewsSections(elsPolitic, 0);
            Document docPolitic = getDocument(urlToPolitic, BERLIN_NEWS_URL);
            Elements elsActualNews = docPolitic.getElementsByClass("flexgrid grid--3");
            String urlToActualNews2 = BERLIN_NEWS_URL + elsActualNews.get(0).child(1).child(1).child(0).attr("href");
            Element elActualNews2 = docPolitic.getElementsByClass("modul-teaser span4").get(1);
            String shortDescription = elActualNews2.child(2).child(0).selectFirst("p.text").ownText();
            Document docActualNews2 = getDocument(urlToActualNews2, urlToPolitic);
            Element elImg = docActualNews2.getElementsByClass("image image--force-2x1 article-mainimage").first();
            String imageUrl = BERLIN_NEWS_URL  + elImg.child(0).child(0).attr("src");
            Element elImgCopyright = elImg.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            String title = docActualNews2.getElementsByClass("article__title heading--article").first().ownText();
            String firstParagraph = docActualNews2.getElementsByClass("article__introtext").first().ownText();
            String secondParagraph = docActualNews2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0).ownText();
            String thirdSubTitle = docActualNews2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(0).text();
            String thirdParagraph = docActualNews2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(1).child(0).ownText();
            String fourthSubTitle = docActualNews2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(0).text();
            String fourthParagraph = docActualNews2.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(1).child(0).ownText();

            StringBuilder textActualNews2 = new StringBuilder();

            String contentActualNews2 = textActualNews2
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

            BerlinDeNewsParsObj berlinDeNewsParsObj = new BerlinDeNewsParsObj();
            LocalNewsCategory berlinNewsCategory = berlinDeNewsCategoryRepository.findByTitle("ACTUAL_NEWS");

            berlinDeNewsParsObj.setTitle(translationService.translateText(title));
            berlinDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
            berlinDeNewsParsObj.setImgUrl(imageUrl);
            berlinDeNewsParsObj.setImgCopyright(imgCopyright);
            berlinDeNewsParsObj.setContent(translationService.translateText(contentActualNews2));
            berlinDeNewsParsObj.setBerlinNewsCategory(berlinNewsCategory);

            saveObjectIfNotExists(
                    () -> berlinDeNewsParsObj,
                    berlinDeNewsParsingRepository,
                    BerlinDeNewsParsObj::getTitle
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void BerlinActualNews3ParsingTranslateSave() {
        try {
            Document basDoc = getDocument(BERLIN_NEWS_URL , REFERRER);
            Element elsPolitic = basDoc.getElementsByClass("tilesgrid").first();
            String urlToPolitic = getURLToBerlinNewsSections(elsPolitic, 0);
            Document docPolitic = getDocument(urlToPolitic, BERLIN_NEWS_URL);
            Elements elsActualNews = docPolitic.getElementsByClass("flexgrid grid--3");
            String urlToActualNews3 = BERLIN_NEWS_URL + elsActualNews.get(0).child(2).child(1).child(0).attr("href");
            Element elActualNews3 = docPolitic.getElementsByClass("modul-teaser span4").get(2);
            String shortDescription = elActualNews3.child(2).child(0).selectFirst("p.text").ownText();
            Document docActualNews3 = getDocument(urlToActualNews3, urlToPolitic);
            Element elImg = docActualNews3.getElementsByClass("image image--force-2x1 article-mainimage").first();
            String imageUrl = BERLIN_NEWS_URL  + elImg.child(0).child(0).attr("src");
            Element elImgCopyright = elImg.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            String title = docActualNews3.getElementsByClass("article__title heading--article").first().ownText();
            String firstParagraph = docActualNews3.getElementsByClass("article__introtext").first().ownText();
            String secondParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0).ownText();
            String thirdSubTitle = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(0).text();
            String thirdParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(1).child(0).ownText();
            String fourthSubTitle = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(0).text();
            String fourthParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(1).child(0).ownText();
            String fifthSubTitle = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(3).child(0).text();
            String fifthParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(3).child(1).child(0).ownText();

            StringBuilder textActualNews3 = new StringBuilder();

            String contentActualNews3 = textActualNews3
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
                    .append("\n")
                    .append(fifthSubTitle)
                    .append("\n")
                    .append(fifthParagraph)
                    .append("\n\n")
                    .toString();

            BerlinDeNewsParsObj berlinDeNewsParsObj = new BerlinDeNewsParsObj();
            LocalNewsCategory berlinNewsCategory = berlinDeNewsCategoryRepository.findByTitle("ACTUAL_NEWS");

            berlinDeNewsParsObj.setTitle(translationService.translateText(title));
            berlinDeNewsParsObj.setShortDescription(translationService.translateText(shortDescription));
            berlinDeNewsParsObj.setImgUrl(imageUrl);
            berlinDeNewsParsObj.setImgCopyright(imgCopyright);
            berlinDeNewsParsObj.setContent(translationService.translateText(contentActualNews3));
            berlinDeNewsParsObj.setBerlinNewsCategory(berlinNewsCategory);

            saveObjectIfNotExists(
                    () -> berlinDeNewsParsObj,
                    berlinDeNewsParsingRepository,
                    BerlinDeNewsParsObj::getTitle
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


/*
static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";
    static final int TIMEOUT = 5000;
    static final String REFERRER = "https://www.google.com";
    static final String BERLIN_NEWS_URL  = "https://www.berlin.de";


    public static String getURLToBerlinNewsSects(Element element, int index) {
        String urlToNewsSection = BERLIN_NEWS_URL + element.child(index).child(0).child(0).child(1).child(0).attr("href");
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
            Document basDoc = getDoc(BERLIN_NEWS_URL , REFERRER);
            Element elsPolitic = basDoc.getElementsByClass("tilesgrid").first();
            String urlToPolitic = getURLToBerlinNewsSects(elsPolitic, 0);
            Document docPolitic = getDoc(urlToPolitic, BERLIN_NEWS_URL);
            Elements elsActualNews = docPolitic.getElementsByClass("flexgrid grid--3");
            String urlToActualNews3 = BERLIN_NEWS_URL + elsActualNews.get(0).child(2).child(1).child(0).attr("href");
            Element elActualNews3 = docPolitic.getElementsByClass("modul-teaser span4").get(2);
            String shortDescription = elActualNews3.child(2).child(0).selectFirst("p.text").ownText();
            Document docActualNews3 = getDoc(urlToActualNews3, urlToPolitic);
            Element elImg = docActualNews3.getElementsByClass("image image--force-2x1 article-mainimage").first();
            String imageUrl = BERLIN_NEWS_URL  + elImg.child(0).child(0).attr("src");
            Element elImgCopyright = elImg.select(".image__copyright").first();
            String imgCopyright = elImgCopyright.ownText();
            String title = docActualNews3.getElementsByClass("article__title heading--article").first().ownText();
            String firstParagraph = docActualNews3.getElementsByClass("article__introtext").first().ownText();
            String secondParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(0).child(0).child(0).ownText();
            String thirdSubTitle = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(0).text();
            String thirdParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(1).child(1).child(0).ownText();
            String fourthSubTitle = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(0).text();
            String fourthParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(2).child(1).child(0).ownText();
            String fifthSubTitle = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(3).child(0).text();
            String fifthParagraph = docActualNews3.getElementsByClass("modul-text_bild paragraph _block--nogap_ imagealignleft").get(3).child(1).child(0).ownText();
//            Elements elsLocation = docActualNews3.getElementsByClass("info-container-list list--horizontal list--contact");
//            String where = elsLocation.get(0).child(3).ownText();
//            String from = elsLocation.get(0).child(5).ownText();
//            String to = elsLocation.get(0).child(7).ownText();
//            String when = elsLocation.get(0).child(9).ownText();
//            String when1 = elsLocation.get(0).child(11).ownText();
//            String admission = elsLocation.get(0).child(12).ownText();
//            String admissSyst = elsLocation.get(0).child(13).child(0).text();
//
            StringBuilder textActualNews3 = new StringBuilder();

            String contentActualNews3 = textActualNews3
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
                    .append("\n")
                    .append(fifthSubTitle)
                    .append("\n")
                    .append(fifthParagraph)
                    .append("\n\n")
//                    .append("Location:\t").append(where)
//                    .append("\n")
//                    .append("Zeitraum:\t").append(from).append(" - ").append(to)
//                    .append("\n")
//                    .append("Wann:\t").append(when).append("\n").append(when1)
//                    .append("\n")
//                    .append(admission).append(":\t").append(admissSyst)
                    .toString();
//
            System.out.println(contentActualNews3);

//            System.out.println(urlToPolitic);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

*/
