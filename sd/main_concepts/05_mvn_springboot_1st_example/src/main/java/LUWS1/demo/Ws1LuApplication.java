package LUWS1.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.gson.Gson;


import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class Ws1LuApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ws1LuApplication.class, args);
		System.out.println("HOLA");
		Staff staff = new Staff();
		staff.setName("mkyong");
        staff.setAge(35);
        staff.setPosition(new String[]{"Founder", "CTO", "Writer"});
        Map<String, BigDecimal> salary = new HashMap() {{
            put("2010", new BigDecimal(10000));
            put("2012", new BigDecimal(12000));
            put("2018", new BigDecimal(14000));
        }};
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));

		System.out.println(staff);

		Gson gson = new Gson();

		System.out.println(gson.toJson(staff));

	}

}
