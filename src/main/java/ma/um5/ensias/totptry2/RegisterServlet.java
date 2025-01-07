package ma.um5.ensias.totptry2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String secretKey = request.getParameter("secretKey");

        // Validate input
        if (username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            response.sendRedirect("register.jsp?error=Username and Password are required");
            return;
        }

        // Check if user already exists
        if (UserStore.getSecretKey(username) != null) {
            response.sendRedirect("register.jsp?error=Username already exists. Please choose a different username.");
            return;
        }

        // If no secretKey is provided, generate one
        if (secretKey == null || secretKey.trim().isEmpty()) {
            secretKey = OTPGenerator.generateSecretKey(); // Replace with a more secure generator if needed
        }

        // Register the user
        UserStore.addUser(username, password, secretKey);

        System.out.println("Registered new user: " + username); // Log for debugging during development
        System.out.println("Generated Secret Key: " + secretKey);

        // Redirect to success message
        response.sendRedirect("register.jsp?success=User registered successfully!");
    }
}