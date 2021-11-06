package chiw.spc.types

enum class CountryPortable(val countryName: String) {
    None("None"),
    JP("Japan"),
    SG("Singapore"),
    US("United States");

    companion object {
        private val map = values().associateBy { CountryPortable::countryName }
        fun fromCountryName(name: String): CountryPortable {
            return map[name] ?: None
        }
    }
}
