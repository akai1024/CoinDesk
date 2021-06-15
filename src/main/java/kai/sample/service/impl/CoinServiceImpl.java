package kai.sample.service.impl;

import kai.sample.controller.dto.request.GetCoinListRequest;
import kai.sample.datasource.entity.Coin;
import kai.sample.datasource.repo.CoinRepository;
import kai.sample.service.CoinService;
import kai.sample.service.dto.CoinDeskAPIReponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoinServiceImpl implements CoinService {

    private static final Logger logger = LoggerFactory.getLogger(CoinServiceImpl.class);

    @Autowired
    private CoinRepository coinRepository;

    @Override
    public List<Coin> getList(GetCoinListRequest request) {
        String chartName = request.getChartName();
        String code = request.getCode();

        List<Coin> result;
        if (StringUtils.isEmpty(chartName) && StringUtils.isEmpty(code)) {
            result = coinRepository.findAll();
        } else if (StringUtils.isEmpty(code)) {
            result = coinRepository.findByChartNameContains(chartName);
        } else if (StringUtils.isEmpty(chartName)) {
            result = coinRepository.findByCodeContains(code);
        } else {
            result = coinRepository.findByChartNameContainsAndCodeContains(chartName, code);
        }
        return result;
    }

    @Transactional
    @Override
    public void updateByCoinDesk(CoinDeskAPIReponse coinDeskAPIReponse) {
        if (coinDeskAPIReponse == null) {
            return;
        }

        if (!coinDeskAPIReponse.isLegalData()) {
            logger.error("錯誤的資訊, {}", coinDeskAPIReponse);
            return;
        }

        String chartName = coinDeskAPIReponse.getChartName();
        HashMap<String, CoinDeskAPIReponse.BPIData> bpi = coinDeskAPIReponse.getBpi();
        Date updateTime;
        try {
            updateTime = coinDeskAPIReponse.getUpdateTime();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            updateTime = new Date();
        }

        for (Map.Entry<String, CoinDeskAPIReponse.BPIData> entry : bpi.entrySet()) {
            String code = entry.getKey();
            CoinDeskAPIReponse.BPIData data = entry.getValue();

            Coin coin = new Coin();
            coin.setChartName(chartName);
            coin.setCode(code);
            coin.setSymbol(data.getSymbol());
            coin.setRate(data.getRate_float());
            coin.setDescription(data.getDescription());
            coin.setUpdateTime(updateTime);
            coinRepository.save(coin);
        }
    }

}
