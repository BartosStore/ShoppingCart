# Shopping cart

A simple Kotlin test project on Android. It consists of two activities. The first activity checks cart changes from the server and the second one shows the cart items and add new.

## Kotlin language

[A statically typed programming language.](https://kotlinlang.org)

## Firebase platform

[A tool with NoSQL DB.](https://firebase.com) After simple project connecting with Firebase through Google account, you can use realtime DB. The data are stored as JSON object.

1. Pair the project with the Firebase

Android Studio -> Tools -> Firebase -> Save and retrieve data

2. Get a DB object reference

```kotlin
firebaseDB = FirebaseDatabase.getInstance().reference
shoppingItemsDB = firebaseDB!!.child("shopping-items")
```

3. Push/pull changes

```kotlin
val shoppingItem = HashMap<String, String>()
shoppingItem.put("name", "milk")
shoppingItem.put("description", "BIO")
shoppingItemsDB.push().setValue(shoppingItem)
```
