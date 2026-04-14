package com.zosh.dto;

import lombok.Data;

@Data
public class PromptBody {
   public String prompt;
   public String message;
   public String status;
}
