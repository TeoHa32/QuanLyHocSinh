package GUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentPanel extends JPanel {
    
    private JTable studentTable;
    private JTable parentTable;
    private JTextField txtSearch;
    private JTabbedPane tabbedPane;
    private DefaultTableModel studentModel;
    private DefaultTableModel parentModel;
    private JPanel filterPanel;
    private CardLayout cardLayout;
    private JPanel tableCards;
    private JPanel actionPanel;
    
    public StudentPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Tiêu đề
        JLabel lblTitle = new JLabel("Quản lý học sinh");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Tạo TabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Tab học sinh
        tabbedPane.addTab("Danh sách học sinh", createStudentListPanel());
        
        // Tab phụ huynh
        tabbedPane.addTab("Danh sách phụ huynh", createParentListPanel());
        
        add(lblTitle, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createStudentListPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBackground(Color.WHITE);
        
        // Panel chức năng (tìm kiếm và các nút)
        panel.add(createActionPanel(), BorderLayout.NORTH);
        
        // Panel bảng dữ liệu học sinh
        panel.add(createStudentTablePanel(), BorderLayout.CENTER);
        
        // Panel phân trang và thao tác
        panel.add(createBottomPanel(), BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createParentListPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBackground(Color.WHITE);
        
        // Panel tìm kiếm phụ huynh
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        
        JTextField txtParentSearch = new JTextField(20);
        JButton btnParentSearch = new JButton("Tìm kiếm");
        btnParentSearch.setPreferredSize(new Dimension(100, 35));
        btnParentSearch.setBackground(new Color(0, 102, 204));
        btnParentSearch.setForeground(Color.BLACK);
        btnParentSearch.setFocusPainted(false);
        btnParentSearch.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Bộ lọc quan hệ
        String[] relationships = {"Tất cả quan hệ", "Cha", "Mẹ", "Người giám hộ"};
        JComboBox<String> cboRelationship = new JComboBox<>(relationships);
        cboRelationship.setPreferredSize(new Dimension(150, 35));
        cboRelationship.setBackground(Color.WHITE);
        cboRelationship.setFont(new Font("Arial", Font.PLAIN, 14));
        
        searchPanel.add(new JLabel("Tìm kiếm: "));
        searchPanel.add(txtParentSearch);
        searchPanel.add(btnParentSearch);
        searchPanel.add(Box.createHorizontalStrut(20));
        searchPanel.add(new JLabel("Quan hệ:"));
        searchPanel.add(cboRelationship);
        
        // Tạo bảng phụ huynh
        String[] parentColumns = {
            "Mã PH", "Họ tên phụ huynh", "Quan hệ", "Học sinh", "Địa chỉ", 
            "Số điện thoại", "Email", "Nghề nghiệp"
        };
        
        DefaultTableModel parentModel = new DefaultTableModel(parentColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Thêm dữ liệu mẫu
        Object[][] sampleParentData = {
            {"PH001", "Nguyễn Văn X", "Cha", "Nguyễn Văn A", "Hà Nội", "0987654321", "nvx@email.com", "Kỹ sư"},
            {"PH002", "Trần Thị Y", "Mẹ", "Trần Thị B", "Hà Nội", "0987654322", "tty@email.com", "Giáo viên"},
            {"PH003", "Lê Văn Z", "Cha", "Lê Văn C", "Hà Nội", "0987654323", "lvz@email.com", "Bác sĩ"},
            {"PH004", "Phạm Thị W", "Mẹ", "Phạm Thị D", "Hà Nội", "0987654324", "ptw@email.com", "Kế toán"},
            {"PH005", "Hoàng Văn V", "Cha", "Hoàng Văn E", "Hà Nội", "0987654325", "hvv@email.com", "Kinh doanh"}
        };
        
        for (Object[] row : sampleParentData) {
            parentModel.addRow(row);
        }
        
        parentTable = new JTable(parentModel);
        parentTable.setRowHeight(35);
        parentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        parentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        // Thêm các component vào panel
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(parentTable), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        
        // Panel tìm kiếm và lọc
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        
        // Ô tìm kiếm
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.setPreferredSize(new Dimension(100, 35));
        btnSearch.setBackground(new Color(0, 102, 204));
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setFocusPainted(false);
        btnSearch.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Bộ lọc cho học sinh
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filterPanel.setBackground(Color.WHITE);
        
        // ComboBox cho lớp
        String[] classes = {"Tất cả lớp", "10A1", "10A2", "10A3", "11A1", "11A2", "11A3", "12A1", "12A2", "12A3"};
        JComboBox<String> cboClass = new JComboBox<>(classes);
        
        // ComboBox cho giới tính
        String[] genders = {"Tất cả giới tính", "Nam", "Nữ"};
        JComboBox<String> cboGender = new JComboBox<>(genders);
        
        // Style cho combobox
        for (JComboBox<String> cbo : new JComboBox[]{cboClass, cboGender}) {
            cbo.setPreferredSize(new Dimension(140, 35));
            cbo.setBackground(Color.WHITE);
            cbo.setFont(new Font("Arial", Font.PLAIN, 14));
        }
        
        // Thêm components vào search panel
        searchPanel.add(new JLabel("Tìm kiếm: "));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        
        // Thêm các bộ lọc học sinh
        filterPanel.add(new JLabel("Lớp:"));
        filterPanel.add(cboClass);
        filterPanel.add(new JLabel("Giới tính:"));
        filterPanel.add(cboGender);
        
        searchPanel.add(filterPanel);
        panel.add(searchPanel, BorderLayout.WEST);
        
        return panel;
    }
    
    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    private JScrollPane createStudentTablePanel() {
        // Tạo model cho bảng
        String[] columns = {
            "Mã HS", "Họ và tên", "Ngày sinh", "Giới tính", 
            "Địa chỉ", "Số điện thoại","Số điện thoại phụ huynh", "Lớp","Năm học",
        };
        
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Thêm dữ liệu mẫu
        Object[][] sampleData = {
            {"HS001", "Nguyễn Văn A", "01/01/2005", "Nam", "Hà Nội", "0123456789", "10A1"},
            {"HS002", "Trần Thị B", "02/02/2005", "Nữ", "Hà Nội", "0123456788", "10A1"},
            {"HS003", "Lê Văn C", "03/03/2005", "Nam", "Hà Nội", "0123456787", "10A2"},
            {"HS004", "Phạm Thị D", "04/04/2005", "Nữ", "Hà Nội", "0123456786", "10A2"},
            {"HS005", "Hoàng Văn E", "05/05/2005", "Nam", "Hà Nội", "0123456785", "10A3"}
        };
        
        for (Object[] row : sampleData) {
            model.addRow(row);
        }
        
        studentTable = new JTable(model);
        studentTable.setRowHeight(35);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        studentTable.getTableHeader().setBackground(new Color(240, 240, 240));
        
        // Cho phép chọn nhiều dòng
        studentTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        return new JScrollPane(studentTable);
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Panel phân trang
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        paginationPanel.setBackground(Color.WHITE);
        
        // Panel nút chức năng
        actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setBackground(Color.WHITE);
        
        // Tạo các button với icon
        JButton btnAdd = createIconButton("Thêm mới", "add.png", new Color(0, 153, 51));
        JButton btnView = createIconButton("Xem chi tiết", "see.png", new Color(51, 153, 255));
        JButton btnEdit = createIconButton("Sửa", "updates.png", new Color(255, 153, 51));
        JButton btnDelete = createIconButton("Xóa", "delete.png", new Color(255, 51, 51));
        JButton btnImport = createIconButton("Nhập Excel", "import.png", new Color(102, 51, 153));
        JButton btnExport = createIconButton("Xuất Excel", "export.png", new Color(51, 102, 153));
        
        // Thêm các button vào panel
        actionPanel.add(btnAdd);
        actionPanel.add(btnView);
        actionPanel.add(btnEdit);
        actionPanel.add(btnDelete);
        actionPanel.add(btnImport);
        actionPanel.add(btnExport);
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Thêm xử lý sự kiện cho các button
        btnImport.addActionListener(e -> importExcel());
        btnExport.addActionListener(e -> exportExcel());
        
        panel.add(paginationPanel, BorderLayout.WEST);
        panel.add(actionPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    // Phương thức hỗ trợ tạo button với icon
    private JButton createIconButton(String text, String iconName, Color bgColor) {
        JButton button = new JButton(text);
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/" + iconName));
            Image img = originalIcon.getImage();
            Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newImg);
            
            button.setIcon(icon);
            button.setHorizontalTextPosition(JButton.RIGHT);
            button.setIconTextGap(10);
        } catch (Exception e) {
            System.out.println("Không thể tải icon: " + iconName);
        }
        
        button.setPreferredSize(new Dimension(150, 35));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setMargin(new Insets(0, 10, 0, 10));
        
        return button;
    }
    
    // Các phương thức xử lý sự kiện
    private void showAddStudentDialog() {
        JOptionPane.showMessageDialog(this, "Chức năng thêm học sinh mới");
        // TODO: Implement add student dialog
    }
    
    private void showStudentDetails() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            String studentId = (String) studentTable.getValueAt(selectedRow, 0);
            JOptionPane.showMessageDialog(this, "Xem chi tiết học sinh: " + studentId);
            // TODO: Implement view student details
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh!");
        }
    }
    
    private void editStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            String studentId = (String) studentTable.getValueAt(selectedRow, 0);
            JOptionPane.showMessageDialog(this, "Sửa thông tin học sinh: " + studentId);
            // TODO: Implement edit student
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh cần sửa!");
        }
    }
    
    private void deleteStudent() {
        int[] selectedRows = studentTable.getSelectedRows();
        if (selectedRows.length > 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa " + selectedRows.length + " học sinh đã chọn?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Xóa từ dưới lên để không bị lỗi index
                DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    model.removeRow(selectedRows[i]);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn học sinh cần xóa!");
        }
    }
    
    private void importExcel() {
        JOptionPane.showMessageDialog(this, "Chức năng nhập dữ liệu từ Excel");
        // TODO: Implement import from Excel
    }
    
    private void exportExcel() {
        JOptionPane.showMessageDialog(this, "Chức năng xuất dữ liệu ra Excel");
        // TODO: Implement export to Excel
    }
    
    private void showParentDetails(String parentId) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thông tin chi tiết phụ huynh");
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Thêm các trường thông tin
        String[] labels = {
            "Mã phụ huynh:", "Họ tên:", "Quan hệ với học sinh:",
            "Địa chỉ:", "Số điện thoại:", "Email:",
            "Nghề nghiệp:", "Nơi làm việc:", "Ghi chú:"
        };
        
        for (String label : labels) {
            panel.add(new JLabel(label), gbc);
            gbc.gridx = 1;
            panel.add(new JTextField(20), gbc);
            gbc.gridx = 0;
            gbc.gridy++;
        }
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
} 