package com.zosh.service;

import com.zosh.response.ApiResponse;

public interface ChatbotService {

    ApiResponse getCoinDetails(String prompt) throws Exception;
    String simpleChat(String prompt);

}
