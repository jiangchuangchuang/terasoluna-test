package user.management.btkoucn.domain.service.authenticate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import user.management.btkoucn.domain.common.Constant;
import user.management.btkoucn.domain.model.Account;
import user.management.btkoucn.domain.model.Role;

public class AccountUserDetails extends User {
	 private static final long serialVersionUID = 1L;

	    private Account account; 

	    public AccountUserDetails(Account account) {
	        super(account.getUsername(), account.getPassword(), setRoleList(account.getRoles())); 
	        this.account = account;
	    }

	    public Account getAccount() {
	        return account;
	    }

	    public void setAccount(Account newAccount) {
	        this.account=newAccount;
	    }

		private static List<GrantedAuthority> setRoleList(List<Role> roles) {
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if(roles != null){
				for (Role role : roles) {
					authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
				}
			}
			return authorities;
		}
		
		@Override
		public boolean isEnabled() {
			return !Constant.LOGIN_STATUS_DEL.equals(account.getLoginStatus());
		}
		
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}
		@Override
		public boolean isAccountNonLocked() {
			return !Constant.LOGIN_STATUS_LOCKED.equals(account.getLoginStatus());
		}
		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
}
