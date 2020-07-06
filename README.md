# Kotlin_MVIPattern

## Intent
Represents the user’s intent when interacting with the UI. For example, a click on a button to refresh a list of data will be modeled as an Intent. To avoid any confusion with the Android framework Intent, we will call it in the rest of this article a UserIntent.

## Model
It is a ViewModel where different synchronous or asynchronous tasks are performed. It accepts UserIntents as input and produces one or more successive states as output. These states are exposed via a LiveData to be used by the view.

## View
The view simply processes immutable states that it receives from the ViewModel to update the UI. It also allows to transmit user actions to the ViewModel in order to accomplish defined tasks.

## State
It represents an immutable state of sight. A new state is created by the ViewModel each time the view needs to be updated.

## Reducer
When you want to create a new State, you use the Reducer. It is provided with the current state as well as new elements to be included and it takes care of producing an immutable state.
