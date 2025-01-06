package ma.um5.ensias.totptry2;

import java.nio.ByteBuffer;
import java.time.Instant;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TOTPGenerator {

    // Algorithme HMAC utilisé (HmacSHA1, HmacSHA256, HmacSHA512)
    private static final String HMAC_ALGORITHM = "HmacSHA1";

    // Longueur du code OTP (6 par défaut)
    private static final int OTP_LENGTH = 6;

    public static void main(String[] args) {
        String secretKey = "JBSWY3DPEHPK3PXP"; // Clé secrète (base32 encodée)
        long timeStepSeconds = 30; // Intervalle de temps
        String totp = generateTOTP(secretKey, timeStepSeconds);
        System.out.println("TOTP: " + totp);
    }

    public static String generateTOTP(String base32Secret, long timeStepSeconds) {
        try {
            // Convertir la clé secrète de Base32 à bytes
            byte[] keyBytes = decodeBase32(base32Secret);

            // Calculer le timestamp (temps actuel / intervalle)
            long currentInterval = Instant.now().getEpochSecond() / timeStepSeconds;

            // Convertir le timestamp en tableau de bytes
            byte[] timeBytes = ByteBuffer.allocate(8).putLong(currentInterval).array();

            // Initialiser HMAC avec la clé secrète
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(new SecretKeySpec(keyBytes, HMAC_ALGORITHM));

            // Calculer le HMAC pour le timestamp
            byte[] hmac = mac.doFinal(timeBytes);

            // Extraire un code dynamique (DT) des derniers 4 bits
            int offset = hmac[hmac.length - 1] & 0xF;
            int binaryCode = ((hmac[offset] & 0x7F) << 24) |
                    ((hmac[offset + 1] & 0xFF) << 16) |
                    ((hmac[offset + 2] & 0xFF) << 8) |
                    (hmac[offset + 3] & 0xFF);

            // Obtenir le code OTP modulo 10^OTP_LENGTH
            int otp = binaryCode % (int) Math.pow(10, OTP_LENGTH);

            // Retourner l'OTP sous forme de chaîne avec des zéros initiaux si nécessaire
            return String.format("%0" + OTP_LENGTH + "d", otp);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération de TOTP", e);
        }
    }

    // Décoder une clé Base32 (par exemple, si générée par Google Authenticator)
    private static byte[] decodeBase32(String base32) {
        String base32Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
        byte[] bytes = new byte[base32.length() * 5 / 8];
        int buffer = 0, bitsLeft = 0, index = 0;

        for (char c : base32.toUpperCase().toCharArray()) {
            if (c == '=') break;
            buffer <<= 5;
            buffer |= base32Chars.indexOf(c);
            bitsLeft += 5;
            if (bitsLeft >= 8) {
                bytes[index++] = (byte) (buffer >> (bitsLeft - 8));
                bitsLeft -= 8;
            }
        }
        return bytes;
    }
}
