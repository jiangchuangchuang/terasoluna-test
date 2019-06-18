package user.management.btkoucn.domain.service.batch;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.domain.model.Job;
import user.management.btkoucn.domain.repository.job.JobRepository;

@Service
public class BatchServiceImpl implements BatchService {
	
	@Inject
	JobRepository jobRepository;
	
	@Override
	public Page<Job> searchJobs(Pageable pageable) {
		Long total = jobRepository.countJobs();
		
        List<Job> jobs;
        if (0 < total) {
        	RowBounds rowBounds = new RowBounds(pageable.getOffset(),
                    pageable.getPageSize());
        	jobs = jobRepository.searchJobs(rowBounds);
        } else {        	
        	throw new BusinessException(ResultMessages.error().add("e.search.not.find",""));
        }

        return new PageImpl<>(jobs, pageable, total);
	}

}
