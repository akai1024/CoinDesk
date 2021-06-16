package kai.sample.dto;

import kai.sample.controller.dto.BasicDataReponse;
import kai.sample.service.dto.CoinDeskAPIReponse;

public class CoinDeskDataResponse extends BasicDataReponse<CoinDeskAPIReponse> {
    public CoinDeskDataResponse() {
        this(null);
    }

    public CoinDeskDataResponse(CoinDeskAPIReponse data) {
        super(data);
    }
}
