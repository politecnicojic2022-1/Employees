package co.edu.poli.ces3.employees.servlets;

import co.edu.poli.ces3.employees.entities.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SrvlEmployee", value = "/SrvlEmployee")
public class SrvlEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ServletOutputStream out = response.getOutputStream();
        ArrayList<Employee> employees = new ArrayList<>();

        /*response.setContentType("text/html");
        out.print("<p>Nombre:"+request.getParameter("name")+"</p>");
        out.print("<p>Edad:"+request.getParameter("age")+"</p>");*/
        Employee employee = new Employee(
                request.getParameter("id"),
                request.getParameter("name"),
                request.getParameter("lastName"),
                Integer.parseInt(request.getParameter("age"))
        );

        employees.add(employee);

        request.setAttribute("employees", employees);

        request.getRequestDispatcher("views/employees/list.jsp").forward(request,response);
    }
}
