package kai.sample.dto;

import kai.sample.controller.dto.BasicListReponse;
import kai.sample.datasource.entity.Coin;

import java.util.List;

public class CoinListResponse extends BasicListReponse<Coin> {
    public CoinListResponse() {
        this(null);
    }

    public CoinListResponse(List<Coin> dataList) {
        super(dataList);
    }
}
