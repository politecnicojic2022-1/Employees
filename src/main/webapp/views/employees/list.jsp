<%@ page import="java.util.ArrayList" %>
<%@ page import="co.edu.poli.ces3.employees.entities.Employee" %><%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 26/06/2022
  Time: 6:04 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<Employee> listEmployees = (ArrayList<Employee> )request.getAttribute("employees");
%>
<html>
<head>
    <title>Lista de empleados</title>
</head>
<body>
<table id="tbl_empleyees">
    <thead>
    <tr>
        <th>Identificación</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Edad</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Employee x: listEmployees){
    %>
    <tr>
        <td><%= x.getId() %></td>
        <td><%= x.getName() %></td>
        <td><%= x.getLastName() %></td>
        <td><%= x.getAge() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
