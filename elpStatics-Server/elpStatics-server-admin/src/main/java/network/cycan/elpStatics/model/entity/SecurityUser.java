package network.cycan.elpStatics.model.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SecurityUser extends SysUser implements UserDetails {

    private static final long serialVersionUID = 3551838767039402859L;

    public SecurityUser(SysUser user) {
        if (user != null) {
            this.setUserId(user.getUserId());
            this.setUserName(user.getUserName());
            this.setCreateTime(user.getCreateTime());
            this.setCreateUser(user.getCreateUser());
            this.setId(user.getId());
            this.setNickName(user.getNickName());
            this.setUpdateTime(user.getUpdateTime());
            this.setUpdateUser(user.getUpdateUser());
            this.setUserPwd(user.getUserPwd());
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String username = this.getUsername();
        if (username != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
            authorities.add(authority);
        }
        return authorities;

    }

    @Override
    public String getPassword() {
        return this.getUserPwd();
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
