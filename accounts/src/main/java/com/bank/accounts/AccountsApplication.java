package com.bank.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Stephane Nganou
 */
@SpringBootApplication
//@ComponentScans({@ComponentScan("com.bank.accounts.controller")})
//@EnableJpaRepositories("com.bank.accounts.repository")
//@EntityScan("com.bank.accounts.model")
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
