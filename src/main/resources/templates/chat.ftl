<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Chat</title>
        <link href="../../css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <script
                src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
                crossorigin="anonymous"></script>
        <script src="../../js/chat.js"></script>
    </head>

    <div class="navigation">
        <ul>
            <#if admin>
                <li><a href="/admin/users">Administration</a></li>
            </#if>
            <li><a href="/user/home">News</a></li>
            <li><a href="/chat">Chat</a></li>
            <li><a href="/plane">About us</a></li>
            <li><a href="/user/hq">Concerts</a></li>
            <li><a href="/user/profile">Profile</a></li>
            <li><a href="/logout">Out</a></li>
        </ul>
    </div>

    <body onload="sendMessage('${pageId}', 'New user')">
        <h1>Welcome to a metal chat!</h1>
        <div>
            <input id="message" placeholder="Your message">
            <button onclick="sendMessage('${pageId}',
                    $('#message').val())">Отправить</button>
        </div>

        <div>
            <ul id="messages">

            </ul>
        </div>
    </body>
</html>