package de.aittr.team24_FP_backend.services.parsing_services.categories_parsing;

import de.aittr.team24_FP_backend.domain.categories.ChildrenInfo;
import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.LegalServicesInfo;
import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.LegalServicesRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LegalServicesInfoParsingService extends CommonParsingTextService {

    LegalServicesRepository legalServicesRepository;
    CityRepository cityRepository;

    public LegalServicesInfoParsingService(LegalServicesRepository legalServicesRepository, CityRepository cityRepository) {
        this.legalServicesRepository = legalServicesRepository;
        this.cityRepository = cityRepository;
    }

    public void legalServicesInfoBerlinParsingSaveService() {

        try {
            List<String> localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 3);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToLegalServicesInfoBerlin = localLinkList.get(1);

            Document docToLegalServicesInfoBerlin = getDocument(urlToLegalServicesInfoBerlin, urlToBerlinPage);
            Elements elsLegalServicesInfoBerlin1 = docToLegalServicesInfoBerlin.getElementsByClass("catalog-item catalog-item-A");
            Elements elsLegalServicesInfoBerlin2 = docToLegalServicesInfoBerlin.getElementsByClass("catalog-item catalog-item-B");
            Elements elsLegalServicesInfoBerlin3 = docToLegalServicesInfoBerlin.getElementsByClass("catalog-item catalog-item-D");
            String urlTo2Page = urlToBerlinPage + docToLegalServicesInfoBerlin.getElementsByClass("pagination").get(0).child(2).child(0).attr("href");
            Document docTo2Page = getDocument(urlTo2Page, urlToLegalServicesInfoBerlin);
            Elements elsLegalServicesInfoBerlin4 = docTo2Page.getElementsByClass("catalog-item catalog-item-D");
            List<Element> legalServicesInfoBerlinList1 = new ArrayList<>();
            List<Element> legalServicesInfoBerlinList2 = new ArrayList<>();
            List<Element> legalServicesInfoBerlinList3 = new ArrayList<>();
            List<Element> legalServicesInfoBerlinList4 = new ArrayList<>();
            List<Element> legalServicesInfoBerlinGeneralList = new ArrayList<>();
            legalServicesInfoBerlinList1.addAll(elsLegalServicesInfoBerlin1);
            legalServicesInfoBerlinList2.addAll(elsLegalServicesInfoBerlin2);
            legalServicesInfoBerlinList3.addAll(elsLegalServicesInfoBerlin3);
            legalServicesInfoBerlinList4.addAll(elsLegalServicesInfoBerlin4);
            legalServicesInfoBerlinGeneralList.addAll(legalServicesInfoBerlinList1);
            legalServicesInfoBerlinGeneralList.addAll(legalServicesInfoBerlinList2);
            legalServicesInfoBerlinGeneralList.addAll(legalServicesInfoBerlinList3);
            legalServicesInfoBerlinGeneralList.addAll(legalServicesInfoBerlinList4);

            City city = cityRepository.findByName("BERLIN");

            for (int i = 0; i < legalServicesInfoBerlinGeneralList.size(); i++) {

                LegalServicesInfo legalServicesInfo = new LegalServicesInfo();

                String title = legalServicesInfoBerlinGeneralList.get(i).getElementsByClass("title").text();
                String tel = legalServicesInfoBerlinGeneralList.get(i).getElementsByClass("phone").text();
                String description = legalServicesInfoBerlinGeneralList.get(i).getElementsByClass("description").text();
                String address = legalServicesInfoBerlinGeneralList.get(i).getElementsByClass("address").text();
                String link = null;
                Element wwwElement = legalServicesInfoBerlinGeneralList.get(i).select("div.www").first();
                Element linkElement = null;
                if (wwwElement != null) {
                    linkElement = wwwElement.select("a").first();
                    link = linkElement.text();
                }

                legalServicesInfo.setTitle(title);
                legalServicesInfo.setDescription(description);
                legalServicesInfo.setAddress(address);
                legalServicesInfo.setTel(tel);
                legalServicesInfo.setLink(link);
                legalServicesInfo.setCity(city);

                saveObjectIfNotExists(
                        () -> legalServicesInfo,
                        legalServicesRepository,
                        LegalServicesInfo::getTitle
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
