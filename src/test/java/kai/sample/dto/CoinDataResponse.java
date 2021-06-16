package kai.sample.dto;

import kai.sample.controller.dto.BasicDataReponse;
import kai.sample.datasource.entity.Coin;

public class CoinDataResponse extends BasicDataReponse<Coin> {

    public CoinDataResponse() {
        this(null);
    }

    public CoinDataResponse(Coin data) {
        super(data);
    }
}
