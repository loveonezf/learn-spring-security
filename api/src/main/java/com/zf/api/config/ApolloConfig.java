package com.zf.api.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.google.common.util.concurrent.SettableFuture;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ApolloConfig {

    //是否开启swagger，正式环境一般是需要关闭的
    @Value("${swagger.enabled:false}")
    private boolean enableSwagger;


    SettableFuture<ConfigChangeEvent> futureData = SettableFuture.create();

    //ApolloConfigChangeListener默认监听application，如果要监听多个命名空间@ApolloConfigChangeListener({"application", "mysql"})
    @ApolloConfigChangeListener
    private void someChangeHandler(ConfigChangeEvent changeEvent) {
        futureData.set(changeEvent);
    }
}

