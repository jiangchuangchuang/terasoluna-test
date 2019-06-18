package user.management.btkoucn.domain.service.batch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import user.management.btkoucn.domain.model.Job;

public interface BatchService {
	Page<Job> searchJobs(Pageable pageable);
}
