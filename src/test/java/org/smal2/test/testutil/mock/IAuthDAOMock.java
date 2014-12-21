package org.smal2.test.testutil.mock;

import org.smal2.persistence.IAuthDAO;
import org.springframework.stereotype.Component;

@Component
public class IAuthDAOMock extends AInMemoryDAO<String[], Long> implements
		IAuthDAO {

	private static long sequence = 0;

	public void create(String username, String password) {
		String[] user = new String[2];
		user[0] = username;
		user[1] = password;
		createByKey(user, ++sequence);
	}

	@Override
	public String getRemoteLoginMessage(String username, String password) {
		for (String[] user : readAll()) {
			if (user[0].equals(username) && user[1].equals(password)) {

				return "{\"token\":\"000a000aaaaa00aa00a00aa0a0000000\"}";
			}
		}

		return "{\"error\":\"The username was not found in the database\",\"stacktrace\":null,\"debuginfo\":null,\"reproductionlink\":null}";
	}
}