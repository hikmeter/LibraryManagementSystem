package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.UserDao;
import com.lms.pojo.User;
import com.lms.util.DbUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User checkLogin(String username, String password) {
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			String sql = "Select * from users where email = ? and password = ?";
			connection = DbUtil.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next())
			{
				User user = new User();
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setPhoneNo(rs.getString("phone_no"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}

	@Override
	public boolean addUser(User user) {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO users(first_name, last_name, email, password, role, phone_no, "
                    + "address, created_at) VALUES(?,?,?,?,?,?,?,?)";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getPhoneNo());
            ps.setString(7, user.getAddress());
            
            Date sqlDate = new Date(user.getCreatedAt().getTime());
            ps.setDate(8, sqlDate);

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

	@Override
	public List<User> getAllUsers() {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<User> userList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM users WHERE role = 'USER' && is_deleted = FALSE ORDER BY user_id DESC";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                
                user.setUserId(rs.getLong("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setPhoneNo(rs.getString("phone_no"));
                user.setAddress(rs.getString("address"));
                user.setCreatedAt(rs.getDate("created_at"));

                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userList;
	}

	@Override
	public boolean deleteUser(long id) {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE users SET is_deleted = TRUE "
                    + "WHERE user_id = ?";

            connection = DbUtil.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setLong(1, id);

            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
	}
	}


