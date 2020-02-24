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
        <th>Id</th>
        <th>Name</th>
        <th>Author</th>
    </tr>
    <#list books as books>
        <tr>
            <td>${books.id}</td>
            <td>${books.name}</td>
            <td>${books.author}</td>
        </tr>
    </#list>
</table>


<br>
<form method="get" action="books">
        <select name="type">
            <option value="sortByAuthor">to sort by author</option>
            <option value="sortByName">to sort by name</option>
        </select>
        <input type="submit" name="bsubmit" value="Send"/>
</form>
<br>

<form method="post" action="/add_book">
    <input type="text" id="name" name="name" placeholder="Name" ></input>
    <input type="text" id="author" name="author" placeholder="Author" ></input>
    <button name="add" value="addBooks">Add</button>


</form>



<form method="post" action="/delete_book">
<select name="deleteSelect">
    <#list books as book>go
        <option value="${book.id}">${book.name}</option>
    </#list>
</select>
    <button name="buttonDelete" value="addBooks">delete</button>

</form>





</body>
</html>
