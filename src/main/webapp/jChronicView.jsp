<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>



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
        var row = document.createElement("tr");


        td1.innerText = ${action};
        td2.innerHTML = ${date};

        row.appendChild(td1);
        row.appendChild(td2);

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
        <td>${action}</td>
    </tr>

</table>
</body>
</html>