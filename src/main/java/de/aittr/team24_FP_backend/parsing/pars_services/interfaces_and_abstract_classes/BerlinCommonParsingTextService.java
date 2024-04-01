package de.aittr.team24_FP_backend.parsing.pars_services.interfaces_and_abstract_classes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

//@Service
public abstract class BerlinCommonParsingTextService implements TextParsingService {

    final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36";
    final int TIMEOUT = 5000;
    protected final String REFERRER = "https://www.google.com";
    final String ATTRIBUTE_KEY = "href";
    protected final String URL = "https://www.berlin.de";
    protected static final String TITLE = "title";
    protected static final String DESCRIPTION = "description";

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
