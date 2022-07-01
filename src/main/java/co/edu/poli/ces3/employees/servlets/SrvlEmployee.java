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
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        //out.print("número 1:" + request.getParameter("numero1"));
        //out.print("número 2:" + request.getParameter("numero2"));
        int n1 = Integer.parseInt(request.getParameter("numero1"));
        int n2 = Integer.parseInt(request.getParameter( "numero2"));

       // out.print("la suma es: " + (n1 + n2));
       // out.print("<br/><br/><br/><br/>el token es:" + request.getHeader("TOKEN"));
        out.print("{nombre: 'oscar'}");
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
