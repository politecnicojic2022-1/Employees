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

    private GsonBuilder gsonBuilder;
    private Gson gson;
    public SrvlEmployee() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

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
        this.setAccessControlHeaders(response);
        if(request.getParameter("employeeId") == null){
            out.print(gson.toJson(this.EMPLOYEES));
        }else{
            Employee empl = this.findEmployee(request.getParameter("employeeId"));
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

    private Employee findEmployee(String employeeId){
        return this.EMPLOYEES.stream()
                .filter(empl -> empl.getId().equals(employeeId))
                .findAny()
                .orElse(null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.setAccessControlHeaders(response);
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(request);
        int max = 1000000000, min = 100;
        Random rd = new Random();
        Employee employee = new Employee(
                String.valueOf(rd.nextInt(max - min) + min),
                body.get("name").getAsString(),
                body.get("lastName").getAsString(),
                body.get("age").getAsInt()
        );

        this.EMPLOYEES.add(employee);

        out.print(gson.toJson(employee));
        out.flush();
    }

    private JsonObject getParamsFromPost(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();

        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
    }
}
