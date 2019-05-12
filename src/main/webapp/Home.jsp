<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Custom Data Parser</title>
</head>
<jsp:include page="/fragments/header.jsp" />
<%--<jsp:include page="/fragments/ModalButton.jsp" />--%>

<body>
<style>



    .button {
        background-color: green;
        border: none;
        color: white;
        padding: 10px 28px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 12px;
        margin: 4px 2px;
        cursor: pointer;
    }
    .button2 {
        background-color: red;
        border: none;
        color: white;
        padding: 10px 28px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 12px;
        margin: 4px 2px;
        cursor: pointer;
    }
                          .tg  {border-collapse:collapse;border-spacing:0;}
    .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
    .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:black;}
    .tg .tg-0lax{text-align:left;vertical-align:top}
</style>
<div class = "w3-container">

<table  class="tg" style="float: right;">
    <caption>Date Parsed Events</caption>
    <tr>
        <th>Event</th>
        <th>Date</th>
        <th>Action</th>

    </tr>

    <c:forEach items="${tableRows}" var="tableRows">
    <tr>
        <td>${tableRows.title}</td>
        <td>${tableRows.date}</td>
        <form:form method="DELETE" action="${pageContext.request.contextPath}/deleteRow/${tableRows.id}" modelAttribute = "dataParser" class = "w3-container">
            <td>     <input type="hidden" name="_method" value="DELETE" />
                <input class="button2" type="submit" value="Delete" />
            </td>
        </form:form>
    </tr>
    </c:forEach>
</table>

    <table class="tg" style="float: left;">
        <caption>Normal Events</caption>
        <tr>
            <th class="tg-0lax">Event</th>
            <th class="tg-0lax">Start Date</th>
            <th class="tg-0lax">End Date</th>
            <th class="tg-0lax">Action</th>

        </tr>

        <c:forEach items="${tableRows2}" var="tableRows2">
            <tr>
                <td>${tableRows2.title}</td>
                <td>${tableRows2.start}</td>
                <td>${tableRows2.end}</td>
                <form:form method="DELETE" action="${pageContext.request.contextPath}/deleteRow2/${tableRows2.id}" modelAttribute = "event" class = "w3-container">
                    <td>     <input type="hidden" name="_method" value="DELETE" />
                        <input class="button2" type="submit" value="Delete" />
                    </td>
                </form:form>
            </tr>
        </c:forEach>
    </table>


</div>
<div class="marginTable"  data-count="5">

</div>
</body>
</html>