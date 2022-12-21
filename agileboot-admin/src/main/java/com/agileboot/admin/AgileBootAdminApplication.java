package com.agileboot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * 定制banner.txt的网站
 * http://patorjk.com/software/taag
 * http://www.network-science.de/ascii/
 * http://www.degraeve.com/img2txt.php
 * http://life.chacuo.net/convertfont2char
 * @author valarchie
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.agileboot.*")
public class AgileBootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgileBootAdminApplication.class, args);
        String successMsg = "  ____   _                _                                                           __         _  _ \n"
                          + " / ___| | |_  __ _  _ __ | |_   _   _  _ __    ___  _   _   ___  ___  ___  ___  ___  / _| _   _ | || |\n"
                          + " \\___ \\ | __|/ _` || '__|| __| | | | || '_ \\  / __|| | | | / __|/ __|/ _ \\/ __|/ __|| |_ | | | || || |\n"
                          + "  ___) || |_| (_| || |   | |_  | |_| || |_) | \\__ \\| |_| || (__| (__|  __/\\__ \\\\__ \\|  _|| |_| || ||_|\n"
                          + " |____/  \\__|\\__,_||_|    \\__|  \\__,_|| .__/  |___/ \\__,_| \\___|\\___|\\___||___/|___/|_|   \\__,_||_|(_)\n"
                          + "                                      |_|                                                             ";

        System.out.println(successMsg);
    }
}
