package server.controler;

import server.domain.mediator.FileManager;
import server.domain.mediator.ModelManager;
import server.domain.model.*;
import server.domain.model.Event;
import server.view.*;
import server.view.Newsletter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class VIAController {

    private ModelManager manager;
    private ViewManager window;
    private static VIAController controller;

    private VIAController() {
    	
    }
    public static VIAController getInstance() {
        if (controller == null)
            controller = new VIAController();
        return controller;
    }

    public void setManager(ModelManager manager) {
        this.manager = manager;
    }

    public void start() {
        EventQueue.invokeLater(() -> window = new VIAWindow());
    }

    public void performClosingOperations() throws IOException {
        FileManager.generateEventFile(manager.getEventList());
        FileManager.generateLecturerFile(manager.getLecturerList());
        FileManager.generateMemberFile(manager.getMemberList());
    }

    public void performOpeningOperations() throws ClassNotFoundException, IOException {
        manager.updateMemberList();
        manager.updateLecturerList();
        manager.updateEventList();
        manager.updateNewsletterList();
    }

    public void generateEmailsWhoHasntPaid() throws IOException {
        FileManager.generateListOfEmailsWhoHasntPaid(manager.getMemberList().getListOfEmailsWhoHasntPaid());
    }

    public void generateAllEmails() throws IOException {
        FileManager.generateListOfAllEmails(manager.getMemberList().getListOfEmails());
    }

    public void addMemberToList(Object[] configuration) {
        String name = (String) configuration[0];
        String address = (String) configuration[1];
        int phone = (int) configuration[2];
        String email = (String) configuration[3];
        MyDate dateOfMembership = (MyDate) configuration[4];

        manager.signUpMember(name, address, phone, email, dateOfMembership);
        MemberListPanel.refreshTable();
    }

    public void deleteMember(Member member) {
        manager.deleteMember(member);
        MemberListPanel.refreshTable();
    }

    @SuppressWarnings("unchecked")
    public void addLecturerToList(Object[] configuration) {
        String name = (String) configuration[0];
        String email = (String) configuration[1];
        int phone = (int) configuration[2];
        ArrayList<Category> categories = (ArrayList<Category>) configuration[3];
        boolean wantsAdvertise = (boolean) configuration[4];

        manager.signUpLecturer(name, email, phone, categories, wantsAdvertise);
        LecturerListPanel.refreshTable();
    }

    public void addParticipantToList(Object[] configuration) throws EventNotFoundException {
        String name = (String) configuration[0];
        String email = (String) configuration[1];
        int eventId = (int) configuration[2];

        Participant participant = new Participant(name, email);

        manager.signUpParticipantToEvent(participant, eventId);
        ParticipantListPanel.refreshTable();
    }

    public void addEventToList(Map<String, Object> configuration) {
        manager.addEvent(configuration);
        EventListPanel.refreshTable();
    }

    public void generateNewsletter(String newsletterContent) throws IOException {
        manager.generateNewsletter(newsletterContent);
        Newsletter.refreshTable();
    }

    public DefaultTableModel getMembersTableModel() {
        String[] columnNames = {"Name", "E-mail", "Phone", "Paid", "ID", "Member"};
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

        return new DefaultTableModel(data, columnNames) {
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
                        return Member.class;
                    default:
                        return Boolean.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 3;
            }

        };
    }

    public DefaultTableModel getLecturersTableModel() {
        String[] columnNames = {"Name", "E-mail", "Phone", "Category", "Advertise", "Lecturer"};
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

        return new DefaultTableModel(data, columnNames) {

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
                        return Lecturer.class;
                    default:
                        return Boolean.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
    }

    public DefaultTableModel getLecturersMultipleTableModel() {
        String[] columnNames = {"Name", "E-mail", "Phone", "Category", "Advertise", "Choice", "Lecturer"};
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

        return new DefaultTableModel(data, columnNames) {

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
                        return Lecturer.class;
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
    }

    public DefaultTableModel getMembersMultipleTableModel() {
        String[] columnNames = {"Name", "E-mail", "ID", "Choice", "Member"};
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

        return new DefaultTableModel(data, columnNames) {

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
                return col == 3;
            }
        };
    }

    public DefaultTableModel getNewsletterTableModel() {
        String[] columnNames = {"Name of newsletter", "Newsletter"};
        ArrayList<File> newsletter = manager.getAllNewsletters();
        Object[][] data = new Object[newsletter.size()][2];

        for (int i = 0; i < newsletter.size(); i++) {
            Object[] row = new Object[2];
            row[0] = newsletter.get(i).getName();
            row[1] = newsletter.get(i);

            data[i] = row;
        }

        return new DefaultTableModel(data, columnNames) {

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return Newsletter.class;
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
    }

    public DefaultTableModel getEventsTableModel() {
        String[] columnNames = {"Title", "Type", "Event"};
        ArrayList<Event> events = manager.getAllEvents();
        Object[][] data = new Object[events.size()][3];

        for (int i = 0; i < events.size(); i++) {
            Object[] row = new Object[3];
            row[0] = events.get(i).getTitle();
            row[1] = events.get(i).getClass().getName().substring(6);
            row[2] = events.get(i);

            data[i] = row;
        }

        return new DefaultTableModel(data, columnNames) {

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
    }

    public DefaultTableModel getParticipantEventsTableModel() {
        String[] columnNames = {"Title", "Event"};
        ArrayList<Event> events = manager.getAllEvents();
        Object[][] data = new Object[events.size()][2];

        for (int i = 0; i < events.size(); i++) {
            Object[] row = new Object[2];
            row[0] = events.get(i).getTitle();
            row[1] = events.get(i);

            data[i] = row;
        }

        return new DefaultTableModel(data, columnNames) {

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
    }

    public DefaultTableModel getParticipantTableModel(Event event) {
        String[] columnNames = {"Name", "Email", "Participant"};
        ArrayList<Participant> participants = event.getParticipantList().getAllPerticipants();
        Object[][] data = new Object[participants.size()][3];

        for (int i = 0; i < participants.size(); i++) {
            Object[] row = new Object[3];
            row[0] = participants.get(i).getName();
            row[1] = participants.get(i).getEmail();
            row[2] = participants.get(i);

            data[i] = row;
        }

        return new DefaultTableModel(data, columnNames) {

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Participant.class;
                    default:
                        return Boolean.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
    }

    public DefaultTableModel getSearchedMembers(String line) {

        ArrayList<Member> members = SearchEngine.searchForMembers(manager.getAllMembers(), line);
        String[] columnNames = {"Name", "E-mail", "Phone", "Paid", "ID", "Member"};

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

        return new DefaultTableModel(data, columnNames) {

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
                        return Member.class;
                    default:
                        return Boolean.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 3;
            }
        };

    }

    public DefaultTableModel getSearchedLecturers(String line) {

        ArrayList<Lecturer> lecturers = SearchEngine.searchForLecturers(manager.getAllLecturers(), line);
        String[] columnNames = {"Name", "E-mail", "Phone", "Category", "Advertise", "Lecturer"};
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

        return new DefaultTableModel(data, columnNames) {

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
                        return Lecturer.class;
                    default:
                        return Boolean.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
    }

    public DefaultTableModel getSearchedEvents(String line) {

        ArrayList<Event> events = SearchEngine.searchForEvents(manager.getAllEvents(), line);

        String[] columnNames = {"Title", "Type", "Event"};
        Object[][] data = new Object[events.size()][3];

        for (int i = 0; i < events.size(); i++) {
            Object[] row = new Object[3];
            row[0] = events.get(i).getTitle();
            row[1] = events.get(i).getClass().getName().substring(6);
            row[2] = events.get(i);

            data[i] = row;
        }

        return new DefaultTableModel(data, columnNames) {

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
    }

    public DefaultTableModel getSearchedEventsWhenNotFinalized(String line) {

        ArrayList<Event> events = SearchEngine.searchForEvents(manager.getEventList().getNotFinalizedEvents(), line);

        String[] columnNames = {"Title", "Type", "Event"};
        Object[][] data = new Object[events.size()][3];

        for (int i = 0; i < events.size(); i++) {
            Object[] row = new Object[3];
            row[0] = events.get(i).getTitle();
            row[1] = events.get(i).getClass().getName().substring(6);
            row[2] = events.get(i);

            data[i] = row;
        }

        return new DefaultTableModel(data, columnNames) {

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
    }

    public DefaultTableModel getNotFinalizedEvents() {

        ArrayList<Event> events = manager.getEventList().getNotFinalizedEvents();

        String[] columnNames = {"Title", "Type", "Event"};
        Object[][] data = new Object[events.size()][3];

        for (int i = 0; i < events.size(); i++) {
            Object[] row = new Object[3];
            row[0] = events.get(i).getTitle();
            row[1] = events.get(i).getClass().getName().substring(6);
            row[2] = events.get(i);

            data[i] = row;
        }

        return new DefaultTableModel(data, columnNames) {

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
    }

    public DefaultTableModel getFinishedEvents() {

        ArrayList<Event> events = manager.getEventList().getFinishedEvents();

        String[] columnNames = {"Title", "Type", "Event"};
        Object[][] data = new Object[events.size()][3];

        for (int i = 0; i < events.size(); i++) {
            Object[] row = new Object[3];
            row[0] = events.get(i).getTitle();
            row[1] = events.get(i).getClass().getName().substring(6);
            row[2] = events.get(i);

            data[i] = row;
        }

        return new DefaultTableModel(data, columnNames) {

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
    }

    public void showEventsPanel(JPanel currentPanel) {
        window.showEventsPanel(currentPanel);
    }

    public void showNewsletterPanel(JPanel currentPanel) {
        window.showNewsletterPanel(currentPanel);
    }

    public void showSignUpPanel(JPanel currentPanel) {
        window.showSignUpPanel(currentPanel);
    }

    public void showListsPanel(JPanel currentPanel) {
        window.showListsPanel(currentPanel);
    }

    public void showEventCreateFormLectures(JPanel currentPanel) {
        window.showEventCreateFormLectures(currentPanel);
    }

    public void showEventCreateFormWorkshop(JPanel currentPanel) {
        window.showEventCreateFormWorkshop(currentPanel);
    }

    public void showEventCreateFormTrip(JPanel currentPanel) {
        window.showEventCreateFormTrip(currentPanel);
    }

    public void showEventCreateFormSeminars(JPanel currentPanel) {
        window.showEventCreateFormSeminars(currentPanel);
    }

    public void showLecturerChoiceWindow() {
        window.showLecturerChoiceWindow();
    }

    public void showLecturerMultipleChoiceListWindow(JPanel currentPanel) {
        window.showLecturerMultipleChoiceListWindow(currentPanel);
    }

    public void showCategoryMultipleChoiceList(JPanel currentPanel) {
        window.showCategoryMultipleChoiceList(currentPanel);
    }

    public void showNewsletterContentWindow(File newsletter) {
        window.showNewsletterContentWindow(newsletter);
    }

    public void showLecturerListPanel(JPanel currentPanel) {
        window.showLecturerListPanel(currentPanel);
    }

    public void showMemberListPanel(JPanel currentPanel) {
        window.showMemberListPanel(currentPanel);
    }

    public void showEventListPanel(JPanel currentPanel) {
        window.showEventListPanel(currentPanel);
    }

    public void showParticipantListPanel(JPanel currentPanel) {
        window.showParticipantListPanel(currentPanel);
    }

    public void showSignUpFormLecturer(JPanel currentPanel) {
        window.showSignUpFormLecturer(currentPanel);
    }

    public void showSignUpFormMember(JPanel currentPanel) {
        window.showSignUpFormMember(currentPanel);
    }

    public void showSignUpFormParticipant(JPanel currentPanel, Event event) {
        window.showSignUpFormParticipant(currentPanel, event);
    }

    public void showModifyEventPanel(JPanel currentPanel, Event event) {
        window.showModifyEventPanel(currentPanel, event);
    }

    public void showMemberMultipleChoice(JPanel currentPanel) {
        window.showMemberMultipleChoice(currentPanel);
    }

}
