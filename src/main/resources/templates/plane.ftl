<#ftl encoding='UTF-8'>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>News</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>

<div class="navigation">
    <ul>
        <#if model.admin>
            <li><a href="/admin/users">Administration</a></li>
        </#if>
        <li><a href="/user/home">News</a></li>
        <li><a href="/chat">Chat</a></li>
        <li><a href="/plane" class="form">About us</a></li>
        <li><a href="/user/hq">Concerts</a></li>
        <li><a href="/user/profile">Profile</a></li>
        <li><a href="/logout">Out</a></li>
    </ul>
</div>

<div class="news">
    <#list model.news as new>
        <div class="card mb-3">
            <div class="card-body">
                <h1 class="card-text">${new.text}</h1>
                <#if new.photo.path?has_content>
                    <img src="../../images/${new.photo.path}" alt="..." class="img-rounded" width="auto" height="300">
<#--                <#else >-->
<#--                    ${new.photo.path}-->
<#--                    <img src="../../images/alissa.jpeg" alt="..." class="img-rounded" width="auto" height="300">-->
                </#if>
            </div>
        </div>
    </#list>
</div>
</body>
</html>