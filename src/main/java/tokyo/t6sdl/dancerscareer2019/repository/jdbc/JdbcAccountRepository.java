package tokyo.t6sdl.dancerscareer2019.repository.jdbc;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tokyo.t6sdl.dancerscareer2019.model.Account;
import tokyo.t6sdl.dancerscareer2019.repository.AccountRepository;

@Repository
public class JdbcAccountRepository implements AccountRepository {
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcAccountRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Account> find() {
		return jdbcTemplate.query(
				"SELECT * FROM accounts ORDER BY created_at ASC", (resultSet, i) -> {
					Account account = new Account();
					account.setEmail(resultSet.getString("email"));
					account.setPassword(resultSet.getString("password"));
					account.setAuthority(resultSet.getString("authority"));
					account.setEnabled(resultSet.getBoolean("enabled"));
					account.setUpdated_at(resultSet.getTimestamp("updated_at"));
					account.setCreated_at(resultSet.getTimestamp("created_at"));
					account.setEmail_token(resultSet.getString("email_token"));
					account.setPassword_token(resultSet.getString("password_token"));
					return account;
		});
	}

	@Override
	public Account findOneByEmail(String email) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM accounts WHERE email=?", (resultSet, i) -> {
						Account account = new Account();
						account.setEmail(resultSet.getString("email"));
						account.setPassword(resultSet.getString("password"));
						account.setAuthority(resultSet.getString("authority"));
						account.setEnabled(resultSet.getBoolean("enabled"));
						account.setUpdated_at(resultSet.getTimestamp("updated_at"));
						account.setCreated_at(resultSet.getTimestamp("created_at"));
						account.setEmail_token(resultSet.getString("email_token"));
						account.setPassword_token(resultSet.getString("password_token"));
						return account;
					}, email);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Account findOneByEmailToken(String emailToken) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM accounts WHERE email_token = ?", (resultSet, i) -> {
						Account account = new Account();
						account.setEmail(resultSet.getString("email"));
						account.setPassword(resultSet.getString("password"));
						account.setAuthority(resultSet.getString("authority"));
						account.setEnabled(resultSet.getBoolean("enabled"));
						account.setUpdated_at(resultSet.getTimestamp("updated_at"));
						account.setCreated_at(resultSet.getTimestamp("created_at"));
						account.setEmail_token(resultSet.getString("email_token"));
						account.setPassword_token(resultSet.getString("password_token"));
						return account;
					}, emailToken);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Account findOneByPasswordToken(String passwordToken) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM accounts WHERE password_token = ?", (resultSet, i) -> {
						Account account = new Account();
						account.setEmail(resultSet.getString("email"));
						account.setPassword(resultSet.getString("password"));
						account.setAuthority(resultSet.getString("authority"));
						account.setEnabled(resultSet.getBoolean("enabled"));
						account.setUpdated_at(resultSet.getTimestamp("updated_at"));
						account.setCreated_at(resultSet.getTimestamp("created_at"));
						account.setEmail_token(resultSet.getString("email_token"));
						account.setPassword_token(resultSet.getString("password_token"));
						return account;
					}, passwordToken);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void insert(Account newAccount) {
		jdbcTemplate.update(
				"INSERT INTO accounts (email, password) VALUES (?, ?)",
				newAccount.getEmail(), newAccount.getPassword());
	}

	@Override
	public void delete(String loggedInEmail) {
		jdbcTemplate.update(
				"DELETE FROM accounts WHERE email = ?",
				loggedInEmail);
	}

	@Override
	public void updateEmail(String loggedInEmail, String newEmail) {
		jdbcTemplate.update(
				"UPDATE accounts SET email = ?, updated_at = CURRENT_TIMESTAMP WHERE email = ?",
				newEmail, loggedInEmail);
	}

	@Override
	public void updatePassword(String loggedInEmail, String newPassword) {
		jdbcTemplate.update(
				"UPDATE accounts SET password = ?, updated_at = CURRENT_TIMESTAMP WHERE email = ?",
				newPassword, loggedInEmail);
	}

	@Override
	public void updateEnabled(String loggedInEmail, boolean isEnabled) {
		jdbcTemplate.update(
				"UPDATE accounts SET enabled = ?, updated_at = CURRENT_TIMESTAMP WHERE email = ?",
				isEnabled, loggedInEmail);
	}

	@Override
	public void recordEmailToken(String loggedInEmail, String emailToken) {
		jdbcTemplate.update(
				"UPDATE accounts SET email_token = ? WHERE email = ?",
				emailToken, loggedInEmail);
	}

	@Override
	public void refreshEmailToken(String loggedInEmail) {
		jdbcTemplate.update(
				"UPDATE accounts SET email_token = NULL WHERE email = ?",
				loggedInEmail);
	}

	@Override
	public void recordPasswordToken(String loggedInEmail, String passwordToken) {
		jdbcTemplate.update(
				"UPDATE accounts SET password_token = ? WHERE email = ?",
				passwordToken, loggedInEmail);
	}

	@Override
	public void refreshPasswordToken(String loggedInEmail) {
		jdbcTemplate.update(
				"UPDATE accounts SET password_token = NULL WHERE email = ?",
				loggedInEmail);
	}
}