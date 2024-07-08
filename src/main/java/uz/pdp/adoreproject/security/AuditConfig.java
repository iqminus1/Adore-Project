package uz.pdp.adoreproject.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.User;

import java.util.Optional;

@Service
public class AuditConfig implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()||authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }

        Integer userId = ((User) authentication.getPrincipal()).getId();
        return Optional.of(userId);
    }
}
