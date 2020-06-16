<!DOCTYPE html>
<#--<#import "spring.ftl" as spring />-->
<html>
<head>
    <meta charset="utf-8">
    <title>Create topic</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link href="../../css/style.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script>(function(e,t,n){var r=e.querySelectorAll("html")[0];r.className=r.className.replace(/(^|\s)no-js(\s|$)/,"$1js$2")})(document,window,0);</script>

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
    <form class="form-createtopic" role="form" method="post" action="/superuser/save_hq">
        <h2>Creating a quarter</h2>
        <input type="text" name="city" class="form-control" placeholder="City">
        <input type="text" name="description" class="form-control" placeholder="Description">

        <#if error??>
            <div class="alert alert-danger" role="alert">${error}</div>
        </#if>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Create head quarter</button>
    </form>
</div>

<#--<div class="create_topic">-->
<#--    <@spring.bind "concertForm"/>-->
<#--    <form class="form-createtopic" role="form" action="/superuser/save_hq" method="post">-->
<#--        <h2>Creating a quarter</h2>-->
<#--&lt;#&ndash;        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">&ndash;&gt;-->
<#--        <label for="username" class="form-control">City</label>-->
<#--        <@spring.formInput "concertForm.city"/>-->
<#--        <@spring.showErrors "<br>", "error"/>-->
<#--        <br><br>-->
<#--        <label for="username" class="form-control">Description</label>-->
<#--        <@spring.formInput "concertForm.description"/>-->
<#--        <@spring.showErrors "<br>","error"/>-->
<#--        <button class="btn btn-lg btn-primary btn-block" type="submit">Create head quarter</button>-->
<#--    </form>-->
<#--</div>-->

</body>
</html>