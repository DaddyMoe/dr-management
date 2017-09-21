package com.recklassrekkids.drm;

import com.recklassrekkids.drm.console.service.DefaultPartnerProductService;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author moses@doraventures.com
 */
public class ActiveMusicDefinitions extends SpringIntegrationBase {

  String musicContractsInput;
  String partnerContractsInput;
  List<MusicContract> availableProducts;

  @Autowired
  DefaultPartnerProductService productService;

  @Given("^the supplied reference data$")
  public void theSuppliedReferenceData() throws Throwable {
    musicContractsInput = "src/test/resources/music_contracts_input.txt";
    partnerContractsInput = "src/test/resources/partner_contracts_input.txt";
  }

  @When("^user perform search by \"([^\"]*)\"$")
  public void userPerformSearchBy(String searchTerms) throws Throwable {
    availableProducts = productService.getAvailableProducts(musicContractsInput, partnerContractsInput, searchTerms);
  }

  @Then("^the output should be$")
  public void theOutputShouldBe(DataTable table) throws Throwable {
    availableProducts.forEach(musicContract -> {
    });

    int headerRow = 1;
    assertThat(availableProducts.size()).isEqualTo(table.getGherkinRows().size()- headerRow);
    //TODO: Do me detailed assertions here...
  }
}
