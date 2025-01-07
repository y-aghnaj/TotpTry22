package ma.um5.ensias.totptry2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "VerifyServlet", value = "/verify")
public class VerifyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the entered OTP from the form
        String enteredOtp = request.getParameter("otp");

        // Get session data
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String generatedOtp = (String) session.getAttribute("otp"); // OTP stored during login

        if (username == null || generatedOtp == null) {
            // Session invalid or expired
            response.sendRedirect("index.jsp?error=Session expired. Please login again.");
            return;
        }

        // Verify OTP
        if (enteredOtp.equals(generatedOtp)) {
            System.out.println("OTP verified successfully for user: " + username);

            // Redirect to a success page
            response.sendRedirect("success.jsp");
        } else {
            System.out.println("OTP verification failed for user: " + username);

            // Redirect back to the verification page with error
            response.sendRedirect("verify.jsp?error=Invalid OTP. Please try again.");
        }
    }
}