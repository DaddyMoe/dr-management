package com.recklassrekkids.drm.console.service;

import com.recklassrekkids.drm.console.helpers.SearchCriteriaFactory;
import com.recklassrekkids.drm.console.loader.ReferenceDataLoader;
import com.recklassrekkids.drm.DistributionPartnerContract;
import com.recklassrekkids.drm.MusicContract;
import com.recklassrekkids.drm.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author moses@doraventures.com
 */
@Service("defaultPartnerProductService")
public class DefaultPartnerProductService implements PartnerProductService {

  @Autowired
  @Qualifier("fileReferenceDataLoader")
  private ReferenceDataLoader dataLoader;

  public List<MusicContract> getAvailableProducts(String musicContractsPath, String partnerContractsPath, String searchTerm) {

    List<MusicContract> musicContracts = dataLoader.getMusicContracts(musicContractsPath);
    List<DistributionPartnerContract> partnerContracts = dataLoader.getPartnerContracts(partnerContractsPath);
    return fetchEffectiveProducts(musicContracts, partnerContracts, searchTerm);
  }

  private List<MusicContract> fetchEffectiveProducts(List<MusicContract> musicContracts,
                                                     List<DistributionPartnerContract> partnerContracts,
                                                     String searchTerm) {

    List<MusicContract> musicContractsResponse = new ArrayList<>();
    SearchCriteria searchCriteria = SearchCriteriaFactory.getSearchCriteria(searchTerm);
    String medium = assignPartnerSource(partnerContracts, searchCriteria);
    extractMatchingProducts(musicContracts, musicContractsResponse, searchCriteria, medium);
    return musicContractsResponse;
  }


  private String assignPartnerSource(List<DistributionPartnerContract> partnerContracts, SearchCriteria searchCriteria) {

    final String[] partnerSource = {""};
    partnerContracts.stream().forEach(
        distributionPartnerContract -> {
          if (distributionPartnerContract.getPartner().equalsIgnoreCase(searchCriteria.getPartnerName()))
            partnerSource[0] = distributionPartnerContract.getUsage();
        }
    );

    return partnerSource[0];
  }

  private void extractMatchingProducts(List<MusicContract> musicContracts, List<MusicContract> musicContractsResponse, SearchCriteria searchCriteria, String medium) {

    musicContracts.stream().forEach(musicContract ->
        {
          if ((musicContract.getStartDate().compareTo(searchCriteria.getEffectiveDate()) > 0)) {
            return;
          }
          for (String usage : musicContract.getUsages()) {
            if (usage.equalsIgnoreCase(medium)) {
              overrideUsage(medium, musicContract);
              musicContractsResponse.add(musicContract);
            }
          }
        }
    );
  }

  private static void overrideUsage(String medium, MusicContract musicContract) {
    musicContract.setUsages(new String[]{medium});
  }
}
