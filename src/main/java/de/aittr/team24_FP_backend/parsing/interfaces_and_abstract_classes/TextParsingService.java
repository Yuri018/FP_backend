package de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface TextParsingService {

    Document getDocument(String url, String referrer) throws IOException;

    Document getDocumentIgnoreCookies(String url, String referrer) throws IOException;

    List<String> getDocumentToLocalInfoPages(String url, String referrer, int index1, int index2) throws IOException;

    String getURLToBerlinNewsSections(Element element, int index);

    String getURLToPage(Elements elements, int index);

    <T> T saveObjectIfNotExists(Supplier<T> objectSupplier, JpaRepository<T, Integer> repository, Function<T, String> titleExtractor);

    String getTextFromNodes(Node node);
}
