package kai.sample.datasource.entity;

import java.io.Serializable;
import java.util.Objects;

public class CoinId implements Serializable {

    private String chartName;
    private String code;

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinId coinId = (CoinId) o;
        return chartName.equals(coinId.chartName) && code.equals(coinId.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chartName, code);
    }

    public static CoinId extractId(Coin coin) {
        if (coin == null) {
            return null;
        }
        CoinId id = new CoinId();
        id.setChartName(coin.getChartName());
        id.setCode(coin.getCode());
        return id;
    }

}
