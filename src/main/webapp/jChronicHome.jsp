<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Custom Data Parser</title>
</head>
<body>
<div style="text-align:center;">
    <h4>Welcome to dataparser app...</h4>
</div>
<style>
    body {
        color: #fff;
        background: #4c535d;
        font-family: 'Roboto', sans-serif;
    }
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even){background-color: #f2f2f2}

    th {
        color: white;
    }
    td {
        color: black;
    }
    .button {
        background-color: green;
        border: none;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
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
</style>
<form:form method="POST" action="${pageContext.request.contextPath}/addRow" modelAttribute = "dataParser" class = "w3-container">

    <br />
    <p>
    <label>Action:</label>
    <form:input  class = "w3-input" path = "action" type="text" name="action" id="action"/>
    </p>
    <p>
    <label>Date:</label>
    <form:input class = "w3-input" path = "date" type="text" name="date" id="date"/>
    </p>
    <br /><br />
    <input class="button" type="submit" value="Submit" />
</form:form>
<br /><br />

<table border="1" align="center">
    <tr>
        <th>Action</th>
        <th>Date</th>


    </tr>

    <c:forEach items="${tableRows}" var="tableRows">
    <tr>
        <td>${tableRows.action}</td>
        <td>${tableRows.date}</td>
        <form:form method="DELETE" action="${pageContext.request.contextPath}/deleteRow" modelAttribute = "dataParser" class = "w3-container">
            <td>    <input class="button2" type="submit" value="Delete" />
            </td>
        </form:form>
    </tr>
    </c:forEach>
</table>

<div class="marginTable"  data-count="5">

</div>
</body>
</html>