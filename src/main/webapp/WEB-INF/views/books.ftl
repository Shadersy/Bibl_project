<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ONLINE-LIBRARY</title>

    <style>
        /* Basic styling */
        body {
            font-family: sans-serif;
        }

        .container {
            box-sizing: border-box;
            margin: 0 auto;
            max-width: 500px;
            padding: 0 20px;
            width: 100%;
        }



        #okno {
            width: 300px;
            height: 50px;
            text-align: center;
            padding: 15px;
            border: 3px solid #0000cc;
            border-radius: 10px;
            color: #0000cc;
            display: none;
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            margin: auto;
            background-color: white;
        }

        #okno:target {display: block;}
        .close {
            display: inline-block;
            border: 1px solid #0000cc;
            color: #0000cc;
            padding: 0 12px;
            margin: 10px;
            text-decoration: none;
            background: #f2f2f2;
            font-size: 14pt;
            cursor:pointer;
        }
        .close:hover {background: #e6e6ff;}
    </style>
</head>

<body>
<h1>ONLINE LIBRARY</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Author</th>
        <th>User_id</th>
    </tr>
    <#list books as books>
        <tr>
            <td>${books.id}</td>
            <td>${books.name}</td>
            <td>${books.author}</td>
            <td>${books.userId}</td>
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
    <button name="add" value="addBooks">Add book</button>


</form>

<br>

<form method="post" action="/delete_book">
<select name="deleteSelect">
    <#list books as book>go
        <option value="${book.id}">${book.name}</option>
    </#list>
</select>
    <button name="buttonDelete" value="addBooks">Delete</button>

</form>


<br>


<form method="post" action="/edit_book">
    <select name="editSelect">
        <#list books as book>go
            <option value="${book.id}">${book.name}</option>
        </#list>
    </select>
    <button type="button" id="kek">Edit book</button>

    <br>

    <div id="okno">

        <input type="text" name="nameOfBook" placeholder="Edit name of book">
        <button type="submit">Submit</button>
        <a href="books" class="close">Close</a>
    </div>

    <script>
        document.getElementById("kek").addEventListener('click', function(){
            document.getElementById("okno").style.display="block";})
    </script>
</form>


<br>


<form method="post" action="/take_book">
    <select name="take_book">
        <#list books as book>go
            <option value="${book.id}">${book.name}</option>
        </#list>
    </select>

    <select name="choose_user">
        <#list users as user>
            <option value="${user.id}">${user.username}</option>
        </#list>

    </select>

    <button name="buttonTake" value="take_book">Take book</button>
</form>










</body>
</html>
