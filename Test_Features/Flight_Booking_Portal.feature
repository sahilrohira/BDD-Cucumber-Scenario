Feature: Flight booking Scenario

  Background: 
    Given Open Browser
    And Go to URL after browser is open

	@run
  Scenario Outline: flight_Booking_Scenario_TC001#Validate Flight Booking Scenario
    And From "Home" Page, Click On "Flights" Label with object id as "tab-flight-tab-hp"
    And Enter Text Field "flight-origin-hp-flight" Value As "<Source>"
    And Enter Text Field "flight-destination-hp-flight" Value As "<Destination>"
    And Click On Span Text "One way" Button
    And Enter Text Field "flight-departing-single-hp-flight" Value As "<DepartingDate>"
    And Select "flight-adults-hp-flight" Value As "2"
    And Wait For "1" Second
    And Select "flight-children-hp-flight" Value As "0"
    And Click On Span Text "Search" Button
    And Select "sortDropdown" Value As "Duration (Shortest)"
    And For page "Flights" Click On "Departure-Evening" Label CheckBox having object "departure-times-Evening-flights-checkbox"
    And For page "Flights" click On "select-button" button
    And Wait For "1" Second
    And Switch To Original Window
    And Click On Link with text contains "No thanks"
   
   Examples: -
   |Source|Destination|DepartingDate|
   |Delhi (DEL-Indira Gandhi Intl.)|Mumbai (BOM-Chhatrapati Shivaji Intl.)|10/10/2020|
   |Mumbai (BOM-Chhatrapati Shivaji Intl.)|Delhi (DEL-Indira Gandhi Intl.)|10/05/2020|
