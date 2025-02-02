package ru.kpfu.itis.coroutines.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*

@Composable
fun MainScreen() {
    var coroutineCount by remember { mutableStateOf("") }
    var launchMode by remember { mutableStateOf(LaunchMode.SEQUENTIAL) }
    var lifecycleMode by remember { mutableStateOf(LifecycleMode.CANCEL_ON_PAUSE) }
    var dispatcher by remember { mutableStateOf(Dispatchers.Default) }
    var isTextFieldError by remember { mutableStateOf(false) }

    var jobCount by remember { mutableIntStateOf(0) }
    var isJobRunning by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = coroutineCount,
            onValueChange = {
                coroutineCount = it
                isTextFieldError = false
            },
            label = { Text("Количество корутин") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = isTextFieldError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        RadioGroup(
            title = "Режим запуска",
            options = LaunchMode.values().map { it.name },
            selectedOption = launchMode.name,
            onOptionSelected = { launchMode = LaunchMode.valueOf(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        RadioGroup(
            title = "Поведение при сворачивании",
            options = LifecycleMode.values().map { it.name },
            selectedOption = lifecycleMode.name,
            onOptionSelected = { lifecycleMode = LifecycleMode.valueOf(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        DropdownSelector(
            title = "Пул потоков",
            options = listOf(Dispatchers.Default, Dispatchers.IO, Dispatchers.Main),
            selectedOption = dispatcher,
            onOptionSelected = { dispatcher = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    if (coroutineCount.isBlank()) {
                        isTextFieldError = true
                        return@Button
                    }

                    val count = coroutineCount.toIntOrNull() ?: return@Button
                    if (count <= 0) return@Button

                    jobCount = count
                    isJobRunning = true

                    when (launchMode) {
                        LaunchMode.SEQUENTIAL -> launchSequentialJobs(count, dispatcher, lifecycleMode, scope, context)
                        LaunchMode.PARALLEL -> launchParallelJobs(count, dispatcher, lifecycleMode, scope, context)
                    }
                },
            ) {
                Text("Запустить")
            }

            Button(
                onClick = {
                    if (isJobRunning) {
                        scope.coroutineContext.cancelChildren()
                        isJobRunning = false
                        showSnackbar(context, "Отменено $jobCount корутин")
                    } else {
                        showSnackbar(context, "Корутины не запущены")
                    }
                }
            ) {
                Text("Отменить")
            }
        }
    }
}

private fun launchSequentialJobs(
    count: Int,
    dispatcher: CoroutineDispatcher,
    lifecycleMode: LifecycleMode,
    scope: CoroutineScope,
    context: Context
) {
    for (i in 1..count) {
        scope.launch(dispatcher) {
            try {
                delay(2000)
                println("Корутина $i завершена")
            } catch (e: Exception) {
                showSnackbar(context, "Ошибка: ${e.message}")
            }
        }
    }
}

private fun launchParallelJobs(
    count: Int,
    dispatcher: CoroutineDispatcher,
    lifecycleMode: LifecycleMode,
    scope: CoroutineScope,
    context: Context
) {
    repeat(count) { i ->
        scope.launch(dispatcher) {
            try {
                delay(2000)
                println("Корутина $i завершена")
            } catch (e: Exception) {
                showSnackbar(context, "Ошибка: ${e.message}")
            }
        }
    }
}

private fun showSnackbar(context: Context, message: String) {
    // Здесь можно использовать Snackbar или Toast
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

enum class LaunchMode {
    SEQUENTIAL, PARALLEL
}

enum class LifecycleMode {
    CANCEL_ON_PAUSE, CONTINUE_ON_PAUSE
}