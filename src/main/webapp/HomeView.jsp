<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>
<jsp:include page="/fragments/header.jsp" />



<table id="table" border="1">
    <thead id="table-head">
    <tr>
        <th>Action</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody id="table-body">
    </tbody>
</table>

<script type = "text/javascript">
    function addRow() {
        "use strict";

        var tableBody = document.getElementById("table-body");
        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var row = document.createElement("tr");


        td1.innerText = ${title};
        td2.innerHTML = ${date};
        td3.innerHTML = ${id};

        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        tableBody.appendChild(row);
    }
</script>



<table>
    <tr>
        <td>Date :</td>
        <td>${date}</td>
    </tr>
    <tr>
        <td>ID :</td>
        <td>${title}</td>
    </tr>
    <tr>
        <td>Delete</td>
        <td>${id}</td>
    </tr>

</table>
</body>
</html>