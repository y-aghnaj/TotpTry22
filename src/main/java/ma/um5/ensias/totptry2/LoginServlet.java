package ma.um5.ensias.totptry2;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    // Sample hard-coded user credentials (replace with database in production)
    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "password123";
    private static final String SECRET_KEY = "JBSWY3DPEHPK3PXP"; // Base32 secret key for TOTP

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            // Login success, redirect to the TOTP verification page
            response.sendRedirect("verify.jsp");
        } else {
            // Login failed, return error message
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Invalid username or password</h1>");
            out.println("<a href='index.jsp'>Try Again</a>");
            out.println("</body></html>");
        }
    }
}