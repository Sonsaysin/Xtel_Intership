package i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Message {

    private static ResourceBundle bundle;

    public static void setLocale(Locale locale) {
        bundle = ResourceBundle.getBundle(
                "resource.messages",
                locale
        );
    }

    public static String get(String key) {
        return bundle.getString(key);
    }
}