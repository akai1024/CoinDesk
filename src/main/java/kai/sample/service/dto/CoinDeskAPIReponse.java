package kai.sample.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kai.sample.common.CustomJsonUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CoinDeskAPIReponse {

    private TimeData time;
    private String disclaimer;
    private String chartName;
    private HashMap<String, BPIData> bpi;

    public static class TimeData {
        private String updated;
        private String updatedISO;
        private String updateduk;

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getUpdatedISO() {
            return updatedISO;
        }

        public void setUpdatedISO(String updatedISO) {
            this.updatedISO = updatedISO;
        }

        public String getUpdateduk() {
            return updateduk;
        }

        public void setUpdateduk(String updateduk) {
            this.updateduk = updateduk;
        }
    }

    public static class BPIData {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        private BigDecimal rate_float;

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

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public BigDecimal getRate_float() {
            return rate_float;
        }

        public void setRate_float(BigDecimal rate_float) {
            this.rate_float = rate_float;
        }
    }

    public TimeData getTime() {
        return time;
    }

    public void setTime(TimeData time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public HashMap<String, BPIData> getBpi() {
        return bpi;
    }

    public void setBpi(HashMap<String, BPIData> bpi) {
        this.bpi = bpi;
    }

    @Override
    @JsonIgnore
    public String toString() {
        return CustomJsonUtil.toJson(this);
    }

    @JsonIgnore
    public boolean isLegalData() {
        return !StringUtils.isEmpty(chartName) && !CollectionUtils.isEmpty(bpi);
    }

    @JsonIgnore
    public Date getUpdateTime() throws Exception {
        if (time == null || StringUtils.isEmpty(time.getUpdatedISO())) {
            return new Date();
        }

        String isoTime = time.getUpdatedISO();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        return format.parse(isoTime);
    }

}
