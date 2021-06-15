package kai.sample.controller.dto;

public class BasicDataReponse<T> extends BasicReponse {

    private T data;

    public BasicDataReponse(T data) {
        super();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
