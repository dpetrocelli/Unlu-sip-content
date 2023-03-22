package com.mycompany.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class App {

    Gson gson;
    //private static final String MESSAGE = "Hello World!";

    public static void main(String[] args) {
        System.out.println("Server is starting");
        Gson gson = new Gson();
        System.out.println("Gson object manager is created");

        Staff staff = new Staff();
        staff.setName("seba");
        staff.setAge(24);
        staff.setPosition(new String[]{"estudiante", "desarrollador", "tester"});

        String jsonStaff = gson.toJson(staff);
        System.out.println ("STAFF OBJECT: "+jsonStaff);

        Staff staff2 = gson.fromJson(jsonStaff, Staff.class);
        staff.setName("facu1");
        System.out.println ("STAFF OBJECT 2 -name: "+staff2.getName());
        String jsonStaff2 = gson.toJson(staff);
        System.out.println ("STAFF OBJECT 2 : "+jsonStaff2);
        //App application = new App();

    }

    
}
