@all
Feature: Adding item to the table

  @success
  Scenario Outline: Successfully adding element to table
  Given page opened "Главная" with name "QualIT"
    * element visible "Главная"
    * element visible "Песочница"
    * click on "Песочница"
    * element visible "Товары"
    * click on "Товары"
    * page opened "Товары" with name "Список товаров"
    * element visible "Таблица"
    * element visible "Добавить"
    * click on "Добавить"
    * element visible "Модальное окно"
    * following fields visible
    | Наименование |
    | Тип |
    | Чекбокс |
    * element visible "Сохранить"
    * fill in the fields
    | field | value |
    | name | <string> |
    | foodType | <type> |
    | exotic | <checkbox> |
    * field values
    | field | value |
    | name | <string> |
    | foodType | <type> |
    | exotic | <checkbox> |
    * click on "Сохранить"
    * element visible "Товары"
    * last row contained in table
    | <string> |
    | <type>   |
    | <checkbox> |
    * goods table equal to bd
    * element visible "Песочница"
    * click on "Песочница"
    * element visible "Сбросить"
    * click on "Сбросить"
    * element visible "Таблица"
    * goods table equal to bd

    Examples:
    | string | type | checkbox |
    | !Яблоко1 | VEGETABLE | false |
    | ^_^      | FRUIT     | false  |

