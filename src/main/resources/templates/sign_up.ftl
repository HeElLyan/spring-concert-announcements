<#ftl encoding='UTF-8'>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Sign up</title>
    <link href="../../css/style.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script>(function(e,t,n){var r=e.querySelectorAll("html")[0];r.className=r.className.replace(/(^|\s)no-js(\s|$)/,"$1js$2")})(document,window,0);</script>
</head>

<style>
    .error {
        color: #ff0000;
    }
</style>

<script>
    function trySubmit() {
        let name = $("#name")[0]['value']
        let login = $("#login")[0]['value']
        let email = $("#email")[0]['value']
        let password1 = $("#password1")[0]['value']
        let password2 = $("#password2")[0]['value']

        let errors = []

        if (!name) {
            errors.push("Empty name")
        }

        if (!login) {
            errors.push("Empty username")
        }

        if (!email) {
            errors.push("Empty email")
        }

        if (!password1) {
            errors.push("Empty password")
        } else if (password1.length < 3 || password1.length > 10) {
            errors.push("password length must be greater than 3 and less than 10")
        }

        if (!password2) {
            errors.push("Empty password")
        } else if (password2.length < 3 || password2.length > 10) {
            errors.push("password length must be greater than 3 and less than 10")
        }

        if (errors.length !== 0) {
            alert(errors.reduce((acc, val) => acc + "\n" + val))
        } else {
            $("#d").first().submit()
        }

    }
</script>
<body>

    <div class="navigation">
        <ul>
            <li><a href="/login">Sign in</a></li>
            <li><a href="/signUp" class="sign_up">Sign Up</a></li>
        </ul>
    </div>

    <div class="sign_up">
        <form class="form-signup" role="form" enctype="multipart/form-data" action="/signUp" method="post">
            <h2>Sign up</h2>
<#--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->

            <input type="text" class="form-control" placeholder="Name" name="name">
            <input type="text"  class="form-control" placeholder="Username" name="login">
            <input type="password" class="form-control" placeholder="Password" name="password1">
            <input type="password" class="form-control" placeholder="Confirm password" name="password2">
            <input type="text" class="form-control" placeholder="email" name="email">
            <label for="metalGenre" class="form-control">Choose the metal genre
                <select name="metalGenre">
                    <#list model.enumForMetalGenres as metalGenre>
                        <option value=${metalGenre.value}>${metalGenre.value}</option>
                    </#list>
                </select>
            </label>
            <#if error??>
                <div class="alert alert-danger" role="alert">${error}</div>
            </#if>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
<#--            <button value="sign up" onclick="trySubmit()">Sign Up</button>-->
            <input type="button" onclick="trySubmit()" value="signUp">
        </form>
    </div>

<#--    <div class="sign_up">-->
<#--        <@spring.bind "userRegistrationForm"/>-->
<#--        <form class="form-signup" role="form" enctype="multipart/form-data" action="/signUp" method="post">-->
<#--            &lt;#&ndash;        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">&ndash;&gt;-->
<#--            Name:-->
<#--            <@spring.formInput "userRegistrationForm.name"/>-->
<#--            <@spring.showErrors "<br>","error"/>-->
<#--            <br>-->
<#--            Username:-->
<#--            <@spring.formInput "userRegistrationForm.username"/>-->
<#--            <@spring.showErrors "<br>","error"/>-->
<#--            <br>-->
<#--            Password1:-->
<#--            <@spring.formInput "userRegistrationForm.password1"/>-->
<#--            <@spring.showErrors "<br>","error"/>-->
<#--            <br>-->
<#--            Password2:-->
<#--            <@spring.formInput "userRegistrationForm.password2"/>-->
<#--            <@spring.showErrors "<br>","error"/>-->
<#--            <br>-->
<#--            Email:-->
<#--            <@spring.formInput "userRegistrationForm.email"/>-->
<#--            <@spring.showErrors "<br>"/>-->
<#--    -->
<#--            <br>-->
<#--            City:-->
<#--            <@spring.formInput "userRegistrationForm.city"/>-->
<#--            <@spring.showErrors "<br>","error"/>-->
<#--    -->
<#--            <label for="metalGenre" class="form-control">Choose the metal genre-->
<#--                <select name="metalGenre">-->
<#--                    <#list model.enumForMetalGenres as metalGenre>-->
<#--                        <option value=${metalGenre.value}>${metalGenre.value}</option>-->
<#--                    </#list>-->
<#--                </select>-->
<#--            </label>-->
<#--            <br>-->
<#--            <button class="btn btn-lg btn-primary btn-block" type="submit">Become a true metallist</button>&ndash;&gt;-->
<#--        </form>-->
<#--    </div>-->

</body>
</html>