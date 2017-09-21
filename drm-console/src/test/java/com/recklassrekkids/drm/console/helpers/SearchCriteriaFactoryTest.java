package com.recklassrekkids.drm.console.helpers;

import com.recklassrekkids.drm.SearchCriteria;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author moses@doraventures.com
 */
public class SearchCriteriaFactoryTest {

  @Test
  public void getSearchCriteria_shouldReturnSearchCriteriaTerms() throws Exception {

    // When
    SearchCriteria searchCriteria = SearchCriteriaFactory.getSearchCriteria("ITunes 1st March 2012");

    // Then
    assertThat(searchCriteria.getPartnerName(), is("ITunes"));
    assertThat(searchCriteria.getEffectiveDate().toString(), is("Thu Mar 01 00:00:00 GMT 2012"));
  }

  // TODO: cover edge cases too
}