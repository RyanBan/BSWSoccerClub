
Systems login:<br>
1)accData.csv: accounts data used to login are saved in this file.(format: id,username,password,type)<br>
&nbsp;Examples:<br>
  &nbsp;&nbsp; 1,admin,asdf123,admin <-username:admin;password:asdf123;can be used to login to Admin system;<br>
  &nbsp;&nbsp; 2,coach,binu,coach <-username:coach;password:binu;can be used to login to Coach system;<br>
  
Admin system:<br> 
(After clicking "save" in each screen, the corresponding data changes will be saved to data file)<br>
2) In Admin system main menu, you can choose one of four functions<br>
2.1)players: to display, add, delete, or edit player information.<br> 
Note: 2.1.1)Player's team information cannot be changed here.<br> 
            We have teams management to register or unregister a player to a team.<br>
            Any new player will be assigned "UR" team which means unregistered to any team. <br>
      2.1.2)Player's birthyear and gender cannot be edited;<br>
            Instead you can delete then add a pleayer if you want to modify the birthyear or gender;<br>
            but this means the player will be removed from the current team and become "UR"<br><br>

2.2)teams:  to display member list of each team and register a player to a team or unregister a player from current team.<br>
Note: 2.2.1)First step is to search a player by id<br>
     &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp; 2.2.2)If the player dosn't exist, you can start new search<br>
           2.2.3)If the player is unregistered, now the textfield is locked and the combobox <br>
            give the potential teams choices according to the player's age and gender<br>
           2.2.4)You can choose to register the player to a team or click "clear search" to cancel the registration and start a new search<br>
           2.2.5)Before clicking "clear search", you can not start a new search.<br>
           2.2.6)Also provide the demo function to register or unregister a coach(represented by a coach account.<br><br>
   
2.3)schedules: to display and change the time for matches schedules.<br>

2.4)accounts: to add,delete or edit accounts information.<br>
Note: 2.4.1) The id and usernames are required to be unique
           2.4.2) In "edit" mode, you can only change the password

![Screenshot](login.png)<br><br>
