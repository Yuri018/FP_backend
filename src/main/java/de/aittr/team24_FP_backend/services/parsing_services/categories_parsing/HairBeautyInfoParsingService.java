package de.aittr.team24_FP_backend.services.parsing_services.categories_parsing;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.HairBeautyInfo;
import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.HairBeautyRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HairBeautyInfoParsingService extends CommonParsingTextService {

    HairBeautyRepository hairBeautyRepository;
    CityRepository cityRepository;

    public HairBeautyInfoParsingService(HairBeautyRepository hairBeautyRepository, CityRepository cityRepository) {
        this.hairBeautyRepository = hairBeautyRepository;
        this.cityRepository = cityRepository;
    }

    public void hairBeautyInfoBerlinParsingSaveService() {

        try {
            List<String> localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 15);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinHairBeautyLink = localLinkList.get(1);

            Document docHairBeauty = getDocument(urlToBerlinHairBeautyLink, urlToBerlinPage);
            Elements elsHairBeauty1 = docHairBeauty.getElementsByClass("catalog-item catalog-item-A");
            Elements elsHairBeauty2 = docHairBeauty.getElementsByClass("catalog-item catalog-item-B");
            Elements elsHairBeauty3 = docHairBeauty.getElementsByClass("catalog-item catalog-item-D");
            List<Element> hairBeautyList1 = new ArrayList<>();
            List<Element> hairBeautyList2 = new ArrayList<>();
            List<Element> hairBeautyList3 = new ArrayList<>();
            List<Element> hairBeautyGeneralList = new ArrayList<>();
            hairBeautyList1.addAll(elsHairBeauty1);
            hairBeautyList2.addAll(elsHairBeauty2);
            hairBeautyList3.addAll(elsHairBeauty3);
            hairBeautyGeneralList.addAll(hairBeautyList1);
            hairBeautyGeneralList.addAll(hairBeautyList2);
            hairBeautyGeneralList.addAll(hairBeautyList3);

            City city = cityRepository.findByName("BERLIN");

            for (int i = 0; i < hairBeautyGeneralList.size(); i++) {
                HairBeautyInfo hairBeautyInfo = new HairBeautyInfo();
                String title = hairBeautyGeneralList.get(i).getElementsByClass("title").text();
                String tel = hairBeautyGeneralList.get(i).getElementsByClass("phone").text();
                String description = hairBeautyGeneralList.get(i).getElementsByClass("description").text();
                String address = hairBeautyGeneralList.get(i).getElementsByClass("address").text();

                Element wwwElement = hairBeautyGeneralList.get(i).select("div.www").first();
                Element linkElement = null;
                String link = null;
                if (wwwElement != null) {
                    linkElement = wwwElement.select("a").first();
                    link = linkElement.text();
                }

                hairBeautyInfo.setTitle(title);
                hairBeautyInfo.setDescription(description);
                hairBeautyInfo.setAddress(address);
                hairBeautyInfo.setTel(tel);
                hairBeautyInfo.setLink(link);
                hairBeautyInfo.setCity(city);

                saveObjectIfNotExists(
                        () -> hairBeautyInfo,
                        hairBeautyRepository,
                        HairBeautyInfo::getTitle
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
    static final String INFO_URL = "https://germany24.ru";


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
        Document baseDoc = getDoc(INFO_URL, REFERRER);
        Elements elToBerlinLink = baseDoc.getElementsByAttributeValue("id", "city-links");
        String urlToLocalLink = elToBerlinLink.get(0).child(index1).attr("href");
        Document docLocalLink = getDoc(urlToLocalLink, INFO_URL);
        Element elToSideCategories = docLocalLink.getElementsByClass("block-accent block-menu col-menu").get(0);
        String urlLinkToCategory = urlToLocalLink + elToSideCategories.child(index2).attr("href");
        localPagesList.add(urlToLocalLink);
        localPagesList.add(urlLinkToCategory);
        return localPagesList;
    }

    public static void main(String[] args) {

        try {
            List<String> localLinkList = getDocToLocalPages(INFO_URL, REFERRER, 2, 15);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinHairBeautyLink = localLinkList.get(1);

            Document docHairBeauty = getDoc(urlToBerlinHairBeautyLink, urlToBerlinPage);
            Elements elsHairBeauty1 = docHairBeauty.getElementsByClass("catalog-item catalog-item-A");
            Elements elsHairBeauty2 = docHairBeauty.getElementsByClass("catalog-item catalog-item-B");
            Elements elsHairBeauty3 = docHairBeauty.getElementsByClass("catalog-item catalog-item-D");
            List<Element> hairBeautyList1 = new ArrayList<>();
            List<Element> hairBeautyList2 = new ArrayList<>();
            List<Element> hairBeautyList3 = new ArrayList<>();
            List<Element> hairBeautyGeneralList = new ArrayList<>();
            hairBeautyList1.addAll(elsHairBeauty1);
            hairBeautyList2.addAll(elsHairBeauty2);
            hairBeautyList3.addAll(elsHairBeauty3);
            hairBeautyGeneralList.addAll(hairBeautyList1);
            hairBeautyGeneralList.addAll(hairBeautyList2);
            hairBeautyGeneralList.addAll(hairBeautyList3);

            for (int i = 0; i < hairBeautyGeneralList.size(); i++) {
                String title = hairBeautyGeneralList.get(i).getElementsByClass("title").text();
                String tel = hairBeautyGeneralList.get(i).getElementsByClass("phone").text();
                String description = hairBeautyGeneralList.get(i).getElementsByClass("description").text();
                String address = hairBeautyGeneralList.get(i).getElementsByClass("address").text();

                Element wwwElement = hairBeautyGeneralList.get(i).select("div.www").first();
                Element linkElement = null;
                String link = null;
                if (wwwElement != null) {
                    linkElement = wwwElement.select("a").first();
                    link = linkElement.text();
                }

                System.out.println(title);
                System.out.println(tel);
                System.out.println(description);
                System.out.println(address);
                System.out.println((link == null) ? "\n" : link);
            }


//            System.out.println(urlToBerlinPage);
//            System.out.println(urlToBerlinHairBeautyLink);
//            System.out.println(urlToHomeChildren);
//            System.out.println(elsChildOccasions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}*/
