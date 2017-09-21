package com.recklassrekkids.drm.console;

import com.recklassrekkids.drm.DistributionPartnerContract;
import com.recklassrekkids.drm.MusicContract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.recklassrekkids.drm.console.helpers.DateHelpers.getDate;

/**
 * @author moses@doraventures.com
 */
public class TestData {

  public static List<MusicContract> testMusicContracts() {

    List<MusicContract> musicContracts = new ArrayList<>();

    musicContracts.add(
        MusicContract.builder()
            .artist("Tinie Tempah")
            .title("Frisky (Live from SoHo)")
            .usages(new String[]{"digital download", "streaming"})
            .startDate(getDate("1st Feb 2012").orElse(new Date()))
            .build()
    );

    musicContracts.add(
        MusicContract.builder()
            .artist("Tinie Tempah")
            .title("Miami 2 Ibiza")
            .usages(new String[]{"digital download"})
            .startDate(getDate("1st Feb 2012").orElse(new Date()))
            .build()
    );

    musicContracts.add(
        MusicContract.builder()
            .artist("Tinie Tempah")
            .title("Till I'm Gone")
            .usages(new String[]{"digital download"})
            .startDate(getDate("1st Aug 2012").orElse(new Date()))
            .build()
    );

    musicContracts.add(
        MusicContract.builder()
            .artist("Monkey Claw")
            .title("Black Mountain")
            .usages(new String[]{"digital download"})
            .startDate(getDate("1st Feb 2012").orElse(new Date()))
            .build()
    );

    musicContracts.add(
        MusicContract.builder()
            .artist("Monkey Claw")
            .title("Iron Horse")
            .usages(new String[]{"digital download", "streaming"})
            .startDate(getDate("1st June 2012").orElse(new Date()))
            .build()
    );

    musicContracts.add(
        MusicContract.builder()
            .artist("Monkey Claw")
            .title("Motor Mouth")
            .usages(new String[]{"digital download", "streaming"})
            .startDate(getDate("1st Mar 2011").orElse(new Date()))
            .build()
    );

    musicContracts.add(
        MusicContract.builder()
            .artist("Monkey Claw")
            .title("Christmas Special")
            .usages(new String[]{"streaming"})
            .startDate(getDate("25th Dec 2012").orElse(new Date()))
            .endDate(getDate("31st Dec 2012").orElse(new Date()))
            .build()
    );

    return musicContracts;
  }

  public static List<DistributionPartnerContract> testPartnerContracts() {

    List<DistributionPartnerContract> contracts = new ArrayList<>();
    contracts.add(DistributionPartnerContract.builder().partner("ITunes").usage("digital download").build());
    contracts.add(DistributionPartnerContract.builder().partner("YouTube").usage("streaming").build());
    return contracts;
  }
}
