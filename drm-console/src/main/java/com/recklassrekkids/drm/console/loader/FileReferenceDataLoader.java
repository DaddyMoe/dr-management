package com.recklassrekkids.drm.console.loader;

import com.recklassrekkids.drm.DistributionPartnerContract;
import com.recklassrekkids.drm.MusicContract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.recklassrekkids.drm.console.helpers.DateHelpers.getDate;

/**
 * ReferenceDataLoader from a File implementation
 *
 * @author moses@doraventures.com
 */
@Service("fileReferenceDataLoader")
@Slf4j
public class FileReferenceDataLoader implements ReferenceDataLoader {

  private static final String DATA_COLUMN_DELIMITER = "\\|";
  private static final String USAGES_DELIMITER = ", ";

  File getFile(String fileLocation) {
    return new File(fileLocation);
  }

  public List<String> getDataRows(String resourceLocation) {

    File file = getFile(resourceLocation);
    List<String> list = new ArrayList<>();

    String absolutePath = file.getAbsolutePath();
    try (BufferedReader br = Files.newBufferedReader(Paths.get(absolutePath))) {
      list = br.lines().skip(1).collect(Collectors.toList()); // skip 1 = the column header
    } catch (IOException e) {
      // TODO: test for edge cases
      log.error("Failed to read and load file [{}] to list ", absolutePath, e.getCause());
    }

    return list;
  }

  public List<MusicContract> getMusicContracts(String resourceLocation) {

    List<MusicContract> musicContracts = new ArrayList<>();
    List<String> dataRows = getDataRows(resourceLocation);
    dataRows.forEach(s -> {

          String[] dataColumns = s.split(DATA_COLUMN_DELIMITER);
          if (dataColumns.length > 4) {
            musicContracts.add(buildMusicContract(dataColumns, true));
          } else if (dataColumns.length == 4) {
            musicContracts.add(buildMusicContract(dataColumns, false));
          }
        }
    );

    return musicContracts;
  }

  public List<DistributionPartnerContract> getPartnerContracts(String resourceLocation) {

    List<DistributionPartnerContract> partnerContracts = new ArrayList<>();
    List<String> dataRows = getDataRows(resourceLocation);
    dataRows.forEach(s -> {

          String[] dataColumns = s.split(DATA_COLUMN_DELIMITER);
          if (dataColumns.length >= 2) {
            partnerContracts.add(buildDistributionPartnerContract(dataColumns));
          }
        }
    );

    return partnerContracts;
  }

  private MusicContract buildMusicContract(String[] dataColumns, boolean withEndDate) {

    MusicContract.MusicContractBuilder musicContractBuilder = MusicContract.builder()
        .artist(dataColumns[0])
        .title(dataColumns[1])
        .usages(getUsages(dataColumns[2]));

    Optional<Date> date = getDate(dataColumns[3]);
    date.ifPresent(musicContractBuilder::startDate);

    if (withEndDate) {
      date = getDate(dataColumns[4]);
      date.ifPresent(musicContractBuilder::endDate);
    }

    return musicContractBuilder.build();
  }

  private DistributionPartnerContract buildDistributionPartnerContract(String[] dataColumns) {

    return DistributionPartnerContract.builder()
        .partner(dataColumns[0])
        .usage(dataColumns[1]).build();
  }

  private String[] getUsages(String s) {
    return s.split(USAGES_DELIMITER);
  }
}
