package masterSpringMvc.error;

public class EntityNotFoundException extends Exception {
	private static final long serialVersionUID = 4629424839238531059L;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}