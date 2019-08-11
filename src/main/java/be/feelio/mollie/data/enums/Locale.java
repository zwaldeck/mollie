package be.feelio.mollie.data.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Locale {

    en_US,
    nl_NL,
    nl_BE,
    fr_FR,
    fr_BE,
    de_DE,
    de_AT,
    de_CH,
    es_ES,
    ca_ES,
    pt_PT,
    it_IT,
    nb_NO,
    sv_SE,
    fi_FI,
    da_DK,
    is_IS,
    hu_HU,
    pl_PL,
    lv_LV,
    lt_LT;

    @JsonValue
    public String getJsonValue() {
        return this.name();
    }
}
