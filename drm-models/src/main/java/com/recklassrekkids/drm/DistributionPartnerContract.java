package com.recklassrekkids.drm;

import lombok.Builder;
import lombok.Data;

/**
 * POJO For RR's Global Rights Management Distribution Partner Contracts
 *
 * @author moses@doraventures.com
 */
@Builder
@Data
public class DistributionPartnerContract {

  String partner;
  String usage;
}
