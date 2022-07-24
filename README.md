Simple Calc Data Binding App
===================================

A simple calc with addition, subtraction, multiplication and division of floating point numbers.

The user enters the two operands and the calculation takes place at a ViewModel.
The app handles division by 0. 
The result of the operation is updated on the UI using LiveData and DataBinding.
There's an image that changes showing the current the operation. This changes were implemented using a custom Binding Adapter.

There's an overflow menu in the tool bar for settings that navigates to a preference fragment.
Preferences are stored using Preference Manager (Shared Preferences).
It's possible to switch to select the theme (Night Mode, Day Mode, Auto).

The app has strings for English and Spanish language.

Unit tests to check calculation operations were implemented.
Instrumented tests using Espresso were implemented using text matchers and drawable matchers. 

A custom .xml icon launcher was included.

![Simple-Calc-readme-new-800](https://user-images.githubusercontent.com/96868937/180648235-37febd29-94ec-433d-9529-eb22e3d2043d.jpg)

Purpose
--------------

This code demonstrates the Android Architecture component- ViewModel and LiveData, and how to implement Data Binding with LiveData and a custom Binding adapter.

For the UI, it shows how to create an overflow menu, navigate to a PreferenceFragment and store app preferences using SharedPreferences.

Localization for Spanish language was added.

For the unit testing it also shows how to test when ViewModel and LiveData are involved.

Getting Started
---------------

1. Download and run the app.
