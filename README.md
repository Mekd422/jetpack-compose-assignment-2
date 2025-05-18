# 📝 Jetpack Compose TODO App

## Overview

This Android application fetches and displays a list of TODO items from the [JSONPlaceholder API](https://jsonplaceholder.typicode.com/todos). It leverages **Jetpack Compose** for building the UI, **Retrofit** for networking, and **Room** for local caching. The app follows the **MVVM architecture** for a clean separation of concerns and better scalability.

---

## 🚀 Features

- **Remote Fetching**  
  Retrieves TODO items from JSONPlaceholder API using Retrofit with Kotlin coroutines.

- **Local Caching**  
  Persists data locally using Room database for offline access and faster loading.

- **List View**  
  Displays a scrollable list of TODOs with title and completion status using Jetpack Compose’s `LazyColumn`.

- **Detail View**  
  Shows detailed information of a selected TODO item on a separate screen.

- **Error Handling**  
  Handles network errors gracefully, providing retry options and falling back to cached data if available.

- **Data Synchronization**  
  Loads cached data immediately on app start and refreshes UI with fresh data when network fetch completes.

---

## 🏗 Architecture

The app follows the **Model-View-ViewModel (MVVM)** pattern for a clean and maintainable codebase:

| Layer        | Responsibilities                                  |
|--------------|-------------------------------------------------|
| **Model**    | Data classes, Room entities, DAO, Retrofit API interfaces |
| **ViewModel**| Handles business logic, data fetching, and exposes UI states |
| **View (UI)**| Jetpack Compose UI screens observing ViewModel states |

---

## 🛠 Tech Stack

| Layer        | Library/Technology                 |
|--------------|----------------------------------|
| UI           | Jetpack Compose                  |
| Navigation   | Jetpack Compose Navigation       |
| Networking   | Retrofit with Kotlin Coroutines  |
| Persistence  | Room Database                   |
| Architecture | MVVM                            |
| Language     | Kotlin                         |

---

## 👩‍💻 Author

Developed by **Mekdes Assefa**  
ID: `UGR/1419/15`
          section: 1


![Sc1](https://github.com/user-attachments/assets/56a389c1-67d6-4e5c-92bb-9ff414048653)![Sc2](https://github.com/user-attachments/assets/8c46a639-8095-4728-b7ba-b1c0f97648bc)


  
 
