package com.recklassrekkids.drm.console.service;

import com.recklassrekkids.drm.MusicContract;

import java.util.List;

/**
 * Interface for Partner Product Service on a given date
 *
 * @author moses@doraventures.com
 */
public interface PartnerProductService {

  List<MusicContract> getAvailableProducts(String musicContractsPath, String partnerContractsPath, String searchTerm);
}
