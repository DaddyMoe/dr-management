package com.recklassrekkids.drm;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * POJO For RR's Global Rights Management Music Contracts
 *
 * @author moses@doraventures.com
 */
@Builder
@Data
public class MusicContract {

  String artist;
  String title;
  String[] usages;
  Date startDate;
  Date endDate;
}
