<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>News</title>
    <link href="../../css/style.css" rel="stylesheet"/>
    <link href="<@spring.url '/css/style.css'/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<#--<link rel="stylesheet" type="text/css" href="<@spring.url '../css/style.css'/>"/>-->

</head>
<body>

<div class="navigation">
    <ul>
        <#--<#if is_admin == 1>-->
        <#--<li><a href="/admin">Administration</a></li>-->
        <#--</#if>-->
        <#if model.admin>
            <li><a href="/admin/users" class="form">Administration</a></li>
        </#if>
        <li><a href="/user/home">News</a></li>
        <li><a href="/chat">Chat</a></li>
        <li><a href="/plane">About us</a></li>
        <li><a href="/user/hq">Concerts</a></li>
        <li><a href="/user/profile">Profile</a></li>
        <li><a href="/logout">Out</a></li>
        <#--<#else >-->
        <#--<li><a href="/login">Sign in</a></li>-->
        <#--</#if>-->
    </ul>
</div>

<table>
    <tr>
        <th>ID</th>
        <th>login</th>
        <th>password</th>
        <th>role</th>
        <th>delete</th>
    </tr>
<#list model.users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td><a href="/admin/password/temp/${user.id}">Send</a></td>
        <td><a href="/admin/make_super/${user.id}"${user.role}>Make super</a></td>
        <td><a href="/admin/kill/${user.id}">Kill</a></td>
    </tr>
</#list>
</table>
</body>