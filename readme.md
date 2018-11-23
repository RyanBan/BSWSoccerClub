
<b>Systems login:</b><br>
1)accData.csv: accounts data used to login are saved in this file.(format: id,username,password,type)<br>
&nbsp;Examples:<br>
  &nbsp;&nbsp; 1,admin,asdf123,admin <-username:admin;password:asdf123;can be used to login to Admin system;<br>
  &nbsp;&nbsp; 2,coach,binu,coach <-username:coach;password:binu;can be used to login to Coach system;<br>
  
<b>Admin system:</b><br> 
(After clicking "save" in each screen, the corresponding data changes will be saved to data file)<br><br>
    2) In Admin system main menu, you can choose one of four functions<br>
    
2.1)players: to display, add, delete, or edit player information<br> 
1. Player's team information cannot be changed here<br> 
2. We have teams management to register or unregister a player to a team<br>
3. Any new player will be assigned "UR" team which means unregistered to any team<br>
4. Player's birthyear and gender cannot be edited instead you can delete then add a pleayer if you want to modify the birthyear or gender<br>but this means the player will be removed from the current team and become "UR"<br><br>

2.2)teams:  to display member list of each team and register a player to a team or unregister a player from current team<br>
1. First step is to search a player by id<br>
2. If the player is unregistered, now the textfield is locked and the combobox give the potential teams choices according to the player's age and gender<br>
3. Also provide the demo function to register or unregister a coach(represented by a coach account<br><br>

2.3)schedules: to display and change the time for matches schedules<br><br>

2.4)accounts: to add,delete or edit accounts information<br>
1. The id and usernames are required to be unique<br>
2. In "edit" mode, you can only change the password<br><br>

<b>Coach system:</b><br>
 Click user namme and send a email<br>

<b>Login Page</b><br>
![Screenshot](login.png)<br>

<b>Player Page</b><br>
![Screenshot](player.png)<br>

<b>Teams Page</b><br>
![Screenshot](teams.png)<br>

<b>Schedule Page</b><br>
![Screenshot](schedule.png)<br>

<b>Account Page</b><br>
![Screenshot](account.png)<br>

<b>Coach Page_email</b><br>
![Screenshot](email.png)<br>

<b>Success_email</b><br>
![Screenshot](success.png)<br>
