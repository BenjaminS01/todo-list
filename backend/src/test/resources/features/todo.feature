Feature: TODO Management

  Scenario: Create a new TODO entry
    When I send a POST request with body:
      """json
      {
        "description": "Ölwechsel",
        "completionDate": "20.06.2026"
      }
      """
    Then I receive status 201