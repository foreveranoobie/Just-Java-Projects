<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head></head>
<body>
<c:forEach items="${articles}" var="article">
<div style="text-align:center; max-height:15%; max-width: 20%;">
<a href="/article/getArticle/${article.id}">${article.name}</a><br/>
</div>
</c:forEach>
</body>
</html>