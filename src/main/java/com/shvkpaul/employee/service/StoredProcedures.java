package com.shvkpaul.employee.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoredProcedures {
    public static void deleteRoleAndReassignProjects(Connection conn, long roleId, long defaultEmployeeId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE project SET employee_id = ? WHERE employee_id IN (SELECT id FROM employee WHERE role_id = ?)"
        )) {
            ps.setLong(1, defaultEmployeeId);
            ps.setLong(2, roleId);
            ps.executeUpdate();
        }

        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE role_id = ?")) {
            ps.setLong(1, roleId);
            ps.executeUpdate();
        }

        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM role WHERE id = ?")) {
            ps.setLong(1, roleId);
            ps.executeUpdate();
        }
    }
}
