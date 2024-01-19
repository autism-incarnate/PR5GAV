@all
@DB

  Feature: Add to DB

    @success
    Scenario: Add straight to DB and compare
      * save current DB state
      * add "Банан", FRUIT, false to DB
      * compare current DB state to old DB state