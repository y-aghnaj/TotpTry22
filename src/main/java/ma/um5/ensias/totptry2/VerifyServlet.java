package ma.um5.ensias.totptry2;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "verifyServlet", value = "/verify")
public class VerifyServlet extends HttpServlet {

    private static final String SECRET_KEY = "JBSWY3DPEHPK3PXP"; // Base32 secret key for TOTP
    private static final long TIME_STEP_SECONDS = 30; // TOTP time step interval

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userOtp = request.getParameter("otp");

        // Generate the expected TOTP for the current time interval
        String generatedOtp = TOTPGenerator.generateTOTP(SECRET_KEY, TIME_STEP_SECONDS);
        System.out.println("YO\nYO\nYO\nYO\nYO\nYO\nYO\nYO\nYO\nYO\nYO\nYO\nYO\nYO\nYO\nGenerated Otp = " + generatedOtp);
        if (generatedOtp.equals(userOtp)) {
            // OTP is correct, user is authenticated
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Authentication Successful!</h1>");
            out.println("<p>Welcome to the secure area.</p>");
            out.println("</body></html>");
        } else {
            // OTP is incorrect
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Invalid OTP</h1>");
            out.println("<a href='verify.jsp'>Try Again</a>");
            out.println("</body></html>");
        }
    }
}