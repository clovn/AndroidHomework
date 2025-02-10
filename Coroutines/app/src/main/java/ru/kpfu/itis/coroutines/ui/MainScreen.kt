package ru.kpfu.itis.coroutines.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.*
import ru.kpfu.itis.coroutines.LaunchMode
import ru.kpfu.itis.coroutines.LifecycleMode
import ru.kpfu.itis.coroutines.R
import ru.kpfu.itis.coroutines.showToast

@Composable
fun MainScreen() {
    var coroutineCount by remember { mutableStateOf("") }
    var launchMode by remember { mutableStateOf(LaunchMode.SEQUENTIAL) }
    var lifecycleMode by remember { mutableStateOf(LifecycleMode.CANCEL_ON_PAUSE) }
    var dispatcher by remember { mutableStateOf(Dispatchers.Default) }
    var isTextFieldError by remember { mutableStateOf(false) }

    var jobCount by remember { mutableIntStateOf(0) }
    var jobs: Job? = null

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner, lifecycleMode, jobs) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE && lifecycleMode == LifecycleMode.CANCEL_ON_PAUSE) {
                jobs?.cancel()
                jobs = null
                jobCount = 0
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp),
        ) {
            TextField(
                value = coroutineCount,
                onValueChange = {
                    coroutineCount = it
                    isTextFieldError = false
                },
                label = { Text(stringResource(R.string.coroutine_count)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = isTextFieldError,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            RadioGroup(
                title = stringResource(R.string.launch_mode),
                options = LaunchMode.entries.map { it.name },
                selectedOption = launchMode.name,
                onOptionSelected = { launchMode = LaunchMode.valueOf(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            RadioGroup(
                title = stringResource(R.string.behavior),
                options = LifecycleMode.entries.map { it.name },
                selectedOption = lifecycleMode.name,
                onOptionSelected = { lifecycleMode = LifecycleMode.valueOf(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DropdownSelector(
                title = stringResource(R.string.threads_pull),
                options = listOf(Dispatchers.Default, Dispatchers.Main, Dispatchers.Unconfined, Dispatchers.IO),
                selectedOption = dispatcher,
                onOptionSelected = { dispatcher = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(48.dp),
                    onClick = {
                        if (coroutineCount.isBlank()) {
                            isTextFieldError = true
                            return@Button
                        }

                        val count = coroutineCount.toIntOrNull() ?: return@Button
                        if (count <= 0) return@Button

                        jobCount += count

                        when (launchMode) {
                            LaunchMode.SEQUENTIAL -> {
                                jobs = launchSequentialJobs(
                                    count,
                                    dispatcher,
                                    scope,
                                    context
                                ) { jobCount-- }
                            }
                            LaunchMode.PARALLEL -> jobs = launchParallelJobs(
                                count,
                                dispatcher,
                                scope,
                                context
                            ) { jobCount-- }
                        }
                    },
                ) {
                    Text(stringResource(R.string.launch))
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    onClick = {
                        if (jobCount > 0) {
                            scope.coroutineContext.cancelChildren()
                            showToast(context,
                                context.getString(R.string.cancel_coroutines, jobCount))
                            jobCount = 0
                        } else {
                            showToast(context, context.getString(R.string.coroutines_not_started))
                        }
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        }
    }
}

private fun launchSequentialJobs(
    count: Int,
    dispatcher: CoroutineDispatcher,
    scope: CoroutineScope,
    context: Context,
    onCoroutineCompleted: () -> Unit
): Job {
    return scope.launch {
        try {
            repeat(count) {
                launch(dispatcher) {
                    try {
                        delay(2000)
                        println("$it Завершена")
                    } finally {
                        onCoroutineCompleted()
                    }
                }.join()
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            scope.launch(Dispatchers.Main) {
                showToast(context, context.getString(R.string.error, e.message))
            }
        }
    }

}

private fun launchParallelJobs(
    count: Int,
    dispatcher: CoroutineDispatcher,
    scope: CoroutineScope,
    context: Context,
    onCoroutineCompleted: () -> Unit
): Job {
    return scope.launch {
        try{
            repeat(count) {
                launch(dispatcher) {
                    try {
                        delay(2000)
                        println("$it Завершена")
                    } finally {
                        onCoroutineCompleted()
                    }
                }
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            scope.launch(Dispatchers.Main) {
                showToast(context, context.getString(R.string.error, e.message))
            }
        }
    }
}
