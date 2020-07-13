package com.zf.api.controller;

import com.zf.api.vmodel.ValidateImageCode;
import com.zf.service.constant.ValidateCodeService;
import com.zf.service.sys.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public void login() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

    }

    @RequestMapping(value = "/getValidateCode")
    public ValidateImageCode getValidateCode() throws IOException {
        var validateCodeObj = ValidateCodeService.getValidateCode(60, 30);
        var redisUUID = userService.addValidateCode(validateCodeObj.getCode());

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(validateCodeObj.getCodeimg(), "png", bos);
        byte[] imageBytes = bos.toByteArray();
        String imageBase64 = new String(Base64.getEncoder().encode(imageBytes));
        imageBase64 = imageBase64.replaceAll("\n", "")
                .replaceAll("\r", "");

        ValidateImageCode imageCode = new ValidateImageCode();
        imageCode.setUuid(redisUUID);
        imageCode.setImageCode(imageBase64);
        return imageCode;
    }
}
