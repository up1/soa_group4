package com.soa4.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Boeing on 4/28/2017.
 */

@Repository
public class ReportRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void requestReport(int report_bannerID, int report_bannedAccountID, String report_description){
        String sql = "INSERT INTO REPORT(reporter_id, reported_id, report_topic) values (?,?,?)";
        jdbcTemplate.update(sql, report_bannerID, report_bannedAccountID, report_description);

    }
}
