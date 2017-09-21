package com.recklassrekkids.drm.console.loader;

import com.recklassrekkids.drm.DistributionPartnerContract;
import com.recklassrekkids.drm.MusicContract;

import java.util.List;

/**
 *
 * @author moses@doraventures.com
 */
public interface ReferenceDataLoader {

  List<String> getDataRows(String resourceLocation);

  List<MusicContract> getMusicContracts(String resourceLocation);

  List<DistributionPartnerContract> getPartnerContracts(String resourceLocation);

}
