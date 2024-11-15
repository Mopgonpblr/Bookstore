<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Bookstore</title>
    </head>
    <body>
        <%=request.getAttribute("bookList")%>
        <form method="post" action="availability?action=submit">
            <label for="bookNumber">Book number:</label><br>

            <input type="number" id="bookNumber" name="bookNumber" min = "1" value = "1"><br>

            <input type="radio" id="yes" name="isAvailable" value="yes" checked="checked">
            <label for="yes">Yes</label><br>

            <input type="radio" id="no" name="isAvailable" value="no">
            <label for="no">No</label><br>

            <button type="submit">submit</button>
        </form>
        <br><a href="/bookstore">Go back</a></br>
    </body>
</html>