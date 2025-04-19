package com.example.plantapphubx.presantation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.plantapphubx.core.util.ApiResult
import com.example.plantapphubx.data.remote.model.CategoriesResponse
import com.example.plantapphubx.data.remote.model.QuestionsResponse
import com.example.plantapphubx.domain.usecase.GetCategoriesUseCase
import com.example.plantapphubx.domain.usecase.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuestions: GetQuestionsUseCase,
    private val getCategories: GetCategoriesUseCase,
) : ViewModel() {
    private val _questionState = MutableLiveData<ApiResult<List<QuestionsResponse>>>()
    val questionState: LiveData<ApiResult<List<QuestionsResponse>>> = _questionState

    private val _categories = MutableLiveData<ApiResult<PagingData<CategoriesResponse>>>()
    val categories: LiveData<ApiResult<PagingData<CategoriesResponse>>> = _categories

    init {
        getQuestionsFromApi()
        fetchCategories()
    }

    private fun getQuestionsFromApi() {
        viewModelScope.launch {
            getQuestions()
                .onStart { _questionState.value = ApiResult.Loading }
                .catch { e -> _questionState.value = ApiResult.Error(e) }
                .collect { result ->
                    _questionState.value = result
                }
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            getCategories()
                .onStart { _questionState.value = ApiResult.Loading }
                .catch { e -> _questionState.value = ApiResult.Error(e) }
                .collect {
                    _categories.postValue(it)
                }
        }
    }
}