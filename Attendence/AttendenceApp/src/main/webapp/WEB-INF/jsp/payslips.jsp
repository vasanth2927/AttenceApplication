<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payroll Processing</title>
</head>
<body>
    <h1>Process Payroll</h1>
    <form action="/payroll/process" method="post">
        <label for="payrollMonth">Payroll Month:</label>
        <input type="month" id="payrollMonth" name="payrollMonth" required>
        <br><br>
        <input type="submit" value="Process Payroll">
    </form>
</body>
</html>
