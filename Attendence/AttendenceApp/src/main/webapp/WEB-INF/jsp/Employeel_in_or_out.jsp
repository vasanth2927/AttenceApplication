<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Attendance</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            display: flex;
            height: 100vh;
            transition: background-color 1s;
            margin: 0;
        }
        #video {
            width: 300px;
            height: 225px;
        }
        .status {
            color: red;
            font-weight: bold;
        }
        .happy {
            background-color: #b2ffb2; /* Light green for success */
        }
        .sad {
            background-color: #ffb2b2; /* Light red for failure */
        }
        #emoji {
            font-size: 50px;
        }
        .sidebar {
            width: 220px;
            padding: 15px;
            background-color: #1C2331;
            color: white;
            position: fixed;
            height: 100%;
            display: flex;
            flex-direction: column;
        }
        .sidebar a, .sidebar button {
            display: block;
            padding: 10px;
            margin: 5px 0;
            color: #fff;
            background-color: #007bff;
            border: none;
            text-align: left;
            text-decoration: none;
            border-radius: 5px;
        }
        .sidebar button {
            cursor: pointer;
        }
        .sidebar a:hover, .sidebar button:hover {
            background-color: #0056b3;
        }
        .content {
            margin-left: 240px; /* Space for sidebar */
            padding: 20px;
            flex: 1;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <a href="#">Dashboard</a>
    <form action="${pageContext.request.contextPath}/photo/scanemployee" method="get">
        <button type="submit">Mark Attendance</button>
    </form>
    <a href="${pageContext.request.contextPath}/attendance/history">View Attendance History</a>
    <a href="${pageContext.request.contextPath}/applyLeave">Apply for Leave</a>
    <a href="${pageContext.request.contextPath}/shift/schedule">Shift Schedule</a>
    <a href="${pageContext.request.contextPath}/api/logout">Logout</a>
</div>

<div class="content">
    <h2>Employee Attendance</h2>
    <video id="video" autoplay></video>
    <div id="status" class="status"></div>
    <div id="emoji"></div>
    <button id="captureBtn" class="btn btn-primary">Capture</button>
    <button id="submitBtn" class="btn btn-success" disabled>Submit</button>

    <script>
        let video = document.getElementById('video');
        let statusDiv = document.getElementById('status');
        let captureBtn = document.getElementById('captureBtn');
        let submitBtn = document.getElementById('submitBtn');
        let emojiDiv = document.getElementById('emoji');
        let capturedPhotoData = '';

        navigator.mediaDevices.getUserMedia({ video: true })
            .then(stream => {
                video.srcObject = stream;
                video.play();
            })
            .catch(err => console.error('Error accessing the camera: ', err));

        captureBtn.addEventListener('click', function() {
            let canvas = document.createElement('canvas');
            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;
            let context = canvas.getContext('2d');
            context.drawImage(video, 0, 0, canvas.width, canvas.height);

            canvas.toBlob(blob => {
                let reader = new FileReader();
                reader.onloadend = function() {
                    capturedPhotoData = reader.result.split(',')[1];
                    statusDiv.textContent = 'Photo captured. You can now submit.';
                    statusDiv.style.color = 'green';
                    submitBtn.disabled = false;  
                };
                reader.readAsDataURL(blob);
            });
        });

        submitBtn.addEventListener('click', function() {
            if (capturedPhotoData) {
                fetch('<%= request.getContextPath() %>/photo/scan-face', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ photo: capturedPhotoData })
                })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        statusDiv.textContent = 'Attendance marked successfully!';
                        document.body.className = 'happy';
                        emojiDiv.innerHTML = '😊'; // Happy emoji
                    } else {
                        statusDiv.textContent = 'Face not recognized. Try again.';
                        document.body.className = 'sad';
                        emojiDiv.innerHTML = '😞'; // Sad emoji
                    }
                })
                .catch(err => {
                    console.error('Error during face scan: ', err);
                    statusDiv.textContent = 'Error during face scan. Please try again.';
                });
            }
        });
    </script>
</div>

</body>
</html>
