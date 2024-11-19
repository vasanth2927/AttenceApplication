<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Payroll Details</title>
</head>
<body>
    <h1>Payroll Details for Employee: ${employee.username}</h1>
    
    <table border="1">
        <tr>
            <th>Payroll Date</th>
            <th>Total Salary</th>
            <th>Deductions</th>
            <th>Net Salary</th>
        </tr>
        <tr>
            <td>${payroll.payrollDate}</td>
            <td>${payroll.totalSalary}</td>
            <td>${payroll.deductions}</td>
            <td>${payroll.netSalary}</td>
        </tr>
    </table>

    <!-- Add a button to download the payroll as a PDF -->
    <form action="<c:url value='/payroll/download/${payroll.employee.employeeId}' />" method="get">
        <button type="submit">Download Payroll PDF</button>
    </form>
</body>
</html>

