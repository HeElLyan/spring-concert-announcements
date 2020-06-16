<#ftl encoding='UTF-8'>

<!DOCTYPE html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Sign in</title>
    <link href="../../css/style.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script>(function(e,t,n){var r=e.querySelectorAll("html")[0];r.className=r.className.replace(/(^|\s)no-js(\s|$)/,"$1js$2")})(document,window,0);</script>
</head>

<body>

    <div class="navigation">
        <ul>
            <li><a href="/login" class="sign_in">Sign in</a></li>
            <li><a href="/signUp">Sign up</a></li>
        </ul>
    </div>


    <div class="container">
        <form class="form-signin" role="form" enctype="multipart/form-data" method="post" action="/login" >
            <h2>Sign in</h2>
<#--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
            <input type="text" class="form-control" name="login" placeholder="username">
            <input type="password" class="form-control" name="password" placeholder="password">
            <label for="remember-me">
                <input type="checkbox" id="remember-me" name="remember-me">Remember me
            </label>
            <br>
            <#if error??>
                <div class="alert alert-danger" role="alert">${error}</div>
            </#if>

            <#if error1??>
                <div class="alert alert-danger" role="alert">Неверно введены данные</div>
            </#if>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign In</button>
        </form>
    </div>

<#--        <div class="sign_up">-->
<#--            <@spring.bind "userAuthForm"/>-->
<#--            <form class="form-signup" role="form" enctype="multipart/form-data" action="/signUp" method="post">-->
<#--                &lt;#&ndash;        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">&ndash;&gt;-->
<#--                <label for="login" class="form-control">Username</label>-->
<#--                <@spring.formInput "userAuthForm.login"/>-->
<#--                <@spring.showErrors "<br>","error"/>-->
<#--                <br>-->
<#--                <label for="password" class="form-control">Password</label>-->
<#--                <@spring.formInput "userAuthForm.password"/>-->
<#--                <@spring.showErrors "<br>","error"/>-->
<#--                <br>-->
<#--                <button class="btn btn-lg btn-primary btn-block" type="submit">Be a true metallist</button>-->
<#--            </form>-->
<#--        </div>-->
</body>
</html>