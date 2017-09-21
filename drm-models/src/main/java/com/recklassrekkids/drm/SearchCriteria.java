package com.recklassrekkids.drm;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * TODO: add description here
 *
 * @author moses@doraventures.com
 */
@Getter
@Builder
public class SearchCriteria {

  private String partnerName;
  private Date effectiveDate;
}
