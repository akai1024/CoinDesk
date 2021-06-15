package kai.sample.controller.dto;

import java.util.List;

public class BasicListReponse<T> extends BasicReponse {

    private List<T> dataList;

    public BasicListReponse(List<T> dataList) {
        super();
        this.dataList = dataList;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
