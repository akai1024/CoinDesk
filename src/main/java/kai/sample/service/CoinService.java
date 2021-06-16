package kai.sample.service;

import kai.sample.controller.dto.request.GetCoinListRequest;
import kai.sample.datasource.entity.Coin;
import kai.sample.datasource.entity.CoinId;
import kai.sample.service.dto.CoinDeskAPIReponse;

import java.util.List;

public interface CoinService {

    List<Coin> getList(GetCoinListRequest request);

    void updateByCoinDesk(CoinDeskAPIReponse coinDeskAPIReponse);

    Coin add(Coin coin);

    boolean delete(CoinId coinId);

    Coin modify(Coin coin);

}
