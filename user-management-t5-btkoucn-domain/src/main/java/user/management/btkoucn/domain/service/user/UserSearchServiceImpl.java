package user.management.btkoucn.domain.service.user;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.model.AccountSearchCondition;
import user.management.btkoucn.domain.repository.account.AccountRepository;

@Service
@Transactional
public class UserSearchServiceImpl implements UserSearchService {

	@Inject
	AccountRepository accountRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Page<Account> searchUsers(AccountSearchCondition accountSearchCondition,Pageable pageable) {
		if(accountSearchCondition.getBirthDay() != null){
			accountSearchCondition.setBirthDay(accountSearchCondition.getBirthDay().replace("-", "/"));
		}
		Long total = accountRepository.countByCondition(accountSearchCondition);
		
        List<Account> accounts;
        if (0 < total) {
            accounts = accountRepository.findByCondition(accountSearchCondition, pageable);
        } else {        	
        	throw new BusinessException(ResultMessages.error().add("e.search.not.find",""));
        }

        return new PageImpl<>(accounts, pageable, total);

	}

}
