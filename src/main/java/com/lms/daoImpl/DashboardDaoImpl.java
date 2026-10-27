package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lms.dao.DashboardDao;
import com.lms.pojo.DashboardStats;
import com.lms.util.DbUtil;

public class DashboardDaoImpl implements DashboardDao {

    private static final String DASHBOARD_QUERY =
            "SELECT " +
            "    (SELECT COUNT(*) FROM BOOKS) AS total_books, " +
            "    (SELECT COUNT(*) FROM BOOK_ISSUED WHERE STATUS = 'ISSUED') AS total_assigned_books, " +
            "    (SELECT COUNT(*) FROM BOOK_ISSUED WHERE STATUS = 'RETURNED') AS total_returned_books, " +
            "    (SELECT COUNT(*) FROM USERS WHERE ROLE = 'USER') AS total_users";

    @Override
    public DashboardStats getDashboardData() {

        DashboardStats stats = new DashboardStats();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(DASHBOARD_QUERY);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                stats.setTotalBooks(rs.getInt("total_books"));
                stats.setBooksAssigned(rs.getInt("total_assigned_books"));
                stats.setBooksReturned(rs.getInt("total_returned_books"));
                stats.setTotalUsers(rs.getInt("total_users"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Dashboard istatistikleri alınırken hata oluştu.", e
            );
        }

        return stats;
    }
}