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

public class LoginAcceptanceTest extends AcceptanceTest {

  private static final Logger log = LoggerFactory.getLogger(UserAcceptanceTest.class);

  @Autowired
  private UserRepository userRepository;

  /**
   * 로그인 기능 테스트 기능 요구사항
   *
   * src/test/java 폴더 codesquad.web.UserAcceptanceTest를 실행하면 회원정보 수정 기능 테스트가 실패한다. 테스트가 실패하는 이유는 로그인
   * 기능이 구현되지 않았기 때문이다. 로그인 기능을 ATDD 기반(HTTP Client 테스트)으로 구현해야 한다.
   *
   * 프로그래밍 구현 힌트
   *
   * codesquad.web.UserAcceptanceTest 소스 코드를 참고해 LoginAcceptanceTest를 구현하면서 시작한다. POST로 값을 전달하고 서버에
   * 요청을 보내는 방법은 UserAcceptanceTest의 create() 메소드를 참고한다. src/main/java 폴더의
   * codesquad.web.UserController에 LoginAcceptanceTest에 대응하는 서버측 코드를 구현한다. 예를 들어
   * LoginAcceptanceTest에서 "/users/login"으로 요청을 보내면 이에 대응하는 서버측 코드를 구현한다.
   *
   * UserService 클래스의 login() 메소드를 구현해 UserServiceTest의 모든 테스트 메소드가 성공해야 한다. UserRepository의
   * findByUserId() 메소드를 활용해 구현한다. Optional을 적용 활용해 로그인 기능을 구현한다. 기능을 구현한 후 UserServiceTest 테스트가 모두
   * 통과하는지 확인한다.
   *
   * ## 인수테스트케이스 로그인 성공 로그인 실패
   */

  @Test
  public void login_성공() throws Exception {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    String userId = "testuser";
    MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
    params.add("userId", userId);
    params.add("password", "password");
    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(
        params, headers);

    ResponseEntity<String> response = template()
        .postForEntity("/users/login", request, String.class);

    // 로그인 기능 - 사용자 정보가 있는지 없는지 확인
    assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
    assertThat(response.getHeaders().getLocation().getPath(), is("/users"));
  }


}
