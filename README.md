# Twitter Counter App

## Overview

The **Twitter Counter App** is an Android application that allows users to compose tweets while keeping track of the character count and send tweets directly to Twitter using Twitter API v2 with OAuth 1.0a authentication.

## Features

- **Character Counter**: Real-time character count display as you type.
- **Tweet Validation**: Ensures that tweets meet Twitter's character limits and content rules.
- **Twitter Integration**: Send tweets directly from the app using Twitter API v2 with OAuth 1.0a.
- **Modular Architecture**: The app is divided into two modules:
  - `app`: The main application module.
  - `counterView`: Handles character counting and input validation.

## Architecture

The app follows the **MVVM (Model-View-ViewModel)** architecture and **Clean Architecture** principles. It makes use of **Dependency Injection** for managing dependencies.

## Tech Stack

- **Programming Language**: Kotlin
- **Asynchronous Programming**: Kotlin Coroutines, Flow
- **Dependency Injection**: Hilt
- **Networking**: Retrofit
- **Unit Testing**: Mockito

## Modules

### App Module

This is the main application module that includes:
- User Interface
- ViewModels
- Dependency Injection setup
- Integration with `counterView` module

### CounterView Module

This module handles:
- Character counting logic
- Input validation logic
- Exposed as a reusable component for the `app` module

## Setup
Add your Twitter API keys to the Constants object in your project:


      `object Constants {
          const val API_KEY = "your_api_key"
          const val API_SECRET_KEY = "your_api_secret_key"
          const val ACCESS_TOKEN = "your_access_token"
          const val ACCESS_TOKEN_SECRET = "your_access_token_secret"
      }`
