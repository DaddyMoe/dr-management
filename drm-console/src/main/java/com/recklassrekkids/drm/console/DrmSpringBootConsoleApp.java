package com.recklassrekkids.drm.console;

import com.recklassrekkids.drm.console.service.DefaultPartnerProductService;
import com.recklassrekkids.drm.console.service.HelloMessageService;
import com.recklassrekkids.drm.MusicContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


/**
 * @author moses@doraventures.com
 */
@SpringBootApplication // Same as @SpringBootConfiguration @EnableAutoConfiguration @ComponentScan
public class DrmSpringBootConsoleApp implements CommandLineRunner {

  @Autowired
  private HelloMessageService helloService;

  @Autowired
  DefaultPartnerProductService productService;

  public static void main(String[] args) throws Exception {

    SpringApplication app = new SpringApplication(DrmSpringBootConsoleApp.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }

  @Override
  public void run(String... args) throws Exception {

//    if (args.length > 0) {
//      System.out.println(helloService.getMessage(args[0].toString()));
//    } else {
//      System.out.println(helloService.getMessage());
//    }

    if (args.length != 3) {
      System.out.println("Invalid arguments.  Run command format is:");
      System.out.println("Java -jar \"<music_contracts_path>\" \"<partner_contracts_path>\" \"<search term>\" ");
      System.out.println("Example: for Scenario 01 ");
      System.out.println("$ java -jar drm-console/target/drm-console-1.0.jar " +
          "\"drm-console/src/main/resources/music_contracts_input.txt\"  " +
          "\"drm-console/src/main/resources/partner_contracts_input.txt\" " +
          "\"YouTube 27th Dec 2012\"");
      return;
    }

    // Given
//    String musicContractsInput = "/Users/mosesmansaray/dev/code/mm/technical test/universal_music/digitalrightsmanagement/drm-console/src/main/resources/music_contracts_input.txt";
//    String partnerContractsInput = "/Users/mosesmansaray/dev/code/mm/technical test/universal_music/digitalrightsmanagement/drm-console/src/main/resources/partner_contracts_input.txt";
//    String searchTerms = "YouTube 27th Dec 2012";

    String musicContractsInput = args[0];
    String partnerContractsInput = args[1];
    String searchTerms = args[2];

    // When
    List<MusicContract> effectProducts = productService
        .getAvailableProducts(musicContractsInput, partnerContractsInput, searchTerms);

    printEffectiveProducts(effectProducts);
  }

  private void printEffectiveProducts(List<MusicContract> effectProducts) {
    System.out.println("Effective products are:");
    System.out.println("| Artist       | Title                   | Usages    | StartDate     | EndDate       |");
        effectProducts.forEach(musicContract -> {
              StringBuilder row = new StringBuilder()
                  .append("|  ").append(musicContract.getArtist()).append("  |  ")
                  .append(musicContract.getTitle()).append("  |  ")
                  .append(musicContract.getUsages()).append("  |  ")
                  .append(musicContract.getStartDate()).append("  |  ")
                  .append(musicContract.getEndDate()).append("  |  ");
          System.out.println(row);
            }
        );
  }


//    exit(0);
}
