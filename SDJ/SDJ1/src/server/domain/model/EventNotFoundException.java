package server.domain.model;

public class EventNotFoundException extends Exception {
	public EventNotFoundException(String message) {
		super(message);
	}
}