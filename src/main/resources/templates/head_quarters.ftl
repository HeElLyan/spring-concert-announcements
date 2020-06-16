<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Head Quarters</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link href="../../css/style.css" rel="stylesheet">
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

<#--    <#if model.hq_recommendation_list?has_content >-->
<#--        <#list model.hq_recommendation_list as hq>-->
<#--            <div class="topic_11">-->
<#--                <h4><a href="/user/hq?search=${hq.city}">${hq.city}</a></h4>-->
<#--                <br>-->
<#--            </div>-->
<#--        </#list>-->
<#--    <#else>-->
<#--        <div class="forum_search">-->
<#--            <p>Nothing found</p>-->
<#--        </div>-->
<#--    </#if>-->

    <#if model.hq_list?has_content >
        <#list model.hq_list as hq>
            <div class="topic_11">
                <h4><a href="/user/hq?search=${hq.city}">${hq.city}</a></h4>
                <br>
            </div>
        </#list>
    <#else>
        <div class="forum_search">
            <p>Nothing found</p>
        </div>
    </#if>
<#--    <div class="topic_11">-->
<#--            <h4><a href="/user/hq?search=Los-Angeles">Los-Angeles</a></h4>-->
<#--            <br>-->
<#--            <h4><a href="/user/hq?search=New-York">New-York</a></h4>-->
<#--            <br>-->
<#--            <h4><a href="/user/hq?search=London">London</a></h4>-->
<#--    </div>-->
</div>

<div class="forum">
    <div class="row">
        <div class="col-lg-6 offset-lg-3">
            <div class="input-group">
            <#if model.superuser >
                <form method="get" action="/superuser/create_hq">
                    <button class="btn btn-secondary" type="submit">Create hq</button>
                </form>
            </#if>
                <form method="get" action="/user/hq">
                    <input type="text" id="s_id" name="search" class="form-control"  placeholder="" aria-label="Search" oninput="f()">
                    <button class="btn btn-secondary" type="submit">Search</button>
                </form>
            </div>
        </div>
    </div>
</div>

<#if model.hq_list_recommended?has_content >
    <#list model.hq_list_recommended as hq>
        <div class="topic_1">
            <h4><a href="/user/hq/${hq.id}">${hq.city}</a></h4>
            <br>
            <p>${hq.description}</p>
            <#if model.superuser>
                <div class="delete">
                    <a href="/superuser/delete/hq/${hq.id}"><img src="../../images/delete.png" width="25" height="25"></a>
                </div>
            </#if>
        </div>
    </#list>
<#--<#else>-->
<#--    <#list model.hq_list as hq>-->
<#--        <div class="topic_1">-->
<#--            <h4><a href="/user/hq/${hq.id}">${hq.city}</a></h4>-->
<#--            <br>-->
<#--            <p>${hq.description}</p>-->
<#--            <#if model.superuser>-->
<#--                <div class="delete">-->
<#--                    <a href="/superuser/delete/hq/${hq.id}"><img src="../../images/delete.png" width="25" height="25"></a>-->
<#--                </div>-->
<#--            </#if>-->
<#--        </div>-->
<#--    </#list>-->
</#if>

<#--<#if model.hq_list?has_content >-->
<#--    <#list model.hq_list as hq>-->
<#--<div class="topic_1">-->
<#--    <h4><a href="/user/hq/${hq.id}">${hq.city}</a></h4>-->
<#--    <br>-->
<#--    <p>${hq.description}</p>-->
<#--        <#if model.superuser>-->
<#--    <div class="delete">-->
<#--        <a href="/superuser/delete/hq/${hq.id}"><img src="../../images/delete.png" width="25" height="25"></a>-->
<#--    </div>-->
<#--    </#if>-->
<#--</div>-->
<#--    </#list>-->
<#--<#else>-->
<#--<div class="forum_search">-->
<#--    <p>Nothing found</p>-->
<#--</div>-->
<#--</#if>-->



<div id="results"></div>
<script type="application/javascript">
    function f() {
        console.log(1);
        $.ajax({
            url: "/forum",
            data: JSON.parse({'search': $("#s_id").val()}),
            dataType: "json",
            success: function (result) {

                for (var i = 0; i < result.topics.length; i++) {
                    $("#results").append("<li>" + result.topics[i] + "</li>");
                }
            },
            error: function (jqXHR, exception) {

                if (jqXHR.status === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.status == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.status == 500) {
                    alert('Internal Server Error [500].');
                } else if (exception === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (exception === 'timeout') {
                    alert('Time out error.');
                } else if (exception === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error.\n' + jqXHR.responseText);
                }
            },
        });
    }
</script>

<#--<#include "footer.ftl">-->
</body>
</html>