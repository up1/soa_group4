package app.soa4.Modal;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MatchingRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;  
    
    @Transactional(readOnly = true)
    public List<Matching> listMatching(int id, double lat, double lon, String sex, String sexual_taste, int min_age, int max_age, double distance) {
        final double P = 0.017453292519943295;
        long dateInMillis = new Date().getTime();
            return this.jdbcTemplate.query("SELECT account_id, account_username, account_name, account_lastname, ((? - account_birthday)/(24*3600000))/365 AS account_age, account_location, account_descriptions, account_sexual_taste, " +
                    "(12742 * ASIN(SQRT(0.5 - COS((? - account_latitude) * ?)/2 + COS(account_latitude * ?) * COS(? * ?) * (1 - COS((? - account_longtitude) * ?))/2))) AS account_distance " +
                    "FROM ACCOUNT " +
                    "WHERE account_id != ?" +
                    " AND (12742 * ASIN(SQRT(0.5 - COS((? - account_latitude) * ?)/2 + COS(account_latitude * ?) * COS(? * ?) * (1 - COS((? - account_longtitude) * ?))/2))) <= ?" +
                    " AND account_sex = ?" +
                    " AND account_sexual_taste = ?" +
                    " AND ? <= ((? - account_birthday)/(24*3600000))/365 <= ?" +
                    " AND account_id NOT IN (SELECT matching_account_done " +
                    "FROM MATCHING WHERE matching_account_do = ?)", new Object[]{dateInMillis,lat,P,P,lat,P,lon,P,id,lat,P,P,lat,P,lon,P,distance,sex,sexual_taste,min_age,dateInMillis,max_age,id}, new MatchingRowMapper());
    }

    @Transactional
    public Searching getSearchingData(int id) {
        return this.jdbcTemplate.queryForObject("SELECT search_id, search_latitude, search_longtitude, search_sex, search_sexual_taste, search_min_age, search_max_age, search_distance FROM SEARCHING WHERE account_id = ?", new Object[]{id}, new SearchingRowMapper());
    }

    @Transactional
    public void makeStatus(int account_do , int account_done, int status){
        String sql = "INSERT INTO MATCHING(matching_account_do, matching_account_done, matching_status) values (?,?,?)";
        this.jdbcTemplate.update(sql, account_do, account_done, status);
    }

    @Transactional
    public void unmatchUpdate(int account_do , int account_done, int status){
        String sql = "UPDATE MATCHING SET matching_status = ? WHERE matching_account_do = ?, matching_account_done = ?";
        this.jdbcTemplate.update(sql,status,account_do,account_done);
    }

}
