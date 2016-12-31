package masterSpringMvc.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@Component
public class AuthenticatingSignInAdapter implements SignInAdapter {
	public static void authenticate(Connection<?> connection) {
		System.out.println("AuthenticatingSignInAdapter: authenticate START");
		UserProfile userProfile = connection.fetchUserProfile();
		String username = userProfile.getUsername();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
				null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(String.format("Użytkownik %s %s został połączony.", userProfile.getFirstName(),
				userProfile.getLastName()));
		System.out.println("AuthenticatingSignInAdapter: authenticate END");
	}

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		System.out.println("AuthenticatingSignInAdapter: signIn START");
		authenticate(connection);
		System.out.println("AuthenticatingSignInAdapter: signIn END");
		return null;
	}
}