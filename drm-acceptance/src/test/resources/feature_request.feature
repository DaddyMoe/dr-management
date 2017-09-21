Feature: the message can be retrieved

  Scenario: Search for active music contracts
    Given the supplied reference data
    When user perform search by "ITunes 1st March 2012"
    Then the output should be
      | Artist       | Title                   | Usages           | StartDate    | EndDate |
      | Monkey Claw  | Black Mountain          | digital download | 1st Feb 2012 |         |
      | Monkey Claw  | Motor Mouth             | digital download | 1st Mar 2011 |         |
      | Tinie Tempah | Frisky (Live from SoHo) | digital download | 1st Feb 2012 |         |
      | Tinie Tempah | Miami 2 Ibiza           | digital download | 1st Feb 2012 |         |

  Scenario: Search for active music contracts_2
    Given the supplied reference data
    When user perform search by "YouTube 27th Dec 2012"
    Then the output should be
      | Artist       | Title                   | Usages    | StartDate     | EndDate       |
      | Monkey Claw  | Christmas Special       | streaming | 25th Dec 2012 | 31st Dec 2012 |
      | Monkey Claw  | Iron Horse              | streaming | 1st June 2012 |               |
      | Monkey Claw  | Motor Mouth             | streaming | 1st Mar 2011  |               |
      | Tinie Tempah | Frisky (Live from SoHo) | streaming | 1st Feb 2012  |               |

  Scenario: Search for active music contracts_3
    Given the supplied reference data
    When user perform search by "YouTube 1st April 2012"
    Then the output should be
      | Artist       | Title                   | Usages    | StartDate    | EndDate |
      | Monkey Claw  | Motor Mouth             | streaming | 1st Mar 2011 |         |
      | Tinie Tempah | Frisky (Live from SoHo) | streaming | 1st Feb 2012 |         |