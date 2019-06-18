package user.management.btkoucn.domain.repository.job;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import user.management.btkoucn.domain.model.Job;

public interface JobRepository {
	List<Job> searchJobs(RowBounds rowBounds);
	Long countJobs();
	Long insertJob(@Param("jobName")String jobName,@Param("returnValue")String returnValue,@Param("jobType")String jobType);
}
