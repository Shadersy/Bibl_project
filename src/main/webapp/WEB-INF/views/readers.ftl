<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Books</title>
</head>

<body>

<h1>Books list</h1>
<table>
    <tr>
        <th>id</th>
        <th>first_name</th>
        <th>second_name</th>
        <th>email</th>
        <th>password</th>
    </tr>
    <#list readers as reader>
        <tr>
            <td>${reader.id}</td>
            <td>${reader.first_name}</td>
            <td>${reader.second_name}</td>
            <td>${reader.email}</td>
        </tr>
    </#list>
</table>


</body>
</html>
