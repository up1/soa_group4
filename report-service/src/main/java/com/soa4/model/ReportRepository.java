package com.soa4.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public class ReportRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void requestReport(int reporter_id, int reported_id, String report_topic){
        String sql = "INSERT INTO REPORT(reporter_id, reported_id, report_topic) values (?,?,?)";
        jdbcTemplate.update(sql, reporter_id, reported_id, report_topic);
    }

    @Transactional(readOnly = true)
    public ArrayList<Report> showReport(long userid){
        String sql = "SELECT reporter_id, reported_id, report_topic FROM REPORT WHERE reported_id = ?";
        ArrayList<Report> report = (ArrayList)this.jdbcTemplate.query(sql, new Object[] { userid }, new BeanPropertyRowMapper(Report.class));
        return report;
    }
}
