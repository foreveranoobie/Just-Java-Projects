<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<s:url value="/resources/js/jquery/jquery_3.4.1.js" var="jquery"/>
<s:url value="/resources/js/news/article.js" var="article"/>
<script src=${jquery}></script>
<script src=${article}></script>
<form name="article" action="/article/sendArticle" method="post">
    <div>
        Название статьи
        <textarea name="articleName"
                  style="width: 80%; resize: none; display: block; margin-left: auto; margin-right: auto; height: 33px; overflow-y: hidden;"></textarea>
        <br>
        <div style="text-align: center; margin-top: 2%;">
            <button type="button" name="header" onclick="insertTag(this);">Подзаголовок</button>
            <button type="button" name="video" onclick="insertTag(this);">Видео</button>
            <button type="button" name="image" onclick="insertTag(this);">Изображение</button>
        </div>
        Текст статьи
        <textarea name="articleText" id="text"
                  style="width: 80%; resize: none; display: block; margin-left: auto; margin-right: auto; height: 32px; overflow-y: hidden;"></textarea>
        <input type="submit" value="Создать статью">
    </div>
</form>


</body>
</html>