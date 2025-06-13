package GUI;


import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    
    public DashboardPanel() {
        setLayout(new BorderLayout(30, 30));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel lblTitle = new JLabel("Dashboard");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Panel thống kê
        JPanel statsPanel = createStatsPanel();
        
        // Panel biểu đồ
        JPanel chartsPanel = createChartsPanel();
        
        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        
        // Thêm các panel với khoảng cách
        mainPanel.add(statsPanel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(chartsPanel);
        
        add(lblTitle, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 30, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Thêm các card thống kê
        panel.add(createStatCard("Tổng số học sinh", "1,234", new Color(0, 102, 204)));
        panel.add(createStatCard("Tổng số giáo viên", "56", new Color(0, 153, 51)));
        panel.add(createStatCard("Tổng số lớp học", "32", new Color(204, 102, 0)));
        panel.add(createStatCard("Điểm trung bình", "7.5", new Color(153, 51, 153)));
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Arial", Font.BOLD, 32));
        lblValue.setForeground(color);
        lblValue.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblValue.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        panel.add(lblTitle);
        panel.add(lblValue);
        
        return panel;
    }
    
    private JPanel createChartsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 30, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Panel biểu đồ học lực
        JPanel chartPanel = new JPanel(new BorderLayout());
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        JLabel lblChartTitle = new JLabel("THCS Nguyễn Trãi", SwingConstants.CENTER);
        lblChartTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblChartTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Thêm ảnh biểu đồ
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/Dashboard.jpg"));
            Image image = originalIcon.getImage();
            // Scale ảnh để vừa với panel nhưng giữ tỷ lệ
            Image scaledImage = image.getScaledInstance(-1, 250, Image.SCALE_SMOOTH);
            JLabel lblChart = new JLabel(new ImageIcon(scaledImage));
            lblChart.setHorizontalAlignment(SwingConstants.CENTER);
            chartPanel.add(lblChart, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Không thể tải ảnh biểu đồ");
        }
        
        chartPanel.add(lblChartTitle, BorderLayout.NORTH);
        
        // Panel thông báo
        JPanel noticePanel = new JPanel(new BorderLayout(0, 10));
        noticePanel.setBackground(Color.WHITE);
        noticePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        JLabel lblNoticeTitle = new JLabel("Thông báo nhà trường", SwingConstants.CENTER);
        lblNoticeTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblNoticeTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Panel cuộn cho danh sách thông báo
        JPanel noticeList = new JPanel();
        noticeList.setLayout(new BoxLayout(noticeList, BoxLayout.Y_AXIS));
        noticeList.setBackground(Color.WHITE);
        
        // Thêm các thông báo mẫu
        String[] notices = {
            "<html><b>[20/03/2024]</b> Thông báo về lịch thi học kỳ II năm học 2023-2024</html>",
            "<html><b>[15/03/2024]</b> Kế hoạch tổ chức các hoạt động ngoại khóa tháng 4/2024</html>",
            "<html><b>[10/03/2024]</b> Thông báo về việc đóng học phí học kỳ II năm học 2023-2024</html>",
            "<html><b>[05/03/2024]</b> Lịch sinh hoạt chuyên môn các tổ bộ môn tháng 3/2024</html>",
            "<html><b>[01/03/2024]</b> Kế hoạch tổ chức Hội nghị học tập và làm theo tấm gương đạo đức Hồ Chí Minh</html>"
        };
        
        for (String notice : notices) {
            JLabel lblNotice = new JLabel(notice);
            lblNotice.setFont(new Font("Arial", Font.PLAIN, 14));
            lblNotice.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            noticeList.add(lblNotice);
            noticeList.add(Box.createVerticalStrut(10));
        }
        
        JScrollPane scrollPane = new JScrollPane(noticeList);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        noticePanel.add(lblNoticeTitle, BorderLayout.NORTH);
        noticePanel.add(scrollPane, BorderLayout.CENTER);
        
        panel.add(chartPanel);
        panel.add(noticePanel);
        
        return panel;
    }
} 