package co.edu.poli.ces3.employees.servlets;

import co.edu.poli.ces3.employees.entities.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@WebServlet(name = "SrvlEmployee", value = "/SrvlEmployee")
public class SrvlEmployee extends HttpServlet {

    public static ArrayList<Employee> listEmployees = new ArrayList<>(Arrays.asList(
            new Employee("1111111", "Oscar", "Mesa", 20),
            new Employee("4444",  "Juan", "Garcia", 15)
    ));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ServletOutputStream out = response.getOutputStream();
        //response.setContentType("application/json");
        //out.print("número 1:" + request.getParameter("numero1"));
        //out.print("número 2:" + request.getParameter("numero2"));
       // int n1 = Integer.parseInt(request.getParameter("numero1"));
        //int n2 = Integer.parseInt(request.getParameter( "numero2"));
        this.setAccessControlHeaders(response);
       // out.print("la suma es: " + (n1 + n2));
       // out.print("<br/><br/><br/><br/>el token es:" + request.getHeader("TOKEN"));
       // out.print("{nombre: 'oscar'}");
        ServletOutputStream out = response.getOutputStream();
       /* ArrayList<String> crunchify = new ArrayList<String>();
        crunchify.add("Google");
        crunchify.add("Facebook");
        crunchify.add("Crunchify");
        crunchify.add("Twitter");
        crunchify.add("Snapchat");
        crunchify.add("Microsoft");
        log("Raw ArrayList ===> " + crunchify);
*/
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.create();

        String JSONObject = gson.toJson(listEmployees);

        response.setContentType("application/json");
        out.print(JSONObject);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ServletOutputStream out = response.getOutputStream();
        //ArrayList<Employee> employees = new ArrayList<>();
        this.setAccessControlHeaders(response);
        /*response.setContentType("text/html");
        out.print("<p>Nombre:"+request.getParameter("name")+"</p>");
        out.print("<p>Edad:"+request.getParameter("age")+"</p>");*/
        GsonBuilder gsonBuilder = new GsonBuilder();
        JsonObject jsonObject = new JsonParser().parse(this.getParamsFromPost(request)).getAsJsonObject();
        int max = 100000000;
        int min = 1;

        ServletOutputStream out = response.getOutputStream();
        Random rd = new Random();
        Employee employee = new Employee(
                String.valueOf(rd.nextInt(max - min) + min),
                jsonObject.get("name").getAsString(),
                jsonObject.get("lastName").getAsString(),
                jsonObject.get("age").getAsInt()
        );

        SrvlEmployee.listEmployees.add(employee);
        //this.employees.add(new Employee("Julian", "Cadavid"));

        //request.setAttribute("employees", employees);

        //request.getRequestDispatcher("views/employees/list.jsp").forward(request,response);



        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(employee);
        response.setContentType("application/json");
        out.print(JSONObject);
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPut(req, resp);
        Employee empl;
        empl = this.searchEmployee(req.getParameter("employeeId").toString());
        GsonBuilder gsonBuilder = new GsonBuilder();
        ServletOutputStream out = resp.getOutputStream();

        if(empl != null){
            JsonObject jsonObject = new JsonParser().parse(this.getParamsFromPost(req)).getAsJsonObject();
            empl.setName(jsonObject.get("name").getAsString());
            empl.setLastName(jsonObject.get("lastName").getAsString());
            empl.setAge(jsonObject.get("age").getAsInt());
            Gson gson = gsonBuilder.create();
            String JSONObject = gson.toJson(empl);
            resp.setContentType("application/json");
            out.print(JSONObject);
            out.flush();
        }
    }

    private String getParamsFromPost(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        String params = sb.toString();

        return params;
    }

    public Employee searchEmployee(String employeeId) {
        for(Employee x: SrvlEmployee.listEmployees){
            if(x.getId().equals(employeeId)){
                return x;
            }
        }
        return null;
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
    }
}
