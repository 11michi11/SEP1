package controler;

import java.awt.EventQueue;
import java.util.Arrays;

import model.Member;
import model.MyDate;
import model.VIAManager;
import view.VIAWindow;

public class VIAController {
	
	private static VIAManager manager;
	private static VIAWindow window;
	
	public VIAController() {
		this.manager = new VIAManager();
		showWindow();
		
	}
	
	public static void addMemberToList(Object[] configuration) {
		String name = (String) configuration[0];
		String address = (String) configuration[1];
		int phone = (int) configuration[2];
		String email = (String) configuration[3];
		MyDate dateOfMembership = (MyDate) configuration[4];
		
		manager.signUpMember(name, address, phone, email, dateOfMembership);
	
		
		System.out.println(manager.getMembersString());
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		VIAController controller = new VIAController();
		
	}
	
	public static void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				window = new VIAWindow();
			}
		});
	}
	
}
