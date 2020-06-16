<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Create topic</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link href="../../css/style.css" rel="stylesheet">
</head>
<body>

<div class="navigation">
    <ul>
        <li><a href="/user/home">News</a></li>
        <li><a href="/chat">Chat</a></li>
        <li><a href="/plane">About us</a></li>
        <li><a href="/user/hq">Concerts</a></li>
        <li><a href="/user/profile">Profile</a></li>
        <li><a href="/logout">Out</a></li>
    </ul>
</div>

<div class="create_topic">
    <form class="form-createtopic" role="form" method="post" action="/superuser/upload" enctype="multipart/form-data">
        <h2>Creating news</h2>
        <input type="text" name="title" class="form-control" placeholder="title" required autofocus>
        <input type="text" name="description" class="form-control" placeholder="description" required autofocus>
        <input type="file" class="form-control" name="file">

        <button class="btn btn-lg btn-primary btn-block" type="submit">Create a metal new</button>
    </form>
</div>

</body>
</html>