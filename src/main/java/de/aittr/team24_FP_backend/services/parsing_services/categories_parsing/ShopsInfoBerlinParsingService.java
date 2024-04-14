package de.aittr.team24_FP_backend.services.parsing_services.categories_parsing;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.ShopsInfo;
import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.ShopRepository;
import jakarta.transaction.Transactional;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopsInfoBerlinParsingService extends CommonParsingTextService {

    ShopRepository shopRepository;
    CityRepository cityRepository;

    public ShopsInfoBerlinParsingService(ShopRepository shopRepository, CityRepository cityRepository) {
        this.shopRepository = shopRepository;
        this.cityRepository = cityRepository;
    }

    @Transactional
    public void shopsInfoBerlinParsingSaveService() {
        try {
            List<String> localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 16);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinShopsLink = localLinkList.get(1);

            Document docToLocalCategory = getDocument(urlToBerlinShopsLink, urlToBerlinPage);
            Elements elsShops1 = docToLocalCategory.getElementsByClass("catalog-item catalog-item-D");
            String urlTo2Page = urlToBerlinPage + docToLocalCategory.getElementsByClass("pagination").get(0).child(2).child(0).attr("href");
            Document docTo2Page = getDocument(urlTo2Page, urlToBerlinShopsLink);
            Elements elsShops2 = docTo2Page.getElementsByClass("catalog-item catalog-item-D");
            List<Element> elShops1List = new ArrayList<>();
            elShops1List.addAll(elsShops1);
            List<Element> elShops2List = new ArrayList<>();
            elShops2List.addAll(elsShops2);
            List<Element> elsShops = new ArrayList<>();
            elsShops.addAll(elShops1List);
            elsShops.addAll(elShops2List);

            City city = cityRepository.findByName("BERLIN");

            for (int i = 0; i < elsShops.size(); i++) {
                ShopsInfo shopsInfo = new ShopsInfo();
                String title = elsShops.get(i).getElementsByClass("title").text();
                String tel = elsShops.get(i).getElementsByClass("phone").text();
                String description = elsShops.get(i).getElementsByClass("description").text();
                String address = elsShops.get(i).getElementsByClass("address").text();

                shopsInfo.setTitle(title);
                shopsInfo.setDescription(description);
                shopsInfo.setAddress(address);
                shopsInfo.setTel(tel);
                shopsInfo.setCity(city);

                saveObjectIfNotExists(
                        () -> shopsInfo,
                        shopRepository,
                        ShopsInfo::getTitle
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public static List<String> getDocToLocalPages(String url, String referrer, int index1, int index2) throws IOException {
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
            List<String> localLinkList = getDocToLocalPages(URL, REFERRER, 2, 16);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinShopsLink = localLinkList.get(1);

            Document docToLocalCategory = getDoc(urlToBerlinShopsLink, urlToBerlinPage);
            Elements elsShops1 = docToLocalCategory.getElementsByClass("catalog-item catalog-item-D");
            String urlTo2Page = urlToBerlinPage + docToLocalCategory.getElementsByClass("pagination").get(0).child(2).child(0).attr("href");
            Document docTo2Page = getDoc(urlTo2Page, urlToBerlinShopsLink);
            Elements elsShops2 = docTo2Page.getElementsByClass("catalog-item catalog-item-D");
            List<Element> elShops1List = new ArrayList<>();
            elShops1List.addAll(elsShops1);
            List<Element> elShops2List = new ArrayList<>();
            elShops2List.addAll(elsShops2);
            List<Element> elsShops = new ArrayList<>();
            elsShops.addAll(elShops1List);
            elsShops.addAll(elShops2List);
            String title = null;
            String tel = null;
            String description = null;
            String address = null;
            for (int i = 0; i < elsShops.size(); i++) {
                title = elsShops.get(i).getElementsByClass("title").text();
                tel = elsShops.get(i).getElementsByClass("phone").text();
                description = elsShops.get(i).getElementsByClass("description").text();
                address = elsShops.get(i).getElementsByClass("address").text();
                System.out.println(title);
                System.out.println(tel);
                System.out.println(description);
                System.out.println(address);
            }


//            System.out.println(urlToBerlinPage);
//            System.out.println(urlToBerlinShopsLink);
//            System.out.println(urlTo2Page);
//            System.out.println( urlToTeurapeuts);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
