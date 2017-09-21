package com.recklassrekkids.drm.console.service;

import com.recklassrekkids.drm.console.TestConfigs;
import com.recklassrekkids.drm.console.loader.ReferenceDataLoader;
import com.recklassrekkids.drm.MusicContract;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.recklassrekkids.drm.console.TestData.testMusicContracts;
import static com.recklassrekkids.drm.console.TestData.testPartnerContracts;
import static org.mockito.BDDMockito.given;


/**
 * @author moses@doraventures.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfigs.class)
public class DefaultPartnerProductServiceTest {


  @MockBean
  ReferenceDataLoader referenceDataLoader; // I should really mock the data here

  @InjectMocks
  @Autowired
  DefaultPartnerProductService productService;

  @Test
  public void getAvailableProducts() throws Exception {

    // Given
    given(referenceDataLoader.getPartnerContracts(Matchers.anyString())).willReturn(testPartnerContracts());
    given(referenceDataLoader.getMusicContracts(Matchers.anyString())).willReturn(testMusicContracts());
    String musicContractsInput = "src/test/resources/music_contracts_input.txt";
    String partnerContractsInput = "src/test/resources/partner_contracts_input.txt";


    // When
    List<MusicContract> availableProducts = productService
        .getAvailableProducts(musicContractsInput, partnerContractsInput, "ITunes 1st March 2012");

    // Then
    Assertions.assertThat(availableProducts.size()).isEqualTo(4);
  }

}