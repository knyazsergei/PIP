package ps.rip.Controller;

import ps.rip.Model.Car;
import ps.rip.View.DefaultView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet("/car")
public class CarController extends HttpServlet {
    private List<Car> carList = new ArrayList<Car>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = (String) request.getParameter("action");
        switch (action) {
            case "list":
                showCarList(request,response);
                break;
            case "addCarPost":
                addCar(request,response);
                break;
            case "addCar":
                addCarForm(request, response);
                break;
            default:
                showError(request, response);
        }
    }

    private void addCarForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DefaultView.renderHeader(request, response);
        response.getWriter().println(
                "<div class=\"form\">" +
                "<form action=\"car?action=addCarPost\" method=\"POST\">" +
                "<label>Car name:<input name=\"name\" type=\"text\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 20}\"/></label>" +
                "<label>Car speed:<input name=\"speed\" type=\"text\" pattern=\"^[0-9]+$\" /></label>" +
                "<label>Date of purchase:<input type=\"date\" name=\"date\"></label>" +
                "<input name=\"action\" type=\"hidden\" value=\"addCar\"/>" +
                "<input type=\"submit\" value=\"send\" />" +
                "</form>" +
                "</div>");
        DefaultView.renderFooter(response);
    }

    private void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = (String) request.getParameter("name");
        String speed = (String) request.getParameter("speed");
        String strDate = (String) request.getParameter("date");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d", new Locale("ru"));
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Date date = java.sql.Date.valueOf(localDate);

        Car newCar = new Car(name, Double.valueOf(speed), date);
        carList.add(newCar);

        DefaultView.renderHeader(request, response);
        response.getWriter().println(
                "<p>We added a machine with the following parameters:<br />\n" +
                "name: " + name + "\n<br />" +
                "speed: " + speed + "\n<br />" +
                "color: " + date + "</p>"
        );
        DefaultView.renderFooter(response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void showCarList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DefaultView.renderHeader(request, response);
        response.getWriter().println("<table border=\"1\">\n" +
                "   <caption>Car list</caption>\n" +
                "   <thead>" +
                "   <tr>\n" +
                "    <th>name</th>\n" +
                "    <th>speed</th>\n" +
                "    <th>date</th>\n" +
                "   </tr>" +
                "   </thead>" +
                "   <tbody>" +
                "   <tr>");
        for ( Car car: carList) {
            response.getWriter().println(
                    "<td>" + car.getName() + "</td>\n" +
                    "<td>" + car.getSpeed() + "</td>\n" +
                    "<td>" + car.getDate() + "</td>\n"
            );
        }
        response.getWriter().println( "</tr></tbody></table");
        DefaultView.renderFooter(response);
    }

    protected void showError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DefaultView.renderHeader(request, response);
        response.getWriter().println("<h1>Error</h1><br />" +
                "<table border=\"1\">\n" +
                "   <caption>Param list</caption>\n" +
                "   <tr>\n" +
                "    <th>name</th>\n" +
                "    <th>value</th>\n" +
                "   </tr>" +
                "   <tr>");
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue()[0];
            response.getWriter().println(
                    "<td>" + name + "</td>\n" +
                    "<td>" + value + "</td>\n"
            );

        }
        response.getWriter().println( "</tr></table");
        DefaultView.renderFooter(response);
    }
}
