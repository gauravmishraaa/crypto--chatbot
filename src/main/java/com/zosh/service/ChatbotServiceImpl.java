package com.zosh.service;

import com.zosh.dto.CoinDto;
import com.zosh.response.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class ChatbotServiceImpl implements ChatbotService {

    private double convertToDouble(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        }
        else if (value instanceof Long) {
            return ((Long) value).doubleValue();
        }
        else if (value instanceof Double) {
            return ((Double) value).doubleValue();
        }
        else {
            throw new IllegalArgumentException(
                    "Unsupported type: " + value.getClass().getName()
            );
        }
    }


    public CoinDto makeApiRequest(String currencyName) throws Exception {
        String url = "https://api.coingecko.com/api/v3/coins/bitcoin";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> responseBody = responseEntity.getBody();

        if(responseBody!=null){
        Map<String, Object> image = (Map<String, Object>) responseBody.get("image");
        Map<String, Object> marketData = (Map<String, Object>) responseBody.get("market_data");

        CoinDto coinDto = new CoinDto();
        coinDto.setId((String)responseBody.get("id"));
        coinDto.setName((String)responseBody.get("name"));
        coinDto.setSymbol((String)responseBody.get("symbol"));
        coinDto.setImage((String)image.get("large"));

       // market data
        coinDto.setCurrentPrice(convertToDouble(((Map<String, Object>)marketData.get("current_price")).get("usd")));
        coinDto.setMarketCap(convertToDouble(((Map<String, Object>)marketData.get("market_cap")).get("usd")));
        coinDto.setMarketCapRank(convertToDouble(((Map<String, Object>)marketData.get("market_cap_rank")).get("usd")));
        coinDto.setTotalVolume(convertToDouble(((Map<String, Object>)marketData.get("total_valume")).get("usd")));
        coinDto.setHigh24h(convertToDouble(((Map<String, Object>)marketData.get("high_24")).get("usd")));
        coinDto.setLow24h(convertToDouble(((Map<String, Object>)marketData.get("low_24")).get("usd")));
        coinDto.setPriceChange24h(convertToDouble(((Map<String, Object>)marketData.get("pricechange_24"))));
        coinDto.setPriceChangePercentage24h(convertToDouble(((Map<String, Object>)marketData.get("price_change_percentege_24"))));
        coinDto.setMarketCapChange24h(convertToDouble(((Map<String, Object>)marketData.get("market_cap_change_24"))));
        coinDto.setMarketCapChangePercentage24h(convertToDouble(((Map<String, Object>)marketData.get("market_cap_change_percentage_24"))));
        coinDto.setCirculatingSupply(convertToDouble(((Map<String, Object>)marketData.get("circulating_supply"))));
        coinDto.setTotalSupply(convertToDouble(((Map<String, Object>)marketData.get("total_supply"))));

   return  coinDto;

        }
        throw new Exception("coin not found") ;
    }

    @Override
    public ApiResponse getCoinDetails(String prompt) throws Exception {
        CoinDto coinDto = makeApiRequest(prompt);
        System.out.println("coin dto -----"+coinDto);

        return null;
    }

    @Override
    public String simpleChat(String prompt) {
        return "";
    }
}
