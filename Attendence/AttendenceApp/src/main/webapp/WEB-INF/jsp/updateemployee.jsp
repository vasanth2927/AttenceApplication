<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f1f1;
        }
        .form-container {
            max-width: 700px;
            margin: auto;
            padding: 30px;
            border-radius: 10px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border: 1px solid #d1d1d1;
        }
        .form-header {
            background-color: #007bff;
            color: #ffffff;
            padding: 15px;
            border-radius: 10px 10px 0 0;
            text-align: center;
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
        .form-control, .form-select {
            border-radius: 0.375rem;
            border: 1px solid #ced4da;
        }
        .form-control:focus, .form-select:focus {
            border-color: #007bff;
            box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
        }
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

<div class="container mt-5">
    <div class="form-container">
        <div class="form-header">
            <h2>Update Employee</h2>
        </div>

        <form action="/api/updataadd" method="post">
            <input type="hidden" id="employeeId" name="employeeId" value="${employee.employeeId}">

            <div class="mb-3">
                <label for="username" class="form-label">Username:</label>
                <input type="text" id="username" name="username" class="form-control" value="${employee.username}" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" id="email" name="email" class="form-control" value="${employee.email}" required>
            </div>

            <div class="mb-3">
                <label for="phone" class="form-label">Phone:</label>
                <input type="text" id="phone" name="phone" class="form-control" value="${employee.phone}" required pattern="\d{10}">
            </div>

            <div class="mb-3">
                <label for="role" class="form-label">Role:</label>
                <input type="text" id="role" name="role" class="form-control" value="${employee.role}" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Leave empty to keep current password" autocomplete="off">
            </div>

            <div class="mb-3">
                <label for="departmentId" class="form-label">Department:</label>
                <select id="departmentId" name="departmentId" class="form-select" required>
                    <option value="1" ${employee.department.departmentId == 1 ? 'selected' : ''}>Human Resource</option>
                    <option value="2" ${employee.department.departmentId == 2 ? 'selected' : ''}>Developer</option>
                    <option value="3" ${employee.department.departmentId == 3 ? 'selected' : ''}>Finance</option>
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

            <button type="submit" class="btn btn-primary mt-3">Update Employee</button>
        </form>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success mt-3">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger mt-3">${errorMessage}</div>
        </c:if>
    </div>
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

        // Get the Base64 encoded image data (remove 'data:image/png;base64,' part)
        let photoData = canvas.toDataURL('image/png');
        photoData = photoData.replace(/^data:image\/(png|jpg);base64,/, '');

        photoPreview.src = 'data:image/png;base64,' + photoData;

        // Store the image data in the hidden field without 'data:image/png;base64,'
        biometricData.value = photoData;

        // Stop the camera
        video.srcObject.getTracks().forEach(track => track.stop());
    });
</script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
