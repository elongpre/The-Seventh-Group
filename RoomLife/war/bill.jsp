<!DOCTYPE html>
<html>
    <h3>Welcome to Roommate</h3>
    <p>Input your bill info via the two options</p>
    
    <b> Option 1: Split cost evenly</b>
    <form action="BillEntry" method="POST">
        <input type="text" name="billName" value="Bill name"><br>
        <input type="text" name="billAmount" value="Amount"><br>
        <input type="submit" value="Submit">
    </form>
    
    <br>
    <br>
    <b>Option 2: Charge one person independently</b>
    <form action="DebtEntry" method= "POST">
        <input type="text" name="debtName" value="Bill name"><br>
        <select name="roommate">
            <option value="volvo">John</option>
            <option value="saab">Peter</option>
            <option value="fiat">Bob</option>
            <option value="audi">Audi</option>
        </select>
        <input type="text" name="debtAmount" value="Amount"><br>
        <input type="submit" value="submit">
    </form>
    
</html>