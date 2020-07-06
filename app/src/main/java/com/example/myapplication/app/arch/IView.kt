package com.example.myapplication.app.arch

interface IView<S: IState> {
    fun render(state: S)
}