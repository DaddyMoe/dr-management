package com.recklassrekkids.drm.console.service;

import com.recklassrekkids.drm.console.TestConfigs;
import com.recklassrekkids.drm.MusicContract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author moses@doraventures.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfigs.class)
public class DefaultPartnerProductServiceIT {

  @Autowired
  DefaultPartnerProductService productService;

  //Scenario 01
  @Test
  public void searchForActiveMusicContracts_shouldReturnMatchesForGivenTerm() throws Exception {

    // Given
    String musicContractsInput = "src/test/resources/music_contracts_input.txt";
    String partnerContractsInput = "src/test/resources/partner_contracts_input.txt";
    String searchTerms = "ITunes 1st March 2012";

    // When
    List<MusicContract> availableProducts = productService
        .getAvailableProducts(musicContractsInput, partnerContractsInput, searchTerms);

    // Then
    assertThat(availableProducts.size()).isEqualTo(4);
  }

  //Scenario 02
  @Test
  public void searchForActiveMusicContracts2_shouldReturnMatchesForGivenTerm() throws Exception {

    // Given
    String musicContractsInput = "src/test/resources/music_contracts_input.txt";
    String partnerContractsInput = "src/test/resources/partner_contracts_input.txt";
    String searchTerms = "YouTube 27th Dec 2012";

    // When
    List<MusicContract> availableProducts = productService
        .getAvailableProducts(musicContractsInput, partnerContractsInput, searchTerms);

    // Then
    assertThat(availableProducts.size()).isEqualTo(4);
  }

  //Scenario 03
  @Test
  public void searchForActiveMusicContracts3_shouldReturnMatchesForGivenTerm() throws Exception {

    // Given
    String musicContractsInput = "src/test/resources/music_contracts_input.txt";
    String partnerContractsInput = "src/test/resources/partner_contracts_input.txt";
    String searchTerms = "YouTube 1st April 2012";

    // When
    List<MusicContract> availableProducts = productService
        .getAvailableProducts(musicContractsInput, partnerContractsInput, searchTerms);

    // Then
    assertThat(availableProducts.size()).isEqualTo(2);
  }

}