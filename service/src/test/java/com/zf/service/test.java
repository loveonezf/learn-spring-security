package com.zf.service;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@SpringBootApplication(scanBasePackages = "com.zf")
@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class test {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void test() {

        var vvv = (List<String>) redisTemplate.opsForList().index("testRedis", 0);

        var aa = new ArrayList<String>();
        aa.add("aa");
        aa.add("bb");
        aa.add("cc");
        //aa.add("dd");

        redisTemplate.opsForList().leftPush("testRedis", aa);

        var r1 = (List<String>) redisTemplate.opsForList().index("testRedis", 0);

        var ww = r1.stream().filter(w -> w.equals("bb")).collect(Collectors.toList());
        var r3 = r1.stream().filter(w -> !ww.contains(w)).collect(Collectors.toList());


        redisTemplate.opsForList().leftPush("testRedis", r3);
        var r2 = (List<String>) redisTemplate.opsForList().index("testRedis", 0);


        redisTemplate.opsForList().leftPush("test1", "11");
        redisTemplate.opsForList().leftPush("test1", "22");
        redisTemplate.opsForList().leftPush("test1", "33");

        var tt = redisTemplate.opsForList().index("test1", 1);

        //var r = redisTemplate.opsForList().leftPop("testRedis");

    }


    @Test
    public void test1() {
        var aa = new ArrayList<String>();
        for (Integer i = 0; i <= 60000; i++) {
            aa.add(i.toString());
        }

        redisTemplate.opsForList().leftPush("testRedis111111111", aa);

        var vvv = (List<String>) redisTemplate.opsForList().index("testRedis111111111", 0);
    }


    @Test
    public void test2() {
        var vvv = (List<String>) redisTemplate.opsForList().index("testRedis111111111", 0);

        var aaa = vvv.stream().collect(Collectors.toList());

        var bbb = MapUtil.mapToAnotherList(vvv, vvv.getClass());

        bbb.remove(1);
        bbb.remove(1);

        aaa.remove(1);


    }

    private static Map<String, Object> map = new HashMap<String, Object>();
    @Test
    public void test3() {
        var key_time = "key_time";
//        redisTemplate.opsForValue().set(key_time, LocalDateTime.now());
//        var dd = redisTemplate.opsForValue().get(key_time);

        var aa = new ArrayList<String>();
        for (Integer i = 0; i <= 60000; i++) {
            aa.add(i.toString());
        }
        map.put(key_time,aa);

        map.put("aa","b");
        map.remove(key_time);
        int i=0;
    }
}
