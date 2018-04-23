package controler;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import fileManager.FileManager;
import model.Category;
import model.Event;
import model.EventNotFoundException;
import model.Lecturer;
import model.Member;
import model.MyDate;
import model.Participant;
import model.SearchEngine;
import model.VIAManager;
import view.EventListPanel;
import view.LecturerListPanel;
import view.MemberListPanel;
import view.ParticipantListPanel;
import view.VIAWindow;

public class VIAController {

	private static VIAManager manager;
	private static VIAWindow window;
	private static VIAController controller;

	private VIAController() {
		manager = new VIAManager();
		showWindow();
	}
	public static VIAController getInstance(){
	    if(controller==null)
		controller=new VIAController();
	    return controller;
	}

	public static void performClosingOperations() throws IOException {
		FileManager.generateEventFile(manager.getEventList());
		FileManager.generateLecturerFile(manager.getLecturerList());
		FileManager.generateMemberFile(manager.getMemberList());
	}

	public static void performOpeningOperations() throws ClassNotFoundException, IOException {
		manager.updateMemberList();
		manager.updateLecturerList();
		manager.updateEventList();
		manager.updateNewsletterList();
	}

	public static void generateEmailsWhoHasntPaid() throws IOException {
		FileManager.generateListOfEmailsWhoHasntPaid(manager.getMemberList().getListOfEmailsWhoHasntPaid());
	}

	public static void generateAllEmails() throws IOException {
		FileManager.generateListOfAllEmails(manager.getMemberList().getListOfEmails());
	}

	public static void addMemberToList(Object[] configuration) {
		String name = (String) configuration[0];
		String address = (String) configuration[1];
		int phone = (int) configuration[2];
		String email = (String) configuration[3];
		MyDate dateOfMembership = (MyDate) configuration[4];

		manager.signUpMember(name, address, phone, email, dateOfMembership);
		MemberListPanel.refreshTable();
	}

	public static void deleteMember(Member member) {
		manager.deleteMember(member);
		MemberListPanel.refreshTable();
	}

	public static void addLecturerToList(Object[] configuration) {
		String name = (String) configuration[0];
		String email = (String) configuration[1];
		int phone = (int) configuration[2];
		ArrayList<Category> categories = (ArrayList<Category>) configuration[3];
		boolean wantsAdvertise = (boolean) configuration[4];

		manager.signUpLecturer(name, email, phone, categories, wantsAdvertise);
		LecturerListPanel.refreshTable();
	}

	public static void addParticipantToList(Object[] configuration) throws EventNotFoundException {
		String name = (String) configuration[0];
		String email = (String) configuration[1];
		int eventId = (int) configuration[2];

		Participant participant = new Participant(name, email);

		manager.signUpParticipantToEvent(participant, eventId);
		ParticipantListPanel.refreshTable();
	}

	public static void addEventToList(Map<String, Object> configuration) {
		manager.addEvent(configuration);
		EventListPanel.refreshTable();
	}

	public static void generateNewsletter(String newsletterContent) throws IOException {
		manager.generateNewsletter(newsletterContent);
		view.Newsletter.refreshTable();
	}

	public static DefaultTableModel getMembersTableModel() {
		String[] columnNames = { "Name", "E-mail", "Phone", "Paid", "ID", "Member" };
		ArrayList<Member> members = manager.getAllMembers();
		Object[][] data = new Object[members.size()][6];

		for (int i = 0; i < members.size(); i++) {
			Object[] row = new Object[6];
			row[0] = members.get(i).getName();
			row[1] = members.get(i).getEmail();
			row[2] = members.get(i).getPhone();
			row[3] = members.get(i).hasPaid();
			row[4] = members.get(i).getID();
			row[5] = members.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return Boolean.class;
				case 4:
					return Integer.class;
				case 5:
					return model.Member.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 3)
					return true;
				else
					return false;
			}

		};
		return model;
	}

	public static DefaultTableModel getLecturersTableModel() {
		String[] columnNames = { "Name", "E-mail", "Phone", "Category", "Advertise", "Lecturer" };
		ArrayList<Lecturer> lecturers = manager.getAllLecturers();
		Object[][] data = new Object[lecturers.size()][6];

		for (int i = 0; i < lecturers.size(); i++) {
			Object[] row = new Object[6];
			row[0] = lecturers.get(i).getName();
			row[1] = lecturers.get(i).getEmail();
			row[2] = lecturers.get(i).getPhone();
			row[3] = lecturers.get(i).getCategories();
			row[4] = lecturers.get(i).ifWantsAdvertise();
			row[5] = lecturers.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return String.class;
				case 4:
					return Boolean.class;
				case 5:
					return model.Lecturer.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static DefaultTableModel getLecturersMultipleTableModel() {
		String[] columnNames = { "Name", "E-mail", "Phone", "Category", "Advertise", "Choice", "Lecturer" };
		ArrayList<Lecturer> lecturers = manager.getAllLecturers();
		Object[][] data = new Object[lecturers.size()][7];

		for (int i = 0; i < lecturers.size(); i++) {
			Object[] row = new Object[7];
			row[0] = lecturers.get(i).getName();
			row[1] = lecturers.get(i).getEmail();
			row[2] = lecturers.get(i).getPhone();
			row[3] = lecturers.get(i).getCategories();
			row[4] = lecturers.get(i).ifWantsAdvertise();
			row[5] = false;
			row[6] = lecturers.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return String.class;
				case 4:
					return Boolean.class;
				case 5:
					return Boolean.class;
				case 6:
					return model.Lecturer.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				switch (col) {
				case 5:
					return true;
				default:
					return false;
				}
			}
		};
		return model;
	}

	public static DefaultTableModel getMembersMultipleTableModel() {
		String[] columnNames = { "Name", "E-mail", "ID", "Choice", "Member" };
		ArrayList<Member> members = manager.getAllMembers();
		Object[][] data = new Object[members.size()][5];

		for (int i = 0; i < members.size(); i++) {
			Object[] row = new Object[5];
			row[0] = members.get(i).getName();
			row[1] = members.get(i).getEmail();
			row[2] = members.get(i).getID();
			row[3] = false;
			row[4] = members.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return Boolean.class;
				case 4:
					return Member.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 3)
					return true;
				else
					return false;
			}

		};
		return model;
	}

	public static DefaultTableModel getNewsletterTableModel() {
		String[] columnNames = { "Name of newsletter", "Newsletter" };
		ArrayList<File> newsletter = manager.getAllNewsletters();
		Object[][] data = new Object[newsletter.size()][2];

		for (int i = 0; i < newsletter.size(); i++) {
			Object[] row = new Object[2];
			row[0] = newsletter.get(i).getName();
			row[1] = newsletter.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return model.Newsletter.class;
				case 2:
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static DefaultTableModel getEventsTableModel() {
		String[] columnNames = { "Title", "Type", "Event" };
		ArrayList<Event> events = manager.getAllEvents();
		Object[][] data = new Object[events.size()][3];

		for (int i = 0; i < events.size(); i++) {
			Object[] row = new Object[3];
			row[0] = events.get(i).getTitle();
			row[1] = events.get(i).getClass().getName().substring(6);
			row[2] = events.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Event.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static DefaultTableModel getParticipantEventsTableModel() {
		String[] columnNames = { "Title", "Event" };
		ArrayList<Event> events = manager.getAllEvents();
		Object[][] data = new Object[events.size()][2];

		for (int i = 0; i < events.size(); i++) {
			Object[] row = new Object[2];
			row[0] = events.get(i).getTitle();
			row[1] = events.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return Event.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static DefaultTableModel getParticipantTableModel(Event event) {
		String[] columnNames = { "Name", "Email", "Participant" };
		ArrayList<Participant> participants = event.getParticipantList().getAllPerticipants();
		Object[][] data = new Object[participants.size()][3];

		for (int i = 0; i < participants.size(); i++) {
			Object[] row = new Object[3];
			row[0] = participants.get(i).getName();
			row[1] = participants.get(i).getEmail();
			row[2] = participants.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return model.Participant.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static DefaultTableModel getSearchedMembers(String line) {

		ArrayList<Member> members = SearchEngine.searchForMembers(manager.getAllMembers(), line);
		String[] columnNames = { "Name", "E-mail", "Phone", "Paid", "ID", "Member" };

		Object[][] data = new Object[members.size()][5];

		for (int i = 0; i < members.size(); i++) {
			Object[] row = new Object[6];
			row[0] = members.get(i).getName();
			row[1] = members.get(i).getEmail();
			row[2] = members.get(i).getPhone();
			row[3] = members.get(i).hasPaid();
			row[4] = members.get(i).getID();
			row[5] = members.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return Boolean.class;
				case 4:
					return Integer.class;
				case 5:
					return model.Member.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				if (col == 3)
					return true;
				else
					return false;
			}
		};
		return model;

	}

	public static DefaultTableModel getSearchedLecturers(String line) {

		ArrayList<Lecturer> lecturers = SearchEngine.searchForLecturers(manager.getAllLecturers(), line);
		String[] columnNames = { "Name", "E-mail", "Phone", "Category", "Advertise", "Lecturer" };
		Object[][] data = new Object[lecturers.size()][6];

		for (int i = 0; i < lecturers.size(); i++) {
			Object[] row = new Object[6];
			row[0] = lecturers.get(i).getName();
			row[1] = lecturers.get(i).getEmail();
			row[2] = lecturers.get(i).getPhone();
			row[3] = lecturers.get(i).getCategories();
			row[4] = lecturers.get(i).ifWantsAdvertise();
			row[5] = lecturers.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				case 3:
					return String.class;
				case 4:
					return Boolean.class;
				case 5:
					return model.Lecturer.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static DefaultTableModel getSearchedEvents(String line) {

		ArrayList<Event> events = SearchEngine.searchForEvents(manager.getAllEvents(), line);

		String[] columnNames = { "Title", "Type", "Event" };
		Object[][] data = new Object[events.size()][3];

		for (int i = 0; i < events.size(); i++) {
			Object[] row = new Object[3];
			row[0] = events.get(i).getTitle();
			row[1] = events.get(i).getClass().getName().substring(6);
			row[2] = events.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Event.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static DefaultTableModel getSearchedEventsWhenNotFinalized(String line) {

		ArrayList<Event> events = SearchEngine.searchForEvents(manager.getEventList().getNotFinalizedEvents(), line);

		String[] columnNames = { "Title", "Type", "Event" };
		Object[][] data = new Object[events.size()][3];

		for (int i = 0; i < events.size(); i++) {
			Object[] row = new Object[3];
			row[0] = events.get(i).getTitle();
			row[1] = events.get(i).getClass().getName().substring(6);
			row[2] = events.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Event.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static DefaultTableModel getNotFinalizedEvents() {

		ArrayList<Event> events = manager.getEventList().getNotFinalizedEvents();

		String[] columnNames = { "Title", "Type", "Event" };
		Object[][] data = new Object[events.size()][3];

		for (int i = 0; i < events.size(); i++) {
			Object[] row = new Object[3];
			row[0] = events.get(i).getTitle();
			row[1] = events.get(i).getClass().getName().substring(6);
			row[2] = events.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Event.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static DefaultTableModel getFinishedEvents() {

		ArrayList<Event> events = manager.getEventList().getFinishedEvents();

		String[] columnNames = { "Title", "Type", "Event" };
		Object[][] data = new Object[events.size()][3];

		for (int i = 0; i < events.size(); i++) {
			Object[] row = new Object[3];
			row[0] = events.get(i).getTitle();
			row[1] = events.get(i).getClass().getName().substring(6);
			row[2] = events.get(i);

			data[i] = row;
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Event.class;
				default:
					return Boolean.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return model;
	}

	public static void main(String[] args) {
		VIAController controller = new VIAController();
	}

	public void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				window = new VIAWindow();
			}
		});
	}
}
