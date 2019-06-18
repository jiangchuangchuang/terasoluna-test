package user.management.btkoucn.domain.service.authenticate;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.service.account.AccountSharedService;
import user.management.btkoucn.domain.service.authenticate.exception.MyUsernameNotFoundException;



@Service
public class MyUserDetailsService implements UserDetailsService {

    @Inject
    AccountSharedService accountSharedService;
    
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountSharedService.findOne(username);
            if (account == null){
            	throw new MyUsernameNotFoundException("user not found");
            }
            UserDetails userDetails = new AccountUserDetails(account);
            return userDetails;
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException("user not found", e);
        }
    }

}
