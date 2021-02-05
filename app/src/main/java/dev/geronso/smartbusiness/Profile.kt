package dev.geronso.smartbusiness

data class Profile (
    var email: String,
    var phone: String,
    var login: String,
    var password: String,
    var pin: String? = null
)