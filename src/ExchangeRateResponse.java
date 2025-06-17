import com.google.gson.annotations.SerializedName;

public class ExchangeRateResponse {

    @SerializedName("base_code")
    private String baseCurrency;

    @SerializedName("target_code")
    private String targetCurrency;

    @SerializedName("conversion_rate")
    private double exchangeRate;

    @SerializedName("time_next_update_utc")
    private String lastUpdated;

    public ExchangeRateResponse() {
    }

    // Getters
    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }


    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
