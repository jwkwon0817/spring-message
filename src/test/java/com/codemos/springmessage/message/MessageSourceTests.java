package com.codemos.springmessage.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTests {
	@Autowired
	MessageSource messageSource;
	
	@Test
	void helloMessage() {
		String result = messageSource.getMessage("hello", null, null);
		assertThat(result).isEqualTo("안녕");
	}
	
	@Test
	void notFoundMessageCode() {
		assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
			.isInstanceOf(NoSuchMessageException.class);
	}
	
	@Test
	void notFoundMessageCodeDefaultMessage() {
		String result = messageSource.getMessage("no_code", null, "Default Message", null);
		assertThat(result).isEqualTo("Default Message");
	}
	
	@Test
	void argumentMessage() {
		String result = messageSource.getMessage("hello.name", new Object[]{ "Spring" }, null);
		assertThat(result).isEqualTo("안녕 Spring!");
	}
	
	@Test
	void defaultLang() {
		assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕");
		assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
	}
	
	@Test
	void enLang() {
		assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("Hello");
	}
}
