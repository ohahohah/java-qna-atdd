package codesquad.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import codesquad.domain.UserRepository;
import java.util.Arrays;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.test.AcceptanceTest;

public class LoginAcceptanceTest  extends AcceptanceTest {
  private static final Logger log = LoggerFactory.getLogger(UserAcceptanceTest.class);

  @Autowired
  private UserRepository userRepository;

  /**
   * 로그인 기능 테스트
   *
   */

  @Test
  public void login() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    String userId = "testuser";
    MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
    params.add("userId", userId);
    params.add("password", "password");
    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(params, headers);

    ResponseEntity<String> response = template().postForEntity("/users/login", request, String.class);

    assertThat(response.getStatusCode(), is(HttpStatus.FOUND));

  }



}
