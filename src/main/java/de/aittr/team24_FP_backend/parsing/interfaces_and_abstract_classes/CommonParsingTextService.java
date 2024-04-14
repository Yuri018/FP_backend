package de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class CommonParsingTextService implements TextParsingService {

    final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";
    final int TIMEOUT = 5000;
    protected final String REFERRER = "https://www.google.com";
    protected final String BERLIN_NEWS_URL = "https://www.berlin.de";
    protected final String INFO_URL = "https://germany24.ru";
    final String ATTRIBUTE_KEY = "href";

    @Override
    public Document getDocument(String url, String referrer) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(TIMEOUT)
                .referrer(referrer)
                .get();
        return doc;
    }

    @Override
    public Document getDocumentIgnoreCookies(String url, String referrer) throws IOException {
        Connection connection = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(TIMEOUT)
                .referrer(referrer)
                .ignoreHttpErrors(true);

        connection.cookie("", "");

        Document doc = connection.get();
        return doc;
    }

    @Override
    public List<String> getDocumentToLocalInfoPages(String url, String referrer, int index1, int index2) throws IOException {
        List<String> localPagesList = new ArrayList<>();
        Document baseDoc = getDocument(url, REFERRER);
        Elements elToBerlinLink = baseDoc.getElementsByAttributeValue("id", "city-links");
        String urlToLocalLink = elToBerlinLink.get(0).child(index1).attr("href");
        Document docLocalLink = getDocument(urlToLocalLink, url);
        Element elToSideCategories = docLocalLink.getElementsByClass("block-accent block-menu col-menu").get(0);
        String urlLinkToCategory = urlToLocalLink + elToSideCategories.child(index2).attr("href");
        localPagesList.add(urlToLocalLink);
        localPagesList.add(urlLinkToCategory);
        return localPagesList;
    }

    @Override
    public String getURLToBerlinNewsSections(Element element, int index) {
        String urlToNewsSection = BERLIN_NEWS_URL + element.child(index).child(0).child(0).child(1).child(0).attr("href");
        return urlToNewsSection;
    }

    @Override
    public String getURLToPage(Elements elements, int index) {
        String urlToPage = elements.get(index).child(0).attr(ATTRIBUTE_KEY);
        return urlToPage;
    }

    @Override
    public <T> T saveObjectIfNotExists(Supplier<T> objectSupplier, JpaRepository<T, Integer> repository, Function<T, String> titleExtractor) {
        T object = objectSupplier.get();
        List<T> existingObjects = repository.findAll();
        if (existingObjects != null) {
            for (T existingObject : existingObjects) {
                if (titleExtractor.apply(existingObject).equals(titleExtractor.apply(object))) {
                    return null;
                }
            }
        }
        return repository.save(object);
    }

    @Override
    public String getTextFromNodes(Node node) {
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
}
