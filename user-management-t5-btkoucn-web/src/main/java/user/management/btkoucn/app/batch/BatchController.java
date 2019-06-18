package user.management.btkoucn.app.batch;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import user.management.btkoucn.domain.model.Job;
import user.management.btkoucn.domain.service.batch.BatchService;

@RequestMapping("batch")
@Controller
public class BatchController {

	@Inject
	BatchService batchService;


	@RequestMapping(value = "batchResult", method = RequestMethod.GET)
	public String uploadForm(Model model,@PageableDefault(size=10) Pageable pageable) {
		Page<Job> page = batchService.searchJobs(pageable);
		model.addAttribute("page", page);
		return "batch/batchResult";
	}

}
