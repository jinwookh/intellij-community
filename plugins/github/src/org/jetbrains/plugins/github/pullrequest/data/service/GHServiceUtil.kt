// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.plugins.github.pullrequest.data.service

import com.intellij.openapi.diagnostic.Logger
import org.jetbrains.plugins.github.util.GithubAsyncUtil
import java.util.concurrent.CompletableFuture
import java.util.function.BiFunction

object GHServiceUtil {

  fun <T> CompletableFuture<T>.logError(log: Logger, message: String): CompletableFuture<T> =
    handle(BiFunction<T, Throwable?, T> { result: T, error: Throwable? ->
      if (error != null) {
        val actualError = GithubAsyncUtil.extractError(error)
        if (!GithubAsyncUtil.isCancellation(actualError)) log.info(message, actualError)
        throw actualError
      }
      result
    })
}