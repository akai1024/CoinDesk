package kai.sample.datasource.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = Coin.TABLE_NAME)
@IdClass(CoinId.class)
public class Coin implements Serializable {

    public static final String TABLE_NAME = "coin";

    @Id
    @Column
    private String chartName;

    @Id
    @Column
    private String code;

    @Column
    private String symbol;

    @Column
    private BigDecimal rate;

    @Column
    private String description;

    @Column
    private Date updateTime;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

