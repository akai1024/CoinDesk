package kai.sample.service.impl;

import kai.sample.common.JavaScriptMessageConverter;
import kai.sample.service.CoinDeskService;
import kai.sample.service.dto.CoinDeskAPIReponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinDeskServiceImpl implements CoinDeskService {

    private static final String COIN_DESK_URI = "https://api.coindesk.com/v1/bpi/currentprice.json";

    private RestTemplate getTemplate() {
        // 可以客制一些初始化流程
        return new RestTemplate();
    }

    @Override
    public CoinDeskAPIReponse getAPIReponse() {
        RestTemplate template = getTemplate();
        template.getMessageConverters().add(new JavaScriptMessageConverter());
        ResponseEntity<CoinDeskAPIReponse> rsEntity = template.getForEntity(COIN_DESK_URI, CoinDeskAPIReponse.class);
        if (rsEntity.getStatusCode().isError()) {
            return null;
        }
        return rsEntity.getBody();
    }

}
