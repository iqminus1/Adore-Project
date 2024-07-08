package uz.pdp.adoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {
    private final Random random;

    @Override
    public String generateCode() {
        StringBuilder sb = new StringBuilder();
        while (sb.length() != 6) {

            boolean isDigit = random.nextBoolean();

            if (isDigit) sb.append(random.nextInt(10));
            else {
                boolean isLower = random.nextBoolean();
                if (isLower) sb.append((char) random.nextInt(97, 123));
                else sb.append((char) random.nextInt(65, 91));
            }

        }
        return sb.toString();
    }
}
