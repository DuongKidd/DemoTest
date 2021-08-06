package io.github.tubean.myspringcrud.service.impl;

import io.github.tubean.myspringcrud.MyspringcrudApplication;
import io.github.tubean.myspringcrud.entity.User;
import io.github.tubean.myspringcrud.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyspringcrudApplication.class)
@Transactional
public class UserServiceImplTest extends TestCase {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    public void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = (userServiceImpl.getAllUser());
        assertThat(result.size(), greaterThan(0));
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        User result = (userServiceImpl.saveUser(user));
        assertThat(user, is(result));
    }

    @Test
    public void testDeleteUserSuccess() {
        User user = new User((long) 1, "abc", "def", "ghi");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userServiceImpl.deleteUser(user.getId());

    }

    @Test
    public void testDeleteUserFail() {
        User user = new User();
        user.setId(89L);
        user.setName("Kidd");
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));
        userServiceImpl.deleteUser(user.getId());
    }


    @Test
    public void testFindUserById() {
        User user = new User();
        user.setId(89L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Optional<User> result = userServiceImpl.findUserById(user.getId());
        assertThat(user, is(result.get()));
    }

}