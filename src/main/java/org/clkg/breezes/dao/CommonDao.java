package org.clkg.breezes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int execute(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    public int insert(String table, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `").append(table).append("` (");
        List args = new ArrayList();
        map.forEach((key, value) -> {
            if (!key.equalsIgnoreCase("id")) {
                if (!args.isEmpty()) {
                    sb.append(", ");
                }
                sb.append("`").append(key).append("`");
                args.add(value);
            }
        });
        sb.append(") values (");
        for (int i = 0; i < args.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("?");
        }
        sb.append(")");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= args.size(); i++) {
                ps.setObject(i, args.get(i - 1));
            }
            return ps;
        }, keyHolder);
        map.put("id", keyHolder.getKey().intValue());
        return result;
    }

    public int update(String table, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("update `").append(table).append("` set ");
        List args = new ArrayList();
        map.forEach((key, value) -> {
            if (!key.equalsIgnoreCase("id")) {
                if (!args.isEmpty()) {
                    sb.append(", ");
                }
                sb.append("`").append(key).append("` = ?");
                args.add(value);
            }
        });
        sb.append(" where `id` = ?");
        args.add(map.get("id"));
        int result = jdbcTemplate.update(sb.toString(), args.toArray());
        return result;
    }

    public int delete(String table, int id) {
        int result = jdbcTemplate.update("delete from `" + table + "` where `id` = ?", id);
        return result;
    }

    public List<Map<String, Object>> find(String sql, Object... args) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        return list;
    }

    public Map<String, Object> findByPage(int pageNumber, int pageSize, String sql, Object... args) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> count = jdbcTemplate.queryForList("select count(*) total" + sql.substring(sql.toLowerCase().indexOf(" from ")), args);
        int total = Integer.parseInt(count.get(0).get("total").toString());
        if (total <= pageSize * (pageNumber - 1)) {
            pageNumber = total / pageSize;
            if (total % pageSize != 0) {
                pageNumber++;
            }
        }
        int limit = pageSize * (pageNumber - 1);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql + " limit " + limit + ", " + pageSize, args);
        result.put("total", total);
        result.put("rows", list);
        result.put("pageNumber", pageNumber);
        result.put("pageSize", pageSize);
        return result;
    }

    public int execute(JdbcTemplate jdbcTemplate, String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    public int insert(JdbcTemplate jdbcTemplate, String table, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into `").append(table).append("` (");
        List args = new ArrayList();
        map.forEach((key, value) -> {
            if (!key.equalsIgnoreCase("id")) {
                if (!args.isEmpty()) {
                    sb.append(", ");
                }
                sb.append("`").append(key).append("`");
                args.add(value);
            }
        });
        sb.append(") values (");
        for (int i = 0; i < args.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("?");
        }
        sb.append(")");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= args.size(); i++) {
                ps.setObject(i, args.get(i - 1));
            }
            return ps;
        }, keyHolder);
        map.put("id", keyHolder.getKey().intValue());
        return result;
    }

    public int update(JdbcTemplate jdbcTemplate, String table, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("update `").append(table).append("` set ");
        List args = new ArrayList();
        map.forEach((key, value) -> {
            if (!key.equalsIgnoreCase("id")) {
                if (!args.isEmpty()) {
                    sb.append(", ");
                }
                sb.append("`").append(key).append("` = ?");
                args.add(value);
            }
        });
        sb.append(" where `id` = ?");
        args.add(map.get("id"));
        int result = jdbcTemplate.update(sb.toString(), args.toArray());
        return result;
    }

    public int delete(JdbcTemplate jdbcTemplate, String table, int id) {
        int result = jdbcTemplate.update("delete from `" + table + "` where `id` = ?", id);
        return result;
    }

    public List<Map<String, Object>> find(JdbcTemplate jdbcTemplate, String sql, Object... args) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        return list;
    }

    public Map<String, Object> findByPage(JdbcTemplate jdbcTemplate, int pageNumber, int pageSize, String sql, Object... args) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> count = jdbcTemplate.queryForList("select count(*) total" + sql.substring(sql.toLowerCase().indexOf(" from ")), args);
        int total = Integer.parseInt(count.get(0).get("total").toString());
        if (total <= pageSize * (pageNumber - 1)) {
            pageNumber = total / pageSize;
            if (total % pageSize != 0) {
                pageNumber++;
            }
        }
        int limit = pageSize * (pageNumber - 1);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql + " limit " + limit + ", " + pageSize, args);
        result.put("total", total);
        result.put("rows", list);
        result.put("pageNumber", pageNumber);
        result.put("pageSize", pageSize);
        return result;
    }
}
