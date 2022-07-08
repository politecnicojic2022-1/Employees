package co.edu.poli.ces3.employees.servlets;

import co.edu.poli.ces3.employees.entities.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "SrvlEmployee", value = "/SrvlEmployee")
public class SrvlEmployee extends HttpServlet {

    public static ArrayList<Employee> EMPLOYEES = new ArrayList<>(Arrays.asList(
            new Employee("1111", "Carlos","Perez", 80),
            new Employee("9999", "Ana","Diaz", 80),
            new Employee("888888", "Diego","Trujillo", 20),
            new Employee("1828392", "Diana","Perez", 30)
    ));
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.create();

        if(request.getParameter("employeeId") == null){
            out.print(gson.toJson(this.EMPLOYEES));
        }else{
            Employee empl = this.searchEmployee(request.getParameter("employeeId"));
            out.print(gson.toJson(empl));
        }
        out.flush();
    }

    private Employee searchEmployee(String employeeId) {
        for (int i = 0; i < this.EMPLOYEES.size(); i++){
            if(this.EMPLOYEES.get(i).getId().equals(employeeId)){
                return this.EMPLOYEES.get(i);
            }
        }
        return null;
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
        employees.add(new Employee("Julian", "Cadavid"));

        request.setAttribute("employees", employees);

        request.getRequestDispatcher("views/employees/list.jsp").forward(request,response);
    }
}
