package org.smal2.test.testutil.mock;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.smal2.persistence.IAuthDAO;
import org.smal2.service.auth.RemoteLoginResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthDAOMock extends AInMemoryDAO<String[], Long> implements
		IAuthDAO {

	private static long sequence = 0;

	private String successString = "{\"token\":\"000a000aaaaa00aa00a00aa0a0000000\"}";
	private String errorString = "{\"error\":\"The username was not found in the database\",\"stacktrace\":null,\"debuginfo\":null,\"reproductionlink\":null}";

	public void create(String username, String password) {
		String[] user = new String[2];
		user[0] = username;
		user[1] = password;
		createByKey(user, ++sequence);
	}

	@Override
	public RemoteLoginResponse getRemoteLoginResponse(String username,
			String password) {

		String messageResponse = errorString;

		for (String[] user : readAll()) {
			if (user[0].equals(username) && user[1].equals(password)) {

				messageResponse = successString;
				break;
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		RemoteLoginResponse response;

		try {
			response = mapper.readValue(messageResponse,
					RemoteLoginResponse.class);
		} catch (Exception ex) {
			response = new RemoteLoginResponse(null,
					"Jackson mapper error.\nResponse message: "
							+ messageResponse + "\nError message: "
							+ ex.getMessage());
		}

		return response;
	}
}