package vnpt.cmms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vnpt.cmms.entity.User;
import vnpt.cmms.serurity.CmmsUserDetails;
import vnpt.cmms.util.CSVDataLoader;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;
    private Map<String, User> userCache ;
    private Map<Long, User> idCache ;

    @PostConstruct
    public void init(){
        List<User> users = CSVDataLoader.loadObjectList(User.class, CSVDataLoader.USER_FILE);
        userCache = users.stream().collect(Collectors.toMap(User::getUsername, item -> item));
        idCache = users.stream().collect(Collectors.toMap(User::getId, item -> item));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new CustomUserDetails(user);


        if (!userCache.containsKey(username)) {
            throw new UsernameNotFoundException(username);
        }
        return new CmmsUserDetails(userCache.get(username));
    }



    public UserDetails loadUserById(Long id) {
//        User user = userRepository.findById(id).orElseThrow(
//                () -> new UsernameNotFoundException("User not found with id : " + id)
//        );
//
//        return new CustomUserDetails(user);
//        return null;
        if (!idCache.containsKey(id)) {
            throw new UsernameNotFoundException("User not found with id : " + id);
        }
        return new CmmsUserDetails(idCache.get(id));


    }


}
