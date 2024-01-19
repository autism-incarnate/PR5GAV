@all
@REST

  Feature: API testing

    Scenario Template: Adding item using API
      Given get returns table
      Then add using API item:
        |field|value|
        |name |<fName>|
        |type |<fType>|
        |exotic|<fExotic>|
      Given get returns table
      Then compare table to DB
      Given table contains:
        |field|value|
        |name |<fName>|
        |type |<fType>|
        |exotic|<fExotic>|
      Then reset table using API

      Examples:
        |fName|fType|fExotic|
        |!Яблоко1|FRUIT|false|
        |^_^     |VEGETABLE|true|