package com.andreiarodrigues.adidasgoals.data;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.andreiarodrigues.adidasgoals.data.database.AdidasGoalsDatabase;
import com.andreiarodrigues.adidasgoals.data.database.GoalsDao;
import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by andreia.rodrigues
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AdidasGoalsDatabase.class)
public class GoalsRepositoryTest {

    @Mock
    private Application application;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private GoalsRepository goalsRepository;

    @Mock
    private AdidasGoalsDatabase adidasGoalsDatabase;

    @Mock
    private GoalsDao goalsDao;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(AdidasGoalsDatabase.class);
        when(AdidasGoalsDatabase.getDatabaseInstance(application)).thenReturn(adidasGoalsDatabase);

        final MutableLiveData<List<GoalsModel>> result = new MutableLiveData<>();
        result.setValue(new ArrayList<GoalsModel>());
        when(adidasGoalsDatabase.goalsDao()).thenReturn(goalsDao);

        when(goalsDao.getAllGoals()).thenReturn(result);

        goalsRepository = new GoalsRepository(application);
    }

    @Test
    public void givenGoalsInDatabase_WhenCallGetAllGoals_ShouldRetrieveListOfGoalsWithSuccess() {
        // Given
        final MutableLiveData<List<GoalsModel>> result = new MutableLiveData<>();
        result.setValue(new ArrayList<GoalsModel>());

        //When
        Observer<List<GoalsModel>> observer = mock(Observer.class);
        goalsRepository.getAllGoals().observeForever(observer);

        //Should
        verify(observer).onChanged(result.getValue());
    }

}