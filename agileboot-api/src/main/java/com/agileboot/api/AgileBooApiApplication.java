package com.agileboot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序 定制banner.txt的网站
 * <a href="http://patorjk.com/software/taag">http://patorjk.com/software/taag</a>
 * <a href="http://www.network-science.de/ascii/">http://www.network-science.de/ascii/</a>
 * <a href="http://www.degraeve.com/img2txt.php">http://www.degraeve.com/img2txt.php</a>
 * <a href="http://life.chacuo.net/convertfont2char">http://life.chacuo.net/convertfont2char</a>
 *
 * @author valarchie
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.agileboot.*")
public class AgileBooApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgileBooApiApplication.class, args);
        String successMsg = "  ____   _                _                                                           __         _  _ \n"
                          + " / ___| | |_  __ _  _ __ | |_   _   _  _ __    ___  _   _   ___  ___  ___  ___  ___  / _| _   _ | || |\n"
                          + " \\___ \\ | __|/ _` || '__|| __| | | | || '_ \\  / __|| | | | / __|/ __|/ _ \\/ __|/ __|| |_ | | | || || |\n"
                          + "  ___) || |_| (_| || |   | |_  | |_| || |_) | \\__ \\| |_| || (__| (__|  __/\\__ \\\\__ \\|  _|| |_| || ||_|\n"
                          + " |____/  \\__|\\__,_||_|    \\__|  \\__,_|| .__/  |___/ \\__,_| \\___|\\___|\\___||___/|___/|_|   \\__,_||_|(_)\n"
                          + "                                      |_|                                                             ";

        System.out.println(successMsg);
    }
}
