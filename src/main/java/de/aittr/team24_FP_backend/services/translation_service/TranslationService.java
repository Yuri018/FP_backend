package de.aittr.team24_FP_backend.services.translation_service;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    @Value("${google.translate.api.key}")
    private String apiKey;

    public String translateText(String textToTranslate) {
        // Инициализируем объект Translate с использованием ключа API
        Translate translate = TranslateOptions.newBuilder().setApiKey(apiKey).build().getService();

        // Выполняем перевод текста
        Translation translation = translate.translate(textToTranslate, Translate.TranslateOption.targetLanguage("ru"));

        // Получаем переведенный текст
        String translatedText = translation.getTranslatedText();

        return translatedText;
    }
}
