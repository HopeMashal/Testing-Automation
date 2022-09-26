# Trello Task

## Test steps 
* Login to trello 
* Create new board 
* In the new board create new card 
* Add name and description 
* Attach file 
* Download the attached file and check it’s content 
* Open the board in 2 tabs in the same time 
* Create new card in the first tab 
* Refresh the second tab and check that the created card appears 
* Use Actions class in your test (you can define your steps)
* Delete one of the cards from one of the tabs and refresh the other tab to verify the deletion 
* Delete the board

## Notes 
* Don’t use sleep, use wait (implicit or explicit) if needed ~ **IGNORE IT** 
* Use CVS file to make it easy to validate 
* Create the file in the test then upload it 
* Create screenshots for the board before and after card creation 
* Create screenshot for the card before and after attach file 
* Add browser name in screenshot name to avoid file override 
* Run the test in the 3 browsers and make sure that it’s ok 
