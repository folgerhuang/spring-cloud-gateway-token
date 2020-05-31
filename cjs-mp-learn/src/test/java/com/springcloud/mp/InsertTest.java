package com.springcloud.mp;

import com.springcloud.mp.dao.UserMapper;
import com.springcloud.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.time.LocalDateTime;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc xxxx
 * @date 2020/5/31 23:19
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("向东");
        user.setAge(35);
        user.setManagerId(1088248166370832385L);
        user.setEmail("xd@baomidou.com");
        user.setCreateTime(LocalDateTime.now());

        int rows = userMapper.insert(user);

        System.out.println("影响记录数："+ rows);

    }

}
