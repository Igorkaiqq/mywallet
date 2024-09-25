package unipar.integrador.mywallet.application.exception;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionUtils {

    private static final Pattern FIELD_PATTERN = Pattern.compile("(?i)(username|cpf|email|telefone)");

    public static String extractDuplicatedFields(String message) {
        Set<String> duplicatedFields = new HashSet<>();

        Matcher matcher = FIELD_PATTERN.matcher(message);

        while (matcher.find()) {
            duplicatedFields.add(matcher.group(1));
        }

        return duplicatedFields.isEmpty() ? "campo" : String.join(", ", duplicatedFields);
    }
}
