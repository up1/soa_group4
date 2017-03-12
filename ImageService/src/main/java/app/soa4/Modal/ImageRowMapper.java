package app.soa4.Modal;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRowMapper implements RowMapper<Image> {

    public Image mapRow(ResultSet resultSet, int i) throws SQLException {
        Image image = new Image();
        image.setId(resultSet.getLong("image_id"));
        image.setImageType(resultSet.getString("image_type"));
        image.setImageName(resultSet.getString("image_name"));
        image.setImagePath(resultSet.getString("image_path"));
        image.setAccountId(resultSet.getLong("account_id"));
        return image;
    }
}
