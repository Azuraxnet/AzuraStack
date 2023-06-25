package net.azura.messages.enums;

public enum Locale {
    EN_US("en_US", "English (United States)"),
    EN_GB("en_GB", "English (United Kingdom)"),
    EN_CA("en_CA", "English (Canada)"),
    EN_AU("en_AU", "English (Australia)"),
    EN_NZ("en_NZ", "English (New Zealand)"),
    ES_ES("es_ES", "Spanish (Spain)"),
    ES_MX("es_MX", "Spanish (Mexico)"),
    FR_FR("fr_FR", "French (France)"),
    FR_CA("fr_CA", "French (Canada)"),
    DE_DE("de_DE", "German (Germany)"),
    IT_IT("it_IT", "Italian (Italy)"),
    PT_BR("pt_BR", "Portuguese (Brazil)"),
    RU_RU("ru_RU", "Russian (Russia)"),
    ZH_CN("zh_CN", "Chinese (China)"),
    ZH_TW("zh_TW", "Chinese (Taiwan)"),
    JA_JP("ja_JP", "Japanese (Japan)"),
    KO_KR("ko_KR", "Korean (South Korea)"),
    AR_SA("ar_SA", "Arabic (Saudi Arabia)"),
    NL_NL("nl_NL", "Dutch (Netherlands)"),
    SV_SE("sv_SE", "Swedish (Sweden)"),
    NO_NO("no_NO", "Norwegian (Norway)"),
    DA_DK("da_DK", "Danish (Denmark)"),
    FI_FI("fi_FI", "Finnish (Finland)"),
    PL_PL("pl_PL", "Polish (Poland)"),
    TR_TR("tr_TR", "Turkish (Turkey)"),
    EL_GR("el_GR", "Greek (Greece)"),
    HI_IN("hi_IN", "Hindi (India)");

    private final String id;
    private final String language;

    Locale(String id, String language) {
        this.id = id;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }
}
