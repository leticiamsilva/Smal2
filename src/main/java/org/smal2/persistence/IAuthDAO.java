package org.smal2.persistence;

import org.smal2.service.auth.RemoteLoginResponse;

public interface IAuthDAO {
	RemoteLoginResponse getRemoteLoginResponse(String username, String password);
}
