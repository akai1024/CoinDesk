package kai.sample.controller;

import kai.sample.controller.dto.BasicDataReponse;
import kai.sample.controller.dto.BasicListReponse;
import kai.sample.controller.dto.request.GetCoinListRequest;
import kai.sample.datasource.entity.Coin;
import kai.sample.service.CoinDeskService;
import kai.sample.service.CoinService;
import kai.sample.service.dto.CoinDeskAPIReponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coinDesk")
public class CoinDeskController {

    private static final Logger logger = LoggerFactory.getLogger(CoinDeskController.class);

    @Autowired
    private CoinDeskService coinDeskService;

    @Autowired
    private CoinService coinService;

    @GetMapping("/call")
    public BasicDataReponse<CoinDeskAPIReponse> call() {
        CoinDeskAPIReponse response = coinDeskService.getAPIReponse();
        return new BasicDataReponse<>(response);
    }

    @GetMapping("/callAndRefresh")
    public BasicDataReponse<CoinDeskAPIReponse> callAndRefresh() {
        CoinDeskAPIReponse response = coinDeskService.getAPIReponse();
        coinService.updateByCoinDesk(response);
        return new BasicDataReponse<>(response);
    }

}
