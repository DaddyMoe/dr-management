package com.recklassrekkids.drm.console.helpers;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author moses@doraventures.com
 */
@Slf4j
public class DateHelpers {

  private static final Pattern ORDINAL_PATTERNS = Pattern.compile("([0-9]+)(st|nd|rd|th)");
  private static final String DATE_PATTERN = "dd MMM yyyy";

  public static Optional<Date> getDate(String dateString) {

    try {
      String source = deleteOrdinal(dateString);
      Date parseDate = new SimpleDateFormat(DATE_PATTERN).parse(source);
      return Optional.of(parseDate);
    } catch (ParseException e) {
      // TODO: test for edge cases
      log.error(String.format("Failed to convert date string [%s]", dateString), e.getCause());
      return Optional.empty();
    }
  }

  private static String deleteOrdinal(String dateString) {

    Matcher m = ORDINAL_PATTERNS.matcher(dateString);
    String stripped = "";
    while (m.find()) {
      stripped = dateString.replaceAll(Matcher.quoteReplacement(m.group(0)), m.group(1));
    }
    return stripped;
  }
}
