# kotlin-unified-platform

A simple multiplatform library which allows you to detect if your code is running on 
Kotlin/JVM, Kotlin/JS (Browser or Node.js!) or Kotlin/Native, by exposing a `currentPlatform` field.

## How it works

By using Kotlin's `expect/actual`, the code can detect the platform (JVM, JS or Native).

Then, each code uses its platform' API to detect informations about the platform.
