Feature: Page Loading
	People interested in Schantz 
	should be able to find us on Google

  Scenario: Schantz Google search
   	Given I am a person interested in Schantz
    When I google for Schantz
    Then I should see Schantz byline
   