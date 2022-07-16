Simple Calc Data Binding App
===================================

A simple calc with addition, subtraction, multiplication and division of floating point numbers.

The user enters the two operands and the calculation takes place at a ViewModel.
The app handles division by 0. 
The result of the operation is updated on the UI using live data and data binding.
There's an image that changes showing the current the operation. This changes were implemented using Binding Adapter.

Unit tests to check calculation operations were implemented.

Instrumented tests using Espresso were implemented using text matchers and drawable matchers. 

A custom .xml icon launcher was included.

![Screenshots-for-readme-600](https://user-images.githubusercontent.com/96868937/179285723-911d5cac-2315-44cb-8013-840e4db57f48.jpg)


Purpose
--------------

This code demonstrates the Android Architecture component- ViewModel and LiveData.
It also demonstrates how to implement Data Binding with LiveData and a Binding adapter.

For the unit testing it also shows how to test when ViewModel and LiveData are involved.

Getting Started
---------------

1. Download and run the app.
