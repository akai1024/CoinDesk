package kai.sample.controller.dto.request;

public class GetCoinListRequest {

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

}
