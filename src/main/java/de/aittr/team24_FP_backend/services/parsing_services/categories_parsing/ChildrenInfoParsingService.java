package de.aittr.team24_FP_backend.services.parsing_services.categories_parsing;

import de.aittr.team24_FP_backend.domain.categories.ChildrenInfo;
import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.repositories.categories.ChildrenRepository;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChildrenInfoParsingService extends CommonParsingTextService {

    ChildrenRepository childrenRepository;
    CityRepository cityRepository;

    public ChildrenInfoParsingService(ChildrenRepository childrenRepository, CityRepository cityRepository) {
        this.childrenRepository = childrenRepository;
        this.cityRepository = cityRepository;
    }

    public void childrenInfoBerlinParsingSaveService() {

        try {
            List<String> localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 40);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinOtherLink = localLinkList.get(1);

            Document docToOtherLocalCategory = getDocument(urlToBerlinOtherLink, urlToBerlinPage);
            Element elCategories = docToOtherLocalCategory.getElementsByClass("root-cat").get(5);
            urlToBerlinOtherLink = urlToBerlinOtherLink.substring(0, urlToBerlinOtherLink.length() - 1);
            String urlToHomeChildren = urlToBerlinOtherLink + elCategories.child(0).attr("href");
            Document docHomeChildren = getDocument(urlToHomeChildren, urlToBerlinOtherLink);
            String urlToChildOccasions = urlToBerlinPage + docHomeChildren.getElementsByClass("subcategory").first().child(6).attr("href");
            Document docChildOccasions = getDocument(urlToChildOccasions, urlToHomeChildren);
            Elements childOccasions = docChildOccasions.getElementsByClass("catalog-item catalog-item-A");
            String urlToChildCafe = urlToBerlinPage + docHomeChildren.getElementsByClass("subcategory").first().child(1).attr("href");
            Document docChildCafe = getDocument(urlToChildCafe, urlToHomeChildren);
            Elements elsChildCafe = docChildCafe.getElementsByClass("catalog-item catalog-item-D");
            String urlToKindergarten = urlToBerlinPage + docHomeChildren.getElementsByClass("subcategory").first().child(0).attr("href");
            Document docKindergarten = getDocument(urlToKindergarten, urlToHomeChildren);
            Elements elsKindergarten1 = docKindergarten.getElementsByClass("catalog-item catalog-item-B");
            Elements elsKindergarten2 = docKindergarten.getElementsByClass("catalog-item catalog-item-D");
            List<Element> childOccasionsList = new ArrayList<>();
            List<Element> childCafeList = new ArrayList<>();
            List<Element> kindergartenList1 = new ArrayList<>();
            List<Element> kindergartenList2 = new ArrayList<>();
            List<Element> childUnitedInfoList = new ArrayList<>();
            childOccasionsList.addAll(childOccasions);
            childCafeList.addAll(elsChildCafe);
            kindergartenList1.addAll(elsKindergarten1);
            kindergartenList2.addAll(elsKindergarten2);
            childUnitedInfoList.addAll(childOccasionsList);
            childUnitedInfoList.addAll(childCafeList);
            childUnitedInfoList.addAll(kindergartenList1);
            childUnitedInfoList.addAll(kindergartenList2);

            City city = cityRepository.findByName("BERLIN");

            for (int i = 0; i < childUnitedInfoList.size(); i++) {

                ChildrenInfo childrenInfo = new ChildrenInfo();

                String title = childUnitedInfoList.get(i).getElementsByClass("title").text();
                String tel = childUnitedInfoList.get(i).getElementsByClass("phone").text();
                String description = childUnitedInfoList.get(i).getElementsByClass("description").text();
                String address = childUnitedInfoList.get(i).getElementsByClass("address").text();
                String link = null;
                Element wwwElement = childUnitedInfoList.get(i).select("div.www").first();
                Element linkElement = null;
                if (wwwElement != null) {
                    linkElement = wwwElement.select("a").first();
                    link = linkElement.text();
                }

                childrenInfo.setTitle(title);
                childrenInfo.setDescription(description);
                childrenInfo.setAddress(address);
                childrenInfo.setTel(tel);
                childrenInfo.setLink(link);
                childrenInfo.setCity(city);

                saveObjectIfNotExists(
                        () -> childrenInfo,
                        childrenRepository,
                        ChildrenInfo::getTitle
                );

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public static List<String> getDocToLocalPages(String url, String referrer, int index1, int index2) throws
            IOException {
        List<String> localPagesList = new ArrayList<>();
        Document baseDoc = getDoc(url, REFERRER);
        Elements elToBerlinLink = baseDoc.getElementsByAttributeValue("id", "city-links");
        String urlToLocalLink = elToBerlinLink.get(0).child(index1).attr("href");
        Document docLocalLink = getDoc(urlToLocalLink, url);
        Element elToSideCategories = docLocalLink.getElementsByClass("block-accent block-menu col-menu").get(0);
        String urlLinkToCategory = urlToLocalLink + elToSideCategories.child(index2).attr("href");
        localPagesList.add(urlToLocalLink);
        localPagesList.add(urlLinkToCategory);
        return localPagesList;
    }

    public static void main(String[] args) {

        final String URL = "https://germany24.ru";

        try {
            List<String> localLinkList = getDocToLocalPages(URL, REFERRER, 2, 40);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinOtherLink = localLinkList.get(1);

            Document docToOtherLocalCategory = getDoc(urlToBerlinOtherLink, urlToBerlinPage);
            Element elCategories = docToOtherLocalCategory.getElementsByClass("root-cat").get(5);
            urlToBerlinOtherLink = urlToBerlinOtherLink.substring(0, urlToBerlinOtherLink.length() - 1);
            String urlToHomeChildren = urlToBerlinOtherLink + elCategories.child(0).attr("href");
            Document docHomeChildren = getDoc(urlToHomeChildren, urlToBerlinOtherLink);
            String urlToChildOccasions = urlToBerlinPage + docHomeChildren.getElementsByClass("subcategory").first().child(6).attr("href");
            Document docChildOccasions = getDoc(urlToChildOccasions, urlToHomeChildren);
            Elements elsChildOccasions = docChildOccasions.getElementsByClass("catalog-item catalog-item-A");
            String title = null;
            String tel = null;
            String description = null;
            String address = null;
            String urlToHomePage = null;
            for (int i = 0; i < elsChildOccasions.size(); i++) {
                title = elsChildOccasions.get(i).getElementsByClass("title").text();
                tel = elsChildOccasions.get(i).getElementsByClass("phone").text();
                description = elsChildOccasions.get(i).getElementsByClass("description").text();
                address = elsChildOccasions.get(i).getElementsByClass("address").text();
                urlToHomePage = elsChildOccasions.get(i).select("div.www > a").first().text();
                System.out.println(title);
                System.out.println(tel);
                System.out.println(description);
                System.out.println(address);
                System.out.println(urlToHomePage);
            }


//            System.out.println(urlToBerlinPage);
//            System.out.println(urlToBerlinOtherLink);
//            System.out.println(urlToHomeChildren);
//            System.out.println(elsChildOccasions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
