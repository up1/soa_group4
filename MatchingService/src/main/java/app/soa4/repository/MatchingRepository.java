package app.soa4.repository;

import java.util.Date;
import java.util.List;

import app.soa4.model.CreateNotification;
import app.soa4.model.Matching;
import app.soa4.model.Searching;
import app.soa4.model.SuperlikeCheck;
import app.soa4.repository.MatchingRowMapper;
import app.soa4.repository.NotificationRowMapper;
import app.soa4.repository.SearchingRowMapper;
import app.soa4.repository.SuperlikeCheckRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MatchingRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    @Transactional(readOnly = true)
    public List<Matching> listMatching(int id, Searching searchingData) {
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
                    "FROM MATCHING WHERE matching_account_do = ?)", new Object[]{dateInMillis,searchingData.getLat(),
                    P,P,searchingData.getLat(),P,searchingData.getLon(),P,id,searchingData.getLat(),P,P,
                    searchingData.getLat(),P,searchingData.getLon(),P,searchingData.getDistance(),
                    searchingData.getSex(),searchingData.getSexual_taste(),searchingData.getMin_age(),
                    dateInMillis,searchingData.getMax_age(),id}, new MatchingRowMapper());
    }

    @Transactional
    public Searching getSearchingData(int id) {
        return this.jdbcTemplate.queryForObject("SELECT search_id, search_latitude, search_longtitude, search_sex, search_sexual_taste, search_min_age, search_max_age, search_distance FROM SEARCHING WHERE account_id = ?", new Object[]{id}, new SearchingRowMapper());
    }

    @Transactional
    public List<CreateNotification> makeStatus(int account_do , int account_done, int status){
        String sql = "INSERT INTO MATCHING(matching_account_do, matching_account_done, matching_status) values (?,?,?)";
        this.jdbcTemplate.update(sql, account_do, account_done, status);

        String sql_check = "SELECT matching_account_do, matching_account_done FROM MATCHING WHERE matching_account_do = ? AND matching_account_done = ? AND matching_status != 3";
        return this.jdbcTemplate.query(sql_check, new Object[]{account_done, account_do}, new NotificationRowMapper());
    }

    @Transactional
    public void unmatchUpdate(int account_do , int account_done, int status){
        String sql = "UPDATE MATCHING SET matching_status = ? WHERE matching_account_do = ? AND matching_account_done = ?";
        this.jdbcTemplate.update(sql,status,account_do,account_done);
        this.jdbcTemplate.update(sql,status,account_done,account_do);
    }

    @Transactional
    public List<SuperlikeCheck> checkSuperlike(int account_do, int account_done){
        String sql = "SELECT matching_status FROM MATCHING WHERE matching_account_do = ? AND matching_account_done = ? AND matching_status = 1";
        return this.jdbcTemplate.query(sql, new Object[]{account_do, account_done}, new SuperlikeCheckRowMapper());
    }

}
