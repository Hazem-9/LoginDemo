export interface LoginRequest {
  username: string;
  password: string;
}

export interface UserInfo {
  id: number;
  username: string;
  email: string;
  roles: string[];
}

export interface LoginResponse {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  user: UserInfo;
}
