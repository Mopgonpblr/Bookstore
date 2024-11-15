<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Bookstore</title>
    </head>
    <body>
        <%=request.getAttribute("orderBookList")%>
        <form method="post" action="new?action=submit">
            <button type="submit">Complete Order</button>
        </form>

        <form method="post" action="new?action=cancel">
            <button type="cancel">Cancel Order</button>
        </form>
        <br><a href="/bookstore">Go back</a></br>
    </body>
</html>