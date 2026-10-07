package in.varma.edutrack.utility;

import java.security.SecureRandom;

public class RandomPasswordGenerator {

    public static String randomPassword(int pwdSize) {

        SecureRandom random = new SecureRandom();

        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789";

        StringBuilder builder = new StringBuilder(pwdSize);

        for (int i = 0; i < pwdSize; i++) {
            int index = random.nextInt(chars.length());
            builder.append(chars.charAt(index));
        }

        return builder.toString();
    }
}