package com.zf.service.dmodel;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Getter
@Setter
public class ValidateCode {

    private String code;

    private BufferedImage codeimg;
}
