package masterSpringMvc.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignupController {
	private final ProviderSignInUtils signInUtils;

	@Autowired
	public SignupController(ConnectionFactoryLocator connectionFactoryLocator,
			UsersConnectionRepository connectionRepository) {
		System.out.println("SignupController: constructor START");
		signInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
		System.out.println("SignupController: constructor END");
	}

	@RequestMapping(value = "/signup")
	public String signup(WebRequest request) {
		System.out.println("SignupController: signup START");
		Connection<?> connection = signInUtils.getConnectionFromSession(request);
		if (connection != null) {
			System.out.println("SignupController: signup in if: connection was null, doAuthenticate from adapter");
			AuthenticatingSignInAdapter.authenticate(connection);
			signInUtils.doPostSignUp(connection.getDisplayName(), request);
		}
		System.out.println("SignupController: signup END:redirect to profile");
		return "redirect:/profile";
	}
}
