package com.itsa.gourds.masks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;



@SpringBootTest
@AutoConfigureMockMvc
public class MaskServiceApplicationTests {

	@Test
	public void contextLoads() {
        System.out.println("LOAD");
        assert true;
	}

}
