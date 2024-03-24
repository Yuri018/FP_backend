package de.aittr.team24_FP_backend.services;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    public String translateText(String textToTranslate) {
        // Инициализируем объект Translate с использованием ключа API
        Translate translate = TranslateOptions.newBuilder().setApiKey("AIzaSyAinSyS7PEcv_6IvCPN5FiSVbqjBephIUI").build().getService();

        // Выполняем перевод текста
        Translation translation = translate.translate(textToTranslate, Translate.TranslateOption.targetLanguage("ru"));

        // Получаем переведенный текст
        String translatedText = translation.getTranslatedText();

        return translatedText;
    }

//    public static void main(String[] args) {
//        TranslationService translationService = new TranslationService();
//        String germanText = "Berlin braucht die Mobilitätswende! Nur dadurch können die Klimaziele erreicht werden. Der Senat will den Radverkehr stärken, die Straßenbahnstrecken ausbauen, S- und U-Bahnstrecken verlängern und mit Rufbussen in den Kiezen individuelle Angebote on Demand schaffen. Mit mehr Park+Ride- und Sharing-Angeboten kommen Berlinerinnen und Berliner schneller durch die Stadt.\n" +
//                "\n" +
//                "Sicher durch die Stadt, heißt nicht nur sicher im ÖPNV zu jeder Tages- und Nachtzeit. Es heißt auch, Chaos im Stadtleben reduzieren. Feste Parkstationen für E-Scooter und E-Bikes sollen mehr Sicherheit für alle schaffen. Der Umbau von Straßenkreuzungen und verkehrsberuhigten Zonen sorgen zusätzlich für mehr Sicherheit.";
//        String translatedText = translationService.translateText(germanText);
//        System.out.println(translatedText);
//    }
}
