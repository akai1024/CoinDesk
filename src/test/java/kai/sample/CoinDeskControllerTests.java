package kai.sample;

import kai.sample.common.CustomJsonUtil;
import kai.sample.common.ResponseCode;
import kai.sample.controller.CoinDeskController;
import kai.sample.dto.CoinDeskDataResponse;
import kai.sample.service.CoinDeskService;
import kai.sample.service.CoinService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoinDeskController.class)
public class CoinDeskControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(CoinDeskControllerTests.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CoinService coinService;

    @MockBean
    private CoinDeskService coinDeskService;

    /**
     * 5. 測試呼叫coindeskAPI成功，並顯示其內容。
     */
    @Test
    public void callCoinDesk() throws Exception {
        MockHttpServletResponse mockResponse = mvc.perform(get("/coinDesk/call")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        CoinDeskDataResponse response = CustomJsonUtil.parseJson(mockResponse.getContentAsString(), CoinDeskDataResponse.class);
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
        logger.info("callCoinDesk test pass, data: {}", response.getData());
    }

    /**
     * 6. 測試呼叫資料轉換的API，並顯示其內容。
     */
    @Test
    public void callCoinDeskAndRefresh() throws Exception {
        MockHttpServletResponse mockResponse = mvc.perform(get("/coinDesk/callAndRefresh")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        CoinDeskDataResponse response = CustomJsonUtil.parseJson(mockResponse.getContentAsString(), CoinDeskDataResponse.class);
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
        logger.info("callCoinDeskAndRefresh test pass, data: {}", response.getData());
    }

}
