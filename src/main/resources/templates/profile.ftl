<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Profile</title>
    <link href="../../css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="navigation">
        <ul>
            <#if model.admin>
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

    <div class="profile">
        <#if model.user.photo?has_content>
            <img src="../../images/${model.user.photo.path}" alt="..." class="img-rounded" width="auto" height="300">
    <#--    <#else >-->
    <#--        ${model.user.photo.path}-->
    <#--        <img src="../../images/alissa.jpeg" alt="..." class="img-rounded" width="auto" height="300">-->
            <#else>
                <form class="form-createtopic" role="form" method="post" action="/upload" enctype="multipart/form-data">
                    <input type="file" class="form-control" name="file">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Add a metal photo</button>
                </form>
        </#if>
        <blockquote>

            <h4>${model.user.login}</h4>
            <#if model.user.name?has_content>
                <p>${model.user.name}</p>
            </#if>
            <#if model.user.headQuarter?has_content>
                <p>${model.user.headQuarter.city}</p>
            </#if>
            <#if model.user.metalGenre?has_content>
                <p>${model.user.metalGenre}</p>
            </#if>

            <p>Last time been here ${model.time}!</p>
            <p>Users seen your page ${model.enterTimes}!</p>
<#--            <p>User-Agent header: ${model.header}</p>-->
            <p>User seen u: ${model.messagesNumbers}</p>
            <#--<#if person.instLink?has_content>-->
            <#--<a href="${person.instLink}">Instagram</a>-->
            <#--</#if>-->
            <#--<#if person.twitLink?has_content>-->
            <#--<a href="${person.twitLink}">Twitter</a>-->
            <#--</#if>-->
            <#--<#if person.faceLink?has_content>-->
            <#--<a href="${person.faceLink}">Facebook</a>-->
        </blockquote>

<#--        <form action="/edit_person/${model.user.id}" method="get">-->
<#--            <button class="comment" type="submit">Edit</button>-->
<#--        </form>-->
    </div>
</body>
</html>