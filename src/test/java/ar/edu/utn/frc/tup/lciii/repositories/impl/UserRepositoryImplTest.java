package ar.edu.utn.frc.tup.lciii.repositories.impl;
import ar.edu.utn.frc.tup.lciii.entity.UserEntity;
import ar.edu.utn.frc.tup.lciii.repositories.UserRepository;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class UserRepositoryImplTest {
    @Mock
    private Session session;
    @Mock
    private Transaction transaction;
    @Mock
    private Query<UserEntity> query;

    private UserRepository userRepository ;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        userRepository = new UserRepositoryImpl();
    }
    @AfterEach
    void setTransaction(){

    }

    @Test
    void testGetByName(){
        UserEntity user = new UserEntity();
        user.setName("Agus");
        Query query = mock(Query.class);
        userRepository.save(user);
        UserEntity res = userRepository.getByName("Agus");
        assertEquals(user.getName(),res.getName());
        transaction.commit();
        verify(transaction).commit();
    }

    @Test
    public void testGameByName2(){
        UserEntity user = new UserEntity();
        user.setName("Agu");
        userRepository.save(user);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.createQuery("FROM UserEntity where name = :name",UserEntity.class)).thenReturn(query);
        when(query.setParameter("name","Agu")).thenReturn(query);
        when(query.uniqueResult()).thenReturn(user);
        UserEntity result = userRepository.getByName("Agu");
        assertEquals(user.getName(), result.getName());
    }



    @Test
    @Disabled
    public void testGetByNameException(){
        //TODO: mismo que arriba, aca va directamente contra la excepcion
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.createQuery("FROM UserEntity WHERE name = :name", UserEntity.class)).thenReturn(query);

        assertThrows(RuntimeException.class, ()-> {
            userRepository.getByName("Agus1");
        });

        verify(transaction).rollback();
    }


    @Test
    void testPostEntity(){
        UserEntity user = new UserEntity();
        user.setName("Agudb");
        user.setPassword("1234");
        Query query = mock(Query.class);
        userRepository.save(user);
        UserEntity result = userRepository.getByName("Agudb");
        assertEquals(user.getId(), result.getId());

    }
}
