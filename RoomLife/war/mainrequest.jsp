<!DOCTYPE html>
<html>
	<head>
		<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Oswald|Titillium+Web:600' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="RoomLife.css">
	</head>
	
	<body>
	    <h1>Submit Maintenance Request</h1>
	    
	    <form action="mreq" method="POST">
	    	<fieldset>
	    		<legend>Description of Issue</legend>
	    		
	    		<label class="field" for="Name">Select Category:</label>
	    		<p><select name="name">
				  <option value="plumbing">Plumbing</option>
				  <option value="electricity">Electricity</option>
				  <option value="pest control">Pest Control</option>
				</select></p>

		        <label class="field" for="Priority">Priority:</label>
		        <p><input type="text" name="priority"></p>
		        
		        <label class="field" for="Place">Place:</label>
		        <p><input type="text" name="place"></p>
		        
		        <label class="field" for="Group">Group:</label>
		        <p><input type="text" name="group"></p>
		        
		        <label class="field" for="Description">Description:</label>
		        <p><textarea rows="10" cols="50" name="description"></textarea></p>
		        
		        <input type="submit" value="Submit">
	        </fieldset>
	    </form>
	    
    </body>
    
</html>