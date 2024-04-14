package de.aittr.team24_FP_backend.services.parsing_services.categories_parsing;


import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.TranslatorsInfo;
import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.TranslatorsRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TranslatorsInfoParsingService extends CommonParsingTextService {

    TranslatorsRepository translatorsRepository;
    CityRepository cityRepository;

    public TranslatorsInfoParsingService(TranslatorsRepository translatorsRepository, CityRepository cityRepository) {
        this.translatorsRepository = translatorsRepository;
        this.cityRepository = cityRepository;
    }

    public void  translatorsInfoBerlinParsingSaveService() {

        try {
            List<String> localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 23);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinTranslatorsLink = localLinkList.get(1);

            Document docTranslators = getDocument(urlToBerlinTranslatorsLink, urlToBerlinPage);
            Elements elsTranslators1 = docTranslators.getElementsByClass("catalog-item catalog-item-A");
            Elements elsTranslators2 = docTranslators.getElementsByClass("catalog-item catalog-item-B");
            Elements elsTranslators3 = docTranslators.getElementsByClass("catalog-item catalog-item-D");
            List<Element> translatorsList1 = new ArrayList<>();
            List<Element> translatorsList2 = new ArrayList<>();
            List<Element> translatorsList3 = new ArrayList<>();
            List<Element> translatorsGeneralList = new ArrayList<>();
            translatorsList1.addAll(elsTranslators1);
            translatorsList2.addAll(elsTranslators2);
            translatorsList3.addAll(elsTranslators3);
            translatorsGeneralList.addAll(translatorsList1);
            translatorsGeneralList.addAll(translatorsList2);
            translatorsGeneralList.addAll(translatorsList3);

            City city = cityRepository.findByName("BERLIN");

            for (int i = 0; i < translatorsGeneralList.size(); i++) {
                TranslatorsInfo translatorsInfo = new TranslatorsInfo();
                String title = translatorsGeneralList.get(i).getElementsByClass("title").text();
                String tel = translatorsGeneralList.get(i).getElementsByClass("phone").text();
                String description = translatorsGeneralList.get(i).getElementsByClass("description").text();
                String address = translatorsGeneralList.get(i).getElementsByClass("address").text();

                Element wwwElement = translatorsGeneralList.get(i).select("div.www").first();
                Element linkElement = null;
                String link = null;
                if (wwwElement != null) {
                    linkElement = wwwElement.select("a").first();
                    link = linkElement.text();
                }

                translatorsInfo.setTitle(title);
                translatorsInfo.setDescription(description);
                translatorsInfo.setAddress(address);
                translatorsInfo.setTel(tel);
                translatorsInfo.setLink(link);
                translatorsInfo.setCity(city);

                saveObjectIfNotExists(
                        () -> translatorsInfo,
                        translatorsRepository,
                        TranslatorsInfo::getTitle
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
