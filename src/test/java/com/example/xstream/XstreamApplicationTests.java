package com.example.xstream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class XstreamApplicationTests {

	Calculator testCal =new Calculator();
	@Test
	void contextLoads() {



	}
	@Test
	void itShouldAddNumbers(){
		int firstNumber=20;
		int secondNumber=40;

		int result =testCal.add(firstNumber,secondNumber);
		int expected=60;
		//then
		assertThat(result).isEqualTo(expected);
	}
	class Calculator{
		int add(int a,int b){
			return a+b;
		}
	}
}
