package de.aittr.team24_FP_backend.services.parsing_services.categories_parsing;

import de.aittr.team24_FP_backend.domain.categories.City;
import de.aittr.team24_FP_backend.domain.categories.DoctorsCategory;
import de.aittr.team24_FP_backend.domain.categories.DoctorsInfo;
import de.aittr.team24_FP_backend.parsing.interfaces_and_abstract_classes.CommonParsingTextService;
import de.aittr.team24_FP_backend.repositories.categories.CityRepository;
import de.aittr.team24_FP_backend.repositories.categories.DoctorsCategoryRepository;
import de.aittr.team24_FP_backend.repositories.categories.DoctorsRepository;
import jakarta.transaction.Transactional;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DoctorsInfoBerlinParsingService extends CommonParsingTextService {

    DoctorsRepository doctorsRepository;
    CityRepository cityRepository;
    DoctorsCategoryRepository doctorsCategoryRepository;

    public DoctorsInfoBerlinParsingService(
            DoctorsRepository doctorsRepository,
            CityRepository cityRepository,
            DoctorsCategoryRepository doctorsCategoryRepository) {
        this.doctorsRepository = doctorsRepository;
        this.cityRepository = cityRepository;
        this.doctorsCategoryRepository = doctorsCategoryRepository;
    }

    @Transactional
    public void doctorsInfoBerlinTerapeutParsingSaveService() {
        try {
            List<String> localLinkList = null;
            localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 8);

            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinDoctorsLink = localLinkList.get(1);

            Document docToLocalCategory = getDocument(urlToBerlinDoctorsLink, urlToBerlinPage);
            Element elTerapeuts = docToLocalCategory.getElementsByClass("subcategory").first();
            String urlToTeurapeuts = urlToBerlinPage + elTerapeuts.child(3).attr("href");
            Document docTerapeuts = getDocument(urlToTeurapeuts, urlToBerlinDoctorsLink);
            Elements elsTerapeuts = docTerapeuts.getElementsByClass("catalog-item catalog-item-D");

            City berlin = cityRepository.findByName("BERLIN");
            DoctorsCategory doctorsCategory = doctorsCategoryRepository.findByName("PHYSICIAN");

            for (int i = 0; i < elsTerapeuts.size(); i++) {
                DoctorsInfo docInfo = new DoctorsInfo();
                String title = elsTerapeuts.get(i).getElementsByClass("title").text();
                String tel = elsTerapeuts.get(i).getElementsByClass("phone").text();
                String description = elsTerapeuts.get(i).getElementsByClass("description").text();
                String address = elsTerapeuts.get(i).getElementsByClass("address").text();

                docInfo.setTitle(title);
                docInfo.setTel(tel);
                docInfo.setDescription(description);
                docInfo.setAddress(address);
                docInfo.setCity(berlin);
                docInfo.setDoctorsCategory(doctorsCategory);

                saveObjectIfNotExists(
                        () -> docInfo,
                        doctorsRepository,
                        DoctorsInfo::getTitle
                );
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void doctorsInfoBerlinPediatristParsingSaveService() {
        try {
            List<String> localLinkList = null;
            localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 8);

            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinDoctorsLink = localLinkList.get(1);

            Document docToLocalCategory = getDocument(urlToBerlinDoctorsLink, urlToBerlinPage);
            Element elPediatrists = docToLocalCategory.getElementsByClass("subcategory").first();
            String urlToPediatrists = urlToBerlinPage + elPediatrists.child(12).attr("href");
            Document docPediatrists = getDocument(urlToPediatrists, urlToBerlinDoctorsLink);
            Elements elsPediatrists = docPediatrists.getElementsByClass("catalog-item catalog-item-D");

            City berlin = cityRepository.findByName("BERLIN");
            DoctorsCategory doctorsCategory = doctorsCategoryRepository.findByName("PEDIATRIST");

            for (int i = 0; i < elsPediatrists.size(); i++) {
                DoctorsInfo docInfo = new DoctorsInfo();
                String title = elsPediatrists.get(i).getElementsByClass("title").text();
                String tel = elsPediatrists.get(i).getElementsByClass("phone").text();
                String description = elsPediatrists.get(i).getElementsByClass("description").text();
                String address = elsPediatrists.get(i).getElementsByClass("address").text();

                docInfo.setTitle(title);
                docInfo.setTel(tel);
                docInfo.setDescription(description);
                docInfo.setAddress(address);
                docInfo.setCity(berlin);
                docInfo.setDoctorsCategory(doctorsCategory);

                saveObjectIfNotExists(
                        () -> docInfo,
                        doctorsRepository,
                        DoctorsInfo::getTitle
                );
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void doctorsInfoBerlinStomatologistParsingSaveService() {
        try {
            List<String> localLinkList = null;
            localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 8);

            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinDoctorsLink = localLinkList.get(1);

            Document docToLocalCategory = getDocument(urlToBerlinDoctorsLink, urlToBerlinPage);
            Element elStomatologists = docToLocalCategory.getElementsByClass("subcategory").first();
            String urlToStomatologists = urlToBerlinPage + elStomatologists.child(8).attr("href");
            Document docStomatologists = getDocument(urlToStomatologists, urlToBerlinDoctorsLink);
            Elements elsStomatologists = docStomatologists.getElementsByClass("catalog-item catalog-item-D");

            City berlin = cityRepository.findByName("BERLIN");
            DoctorsCategory doctorsCategory = doctorsCategoryRepository.findByName("STOMATOLOGIST");

            for (int i = 0; i < elsStomatologists.size(); i++) {
                DoctorsInfo docInfo = new DoctorsInfo();
                String title = elsStomatologists.get(i).getElementsByClass("title").text();
                String tel = elsStomatologists.get(i).getElementsByClass("phone").text();
                String description = elsStomatologists.get(i).getElementsByClass("description").text();
                String address = elsStomatologists.get(i).getElementsByClass("address").text();

                docInfo.setTitle(title);
                docInfo.setTel(tel);
                docInfo.setDescription(description);
                docInfo.setAddress(address);
                docInfo.setCity(berlin);
                docInfo.setDoctorsCategory(doctorsCategory);

                saveObjectIfNotExists(
                        () -> docInfo,
                        doctorsRepository,
                        DoctorsInfo::getTitle
                );
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void doctorsInfoBerlinOrthopedistParsingSaveService() {
        try {
            List<String> localLinkList = null;
            localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 8);

            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinDoctorsLink = localLinkList.get(1);

            Document docToLocalCategory = getDocument(urlToBerlinDoctorsLink, urlToBerlinPage);
            Element elOrthopedists = docToLocalCategory.getElementsByClass("subcategory").first();
            String urlToOrthopedists = urlToBerlinPage + elOrthopedists.child(18).attr("href");
            Document docOrthopedists = getDocument(urlToOrthopedists, urlToBerlinDoctorsLink);
            Elements elsOrthopedists = docOrthopedists.getElementsByClass("catalog-item catalog-item-D");

            City berlin = cityRepository.findByName("BERLIN");
            DoctorsCategory doctorsCategory = doctorsCategoryRepository.findByName("ORTHOPEDIST");

            for (int i = 0; i < elsOrthopedists.size(); i++) {
                DoctorsInfo docInfo = new DoctorsInfo();
                String title = elsOrthopedists.get(i).getElementsByClass("title").text();
                String tel = elsOrthopedists.get(i).getElementsByClass("phone").text();
                String description = elsOrthopedists.get(i).getElementsByClass("description").text();
                String address = elsOrthopedists.get(i).getElementsByClass("address").text();

                docInfo.setTitle(title);
                docInfo.setTel(tel);
                docInfo.setDescription(description);
                docInfo.setAddress(address);
                docInfo.setCity(berlin);
                docInfo.setDoctorsCategory(doctorsCategory);

                saveObjectIfNotExists(
                        () -> docInfo,
                        doctorsRepository,
                        DoctorsInfo::getTitle
                );
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void doctorsInfoBerlinCardiologistParsingSaveService() {
        try {
            List<String> localLinkList = null;
            localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 8);

            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinDoctorsLink = localLinkList.get(1);

            Document docToLocalCategory = getDocument(urlToBerlinDoctorsLink, urlToBerlinPage);
            Element elCardiologists = docToLocalCategory.getElementsByClass("subcategory").first();
            String urlToCardiologists = urlToBerlinPage + elCardiologists.child(7).attr("href");
            Document docCardiologists = getDocument(urlToCardiologists, urlToBerlinDoctorsLink);
            Elements elsCardiologists = docCardiologists.getElementsByClass("catalog-item catalog-item-D");

            City berlin = cityRepository.findByName("BERLIN");
            DoctorsCategory doctorsCategory = doctorsCategoryRepository.findByName("CARDIOLOGIST");

            for (int i = 0; i < elsCardiologists.size(); i++) {
                DoctorsInfo docInfo = new DoctorsInfo();
                String title = elsCardiologists.get(i).getElementsByClass("title").text();
                String tel = elsCardiologists.get(i).getElementsByClass("phone").text();
                String description = elsCardiologists.get(i).getElementsByClass("description").text();
                String address = elsCardiologists.get(i).getElementsByClass("address").text();

                docInfo.setTitle(title);
                docInfo.setTel(tel);
                docInfo.setDescription(description);
                docInfo.setAddress(address);
                docInfo.setCity(berlin);
                docInfo.setDoctorsCategory(doctorsCategory);

                saveObjectIfNotExists(
                        () -> docInfo,
                        doctorsRepository,
                        DoctorsInfo::getTitle
                );
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    @Transactional
    public void doctorsInfoBerlinDermatologistParsingSaveService() {
        try {
            List<String> localLinkList = null;
            localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 8);

            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinDoctorsLink = localLinkList.get(1);

            Document docToLocalCategory = getDocument(urlToBerlinDoctorsLink, urlToBerlinPage);
            Element elDermatologists = docToLocalCategory.getElementsByClass("subcategory").first();
            String urlToDermatologists = urlToBerlinPage + elDermatologists.child(11).attr("href");
            Document docDermatologists = getDocument(urlToDermatologists, urlToBerlinDoctorsLink);
            Elements elsDermatologists = docDermatologists.getElementsByClass("catalog-item catalog-item-D");

            City berlin = cityRepository.findByName("BERLIN");
            DoctorsCategory doctorsCategory = doctorsCategoryRepository.findByName("DERMATOLOGIST");

            for (int i = 0; i < elsDermatologists.size(); i++) {
                DoctorsInfo docInfo = new DoctorsInfo();
                String title = elsDermatologists.get(i).getElementsByClass("title").text();
                String tel = elsDermatologists.get(i).getElementsByClass("phone").text();
                String description = elsDermatologists.get(i).getElementsByClass("description").text();
                String address = elsDermatologists.get(i).getElementsByClass("address").text();

                docInfo.setTitle(title);
                docInfo.setTel(tel);
                docInfo.setDescription(description);
                docInfo.setAddress(address);
                docInfo.setCity(berlin);
                docInfo.setDoctorsCategory(doctorsCategory);

                saveObjectIfNotExists(
                        () -> docInfo,
                        doctorsRepository,
                        DoctorsInfo::getTitle
                );
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void doctorsInfoBerlinOculistParsingSaveService() {
        try {
            List<String> localLinkList = null;
            localLinkList = getDocumentToLocalInfoPages(INFO_URL, REFERRER, 2, 8);

            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinDoctorsLink = localLinkList.get(1);

            Document docToLocalCategory = getDocument(urlToBerlinDoctorsLink, urlToBerlinPage);
            Element elOculists = docToLocalCategory.getElementsByClass("subcategory").first();
            String urlToOculists = urlToBerlinPage + elOculists.child(2).attr("href");
            Document docOculists = getDocument(urlToOculists, urlToBerlinDoctorsLink);
            Elements elsOculists = docOculists.getElementsByClass("catalog-item catalog-item-D");

            City berlin = cityRepository.findByName("BERLIN");
            DoctorsCategory doctorsCategory = doctorsCategoryRepository.findByName("OCULIST");

            for (int i = 0; i < elsOculists.size(); i++) {
                DoctorsInfo docInfo = new DoctorsInfo();
                String title = elsOculists.get(i).getElementsByClass("title").text();
                String tel = elsOculists.get(i).getElementsByClass("phone").text();
                String description = elsOculists.get(i).getElementsByClass("description").text();
                String address = elsOculists.get(i).getElementsByClass("address").text();

                docInfo.setTitle(title);
                docInfo.setTel(tel);
                docInfo.setDescription(description);
                docInfo.setAddress(address);
                docInfo.setCity(berlin);
                docInfo.setDoctorsCategory(doctorsCategory);

                saveObjectIfNotExists(
                        () -> docInfo,
                        doctorsRepository,
                        DoctorsInfo::getTitle
                );
            }

        } catch (
                IOException e) {
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
            List<String> localLinkList = getDocToLocalPages(URL, REFERRER, 2, 8);
            String urlToBerlinPage = localLinkList.get(0);
            String urlToBerlinDoctorsLink = localLinkList.get(1);

            Document docToLocalCategory = getDoc(urlToBerlinDoctorsLink, urlToBerlinPage);
            Element elTerapeuts = docToLocalCategory.getElementsByClass("subcategory").first();
            String urlToTeurapeuts = urlToBerlinPage + elTerapeuts.child(3).attr("href");
            Document docTerapeuts = getDoc(urlToTeurapeuts, urlToBerlinDoctorsLink);
            Elements elsTerapeuts = docTerapeuts.getElementsByClass("catalog-item catalog-item-D");
            String title = null;
            String tel = null;
            String description = null;
            String address = null;
            for (int i = 0; i < elsTerapeuts.size(); i++) {
                title = elsTerapeuts.get(i).getElementsByClass("title").text();
                tel = elsTerapeuts.get(i).getElementsByClass("phone").text();
                description = elsTerapeuts.get(i).getElementsByClass("description").text();
                address = elsTerapeuts.get(i).getElementsByClass("address").text();
                System.out.println(title);
                System.out.println(tel);
                System.out.println(description);
                System.out.println(address);
            }


//            System.out.println(urlToBerlinPage);
//            System.out.println(urlToBerlinDoctorsLink);
//            System.out.println(urlToTeurapeuts);
//            System.out.println( urlToTeurapeuts);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
