package GUI;

import Core.Database;
import Core.Entry;
import Core.Loader;
import Core.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class Main_Window extends BaseWindow {
    private Database db;
    private File currentFile;
    private String masterPassword;
    private JTable pasTab;
    private TableModel tableModel;
    private JTextField search;

    public Main_Window(Database db, File file, String masterPassword) {
        this.db = db;
        this.currentFile = file;
        this.masterPassword = masterPassword;

        setTitle("Password manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int res = JOptionPane.showConfirmDialog(
                        Main_Window.this,
                        "Are yoy sure?",
                        "Confrim exit",
                        JOptionPane.YES_NO_OPTION
                );

                if (res == JOptionPane.YES_OPTION) {
                    Loader.writeFile(db, masterPassword, currentFile);
                    dispose();
                }
            }
        });

        tableModel = new TableModel(db.getEntries());
        pasTab = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(pasTab);
        search = new JTextField(20);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
           String query = search.getText().trim();
           if (query.isEmpty()) {
               tableModel.setEntries(db.getEntries());
           } else {
               tableModel.setEntries(db.findByName(query));
           }
        });

        JButton clear = new JButton("Clear");
        clear.addActionListener(e -> {
            search.setText("");
            tableModel.setEntries(db.getEntries());
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Search:"));
        panel.add(search);
        panel.add(searchButton);
        panel.add(clear);

        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton remove = new JButton("Remove");
        JButton save = new JButton("Save");
        JButton sortName = new JButton("Sort by name");
        JButton sortCategory = new JButton("Sort by category");
        JButton removeCat = new JButton("Remove category");

        add.addActionListener(e -> {
            Form_Dialog dialog = new Form_Dialog(this, null);
            Entry entry = dialog.getRes();
            if (entry != null) {
                db.addEntry(entry);
                tableModel.setEntries(db.getEntries());
            }
        });
        edit.addActionListener(e -> {
            int row = pasTab.getSelectedRow();
            if (row >= 0) {
                Entry entry = tableModel.getEntryAt(row);
                Form_Dialog dialog = new Form_Dialog(this, entry);
                Entry update = dialog.getRes();
                if (update != null) {
                    db.getEntries().set(db.getEntries().indexOf(entry), update);
                    tableModel.setEntries(db.getEntries());
                }
            }
        });
        remove.addActionListener(e -> {
            int row = pasTab.getSelectedRow();
            if (row >= 0) {
                Entry entry = tableModel.getEntryAt(row);
                db.removeEntry(entry);
                tableModel.setEntries(db.getEntries());
            }
        });
        save.addActionListener(e -> {
            Loader.writeFile(db, masterPassword, currentFile);
            JOptionPane.showMessageDialog(this, "File saved!");
        });
        sortName.addActionListener(e -> {
           db.sortByName();
           tableModel.setEntries(db.getEntries());
        });
        sortCategory.addActionListener(e -> {
            db.sortByCategory();
            tableModel.setEntries(db.getEntries());
        });

        removeCat.addActionListener(e -> {
           if(db.getEntries().isEmpty()){
               JOptionPane.showMessageDialog(this, "Nothing to remove!");
               return;
           }
           String[] catArray = db.getCategories().toArray(new String[0]);
           String selectedCat = (String) JOptionPane.showInputDialog(
                   this,
                   "Select category",
                   "Delete category",
                   JOptionPane.QUESTION_MESSAGE,
                   null,
                   catArray,
                   catArray[0]
           );
           if (selectedCat != null && !selectedCat.isEmpty()) {
               int confirm = JOptionPane.showConfirmDialog(
                       this,
                       "Delete category '" + selectedCat + "' and all entries?",
                       "Confirm deleted",
                       JOptionPane.YES_NO_OPTION
               );
               if (confirm == JOptionPane.YES_OPTION) {
                   db.removeCategory(selectedCat);
                   tableModel.setEntries(db.getEntries());
                   JOptionPane.showMessageDialog(this, "Core.Category deleted!");
               }
           }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add);
        buttonPanel.add(edit);
        buttonPanel.add(remove);
        buttonPanel.add(removeCat);
        buttonPanel.add(save);
        buttonPanel.add(sortName);
        buttonPanel.add(sortCategory);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
