package de.aittr.team24_FP_backend.services.parsing_services.categories_parsing;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.RestaurantsInfo;
import de.aittr.team24_FP_backend.domain.categories.TranslatorsInfo;
import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.RestaurantRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantsInfoParsingService extends CommonParsingTextService {
    RestaurantRepository restaurantRepository;
    CityRepository cityRepository;

    public RestaurantsInfoParsingService(RestaurantRepository restaurantRepository, CityRepository cityRepository) {
        this.restaurantRepository = restaurantRepository;
        this.cityRepository = cityRepository;
    }

    public void  restaurantsInfoBerlinParsingSaveService() {

        try {
            List<String> localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 26);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinRestaurantsLink = localLinkList.get(1);

            Document docRestaurants = getDocument(urlToBerlinRestaurantsLink, urlToBerlinPage);
            Elements elsRestaurants1 = docRestaurants.getElementsByClass("catalog-item catalog-item-D");
            String urlTo2Page = urlToBerlinPage + docRestaurants.getElementsByClass("pagination").get(0).child(2).child(0).attr("href");
            Document docTo2Page = getDocument(urlTo2Page, urlToBerlinRestaurantsLink);
            Elements elsRestaurants2 = docTo2Page.getElementsByClass("catalog-item catalog-item-D");
            List<Element> restaurantsList1 = new ArrayList<>();
            List<Element> restaurantsList2 = new ArrayList<>();
            List<Element> restaurantsGeneralList = new ArrayList<>();
            restaurantsList1.addAll(elsRestaurants1);
            restaurantsList2.addAll(elsRestaurants2);
            restaurantsGeneralList.addAll(restaurantsList1);
            restaurantsGeneralList.addAll(restaurantsList2);

            City city = cityRepository.findByName("BERLIN");

            for (int i = 0; i < restaurantsGeneralList.size(); i++) {
                RestaurantsInfo restaurantsInfo = new RestaurantsInfo();
                String title = restaurantsGeneralList.get(i).getElementsByClass("title").text();
                String tel = restaurantsGeneralList.get(i).getElementsByClass("phone").text();
                String description = restaurantsGeneralList.get(i).getElementsByClass("description").text();
                String address = restaurantsGeneralList.get(i).getElementsByClass("address").text();

                restaurantsInfo.setTitle(title);
                restaurantsInfo.setDescription(description);
                restaurantsInfo.setAddress(address);
                restaurantsInfo.setTel(tel);
                restaurantsInfo.setCity(city);

                saveObjectIfNotExists(
                        () -> restaurantsInfo,
                        restaurantRepository,
                        RestaurantsInfo::getTitle
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
