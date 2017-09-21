package com.recklassrekkids.drm.console.helpers;

import com.recklassrekkids.drm.SearchCriteria;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author moses@doraventures.com
 */
@Slf4j
public class SearchCriteriaFactory {

  public static SearchCriteria getSearchCriteria(String searchTerms) {

    String[] terms = searchTerms.split(" ", 2);

    String partnerNameTerm = terms[0];
    Date effectiveDateCriteria = DateHelpers.getDate(terms[1]).orElse(new Date());
    return SearchCriteria.builder().partnerName(partnerNameTerm).effectiveDate(effectiveDateCriteria).build();
  }
}
