package roma.tracker.service.exceptions;

import org.springframework.http.HttpStatus;

public class ProjectNotFoundException extends ApiException {
    public ProjectNotFoundException(HttpStatus status, String title, String description) {
        super(status, title, description);
    }
}
