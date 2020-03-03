<html xmlns:th="http://www.thymeleaf.org" xmlns:tiles="http://www.thymeleaf.org">
<head>
    <title tiles:fragment="title">Login</title>
</head>
<body>
<div style="margin-left: 20%; margin-right: 20%; margin-top: 10%" tiles:fragment="content">
    <form  name="f" th:action="@{/login}" method="post">
        <fieldset>
            <legend>Please Login</legend>
            <label for="login">Login</label>
            <input type="text" id="login" name="login"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>