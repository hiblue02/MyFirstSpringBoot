package springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloResponseDtoTest {

    @Test
    public void lombok_function_test(){
        String name = "test";
        int amount = 1000;
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        assertEquals(name, helloResponseDto.getName());
        assertEquals(amount, helloResponseDto.getAmount());

    }

}