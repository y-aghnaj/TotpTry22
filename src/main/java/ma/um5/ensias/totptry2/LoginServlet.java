package ma.um5.ensias.totptry2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate credentials with UserStore
        if (UserStore.validateUser(username, password)) {
            // Store username in session for further use
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Generate OTP for user (mock example here for simplicity)
            String secretKey = UserStore.getSecretKey(username); // Retrieve user's secret key
            String otp = OTPGenerator.generateOTP(secretKey); // Generate OTP
            session.setAttribute("otp", otp); // Store OTP in session for verification

            System.out.println("Login successful for user: " + username);
            System.out.println("Generated OTP: " + otp); // For testing/logging (remove in production!)

            // Redirect to OTP verification page
            response.sendRedirect("verify.jsp"); // Replace with your actual verification logic
        } else {
            // Login failed - redirect to login page with error message
            response.sendRedirect("index.jsp?error=Invalid username or password");
        }
    }
}