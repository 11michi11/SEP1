package client.controller;


import client.domain.mediator.ClientManager;
import client.view.ViewManager;
import server.domain.model.Member;

import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Controller implements Serializable {

    private static Controller controller;

    private ClientManager manager;

    private ViewManager view;

    public void setManager(ClientManager manager) {
        this.manager = manager;
    }

    public void setView(ViewManager view) {
        this.view = view;
    }

    public void start() {
        new Thread(() -> view.startView()).start();
    }

    public static Controller getInstance() {
        if (controller == null)
            controller = new Controller();
        return controller;
    }

    public ArrayList<Member> getAllMembers() throws RemoteException {
        return manager.getAllMembers();
    }

    public void showMsg(String msg) {
        System.out.println("msg = [" + msg + "]");
        view.showMsg(msg);
    }

    public DefaultTableModel getMembersWhoHasntPaidTableModel() {
        String[] columnNames = {"Name", "E-mail", "Phone", "Paid", "ID", "Member"};
        ArrayList<Member> members = null;
        members = manager.getListOfMembersWhoHasntPaid();
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

        };
    }


    public DefaultTableModel getMembersTableModel() {
        String[] columnNames = {"Name", "E-mail", "Phone", "Paid", "ID", "Member"};
        ArrayList<Member> members = null;
        members = manager.getAllMembers();
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
}
