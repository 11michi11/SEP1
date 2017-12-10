package controler;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

import model.Category;
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

	public static void addLecturerToList(Object[] configuration) {
		System.out.println(Arrays.toString(configuration));

		String name = (String) configuration[0];
		String email = (String) configuration[1];
		int phone = (int) configuration[2];
		ArrayList<Category> categories = (ArrayList<Category>) configuration[3];
		boolean wantsAdvertise = (boolean) configuration[4];

		manager.signUpLecturer(name, email, phone, categories, wantsAdvertise);
		System.out.println(manager.getLecturersString());
	}

	public static void addParticipantToList(Object[] configuration) {

		String name = (String) configuration[0];
		String email = (String) configuration[1];

		// manager.signUpParticipantToEvent(name, email);

		// System.out.println(manager.getParticipantString());

	}

//	public static DefaultTableModel getMembersTableModel() {
//		
//		String[] columnNames = { "Name", "E-mail", "ID", "Paid", "Member" };
//		ArrayList<Member> members = manager.getAllMembers();
//		Object[][] data = new Object[memebrs.size()][5];
//		
//		for(int i = 0;i<members.size();i++) {
//			Object[] row = new Object[5];
//			row[0] = members.get(i).getName();
//			
//			
//			data[i] = row;
//		}
//		
//		Object[][] data = { { "Matej", "andasfsuf@gdgdfg.com", "59599295", true , (member Object)},
//				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
//				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
//				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
//				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
//				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
//				{ "Michal", "andasfsuf@gdgdfg.com", "59599295", true },
//				{ "Miska", "andasfsuf@gdgdfg.com", "59599295", true },
//
//		};
//		
//		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//
//			@Override
//			public Class getColumnClass(int column) {
//				switch (column) {
//				case 0:
//					return String.class;
//				case 1:
//					return String.class;
//				case 2:
//					return Integer.class;
//				case 3:
//					return Boolean.class;
//				default:
//					return Boolean.class;
//				}
//			}
//		};
//	}

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
