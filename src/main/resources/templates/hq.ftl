<!doctype html>
<html lang="">
<head>
    <meta charset="UTF-8">
    <link href="../../css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <title>Head Quarter</title>
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
<#--forum_search-->

    <div class="forum">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <div class="input-group">
                    <form method="get" action="/user/hq/${model.hq.id}">
                        <input type="text" id="s_id" name="search" class="form-control"  placeholder="" aria-label="Search">
                        <button class="btn btn-secondary" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class="topic">
        <blockquote>
            <div class="forum_search">
                <h2>${model.hq.city} @house</h2>
            </div>
        </blockquote>
    </div>


    <#if model.comments?has_content>
        <#list model.comments as c>
            <div class="topic">
                <a href="/profile/${c.user.login}">
                    <#if c.user.photo?has_content>
                        <img src="../../images/${c.user.photo.path}" alt="" class="img-rounded" width="auto" height="150">
                    <#else>
    <#--                    <img src="../../images/0.jpg" alt="" class="img-rounded" width="auto" height="150">-->
                    </#if>
                    <blockquote>
                        <p>${c.description}</p>
                        <#--<p class="dmy">DD/MM/YY</p>-->
                        <#if c.user.role == "SUPERUSER">
                            <footer>superman</cite></footer>
                        <#else>
                            <footer>${c.user.login}</cite></footer>
                        </#if>

                        <#if model.superuser>
                            <div class="delete">
                                <a href="/superuser/delete/${model.hq.id}/comment/${c.id}"><img src="../../images/delete.png" width="25" height="25"></a>
                            </div>
                        </#if>
                    </blockquote>
                </a>
            </div>

        </#list>
    </#if>
<#--
<div class="topic">
    <img src="../../img/get_in.jpg" alt="..." class="img-rounded" width="auto" height="150">

    <blockquote>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
        <p class="dmy">DD/MM/YY</p>
        <footer>Author</cite></footer>
        <div class="delete">
            <a href="#"><img src="../../img/delete.png" width="25" height="25"></a>
        </div>
    </blockquote>
</div>
-->
    <#if model.user?has_content>
        <div class="topic">

            <#if model.user.photo?has_content>
                <img src="../../images/${model.user.photo.path}" alt="" class="img-rounded" width="auto" height="150">
            <#else >
    <#--            <img src="../../images/0.jpg" alt="" class="img-rounded" width="auto" height="150">-->
            </#if>
            <blockquote>
                <div class="topic_form">
                    <form action="/user/hq/comment/${model.hq.id}" method="get">
                        <input type="text" name="comment" class="form-control" placeholder="Comment..." required autofocus>
                        <button class="comment" type="submit">Comment on</button>
                    </form>
                </div>
            </blockquote>

        </div>

    </#if>

</body>
</html>