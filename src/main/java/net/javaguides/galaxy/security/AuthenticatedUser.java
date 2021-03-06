package net.javaguides.galaxy.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import net.javaguides.galaxy.entities.Role;
import net.javaguides.galaxy.entities.User;

/**
 * @author Caio Fernando
 */
public class AuthenticatedUser extends org.springframework.security.core.userdetails.User{

    /**
     *
     */
    private static final long serialVersionUID = 4928486404697932897L;
    private User user;
    

    public AuthenticatedUser(User user) {
        super(user.getEmail(),
              user.getPassword(),
              getAuthorities(user));
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user)
	{
		Set<String> roleAndPermissions = new HashSet<>();
		List<Role> roles = user.getRoles();
		
		for (Role role : roles)
		{
            roleAndPermissions.add("ROLE_USER");
//            roleAndPermissions.add(role.getName());
		}
		String[] roleNames = new String[roleAndPermissions.size()];
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
		return authorities;
	}
}