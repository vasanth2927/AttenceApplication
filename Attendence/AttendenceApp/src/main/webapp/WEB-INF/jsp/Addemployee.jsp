<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.AttendenceApp.Entity.Employee" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en"> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #1C2331;
            padding-top: 20px;
            color: white;
        }
        .sidebar a {
            padding: 10px 15px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            display: block;
        }
        .sidebar a:hover {
            background-color: #575D6E;
            color: white;
        }
        .content {
            margin-left: 260px;
            padding: 20px;
        }
        .camera {
            text-align: center;
            margin-top: 20px;
        }
        .camera video {
            border: 2px solid black;
        }
        .captured-photo {
            margin-top: 20px;
            text-align: center;
        }
        #photoPreview {
            width: 200px;
            height: 200px;
            border-radius: 50%;
            border: 2px solid black;
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

<div class="content">
    <h3 class="text-center">Add Employee</h3>

    <form action="/api/addemployeedata" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="role">Role:</label>
            <input type="text" id="role" name="role" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="departmentId">Department:</label>
            <select id="departmentId" name="departmentId" class="form-control" required>
                <option value="1">Human Resource</option>
                <option value="2">Developer</option>
                <option value="3">Finance</option>
            </select>
        </div>

        <!-- Camera section -->
        <div class="camera">
            <video id="video" width="400" height="300" autoplay></video>
            <br>
            <button type="button" id="captureBtn" class="btn btn-primary">Capture Photo</button>
        </div>

        <!-- Display captured photo -->
        <div class="captured-photo">
            <h5>Captured Photo:</h5>
            <img id="photoPreview" src="#" alt="Captured Photo" />
        </div>

        <!-- Hidden input to store the Base64 photo -->
        <input type="hidden" name="biometricData" id="biometricData">

        <button type="submit" class="btn btn-primary">Add Employee</button>
    </form>
</div>

<script>
    // Access the webcam and capture photo
    const video = document.getElementById('video');
    const captureBtn = document.getElementById('captureBtn');
    const photoPreview = document.getElementById('photoPreview');
    const biometricData = document.getElementById('biometricData');

    // Start the camera
    navigator.mediaDevices.getUserMedia({ video: true })
        .then(stream => {
            video.srcObject = stream;
        })
        .catch(err => {
            console.error("Error accessing the camera: " + err);
        });

    // Capture photo and display it
    captureBtn.addEventListener('click', function() {
        const canvas = document.createElement('canvas');
        canvas.width = 400;
        canvas.height = 300;
        const context = canvas.getContext('2d');
        context.drawImage(video, 0, 0, 400, 300);

        // Get the Base64 encoded image data
        let photoData = canvas.toDataURL('image/png');

        // Remove the 'data:image/png;base64,' prefix
        photoData = photoData.replace(/^data:image\/(png|jpg);base64,/, "");

        // Display the photo
        photoPreview.src = 'data:image/png;base64,' + photoData;

        // Store the image data in the hidden field
        biometricData.value = photoData;

        // Stop the camera
        video.srcObject.getTracks().forEach(track => track.stop());
    });
</script>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
