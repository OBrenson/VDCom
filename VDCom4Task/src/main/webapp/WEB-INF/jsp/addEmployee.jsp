<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <div align="center">
        <p>Add Employee</p>
        <form:form action="/add" method="post" modelAttribute="employee">
            <table border="0">
                <tr>
                    <td></td>
                     <td><form:hidden  path="id" /></td>
                 </tr>
                <tr>
                    <td>First Name:</td>
                    <td><form:input path="firstName" /></td>
                </tr>
                <tr>
                    <td>Second Name:</td>
                    <td><form:input path="lastName" /></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><form:input path="email" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Add" /></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>