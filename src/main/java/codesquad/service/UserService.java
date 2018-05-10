package codesquad.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import codesquad.UnAuthenticationException;
import codesquad.UnAuthorizedException;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.UserDto;

@Service("userService")
public class UserService {

  @Resource(name = "userRepository")
  private UserRepository userRepository;

  public User add(UserDto userDto) {
    return userRepository.save(userDto.toUser());
  }

  public User update(User loginUser, long id, UserDto updatedUser) {
    User original = userRepository.findOne(id);
    original.update(loginUser, updatedUser.toUser());
    return userRepository.save(original);
  }

  public User findById(User loginUser, long id) {
    User user = userRepository.findOne(id);
    if (!user.equals(loginUser)) {
      throw new UnAuthorizedException();
    }
    return user;
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User login(String userId, String password) throws UnAuthenticationException {

    // TODO 로그인 기능 구현
    // UserId 정보있는지, 찾은 User의 password가 입력패스워드가 같은지 찾고싶다
//    User loginUser = new User();
//
//    findById()

    //    return Optional.ofNullable(order)
//        .map(Order::getMember)
//        .map(Member::getAddress)
//        .map(Address::getCity)
//        .orElse("Seoul");

    return null;
  }
}
