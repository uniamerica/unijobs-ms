package com.uniamerica.unijobsbackend.auth.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseTokenDto implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String access_token;

    private String refresh_token;
}
