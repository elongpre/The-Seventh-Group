<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="RoomLife.css">
	</head>
	
	<body>
	    <h1>Submit Bills and Debts</h1>
	    
	    <form action="BillEntry" method="POST">
	    	<fieldset>
	    		<legend>New Bill (split evenly)</legend>
	    		
	    		<label class="field" for="Name">Bill Name:</label>
		        <p><input type="text" name="billName"></p>
		        <label class="field" for="Amount">Amount:</label>
		        <p><input type="text" name="billAmount"></p>
		        <input type="submit" value="Submit">
	        </fieldset>
	    </form>
	    <br>
	    <br>
	    
	    <form action="DebtEntry" method= "POST">
	    	<fieldset>
	    		<legend>New Debt (charge a single roommate)</legend>
	    		
		    	<label class="field" for="Name">Bill Name:</label>
			    <p><input type="text" name="billName"></p>
			    <label class="field" for="RoommateName">Roommate Name:</label>
			    <p><input type="text" name="roommate"></p>
			    <label class="field" for="Amount">Amount:</label>
			    <p><input type="text" name="debtAmount"></p>
		        <input type="submit" value="Submit">
	        </fieldset>
	    </form>
    </body>
    
</html>