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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>

<h1>ONLINE LIBRARY</h1>

<#list getUsernameByUserId as getUsernameByUserId>
    Welcome, <td>${getUsernameByUserId.username}</td>.
</#list>
<br>
<br>




<table>
    <thead>
        <th>Name</th>
        <th>Author</th>
        <th>RESERVED by</th>
    </thead>
    <tbody id="pagination">

    </tbody>
    <#-->
    <#list leftJoinBooksUser as leftJoinBooksUser>
        <tr>
            <td>${leftJoinBooksUser.name}</td>
            <td>${leftJoinBooksUser.author}</td>
            <td>${leftJoinBooksUser.username!"not used"}</td>
        </tr>
    </#list>
    </#-->
</table>
<button id="previousPage" type="button">Previous page</button>
<button id="nextPage" type="button">Next page</button>



<br>
<br>



<br>
<form method="get" action="${rc.getContextPath()}/books">
        <select name="type">
            <option value="sortByAuthor">to sort by author</option>
            <option value="sortByName">to sort by name</option>
            </select>
        <input type="submit" name="bsubmit" value="Sort"/>
</form>
<br>

<form method="post" action="${rc.getContextPath()}/add_book">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="text" id="name" name="name" placeholder="Name" ></input>
    <input type="text" id="author" name="author" placeholder="Author" ></input>
    <button name="add" value="addBooks">Add book</button>


</form>

<br>

<form method="post" action="${rc.getContextPath()}/delete_book">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <select name="deleteSelect">
    <#list find as leftJoinBooksUser>go
        <option value="${leftJoinBooksUser.id}">${leftJoinBooksUser.name}</option>
    </#list>
</select>
    <button name="buttonDelete" value="addBooks">Delete</button>

</form>


<br>


<form method="post" action="${rc.getContextPath()}/edit_book">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <select name="editSelect">
        <#list find as leftJoinBooksUser>go
            <option value="${leftJoinBooksUser.id}">${leftJoinBooksUser.name}</option>
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


<form method="post" action="${rc.getContextPath()}/take_book">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <select name="take_book">
        <#list notReservedBooks as notReservedBooks>go
            <option value="${notReservedBooks.id}">${notReservedBooks.name}</option>
        </#list>
    </select>

    <button name="buttonTake" value="take_book">Take book</button>
</form>

<br>

<form method="post" action="${rc.getContextPath()}/return_book">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <select name="returnSelect">
        <#list returnedBooks as returnedBooks>go
            <option value="${returnedBooks.id}">${returnedBooks.name}</option>
        </#list>
    </select>
    <button name="buttonReturn" value="returnBooks">Return book</button>
</form>

<script type="text/javascript">
    var contextPath = "${rc.getContextPath()}";
    function updatePagination(shift = 0) {

        var searchParams = new URLSearchParams(window.location.search)
        if (!searchParams.has("pageSize")) {
            searchParams.set("pageSize", '5');
        }
        if (!searchParams.has("pageNumber")) {
            searchParams.set("pageNumber", '0');
        }

        if (parseInt(searchParams.get("pageNumber")) < 0) {
            searchParams.set("pageNumber", '0');
        }

        if (parseInt(searchParams.get("pageSize")) < 1) {
            searchParams.set("pageSize", '1');
        }



        $.ajax({
            url: contextPath + "/api/get-books",
            data: {
                type: searchParams.get("type"),
                pageSize: parseInt(searchParams.get("pageSize")),
                pageNumber: parseInt(searchParams.get("pageNumber")) + shift
            }
        }).done(function (data) {
            history.pushState(
                null,
                '',
                contextPath + '/books?type=' + searchParams.get("type")
                + "&pageSize=" + parseInt(searchParams.get("pageSize"))
                + "&pageNumber=" + (parseInt(searchParams.get("pageNumber")) + shift)
            );

            data = JSON.parse(data);
            $("#pagination").html('');

            for (var i = 0; i < data.length; i++) {
                $("#pagination").append("        <tr>\n" +
                    "            <td>" + data[i].name + "</td>\n" +
                    "            <td>" + data[i].author + "</td>\n" +
                    "            <td>" + (data[i].username == 'null' ? '' : data[i].username) + "</td>\n" +
                    "        </tr>");
            }
        });
    }

    $('#previousPage').click(function () {
        updatePagination(-1);
    });

    $('#nextPage').click(function () {
        updatePagination(1);
    });

    $(document).ready(function () {
        updatePagination(0);
    });
</script>
</body>
</html>
