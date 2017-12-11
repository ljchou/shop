package com.ljchou.shop;

import com.ljchou.shop.entity.User;
import com.ljchou.shop.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@MapperScan("com.ljchou.shop.mapper")
@SpringBootApplication
public class ShopApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopApplication.class);

	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/index")
	public String index() {
		List<User> users = userMapper.getAll();
		LOGGER.info(users.toString());
		return users.toString();
	}


	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
}
