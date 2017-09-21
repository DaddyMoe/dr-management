package com.recklassrekkids.drm.console.loader;

import com.recklassrekkids.drm.DistributionPartnerContract;
import com.recklassrekkids.drm.MusicContract;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.List;

import static com.recklassrekkids.drm.console.helpers.DateHelpers.getDate;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author moses@doraventures.com
 */
public class FileReferenceDataLoaderTest {

  @Test
  public void givenAFileValidFilePath_returnAnExistingFile() throws Exception {

    //Given
    FileReferenceDataLoader loader = new FileReferenceDataLoader();
    String pathName = "src/test/resources/music_contracts_input.txt";

    // When
    File resourcesFile = loader.getFile(pathName);
    System.out.println(resourcesFile.getAbsolutePath());

    //Then
    assertThat(resourcesFile.exists()).isTrue();
  }

  @Test
  public void givenAInvalidFileValidFilePath_returnAMissingFile() throws Exception {

    //Given
    FileReferenceDataLoader loader = new FileReferenceDataLoader();
    String pathName = "src/test/resources/example_input_invalid_path.txt";

    // When
    File resourcesFile = loader.getFile(pathName);
    System.out.println(resourcesFile.getAbsolutePath());

    //Then
    assertThat(resourcesFile.exists()).isFalse();
  }

  @Test
  public void givenAValidFile_shouldReturnStringListOfRowsInFile() throws Exception {

    //Given
    ReferenceDataLoader loader = new FileReferenceDataLoader();
    String pathName = "src/test/resources/music_contracts_input.txt";

    // When
    List<String> dataRows = loader.getDataRows(pathName);

    //Then should return all rows ( minus the header)
    assertThat(dataRows.size()).isEqualTo(7);
  }

  @Test
  public void givenAValidFilePath_shouldReturnListOfMappedMusicContracts() throws Exception {

    //Given
    FileReferenceDataLoader loader = new FileReferenceDataLoader();
    MusicContract tinieMiami2Ibiza = MusicContract.builder()
        .artist("Tinie Tempah")
        .title("Miami 2 Ibiza")
        .usages(new String[]{"digital download"})
        .startDate(getDate("1st Feb 2012").orElse(new Date()))
        .build();

    String pathName = "src/test/resources/music_contracts_input.txt";

    // When
    List<MusicContract> musicContracts = loader.getMusicContracts(pathName);

    //Then
    assertThat(musicContracts.size()).isEqualTo(7);
    assertThat(musicContracts).contains(tinieMiami2Ibiza);
  }

  @Test
  public void givenAValidFilePath_shouldReturnListOfMappedPartnerContracts() throws Exception {

    //Given
    ReferenceDataLoader loader = new FileReferenceDataLoader();
    String pathName = "src/test/resources/partner_contracts_input.txt";
    DistributionPartnerContract ItunesContract =
        DistributionPartnerContract.builder().partner("ITunes").usage("digital download").build();
    DistributionPartnerContract youTubeContract =
        DistributionPartnerContract.builder().partner("YouTube").usage("streaming").build();

    // When
    List<DistributionPartnerContract> partnerContracts = loader.getPartnerContracts(pathName);

    //Then
    assertThat(partnerContracts.size()).isEqualTo(2);
    assertThat(partnerContracts.size()).isEqualTo(2);
    assertThat(partnerContracts).contains(ItunesContract);
    assertThat(partnerContracts).contains(youTubeContract);
  }
}
