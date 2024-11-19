
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #9b59b6, #3498db);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 320px;
            max-width: 90%;
        }
        .container img {
            width: 80px;
            margin-bottom: 20px;
        }
        .container h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        .container input[type="text"],
        .container input[type="password"],
        .container select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        .container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #3498db;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }
        .container input[type="submit"]:hover {
            background-color: #2980b9;
        }
        .error-message {
            color: red;
            font-size: 14px;
            margin-bottom: 10px;
        }
    </style>
    <script>
        function handleLogin(event) {
            event.preventDefault(); // Prevent form submission
            
            const role = document.querySelector('select[name="role"]').value;
            const username = document.querySelector('input[name="username"]').value;
            const password = document.querySelector('input[name="password"]').value;

            const loginRequest = {
                role: role,
                username: username,
                password: password
            };

            fetch('<c:url value="/api/login" />', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(loginRequest)
            })
            .then(response => response.text())
            .then(data => {
                document.open();
                document.write(data);
                document.close();
            })
            .catch(error => {
                document.querySelector('.error-message').textContent = 'An error occurred. Please try again.';
                console.error('Error:', error);
            });
        }
    </script>
</head>
<body>
    <div class="container">
        <img src="<c:url value='/images/logo.png' />" alt="Logo">
        <h2>Login</h2>
        <!-- Error message display -->
        <div class="error-message"></div>
        <form onsubmit="handleLogin(event)">
            <select name="role" required>
                <option value="" disabled selected>Select Role</option>
                <option value="Employee">Employee</option>
                <option value="Admin">Admin</option>
            </select>
            <input type="text" name="username" placeholder="Enter Username" required>
            <input type="password" name="password" placeholder="Enter Password" required>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>
