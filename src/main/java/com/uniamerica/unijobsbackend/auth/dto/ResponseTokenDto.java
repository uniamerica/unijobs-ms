package com.uniamerica.unijobsbackend.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
@RequiredArgsConstructor
@Getter
public class ResponseTokenDto implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
}
