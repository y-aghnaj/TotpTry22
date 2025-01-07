package ma.um5.ensias.totptry2;

import java.util.Base64;
import java.security.SecureRandom;

public class OTPGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();
    // Example: Generate a 6-digit random OTP based on user's secret key (simplified)
    public static String generateOTP(String secretKey) {
        int otp = 100000 + secureRandom.nextInt(900000); // Generate random 6-digit number
        return String.valueOf(otp);
    }
    public static String generateSecretKey() {
        byte[] keyBytes = new byte[16]; // 128-bit secret key
        secureRandom.nextBytes(keyBytes); // Fill with secure random bytes
        return Base64.getEncoder().encodeToString(keyBytes); // Encode to Base64 for safe storage
    }
}