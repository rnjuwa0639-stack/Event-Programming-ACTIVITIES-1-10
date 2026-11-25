package week05.activity5;

import javax.swing.*;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class FileManager {

    // Marker child: makes a folder expandable before its sub-folders are read.
    private static final String PLACEHOLDER = "...";

    private JFrame frame;
    private JTree folderTree;
    private JTable fileTable;
    private DefaultTableModel tableModel;
    private JLabel statusBar;
    private File currentFolder;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileManager().createAndShow());
    }

    private void createAndShow() {
        frame = new JFrame("File Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        // 1. MENU BAR
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem refreshItem = new JMenuItem("Refresh");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(refreshItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        // 2. TOOLBAR
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        JButton refreshBtn = new JButton("Refresh");
        JButton upBtn = new JButton("Up Folder");
        JButton homeBtn = new JButton("Home");
        toolbar.add(homeBtn);
        toolbar.add(upBtn);
        toolbar.addSeparator();
        toolbar.add(refreshBtn);
        frame.add(toolbar, BorderLayout.NORTH);

        // 3. TREE (left): sub-folders are loaded lazily, only when a node is expanded.
        // (Reading the whole home directory up front can take minutes and freezes the EDT.)
        File home = new File(System.getProperty("user.home"));
        DefaultMutableTreeNode root = createNode(home);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        folderTree = new JTree(treeModel);
        folderTree.setFont(new Font("Arial", Font.PLAIN, 14));
        folderTree.setRowHeight(22);

        // Show the folder name (not the full path) for every node except the root
        folderTree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                Object o = ((DefaultMutableTreeNode) value).getUserObject();
                if (o instanceof File) {
                    File f = (File) o;
                    setText(f.getName().isEmpty() || value == root ? f.getAbsolutePath() : f.getName());
                }
                return this;
            }
        });

        folderTree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent event) throws ExpandVetoException {
                DefaultMutableTreeNode node =
                        (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                loadChildren(node, treeModel);
            }

            @Override
            public void treeWillCollapse(javax.swing.event.TreeExpansionEvent event) { }
        });

        folderTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selected =
                    (DefaultMutableTreeNode) folderTree.getLastSelectedPathComponent();
            if (selected != null && selected.getUserObject() instanceof File) {
                File folder = (File) selected.getUserObject();
                if (folder.isDirectory()) {
                    loadFolderContents(folder);
                }
            }
        });
        folderTree.expandRow(0);

        // 4. TABLE (right)
        String[] columns = {"Name", "Type", "Size", "Modified"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        fileTable = new JTable(tableModel);
        fileTable.setRowHeight(22);
        fileTable.setFont(new Font("Arial", Font.PLAIN, 13));
        fileTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(folderTree),
                new JScrollPane(fileTable));
        splitPane.setDividerLocation(280);
        frame.add(splitPane, BorderLayout.CENTER);

        // 5. STATUS BAR
        statusBar = new JLabel("Ready");
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        frame.add(statusBar, BorderLayout.SOUTH);

        // 6. EVENT HANDLERS
        loadFolderContents(home);

        homeBtn.addActionListener(e -> loadFolderContents(home));

        upBtn.addActionListener(e -> {
            if (currentFolder != null && currentFolder.getParentFile() != null) {
                loadFolderContents(currentFolder.getParentFile());
            }
        });

        refreshBtn.addActionListener(e -> {
            if (currentFolder != null) {
                loadFolderContents(currentFolder);
            }
        });

        refreshItem.addActionListener(e -> refreshBtn.doClick());
        exitItem.addActionListener(e -> System.exit(0));

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "File Manager v1.0\nBuilt with Swing\nWeek 5 Capstone Project",
                "About", JOptionPane.INFORMATION_MESSAGE));

        // Double-click a row: open a folder, or show the file path
        fileTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = fileTable.getSelectedRow();
                    if (row >= 0) {
                        String fileName = (String) tableModel.getValueAt(row, 0);
                        File file = new File(currentFolder, fileName);
                        if (file.isDirectory()) {
                            loadFolderContents(file);
                        } else {
                            JOptionPane.showMessageDialog(frame,
                                    "Would open: " + file.getAbsolutePath(),
                                    "File Selected", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    // A tree node for a folder, with a placeholder child so it shows an expand arrow
    private DefaultMutableTreeNode createNode(File folder) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(folder);
        node.add(new DefaultMutableTreeNode(PLACEHOLDER));
        return node;
    }

    // Replace the placeholder with the real sub-folders the first time a node is expanded
    private void loadChildren(DefaultMutableTreeNode node, DefaultTreeModel model) {
        if (node.getChildCount() == 1
                && PLACEHOLDER.equals(((DefaultMutableTreeNode) node.getFirstChild()).getUserObject())) {
            node.removeAllChildren();
            File folder = (File) node.getUserObject();
            File[] subFolders = folder.listFiles(f -> f.isDirectory() && !f.isHidden());
            if (subFolders != null) {
                Arrays.sort(subFolders, Comparator.comparing(f -> f.getName().toLowerCase()));
                for (File sub : subFolders) {
                    node.add(createNode(sub));
                }
            }
            model.nodeStructureChanged(node);
        }
    }

    // Load the contents of a folder into the table
    private void loadFolderContents(File folder) {
        if (!folder.isDirectory()) return;
        currentFolder = folder;
        tableModel.setRowCount(0); // clear

        File[] files = folder.listFiles();
        if (files != null) {
            // folders first, then files, each alphabetically
            Arrays.sort(files, Comparator
                    .comparing((File f) -> !f.isDirectory())
                    .thenComparing(f -> f.getName().toLowerCase()));

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            for (File file : files) {
                String type = file.isDirectory() ? "Folder" : getExtension(file.getName());
                String size = file.isDirectory() ? "-" : formatSize(file.length());
                String modified = sdf.format(file.lastModified());
                tableModel.addRow(new Object[]{file.getName(), type, size, modified});
            }
        }

        frame.setTitle("File Manager - " + folder.getAbsolutePath());
        statusBar.setText(files != null
                ? files.length + " items in " + (folder.getName().isEmpty() ? folder.getPath() : folder.getName())
                : "Could not read folder");
    }

    private String getExtension(String name) {
        int dot = name.lastIndexOf('.');
        return dot >= 0 ? name.substring(dot + 1).toUpperCase() : "File";
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        return String.format("%.1f MB", bytes / (1024.0 * 1024.0));
    }
}
