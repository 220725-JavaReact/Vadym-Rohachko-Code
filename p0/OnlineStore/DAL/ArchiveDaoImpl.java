package DAL;

import Interfaces.IArchiveDao;
import Models.Archive;
import Models.Product;
import Util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArchiveDaoImpl implements IArchiveDao {
    @Override
    public ArrayList<Archive> getArchiveByUserId(int userId) {
        try {
            Connection conn = ConnectionFactory.getInstance().getConnection();
            String query = "select * from archives where user_id = ? order by order_id;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ArrayList<Archive> archives = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Archive archive = extractArchiveFromResultSet(rs);
                archives.add(archive);
            }
            return archives;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Archive extractArchiveFromResultSet(ResultSet rs) throws SQLException {
        Archive archive = new Archive(
                rs.getInt("user_id"),
                rs.getInt("order_id"),
                rs.getInt("item_id"),
                rs.getInt("store_id"),
                rs.getInt("product_id"),
                rs.getInt("category_id"),
                rs.getString("lname"),
                rs.getString("fname"),
                rs.getString("product_name"),
                rs.getString("description"),
                rs.getString("category"),
                rs.getString("store_location"),
                rs.getInt("quantity"),
                rs.getBigDecimal("price_per_item"),
                rs.getTimestamp("order_created_at"),
                rs.getTimestamp("archive_created_at"));
        return archive;
    }
}

