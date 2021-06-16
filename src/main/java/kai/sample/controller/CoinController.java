package kai.sample.controller;

import kai.sample.controller.dto.BasicDataReponse;
import kai.sample.controller.dto.BasicListReponse;
import kai.sample.controller.dto.request.GetCoinListRequest;
import kai.sample.datasource.entity.Coin;
import kai.sample.datasource.entity.CoinId;
import kai.sample.service.CoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coin")
public class CoinController {

    private static final Logger logger = LoggerFactory.getLogger(CoinController.class);

    @Autowired
    private CoinService coinService;

    @PostMapping("/getList")
    public BasicListReponse<Coin> getList(@RequestBody GetCoinListRequest request) {
        List<Coin> coinList = coinService.getList(request);
        return new BasicListReponse<>(coinList);
    }

    @PutMapping("/add")
    public BasicDataReponse<Coin> add(@RequestBody Coin coin) {
        return new BasicDataReponse<>(coinService.add(coin));
    }

    @DeleteMapping("/delete")
    public BasicDataReponse<Boolean> delete(@RequestBody CoinId coinId) {
        return new BasicDataReponse<>(coinService.delete(coinId));
    }

    @PostMapping("/modify")
    public BasicDataReponse<Coin> modify(@RequestBody Coin coin) {
        return new BasicDataReponse<>(coinService.modify(coin));
    }

}
