package kai.sample;

import kai.sample.common.CustomJsonUtil;
import kai.sample.common.ResponseCode;
import kai.sample.controller.CoinController;
import kai.sample.controller.dto.request.GetCoinListRequest;
import kai.sample.datasource.entity.Coin;
import kai.sample.datasource.entity.CoinId;
import kai.sample.dto.BooleanDataResponse;
import kai.sample.dto.CoinDataResponse;
import kai.sample.dto.CoinListResponse;
import kai.sample.service.CoinService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoinController.class)
@AutoConfigureMockMvc
public class CoinControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(CoinControllerTests.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CoinService coinService;

    /**
     * 1. 測試呼叫查詢幣別對應表資料API，並顯示其內容。
     */
    @Test
    public void getListTest() throws Exception {
        MockHttpServletResponse mockResponse = mvc.perform(post("/coin/getList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomJsonUtil.toJson(new GetCoinListRequest()))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        CoinListResponse response = CustomJsonUtil.parseJson(mockResponse.getContentAsString(), CoinListResponse.class);
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
        logger.info("getList test pass, list: {}", response.getDataList());
    }

    /**
     * 2. 測試呼叫新增幣別對應表資料API。
     */
    @Test
    public void addTest() throws Exception {
        Coin coin = new Coin();
        coin.setChartName("TestChart");
        coin.setCode("USD");
        coin.setRate(BigDecimal.ONE);
        coin.setSymbol("Symbol");
        coin.setDescription("UnitTestCoin");

        given(coinService.add(coin)).willReturn(coin);

        MockHttpServletResponse mockResponse = mvc.perform(put("/coin/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomJsonUtil.toJson(coin))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        CoinDataResponse response = CustomJsonUtil.parseJson(mockResponse.getContentAsString(), CoinDataResponse.class);
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
        assertThat(CoinId.extractId(coin)).isEqualTo(CoinId.extractId(response.getData()));
        logger.info("add test pass, coin: {}", response.getData());
    }

    /**
     * 3. 測試呼叫更新幣別對應表資料API，並顯示其內容。
     */
    @Test
    public void modifyTest() throws Exception {
        Coin coin = new Coin();
        coin.setChartName("TestChart");
        coin.setCode("USD");
        coin.setRate(BigDecimal.ONE);
        coin.setSymbol("Symbol");
        coin.setDescription("UnitTestCoin");

        given(coinService.modify(coin)).willReturn(coin);

        MockHttpServletResponse mockResponse = mvc.perform(post("/coin/modify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomJsonUtil.toJson(coin))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        CoinDataResponse response = CustomJsonUtil.parseJson(mockResponse.getContentAsString(), CoinDataResponse.class);
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());

        Coin afterCoin = response.getData();
        assertThat(CoinId.extractId(coin)).isEqualTo(CoinId.extractId(response.getData()));
        assertThat(afterCoin.getRate()).isEqualTo(coin.getRate());
        logger.info("modify test pass, afterCoin: {}", afterCoin);
    }

    /**
     * 4. 測試呼叫刪除幣別對應表資料API。
     */
    @Test
    public void deleteTest() throws Exception {
        CoinId id = new CoinId();
        id.setChartName("TestChart");
        id.setCode("USD");

        given(coinService.delete(id)).willReturn(true);

        MockHttpServletResponse mockResponse = mvc.perform(delete("/coin/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CustomJsonUtil.toJson(id))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        BooleanDataResponse response = CustomJsonUtil.parseJson(mockResponse.getContentAsString(), BooleanDataResponse.class);
        assertThat(response.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
        assertThat(response.getData()).isEqualTo(true);
        logger.info("delete test pass");
    }

}
