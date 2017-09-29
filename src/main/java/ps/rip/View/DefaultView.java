package ps.rip.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class DefaultView {

    public static void renderHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<!DOCTYPE HTML>");
        response.getWriter().println("<html>" +
                "<head>" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"" +
                request.getContextPath().toString() +
                "/styles/style.css\" />" +
                "</head>" +
                "<body>");
        List<String> menu = Arrays.asList("addCar", "list");
        response.getWriter().println("<div class=\"main\">");
        renderMenu(response, menu);
    }

    public static void renderFooter(HttpServletResponse response) throws IOException {
        response.getWriter().println("</div></body></html>");
    }

    public static void renderMenu(HttpServletResponse response, List<String> menu) throws IOException {
        response.getWriter().println("<ul class=\"menu-main\">");
        for(String element : menu) {
            response.getWriter().println("<li><a href=\"/car?action=" + element + "\"> " + element + "</a></li>");
        }
        response.getWriter().println("</ul>");
    }
}
