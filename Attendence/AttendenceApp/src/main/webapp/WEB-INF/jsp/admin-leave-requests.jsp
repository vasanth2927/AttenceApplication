<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leave Requests</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .sidebar {
            width: 200px;
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            background-color: #f8f9fa;
            padding-top: 20px;
        }
        .sidebar a {
            display: block;
            color: black;
            padding: 16px;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #ddd;
        }
        .container {
            margin-left: 220px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h2 class="text-center">Inovace Technologies</h2>
    <a href="#">Dashboard</a>
    <a href="/api/employeedetails">Employees Details</a>
    <a href="/api/attendance-report?date=<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">Attendance Report</a>
    <a href="/admin/leaves">Leave Report <span class="badge badge-warning">
    <%= (request.getAttribute("pendingLeaveCount") != null ? request.getAttribute("pendingLeaveCount") : 0) %>
    </span></a>
    <a href="/shift/admin">Shift Management</a>
    <a href="/api/logout">Logout</a>
</div>

<div class="container mt-4">
    <h2>Leave Requests</h2>

    <table>
        <thead>
            <tr>
                <th>Employee Name</th>
                <th>Leave Type</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="leave" items="${leaves}">
                <tr>
                    <td>${leave.employeeName}</td>
                    <td>${leave.leaveType}</td>
                    <td>${leave.startDate}</td>
                    <td>${leave.endDate}</td>
                    <td>${leave.reason}</td>
                    <td>${leave.status}</td>
                    <td>
                        <form action="/admin/leave/${leave.id}/update" method="post" style="display:inline;">
                            <input type="hidden" name="status" value="Approved">
                            <button type="submit" class="btn btn-success">Approve</button>
                        </form>
                        <form action="/admin/leave/${leave.id}/update" method="post" style="display:inline;">
                            <input type="hidden" name="status" value="Rejected">
                            <button type="submit" class="btn btn-danger">Reject</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
