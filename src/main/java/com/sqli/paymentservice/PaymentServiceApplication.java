package com.sqli.paymentservice;


import com.sqli.paymentservice.entities.Customer;
import com.sqli.paymentservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@EnableDiscoveryClient
@SpringBootApplication
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository CustomerRepository){
         return  args -> {


             List<String> fullNames = Arrays.asList("ACHRAF", "KHALID", "FALL");
             List<String> genders = Arrays.asList("M", "M", "M");
             List<LocalDate> birthDates = Arrays.asList(
                     LocalDate.of(1990, 5, 1),
                     LocalDate.of(1993, 3, 9),
                     LocalDate.of(1985, 7, 12)
             );
             List<String> mobiles = Arrays.asList("0656565655", "0653269898", "0356565565");
             List<String> emails = Arrays.asList("customeremail@gmail.com","customer2@gmail.com","customer3@gmail.com");

             IntStream.range(0, fullNames.size())
                     .forEach(i -> {
                         String fullName = fullNames.get(i);
                         String gender = genders.get(i);
                         LocalDate birthDay = birthDates.get(i);
                         String mobile = mobiles.get(i);
                         String email = emails.get(i);

                         CustomerRepository.save(new Customer(null, fullName, gender, birthDay, mobile, email));
                     });
             CustomerRepository.findAll().forEach(System.out::println);
         };
    }

}
