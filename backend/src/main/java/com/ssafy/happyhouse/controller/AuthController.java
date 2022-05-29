package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.happyhouse.secutiry.JwtTokenProvider;
import com.ssafy.happyhouse.service.UserService;
import com.ssafy.happyhouse.vo.UserVO;

@RestController
@CrossOrigin("*")
public class AuthController {
    private final JwtTokenProvider provider;
    private final UserService service;
    
    public AuthController(JwtTokenProvider provider, UserService service) {
		this.provider = provider;
		this.service = service;
	}
	
    @Transactional
	@PostMapping("/auth/kakao")
	public ResponseEntity<?> kakaoAuth(@RequestBody Map<String, String> map, HttpSession session) {
		String code = map.get("code");
		String AccessToken = getKaKaoAccessToken(code);
		UserVO u = createKakaoUser(AccessToken);
		
		try {
			UserVO user = service.readUser(u.getUsername());
    		
			if (user == null) {
				service.signUp(u);
				service.Authinit(u.getUsername());
				user = u;
			}
	        
	        session.setAttribute("actk", AccessToken);
	        
    	    return new ResponseEntity<String>(
    	    		provider.createToken(user.getUsername(), user.getEmail(), user.getNickname(), service.getAuthorities(user.getUsername())), 
    	    		HttpStatus.OK
    	    	);
    	} catch(UsernameNotFoundException e) {
    		System.out.println(e);
    	}
    	
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	public String getKaKaoAccessToken(String code) {
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=2ff47a7a6e530bc10f4851a981a5dde6"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:8080/oauth/redirect/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }
	
	public UserVO createKakaoUser(String token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            String username = element.getAsJsonObject().get("id").getAsString();
            String email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            String nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();

            System.out.println("id : " + username);
            System.out.println("email : " + email);
            br.close();
            
            UserVO user = new UserVO();
            user.setUsername(username);
            user.setEmail(email);
            user.setNickname(nickname);
            user.setPassword("kakaouser");

            return user;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Transactional
	@PostMapping("/auth/naver")
	public ResponseEntity<?> NaverAuth(@RequestBody Map<String, String> map, HttpSession session) {
		String code = map.get("code");
		String AccessToken = getNaverAccessToken(code);
		UserVO u = createNaverUser(AccessToken);
		
		try {
			UserVO user = service.readUser(u.getUsername());
    		
			if (user == null) {
				service.signUp(u);
				service.Authinit(u.getUsername());
				user = u;
			}
	        
	        session.setAttribute("actk", AccessToken);
	        
    	    return new ResponseEntity<String>(
    	    		provider.createToken(user.getUsername(), user.getEmail(), user.getNickname(), service.getAuthorities(user.getUsername())), 
    	    		HttpStatus.OK
    	    	);
    	} catch(UsernameNotFoundException e) {
    		System.out.println(e);
    	}
    	
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
    
    public String getNaverAccessToken(String code) {
        String access_Token="";
        String refresh_Token ="";
        int expires_in = 0;
        
        String reqURL = "https://nid.naver.com/oauth2.0/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=_HwKQIvbYYXwEwkKhtCl"); // TODO REST_API_KEY 입력
            sb.append("&client_secret=X9OkJrAa52"); // TODO REST_API_KEY 입력
            sb.append("&state=RANDOMSTATE"); // 애플리케이션에서 생성한 상태 토큰값
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            expires_in = element.getAsJsonObject().get("expires_in").getAsInt();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
            System.out.println("expires_in : " + expires_in);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

	public UserVO createNaverUser(String token) {
        String reqURL = "https://openapi.naver.com/v1/nid/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
            String username = response.get("id").getAsString();
            String email = response.get("email").getAsString();
            String nickname = response.get("nickname").getAsString();

            System.out.println("id : " + username);
            System.out.println("email : " + email);
            br.close();
            
            UserVO user = new UserVO();
            user.setUsername(username);
            user.setEmail(email);
            user.setNickname(nickname);
            user.setPassword("naveruser");

            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}

