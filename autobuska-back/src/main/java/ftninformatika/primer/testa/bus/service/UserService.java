package ftninformatika.primer.testa.bus.service;

import org.springframework.data.domain.Page;

import ftninformatika.primer.testa.bus.model.User;
import ftninformatika.primer.testa.bus.service.web.dto.UserPasswordChangeDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> one(Long id);

    List<User> all();

    Page<User> all(int pageNo);

    User save(User user);

    void delete(Long id);

    Optional<User> findbyKorisnickoIme(String username);

	boolean changePassword(Long id, UserPasswordChangeDto userPasswordChangeDto);
}
