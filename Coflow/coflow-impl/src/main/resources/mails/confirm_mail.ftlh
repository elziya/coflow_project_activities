<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        div {
            text-align: center;
            align-items: center;
        }
        h2 {
            color: #444485;
        }
        #main-text {
            border: 1px solid #d0d0d0;
            font-size: medium;
            padding: 2vh 3vh;
            text-align: center;
        }
    </style>
</head>
<body>
<h2></h2>
<div class="message">
    <h2><strong>Email Confirmation</strong></h2>
    <div>
        <h4 id="main-text">
            ${username}
            <br>
            Your account verification code: ${confirm_code}
            <br>
            If you didn't request this code, then just skip this message
        </h4>
    </div>
</div>
</body>
</html>

