package com.okworo.kcb.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObject {
    private Boolean success;
    private String message;
    private Object data;
}
