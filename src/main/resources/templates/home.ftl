<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>News</title>
    <link href="<@spring.url '/css/style.css'/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <#--<link rel="stylesheet" type="text/css" href="<@spring.url '../css/style.css'/>"/>-->
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
        <#--<#if >-->
        <li><a href="/user/profile">Profile</a></li>
        <li><a href="/logout">Out</a></li>
        <#--<#else >-->
        <#--<li><a href="/login">Sign in</a></li>-->
        <#--</#if>-->
    </ul>
</div>

<div id='slide'>
    <script type="application/javascript">
        var slide = document.getElementById('slide');
        slide.onmousedown = function(e) {
            slide.style.transition = '0s';
            var curX = e.clientX;
            var left = slide.offsetLeft;
            var oldCurX = 10;
            slide.onmousemove = function(e) {
                if (slide.offsetLeft < 1 || e.clientX < oldCurX) {
                    oldCurX = e.clientX;
                    slide.style.left = left - (curX - e.clientX) + 'px';
                }
            }
            slide.onmouseup = function(e) {
                slide.style.transition = '0.2s';
                slide.style.left = (oldCurX < curX) ? -slide.offsetWidth + 50 + 'px' : '0px';
                slide.onmousemove = null;
            }
        }
    </script>
    <#if model.superuser>
    <div class="topic_11">
        <h4><a href="/superuser/create_news">Create news</a></h4>
        <br>
    </div>
    </#if>
    <#if model.admin>
       <div class="topic_11">
            <#if model.tickets??>
                <#list model.tickets as t>
                    <h4><a href="/user/profile/${t.user.id}">${t.user.login}</a></h4>
                    <br>
                    <h4><a href="/admin/make_super/${t.user.id}">Allow</a></h4>
                    <h4><a href="/admin/dis/${t.user.id}">Cancel</a></h4>
                    <hr><br>

                </#list>
            </#if>
       </div>
    <#else>
        <div class="topic_11">
            <#if model.currentState?has_content>
                <h4>${model.currentState}</h4>
            <#else>
                <h4><a href="/user/please/${model.user.id}">Try super user to be like a metal god</a></h4>
            </#if>
        </div>
    </#if>



</div>


<div class="news">
    <#list model.news as new>
    <div class="card mb-3">
<#--        <img class="card-img-top" src="<@spring.url '/images/${news.photo.path}'/>" alt="">-->
        <img class="card-img-top" src="../../images/${new.photo.path}" alt="">
<#--        <img class="card-img-top" src="<@spring.url '${new.photo.path}'/>" alt="">-->

        <div class="card-body">
            <h4 class="card-title">${new.headLine}</h4>
            <p class="card-text">${new.description}</p>
            <#--<p class="card-text"><small class="text-muted">${new.dt}</small></p>-->
            <#if model.superuser>
                <div class="delete">
                    <a href="/superuser/delete/news/${new.id}"><img src="../../images/delete.png" width="25" height="25" alt=""></a>
                </div>
            </#if>
<#--            <div style="cursor: pointer;"-->
<#--                 onclick="doLike('like-${new.id}', 'like-counter-${new.id}', ${new.id})">-->
<#--                <i id="like-${new.id}" class="far fa-heart like"-->
<#--                   style="font-size: 15px;margin-right: 7px;margin-left: 7px;">-->

<#--                </i>-->
<#--            </div>-->
        </div>
    </div>
    </#list>
</div>
    <#--<#include "footer.ftl">-->
</body>
<#--<script>-->
<#--    function doLike(likeId, counterId, postId) {-->

<#--        let data = {-->
<#--            postId: postId-->
<#--        }-->

<#--        $.post("/like", data, function (response) {-->
<#--            let like = $('#' + likeId);-->
<#--            console.log(response)-->
<#--            if (response.liked) {-->
<#--                like.addClass("liked");-->
<#--            } else {-->
<#--                like.removeClass("liked");-->
<#--            }-->
<#--            let counter = $('#' + counterId);-->
<#--            let newCount = parseInt(counter.html()) + 1;-->
<#--            console.log(newCount);-->
<#--            counter.html(response.likeCount);-->
<#--        });-->

<#--    }-->
<#--</script>-->
</html>
