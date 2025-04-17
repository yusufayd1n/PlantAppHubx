package com.example.plantapphubx.presantation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.data.remote.dto.QuestionsResponse
import com.example.plantapphubx.domain.usecase.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuestions: GetQuestionsUseCase
) : ViewModel() {
    private val _questionState = MutableLiveData<ApiResult<List<QuestionsResponse>>>()
    val questionState: LiveData<ApiResult<List<QuestionsResponse>>> = _questionState

    fun getQuestionsFromApi() {
        viewModelScope.launch {
            getQuestions()
                .onStart { _questionState.value = ApiResult.Loading }
                .catch { e -> _questionState.value = ApiResult.Error(e) }
                .collect { result ->
                    _questionState.value = result
                }
        }
    }
}