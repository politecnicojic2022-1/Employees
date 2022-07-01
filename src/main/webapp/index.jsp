<%@ page import="co.edu.poli.ces3.employees.entities.Employee" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    ArrayList<Employee> employees = new ArrayList();

%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <title>Empleados</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<%
    int a = 15;
    Employee juan = new Employee("juan", "perez");
    juan.getId();
    juan.setAge(20);
    employees.add(juan);
    employees.add(new Employee("diego", "bedoya"));
%>
<a href="hello-servlet">Hello Servlet</a>
<!--<p>a vale: <%= a %></p>
<b>Nombre: </b><%= employees.get(0).getName() %> -->

<form role="form" action="SrvlEmployee" method="post">
    <fieldset>
        <legend>Información del empleado</legend>
        <label>Identificación</label>
        <input type="text" name="id" id="id"/><br/><br/>
        <label>Nombre</label>
        <input type="text" name="name" id="name"/><br/><br/>
        <label>Apellido</label>
        <input type="text" name="lastName" id="lastName"/><br/><br/>
        <label>Edad</label>
        <input type="number" name="age" id="age"/><br/><br/>
        <input type="submit" value="Guardar"/>
    </fieldset>
</form>
<hr/>
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
            for (Employee x: employees){
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