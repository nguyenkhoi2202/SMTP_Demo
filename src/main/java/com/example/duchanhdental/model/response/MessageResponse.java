package com.example.duchanhdental.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse implements Serializable {

    private String errorType;
    private String status;
    private String message;

}
