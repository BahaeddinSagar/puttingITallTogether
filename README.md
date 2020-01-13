This porject aims at making an almost realistic application where we fetch data, handle the response and display a list view with the output.
we also made onItemClickListeners where we redirect the user to a more detailed activity.

the steps to make a ListView are as follows:
1- make the main view with one listview inside it.

2- make a new layout file for the item layout

3- make a new class that represents your data

4- make an array adapter that will be responsible for displaying the data into items inside the listview 
  - the arrayadapter must extend "ArrayAdapter" and should use the "new class" as a type between the <>
  - the arrayadapter MUST contain the getview method that is responsible for displaying the data

5- in the main activity make an HTTP request to get the string response ( or use the JSONobjectrequest instead) then we used the parseresponse function to change the string into an arraylist of objects

6- using the newly created arraylist and the item layout file we make a new instance of our newly created arrayadapter

7- we attach the adapter to the listview 

at this point, the list view is visible and can be seen with the user, to make it interactive we add the onItemSelectListener
