package dev.geronso.smartbusiness

object PINChecker {
    var status: PINStatus = PINStatus.UNSELECTED
}

enum class PINStatus {
    UNSELECTED, SELECTED, CONFIRMED;
}