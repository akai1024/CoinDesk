package kai.sample.dto;

import kai.sample.controller.dto.BasicDataReponse;

public class BooleanDataResponse extends BasicDataReponse<Boolean> {
    public BooleanDataResponse() {
        this(false);
    }

    public BooleanDataResponse(Boolean data) {
        super(data);
    }
}
