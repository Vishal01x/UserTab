package com.exa.android.learn.DataTypes

data class User(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<UserX>
)