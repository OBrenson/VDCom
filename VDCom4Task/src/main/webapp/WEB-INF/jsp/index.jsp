<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <div align="center">
            <table border="1" cellpadding="5">
                <caption><h2>List of employees</h2></caption>
                <tr>
                    <th>First Name</th>
                    <th>Second Name</th>
                    <th>Email</th>
                </tr>
                <c:forEach var="employee" items="${list}">
                    <tr>
                        <td><c:out value="${employee.firstName}" /></td>
                        <td><c:out value="${employee.lastName}" /></td>
                        <td><c:out value="${employee.email}" /></td>
                        <td><a href="editEmployee/${employee.id}">Edit</a></td>
                        <td><a href="deleteEmployee/${employee.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
            <a href="addEmployee">Add Employee</a>
            </br>
            <form method="POST" action="/upload-csv-file" enctype="multipart/form-data">
                <div>
                    <label for="file">Select a CSV file</label>
                    </br>
                    <input type="file" name="file" id="file" accept=".csv">
                </div>
                <button type="submit">Import Employees</button>
            </form>
        </div>
</body>
</html>