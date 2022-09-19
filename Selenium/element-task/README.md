# Element Task

## Task Goal

* Work with the following elements with selenium
  * Check boxes
  * Tables
  * Radio buttons 
  * drop down 

## Demo site to used
Address book demo site will be used, this site is so simple you can create, view, edit and delete addresses.

List of addresses shown in a table with links for operations. [Website Link](http://a.testaddressbook.com/sign_in)


## Automation scenario
### Create
* Login 
* Click on Addresses Menu
* Get number of saved addresses 
* Click on New Address link
* Fill address details with unique data 
* Click on create Address button 
* Verify that the shown data similar to filled data 
* Click on list link 
* Get number of saved addresses and make sure that it’s equals to the number appears in step #3 + 1
* Redo steps (3 - 9) 3 or 4 times to get more data in the table 

### View 
* Login 
* Click on Addresses Menu
* Get number of saved addresses
* Click on Show link for each row and verify that the shown data related to the selected row 
* Click on List link to go back to the list 

### Edit
* Login 
* Click on Addresses Menu
* Get number of saved addresses
* Click on Edit link for one of the rows and verify that the shown data related to the selected row 
* Change one or more value 
* Click Update Address Button 
* Verify that the changed data shown 
* Click on List link to go back to the list 
* Verify that the changed data changed in the table

### Delete
* Login 
* Click on Addresses Menu
* Get number of saved addresses
* Click on Delete link for each row and verify that the shown data related to the selected row 
* Click on List link to go back to the list 
