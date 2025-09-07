package com.Test.repository.respositoryImpl;

import com.Test.dto.BankBalanceDTO;
import com.Test.dto.TransactionDTO;
import com.Test.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private TransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionDTO txn = new TransactionDTO();
        txn.setId(rs.getInt("id"));
        txn.setDate(rs.getTimestamp("txn_date"));
        txn.setType(rs.getString("txn_type"));
        txn.setAmount(rs.getDouble("amount"));
        txn.setStatus(rs.getString("status"));
        return txn;
    }

    @Override
    public List<TransactionDTO> findTransactionsByUsername(String username) {
        String sql = """
            select tc.id,tc.txn_date,tc.txn_type,tc.amount,tc.status from  transactions tc Join users u ON u.id=tc.userid where u.email=? ORDER BY txn_date DESC
        """;
        return jdbcTemplate.query(sql, this::mapRow, username);
    }

    @Override
    public double getBalanceByUsername(String username) {
        String sql = """
            SELECT ub.balance FROM users u Join userbalance ub ON ub.userid =u.id WHERE u.email =?
        """;
        return jdbcTemplate.queryForObject(sql, Double.class, username);

    }
    @Override
    public String getuserinfo(String username) {
        String sql = """
                            SELECT u.name, b.branch_name, ub.balance
                            FROM users u
                            JOIN branch b ON b.id = u.branchId
                            Join userbalance ub ON ub.userid =u.id
                            WHERE u.email =?
                """;
        BankBalanceDTO userInfo = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                        new BankBalanceDTO(
                                rs.getString("name"),
                                rs.getString("branch_name")
                        ),
                username
        );return userInfo.toString();
    }


}
