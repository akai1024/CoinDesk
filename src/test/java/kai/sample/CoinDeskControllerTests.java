package kai.sample;

import kai.sample.common.ResponseCode;
import kai.sample.controller.CoinDeskController;
import kai.sample.controller.dto.BasicDataReponse;
import kai.sample.service.dto.CoinDeskAPIReponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CoinDeskApplication.class})
public class CoinDeskControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(CoinDeskControllerTests.class);

    @Autowired
    private CoinDeskController coinDeskController;

    /**
     * 5. 測試呼叫coindeskAPI成功，並顯示其內容。
     */
    @Test
    public void callCoinDesk() throws Exception {
        BasicDataReponse<CoinDeskAPIReponse> response = coinDeskController.call();
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
        logger.info("callCoinDesk test pass, data: {}", response.getData());
    }

    /**
     * 6. 測試呼叫資料轉換的API，並顯示其內容。
     */
    @Test
    public void callCoinDeskAndRefresh() throws Exception {
        BasicDataReponse<CoinDeskAPIReponse> response = coinDeskController.callAndRefresh();
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
        logger.info("callCoinDesk test pass, data: {}", response.getData());
    }

}
