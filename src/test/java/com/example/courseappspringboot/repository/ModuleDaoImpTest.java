package com.example.courseappspringboot.repository;

import com.example.courseappspringboot.domain.dao.module.ModuleDaoImpl;
import com.example.courseappspringboot.domain.dao.module.ModuleResultSetExtractor;
import com.example.courseappspringboot.domain.model.course.Module;
import com.example.courseappspringboot.exceptions.CustomDatabaseException;
import com.example.courseappspringboot.exceptions.ModuleAlreadyExistsException;
import com.example.courseappspringboot.exceptions.ModuleNotFoundException;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;


public class ModuleDaoImpTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private Logger logger;
    @InjectMocks
    private ModuleDaoImpl moduleDao;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void addModule_ShouldReturnModuleOnSuccess(){
        //Arrange
      Module module=new Module(1,1,"myModule","module_description",1, Date.from(Instant.now()),new ArrayList<>());
      when(jdbcTemplate.update(any(String.class), any(), any(), any(), any())).thenReturn(1);
        //Act
        Module result=moduleDao.addModule(module);


      //Assert
      assertNotNull(result);
      assertEquals(module,result);


    }
    @Test
    void addModule_ShouldThrowModuleAlreadyExistsException() {
        Module module=new Module(1,1,"myModule","module_description",1, Date.from(Instant.now()),new ArrayList<>());
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any()))
                .thenThrow(new DuplicateKeyException("Duplicate key violation"));
        // Act and Assert
        // Verify that ModuleAlreadyExistsException is thrown
        ModuleAlreadyExistsException moduleAlreadyExistsException=assertThrows(ModuleAlreadyExistsException.class, () -> moduleDao.addModule(module));
        assertThat(moduleAlreadyExistsException.getMessage().trim(), containsString("Module with id"+" "+module.getModule_id()+" "+"already exists"));




    }
    @Test
    void addModule_ShouldThrowCustomDatabaseExceptionOnDatabaseError() {
        Module module=new Module(1,1,"myModule","module_description",1, Date.from(Instant.now()),new ArrayList<>());
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenThrow(new DataAccessException("error") {
                });
        // Act and Assert
        // Verify that ModuleAlreadyExistsException is thrown
        CustomDatabaseException customDatabaseException=assertThrows(CustomDatabaseException.class, () -> moduleDao.addModule(module));
        System.out.println(customDatabaseException.getMessage());
        assertThat(customDatabaseException.getMessage().trim(), containsString("Failure to add module to database"));


       /* //verify jdbc template
        verify(jdbcTemplate).update(
                eq("INSERT INTO modules(course_id,module_title,module_description,module_order) VALUES(?,?,?,?)"),
                eq(module.getCourse_id()),
                eq(module.getModule_title()),
                eq(module.getModule_description()),
                eq(module.getModule_order())
        );*/


    }
    @Test
    void updateModule_ShouldNotThrowExceptionOnSuccess() {
        // Arrange
        Module module = new Module(1, 1, "myModule", "module_description", 1, Date.from(Instant.now()), new ArrayList<>());
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

        // Act & assert
        assertDoesNotThrow(() -> moduleDao.updateModule(module));

    }
@Test
void updateModule_ShouldThrowCustomDatabaseExceptionOnDatabaseError(){
    Module module=new Module(1,1,"myModule","module_description",1, Date.from(Instant.now()),new ArrayList<>());
    when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenThrow(new DataAccessException("error") {
    });
    // Act and Assert
    // Verify that ModuleAlreadyExistsException is thrown
    CustomDatabaseException customDatabaseException=assertThrows(CustomDatabaseException.class, () -> moduleDao.updateModule(module));
    assertThat(customDatabaseException.getMessage().trim(), containsString("Failure to update module"));

}
@Test
void findModuleById_ShouldReturnModuleOnSuccess(){
    // Arrange
    int moduleId = 1;
    Module module=new Module(1,1,"myModule","module_description",1, Date.from(Instant.now()),new ArrayList<>());
    List<Module> modules = new ArrayList<>();
    modules.add(module);

    // Mocking jdbcTemplate behavior
    when(jdbcTemplate.query(anyString(), any(ModuleResultSetExtractor.class), any(Object[].class)))
            .thenReturn(modules);

    // Act
    Module result = moduleDao.findModuleById(moduleId);

    // Assert
    assertEquals(modules.get(0), result);


    }

@Test
void findModuleById_EmptyModulesList(){
    int moduleId = 1;

    // Mocking jdbcTemplate behavior
    when(jdbcTemplate.query(anyString(), any(ModuleResultSetExtractor.class), any(Object[].class)))
            .thenReturn(Collections.emptyList());
    ModuleNotFoundException exception = assertThrows(ModuleNotFoundException.class,
            () -> moduleDao.findModuleById(moduleId));
    assertEquals("Module with id " + moduleId + " does not exist", exception.getMessage());

}

@Test
void findModuleById_DatabaseException() {
        int moduleId = 1;

        // Mocking the behavior of jdbcTemplate to simulate DataAccessException
        when(jdbcTemplate.query(anyString(), any(ModuleResultSetExtractor.class), anyInt())).thenThrow(new DataAccessException("Database error") {});

        ModuleNotFoundException exception = assertThrows(ModuleNotFoundException.class,
                () -> moduleDao.findModuleById(moduleId));

        assertEquals("Module with id: " + moduleId + " not found", exception.getMessage());
    }

@Test
void findModuleByTitle_Success(){
        String title="myModule";
    List<Module> modules=new ArrayList<>();
    Module module=new Module(1,1,"myModule","module_description",1, Date.from(Instant.now()),new ArrayList<>());
    modules.add(module);
    //Arrange
    when(jdbcTemplate.query(anyString(), any(ModuleResultSetExtractor.class), anyString()))
            .thenReturn((modules));

    //act
    Module result=moduleDao.findModuleByTitle(title);
    // assert
    assertNotNull(result);
    assertEquals(module,result);


}
@Test
void findModuleByTitle_EmptyModulesList(){
        String title="my-book";
    when(jdbcTemplate.query(anyString(), any(ModuleResultSetExtractor.class), anyString()))
            .thenReturn(Collections.emptyList());
    ModuleNotFoundException exception = assertThrows(ModuleNotFoundException.class,
            () -> moduleDao.findModuleByTitle(title));
    assertEquals("Module with title "+title+" does not exist", exception.getMessage());

}

@Test
void findModuleByTitle_DataAccessException(){
    String title="my-book";
    // Mocking the behavior of jdbcTemplate to simulate DataAccessException
    when(jdbcTemplate.query(anyString(), any(ModuleResultSetExtractor.class), anyString())).thenThrow(new DataAccessException("Database error") {});

    ModuleNotFoundException exception = assertThrows(ModuleNotFoundException.class,
            () -> moduleDao.findModuleByTitle(title));

    assertEquals("Module with title "+title+" not found", exception.getMessage());

}

@Test
void findModulesByCourseId_Success(){
        int course_id=1;
        List<Module> modules=new ArrayList<>();
        Module module=new Module(1,1,"myModule","module_description",1, Date.from(Instant.now()),new ArrayList<>());
        modules.add(module);
        when(jdbcTemplate.query(anyString(),any(ModuleResultSetExtractor.class),anyInt())).thenReturn(modules);

        assertEquals(modules,moduleDao.findModulesByCourseId(course_id));
}
@Test
void deleteModulesByCourseId_ShouldReturnTrueOnSuccess(){
    int course_id=1;
    when(jdbcTemplate.update(any(String.class),anyInt())).thenReturn(1);
    boolean isDeleted=moduleDao.deleteModulesByCourseId(course_id);
    assertTrue(isDeleted);


}

@Test
void deleteModulesByCourseId_ShouldThrowCustomDatabaseExceptionOnDatabaseError(){
    int course_id=1;
    // Mocking the behavior of jdbcTemplate to simulate DataAccessException
    when(jdbcTemplate.update(any(String.class),anyInt())).thenThrow(new DataAccessException("Database error") {});

    CustomDatabaseException exception = assertThrows(CustomDatabaseException.class,
            () -> moduleDao.deleteModulesByCourseId(course_id));

    assertEquals("Failed to delete Modules of course_id: "+course_id, exception.getMessage());

}
@Test
void deleteModulesByModuleId_ShouldReturnTrueOnSuccess(){
    int module_id=1;
    when(jdbcTemplate.update(any(String.class),anyInt())).thenReturn(1);
    boolean isDeleted=moduleDao.deleteModulesByCourseId(module_id);
    assertTrue(isDeleted);
}

@Test
void deleteModuleByModuleId_ShouldThrowCustomDatabaseExceptionOnDatabaseError(){
    int module_id=1;
    // Mocking the behavior of jdbcTemplate to simulate DataAccessException
    when(jdbcTemplate.update(any(String.class),anyInt())).thenThrow(new DataAccessException("Database error") {});

    CustomDatabaseException exception = assertThrows(CustomDatabaseException.class,
            () -> moduleDao.deleteModuleById(module_id));

    assertEquals("Failed to delete module with id : "+module_id, exception.getMessage());


    }
}
