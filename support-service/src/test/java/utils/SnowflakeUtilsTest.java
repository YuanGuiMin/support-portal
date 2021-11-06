package utils;

import org.junit.Test;

import java.text.MessageFormat;

public class SnowflakeUtilsTest{

    @Test
    public void test(){
        for(int i = 0; i < 65; i++){
            System.out.println(MessageFormat.format("i: {0}, centerId: {1}, workerId: {2}", i, i / 32, i % 32));
        }
    }
}
