package com.example.taskomanagement.ui.screen.main.task.task_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskomanagement.data.model.Result
import com.example.taskomanagement.data.repository.MainRepository
import com.example.taskomanagement.data.response.ExecutorByTaskIdDataItem
import com.example.taskomanagement.data.response.MemberOfTeamDataItem
import com.example.taskomanagement.data.response.ProjectDetailData
import com.example.taskomanagement.data.response.TaskDataItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(private val repository: MainRepository) : ViewModel() {
    private val _task = MutableStateFlow<TaskDataItem?>(null)
    val task = _task.asStateFlow()

    private val _project = MutableStateFlow<ProjectDetailData?>(null)
    val project = _project.asStateFlow()

    private val _executor = MutableStateFlow<List<ExecutorByTaskIdDataItem>?>(emptyList())
    val executor = _executor.asStateFlow()

    private val _dataResult = mutableStateOf<Result<String>?>(null)
    val dataResult: State<Result<String>?> = _dataResult

    private val _member = MutableStateFlow<List<MemberOfTeamDataItem>?>(null)
    val member = _member.asStateFlow()

    private val _userId = mutableStateOf<Int?>(null)
    val userId: State<Int?> = _userId

    private var _teamId = mutableStateOf<Int?>(null)
    val teamId: State<Int?> = _teamId

    private val _showBottomSheet = mutableStateOf<Boolean>(false)
    val showBottomSHeet: State<Boolean> = _showBottomSheet

    private val _refreshKey = mutableIntStateOf(0)
    val refreshKey: State<Int> = _refreshKey

    private suspend fun getUserId() = repository.getUserId()

    fun setShowBottomSheet(show: Boolean) {
        _showBottomSheet.value = show
    }

    fun getTaskById(id: Int) {
        _dataResult.value = Result.Loading
        viewModelScope.launch {
            try {
                val taskData = repository.getTaskById(id)
                _task.value = taskData.data
                _dataResult.value = Result.Success("Get data success")
                Log.d("PRID", "${taskData.data.projectId}")
                getProjectById(taskData.data.projectId)
            } catch (e: Exception) {
                e.printStackTrace()
                _dataResult.value = Result.Error("Get data fail")
            }
        }
    }

    private fun getProjectById(id: Int) {
        viewModelScope.launch {
            try {
                val projectData = repository.getProjectById(id)
                _project.value = projectData.data
                Log.d("PRDARA", "${projectData.data.teamId}")
                _teamId.value = projectData.data.teamId
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getExecutorByTaskId(id: Int) {
        viewModelScope.launch {
            try {
                val executorData = repository.getExecutorByTaskId(id)
                _executor.value = executorData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setTaskDone(
        taskId: Int,
        taskName: String,
        taskDescription: String,
        taskDueDate: String,
        taskDueTime: String,
        status: String,
        userId: Int? = null
    ) {
        viewModelScope.launch {
            try {
                repository.updateTask(
                    taskId,
                    taskName,
                    taskDescription,
                    taskDueDate,
                    taskDueTime,
                    status = status,
                    userId = userId
                )
                _refreshKey.intValue++
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMember(
        teamId: Int
    ) {
        viewModelScope.launch {
            try {
                _userId.value = getUserId()
                val memberData = repository.getTeamMemberByTeamId(teamId)
                _member.value = memberData.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}