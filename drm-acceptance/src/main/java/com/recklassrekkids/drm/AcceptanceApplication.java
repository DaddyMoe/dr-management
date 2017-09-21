package com.recklassrekkids.drm;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Same as @SpringBootConfiguration @EnableAutoConfiguration @ComponentScan
public class AcceptanceApplication implements CommandLineRunner {

//  @Autowired()
//  @Qualifier("defaultPartnerProductService")
//  DefaultPartnerProductService productService;

  public static void main(String[] args) throws Exception {

    SpringApplication app = new SpringApplication(AcceptanceApplication.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  /**
   * Callback used to run the bean.
   *
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {

  }
}