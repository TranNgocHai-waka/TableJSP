<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>User</h1>
    <main class = "container">
        <div class = "row">
            <div class = "col">
<%--                <c:if test="${not empty message}"--%>
                    <div class="alert alert-success">${message}</div>
<%--                </c:if>--%>
<%--                <c:if test="${not empty error}"--%>
                    <div class="alert alert-gander">${error}</div>
<%--                </c:if>--%>
            </div>
        </div>
        <div class = "row">
            <div class = "col">
                <form action ="UserServlet" method ="post">
                    <div class = "form-group">
                        <label for="userId">User ID:</label>
                        <input type="text" name="userId" id="userId" class="form-control" value="${user.userId}"/>
                    </div>
                    <div class = "form-group">
                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password" class="form-control" />
                    </div>
                    <div class = "form-group">
                        <label for="fullname">Full name:</label>
                        <input type="text" name="fullname" id="fullname" class="form-control"  value="${user.fullname}"/>
                    </div>
                    <div class = "form-group">
                        <label for="email">User ID:</label>
                        <input type="text" name="email" id="email" class="form-control" value="${user.email}"/>
                    </div>
                    <div class = "form-check form-check-inline">
                        <label >Role:</label>
                        <label><input type="radio" name="admin" id="admin" class="form-check-input" value="true"${user.admin? 'checked':''}/> Admin </label>
                        <label><input type="radio" name="admin" id="user" class="form-check-input" value="false"${user.admin? 'checked':''}/> User </label>
                    </div>
                    <div class = "form-group">
                        <button class="btn btn-primary" formaction="create">Create</button>
                        <button class="btn btn-warning" formaction="update">Update</button>
                        <button class="btn btn-danger" formaction="delete">Delete</button>
                        <button class="btn btn-info" formaction="reset">Reset</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-stripe">
                    <tr>
                        <td>User ID</td>
                        <td>Fullname</td>
                        <td>Password</td>
                        <td>Role</td>
                        <td>&nbsp;</td>
                    </tr>
                    <c:forEach var="item" items="${users}">
                        <tr>
                            <td>${item.userId}</td>
                            <td>${item.fullname}</td>
                            <td>${item.email}</td>
                            <td>${item.admin?'admin' :'User'}</td>
                            <td>
                                <a href="UserServlet/edit?userId=${item.userId}">Edit</a>
                                <a href="UserServlet/delete?userId=${item.userId}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

</body>
</html>
